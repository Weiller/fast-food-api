package br.com.fiap.core.service;

import br.com.fiap.adapter.external.PagamentoServicoExterno;
import br.com.fiap.core.domain.entities.ItemPedido;
import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.ports.PedidoRepositoryPort;
import br.com.fiap.core.ports.PedidoServicePort;
import br.com.fiap.core.ports.ProdutoRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiap.core.domain.enums.SituacaoPagamentoEnum.*;
import static br.com.fiap.core.domain.enums.StatusPedidoEnum.*;
import static java.math.BigDecimal.*;
import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.*;

public class PedidoService implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    private final ProdutoRepositoryPort produtoRepositoryPort;

    private final PagamentoServicoExterno pagamentoServicoExterno;

    public PedidoService(PedidoRepositoryPort pedidoRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort, PagamentoServicoExterno pagamentoServicoExterno) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
        this.pagamentoServicoExterno = pagamentoServicoExterno;
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
    public List<Pedido> obterPedidos() {
        return pedidoRepositoryPort.obterPedidos();
    }

    @Override
    public Pedido adicionarItem(ItemPedido itemPedido) {
        Pedido pedido = pedidoRepositoryPort.obterPorId(itemPedido.getPedidoId()).orElseThrow(() -> new BusinessException("Pedido não encontrado!"));
        Produto produto = produtoRepositoryPort.getProdutoById(itemPedido.getProdutoId());

        if (isNull(produto)) {
            throw new BusinessException("Produto não encontrado!");
        }

        pedido.adicionarItemPedido(itemPedido);
        pedido.setValor(pedido.getValor().add(produto.getValor()));
        return pedidoRepositoryPort.salvar(pedido);
    }

    @Override
    public Pedido cancelarPedido(Long id) {
        Pedido pedido = pedidoRepositoryPort.obterPorId(id)
                .orElseThrow(() -> new BusinessException("Pedido não existente!"));

        return cancelarPedido(pedido);
    }

    @Override
    public Pedido realizarPagamento(Long id) {
        Pedido pedido = pedidoRepositoryPort.obterPorId(id)
                .orElseThrow(() -> new BusinessException("Pedido não existente!"));

        if (isEmpty(pedido.getItens())) {
            throw new BusinessException("Não possui itens no pedido!");
        }

        if (pagamentoServicoExterno.efetuarPagamento("QrCode")) {
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
        Pedido pedido = pedidoRepositoryPort.obterPorId(id)
                .orElseThrow(() -> new BusinessException("Pedido não existente!"));

        if (!pedido.getStatus().equals(PRONTO)) {
            throw new BusinessException("Pedido não esta pronto para entrega!");
        }

        if (pedido.getSituacaoPagamento().equals(PAGO)) {
            throw new BusinessException("Pagamento esta pendente!");
        }

        pedido.setStatus(ENTREGUE);
        pedido.setDataHoraEntrega(LocalDateTime.now());

        return pedidoRepositoryPort.salvar(pedido);
    }

    @Override
    public Pedido atualizarPedidoPronto(Long id) {
        Pedido pedido = pedidoRepositoryPort.obterPorId(id)
                .orElseThrow(() -> new BusinessException("Pedido não existente!"));

        if (!pedido.getStatus().equals(ANDAMENTO)) {
            throw new BusinessException("Pedido não está com status em andamento");
        }

        pedido.setStatus(PRONTO);

        return pedidoRepositoryPort.salvar(pedido);
    }

    private Pedido cancelarPedido(Pedido pedido) {
        pedido.setStatus(CANCELADO);
        return pedidoRepositoryPort.salvar(pedido);
    }

    private static void iniciarPedido(Pedido pedido) {
        pedido.setDataHoraCriacao(LocalDateTime.now());
        pedido.setSituacaoPagamento(PENDENTE);
        pedido.setStatus(INICIADO);
    }
}
