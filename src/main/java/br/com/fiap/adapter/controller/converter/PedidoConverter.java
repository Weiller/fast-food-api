package br.com.fiap.adapter.controller.converter;

import br.com.fiap.adapter.controller.command.CriarPedidoCommand;
import br.com.fiap.adapter.dtos.PedidoDto;
import br.com.fiap.adapter.repositories.pedido.PedidoEntity;
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

    public static PedidoEntity converterPedidoToEntity(Pedido pedido) {
        return  PedidoEntity.builder()
                .id(pedido.getId())
                .clienteId(pedido.getClienteId())
                .valor(pedido.getValor())
                .situacaoPagamento(pedido.getSituacaoPagamento())
                .status(pedido.getStatus())
                .dataHoraPagamento(pedido.getDataHoraPagamento())
                .dataHoraCriacao(pedido.getDataHoraCriacao())
                .dataHoraEntrega(pedido.getDataHoraEntrega())
                .build();
    }

    public static Pedido converterPedidoEntityToPedido(PedidoEntity pedido) {
        return  new Pedido.Builder()
                .id(pedido.getId())
                .clienteId(pedido.getClienteId())
                .valor(pedido.getValor())
                .situacaoPagamento(pedido.getSituacaoPagamento())
                .status(pedido.getStatus())
                .dataHoraPagamento(pedido.getDataHoraPagamento())
                .dataHoraCriacao(pedido.getDataHoraCriacao())
                .dataHoraEntrega(pedido.getDataHoraEntrega())
                .build();
    }

    public static PedidoDto converterDomainToDto(Pedido pedido) {
        return new PedidoDto(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getValor(),
                pedido.getSituacaoPagamento(),
                pedido.getStatus(),
                pedido.getDataHoraPagamento(),
                pedido.getDataHoraCriacao(),
                pedido.getDataHoraEntrega()
        );
    }
}
