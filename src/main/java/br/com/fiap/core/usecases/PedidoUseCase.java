package br.com.fiap.core.usecases;

import br.com.fiap.core.entities.ItemPedido;
import br.com.fiap.core.entities.Pedido;
import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.StatusPedidoEnum;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.gateways.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static br.com.fiap.core.enums.SituacaoPagamentoEnum.PENDENTE;
import static br.com.fiap.core.enums.StatusPedidoEnum.*;
import static java.util.Objects.isNull;

public class PedidoUseCase implements PedidoServiceGateway {

    private final PedidoRepositoryGateway pedidoRepositoryGateway;

    private final ProdutoRepositoryGateway produtoRepositoryGateway;

    private final PagamentoServicoExternoGateway pagamentoServicoExternoGateway;

    private final NotificacaoSonoraGateway notificacaoSonoraGateway;

    public PedidoUseCase(PedidoRepositoryGateway pedidoRepositoryGateway, ProdutoRepositoryGateway produtoRepositoryGateway,
                         PagamentoServicoExternoGateway pagamentoServicoExterno, NotificacaoSonoraGateway notificacaoSonoraGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
        this.produtoRepositoryGateway = produtoRepositoryGateway;
        this.pagamentoServicoExternoGateway = pagamentoServicoExterno;
        this.notificacaoSonoraGateway = notificacaoSonoraGateway;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        if (pedido.getClienteId() == null) {
            throw new BusinessException("O cliente é obrigatório!");
        }
        iniciarPedido(pedido);
        return pedidoRepositoryGateway.salvar(pedido);
    }

    @Override
    public List<Pedido> obterPedidos() {
        List<Pedido> pedidos = new ArrayList<>(pedidoRepositoryGateway.obterPedidos(List.of(StatusPedidoEnum.PRONTO, StatusPedidoEnum.ANDAMENTO, StatusPedidoEnum.ENTREGUE)));

        Map<StatusPedidoEnum, Integer> statusOrdem = new HashMap<>();
        statusOrdem.put(StatusPedidoEnum.PRONTO, 1);
        statusOrdem.put(StatusPedidoEnum.ANDAMENTO, 2);
        statusOrdem.put(StatusPedidoEnum.ENTREGUE, 3);

        pedidos.sort(Comparator.comparing(pedido -> statusOrdem.getOrDefault(pedido.getStatus(), Integer.MAX_VALUE)));

        return pedidos;
    }

    @Override
    public Pedido adicionarItem(ItemPedido itemPedido) {
        Pedido pedido = obterPedido(itemPedido.getPedidoId());
        Produto produto = obterProduto(itemPedido);

        if (isNull(produto)) {
            throw new BusinessException("Produto não encontrado!");
        }

        ItemPedido itemJaInclusoNoPedido = obterItemJaInclusoPedido(itemPedido, pedido);
        if(Objects.nonNull(itemJaInclusoNoPedido) && itemJaInclusoNoPedido.getProdutoId().equals(itemPedido.getProdutoId())) {
            itemJaInclusoNoPedido.setQuantidade(itemJaInclusoNoPedido.getQuantidade() + itemPedido.getQuantidade());
        } else {
            pedido.adicionarItemPedido(itemPedido);
        }

        pedido.setValor(pedido.getValor().add(produto.getValor().multiply(new BigDecimal(itemPedido.getQuantidade()))));
        return pedidoRepositoryGateway.salvar(pedido);
    }

    private static ItemPedido obterItemJaInclusoPedido(ItemPedido itemPedido, Pedido pedido) {
        return pedido.getItens().stream().filter(item -> item.getProdutoId().equals(itemPedido.getProdutoId())).findFirst()
                .orElse(null);
    }

    @Override
    public Pedido removerItem(ItemPedido itemPedido) {
        Pedido pedido = obterPedido(itemPedido.getPedidoId());
        Produto produto = obterProduto(itemPedido);

        if (isNull(produto)) {
            throw new BusinessException("Produto não encontrado!");
        }

        ItemPedido itemDoPedido = obterItemJaInclusoPedido(itemPedido, pedido);

        if(itemDoPedido.getQuantidade().equals(itemPedido.getQuantidade())) {
            pedido.removerItemPedido(itemPedido);
        }

        itemDoPedido.setQuantidade(itemDoPedido.getQuantidade() - itemPedido.getQuantidade());
        pedido.setValor(pedido.getValor().subtract(produto.getValor().multiply(new BigDecimal(itemPedido.getQuantidade()))));

        return pedidoRepositoryGateway.salvar(pedido);
    }

    private Produto obterProduto(ItemPedido itemPedido) {
        return produtoRepositoryGateway.getProdutoById(itemPedido.getProdutoId());
    }

    private Pedido obterPedido(Long id) {
        return pedidoRepositoryGateway.obterPorId(id).orElseThrow(() -> new BusinessException("Pedido não encontrado!"));
    }

    @Override
    public Pedido cancelarPedido(Long id) {
        Pedido pedido = obterPedido(id);

        return cancelarPedido(pedido);
    }

    @Override
    public Pedido checkoutPedido(Long id) {
        Pedido pedido = obterPedido(id);

        try {
            boolean pagamentoEfetuado = pagamentoServicoExternoGateway.efetuarPagamento("QrCode", id, pedido.getValor());

            if(!pagamentoEfetuado) {
                throw new BusinessException("Pedido não realizado, houve erro no pagamento!");
            }
        } catch (HttpClientErrorException e) {
            throw new BusinessException(e.getMessage());
        }

        return obterPedido(id);
    }

    @Override
    public Pedido efetuarEntrega(Long id) {
        Pedido pedido = obterPedido(id);

        if (!pedido.getStatus().equals(PRONTO)) {
            throw new BusinessException("Pedido não esta pronto para entrega!");
        }

        if (pedido.getSituacaoPagamento().equals(PENDENTE)) {
            throw new BusinessException("Pagamento esta pendente!");
        }

        pedido.setStatus(ENTREGUE);
        pedido.setDataHoraEntrega(LocalDateTime.now());

        return pedidoRepositoryGateway.salvar(pedido);
    }

    @Override
    public Pedido atualizarPedidoPronto(Long id) {
        Pedido pedido = obterPedido(id);

        if (!pedido.getStatus().equals(ANDAMENTO)) {
            throw new BusinessException("Pedido não está com status em andamento");
        }

        pedido.setStatus(PRONTO);
        notificacaoSonoraGateway.notificar();
        return pedidoRepositoryGateway.salvar(pedido);
    }

    private Pedido cancelarPedido(Pedido pedido) {
        if(pedido.getStatus().equals(CANCELADO)) {
            throw new BusinessException("Pedido já foi cancelado!");
        }

        if(pedido.getStatus().equals(ENTREGUE)) {
            throw new BusinessException("Pedido já foi entregue!");
        }

        pedido.setStatus(CANCELADO);
        return pedidoRepositoryGateway.salvar(pedido);
    }

    private static void iniciarPedido(Pedido pedido) {
        pedido.setDataHoraCriacao(LocalDateTime.now());
        pedido.setSituacaoPagamento(PENDENTE);
        pedido.setStatus(INICIADO);
    }
}
