package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.ItemPedido;
import br.com.fiap.core.domain.entities.Pedido;

import java.util.List;

public interface PedidoServicePort {
    Pedido salvar(Pedido pedido);

    List<Pedido> obterPedidos();

    Pedido adicionarItem(ItemPedido itemPedido);

    Pedido cancelarPedido(Long id);

    Pedido realizarPagamento(Long id);

    Pedido efetuarEntrega(Long id);

    Pedido atualizarPedidoPronto(Long id);
}
