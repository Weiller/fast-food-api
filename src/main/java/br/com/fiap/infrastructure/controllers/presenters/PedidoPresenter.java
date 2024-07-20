package br.com.fiap.infrastructure.controllers.presenters;

import br.com.fiap.core.entities.Pedido;
import br.com.fiap.infrastructure.dtos.ItemPedidoDto;
import br.com.fiap.infrastructure.dtos.PedidoDto;
import java.util.stream.Collectors;

public class PedidoPresenter {

    public static PedidoDto converterDomainToDto(Pedido pedido) {
        return new PedidoDto(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getValor(),
                pedido.getSituacaoPagamento(),
                pedido.getStatus(),
                pedido.getDataHoraPagamento(),
                pedido.getDataHoraCriacao(),
                pedido.getDataHoraEntrega(),
                pedido.getItens().stream().map(i -> new ItemPedidoDto(i.getId())).collect(Collectors.toList())
        );
    }

}
