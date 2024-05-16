package br.com.fiap.adapter.controller.converter;

import br.com.fiap.adapter.controller.command.CriarPedidoCommand;
import br.com.fiap.adapter.repositories.produto.ProdutoEntity;
import br.com.fiap.core.domain.entities.Pedido;

public class PedidoConverter {

    public static Pedido converterEntityToPedido(ProdutoEntity produto) {
        return new Pedido.Builder()
                .id(produto.getId())
                .build();
    }

    public static Pedido converterCommandToPedido(CriarPedidoCommand command) {
        return new Pedido.Builder()
                .clienteId(command.getClienteId())
                .valor(command.getValor())
                .build();
    }

}
