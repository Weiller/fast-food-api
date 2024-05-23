package br.com.fiap.core.service;

import br.com.fiap.adapter.external.PagamentoServicoExterno;
import br.com.fiap.core.domain.entities.ItemPedido;
import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.ports.NotificacaoSonoraPort;
import br.com.fiap.core.ports.PagamentoServicoExternoPort;
import br.com.fiap.core.ports.PedidoRepositoryPort;
import br.com.fiap.core.ports.PedidoServicePort;
import br.com.fiap.core.ports.ProdutoRepositoryPort;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import static br.com.fiap.core.domain.enums.SituacaoPagamentoEnum.PAGO;
import static br.com.fiap.core.domain.enums.SituacaoPagamentoEnum.PENDENTE;
import static br.com.fiap.core.domain.enums.StatusPedidoEnum.ANDAMENTO;
import static br.com.fiap.core.domain.enums.StatusPedidoEnum.CANCELADO;
import static br.com.fiap.core.domain.enums.StatusPedidoEnum.ENTREGUE;
import static br.com.fiap.core.domain.enums.StatusPedidoEnum.INICIADO;
import static br.com.fiap.core.domain.enums.StatusPedidoEnum.PRONTO;
import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

public class PedidoService implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    private final ProdutoRepositoryPort produtoRepositoryPort;

    private final PagamentoServicoExternoPort pagamentoServicoExternoPort;

    private final NotificacaoSonoraPort notificacaoSonoraPort;

    public PedidoService(PedidoRepositoryPort pedidoRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort,
                         PagamentoServicoExternoPort pagamentoServicoExterno, NotificacaoSonoraPort notificacaoSonoraPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
        this.pagamentoServicoExternoPort = pagamentoServicoExterno;
        this.notificacaoSonoraPort = notificacaoSonoraPort;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        if (pedido.getClienteId() == null) {
            throw new BusinessException("O cliente é obrigatório!");
        }
        iniciarPedido(pedido);
        return pedidoRepositoryPort.salvar(pedido);
    }

    @Override
    public List<Pedido> obterPedidosEmAndamento() {
        return pedidoRepositoryPort.obterPedidos();
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
        return pedidoRepositoryPort.salvar(pedido);
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

        return pedidoRepositoryPort.salvar(pedido);
    }

    private Produto obterProduto(ItemPedido itemPedido) {
        return produtoRepositoryPort.getProdutoById(itemPedido.getProdutoId());
    }

    private Pedido obterPedido(Long id) {
        return pedidoRepositoryPort.obterPorId(id).orElseThrow(() -> new BusinessException("Pedido não encontrado!"));
    }

    @Override
    public Pedido cancelarPedido(Long id) {
        Pedido pedido = obterPedido(id);

        return cancelarPedido(pedido);
    }

    @Override
    public Pedido realizarPagamento(Long id) {
        Pedido pedido = obterPedido(id);

        if (isEmpty(pedido.getItens())) {
            throw new BusinessException("Não possui itens no pedido!");
        }

        if (pagamentoServicoExternoPort.efetuarPagamento("QrCode")) {
            pedido.setSituacaoPagamento(PAGO);
            pedido.setStatus(ANDAMENTO);
            pedido.setDataHoraPagamento(LocalDateTime.now());
        } else {
            pedido.setSituacaoPagamento(PENDENTE);
        }

        return pedidoRepositoryPort.salvar(pedido);
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

        return pedidoRepositoryPort.salvar(pedido);
    }

    @Override
    public Pedido atualizarPedidoPronto(Long id) {
        Pedido pedido = obterPedido(id);

        if (!pedido.getStatus().equals(ANDAMENTO)) {
            throw new BusinessException("Pedido não está com status em andamento");
        }

        pedido.setStatus(PRONTO);
        notificacaoSonoraPort.notificar();
        return pedidoRepositoryPort.salvar(pedido);
    }

    private Pedido cancelarPedido(Pedido pedido) {
        if(pedido.getStatus().equals(CANCELADO)) {
            throw new BusinessException("Pedido já foi cancelado!");
        }

        if(pedido.getStatus().equals(ENTREGUE)) {
            throw new BusinessException("Pedido já foi entregue!");
        }

        pedido.setStatus(CANCELADO);
        return pedidoRepositoryPort.salvar(pedido);
    }

    private static void iniciarPedido(Pedido pedido) {
        pedido.setDataHoraCriacao(LocalDateTime.now());
        pedido.setSituacaoPagamento(PENDENTE);
        pedido.setStatus(INICIADO);
    }
}
