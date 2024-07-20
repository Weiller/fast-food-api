package br.com.fiap.infrastructure.controllers.presenters;

import br.com.fiap.core.entities.Pedido;
import br.com.fiap.infrastructure.dtos.PagamentoDto;

public class PagamentoPresenter {


    public static PagamentoDto converterPedidoToPagamentoDto(Pedido pedido) {
        return PagamentoDto.builder().id(pedido.getId()).situacaoPagamento(pedido.getSituacaoPagamento().getDescricao()).build();
    }

}
