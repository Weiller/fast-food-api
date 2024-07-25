package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.Pedido;
import br.com.fiap.core.enums.StatusPagamentoEnum;

public interface PagamentoServiceGateway {

    Pedido atualizarPedidoSePagamentoAprovado(Long id, StatusPagamentoEnum statusPagamento);

    String obterSituacaoPagamento(Long idPedido);


}
