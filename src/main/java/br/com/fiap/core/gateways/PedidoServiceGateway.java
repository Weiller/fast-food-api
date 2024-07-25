package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.ItemPedido;
import br.com.fiap.core.entities.Pedido;

import java.util.List;

public interface PedidoServiceGateway {
    Pedido salvar(Pedido pedido);

    List<Pedido> obterPedidos();

    Pedido adicionarItem(ItemPedido itemPedido);

    Pedido removerItem(ItemPedido itemPedido);

    Pedido cancelarPedido(Long id);

    Pedido checkoutPedido(Long id);

    Pedido efetuarEntrega(Long id);

    Pedido atualizarPedidoPronto(Long id);
}
