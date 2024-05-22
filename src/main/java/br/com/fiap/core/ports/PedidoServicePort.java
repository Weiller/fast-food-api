package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Pedido;

import java.util.List;

public interface PedidoServicePort {
    Pedido salvar(Pedido pedido);

    List<Pedido> obterPedidos();

    Pedido adicionarProduto(Long itemId, Long pedidoId);

    Pedido cancelarPedido(Long id);
}
