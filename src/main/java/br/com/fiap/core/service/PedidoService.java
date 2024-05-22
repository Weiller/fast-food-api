package br.com.fiap.core.service;

import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.enums.SituacaoPagamentoEnum;
import br.com.fiap.core.domain.enums.StatusPedidoEnum;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.ports.PedidoRepositoryPort;
import br.com.fiap.core.ports.PedidoServicePort;

import br.com.fiap.core.ports.ProdutoRepositoryPort;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PedidoService implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public PedidoService(PedidoRepositoryPort pedidoRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
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
    public Pedido adicionarProduto(Long itemId, Long pedidoId) {
        Pedido pedido = pedidoRepositoryPort.getPedidoById(pedidoId);
        Produto produto = produtoRepositoryPort.getProdutoById(itemId);

        if (Objects.isNull(produto)) {
            throw new BusinessException("Produto não encontrado!");
        }

        pedido.adicionarProduto(produto);
        pedido.setValor(pedido.getValor().add(produto.getValor()));
        return pedidoRepositoryPort.salvar(pedido);
    }

    private static void iniciarPedido(Pedido pedido) {
        pedido.setDataHoraCriacao(LocalDateTime.now());
        pedido.setSituacaoPagamento(SituacaoPagamentoEnum.PENDENTE);
        pedido.setStatus(StatusPedidoEnum.INICIADO);
    }
}
