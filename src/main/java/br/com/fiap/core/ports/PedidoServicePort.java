package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.ItemPedido;
import br.com.fiap.core.domain.entities.Pedido;

import br.com.fiap.core.domain.enums.StatusPagamentoEnum;
import java.util.List;

public interface PedidoServicePort {
    Pedido salvar(Pedido pedido);

    List<Pedido> obterPedidos();

    Pedido adicionarItem(ItemPedido itemPedido);

    Pedido removerItem(ItemPedido itemPedido);

    Pedido cancelarPedido(Long id);

    Pedido efetuarPedido(Long id);

    Pedido atualizarPedidoSePagamentoAprovado(Long id, StatusPagamentoEnum statusPagamento);

    Pedido efetuarEntrega(Long id);

    Pedido atualizarPedidoPronto(Long id);
}
