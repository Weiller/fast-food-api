package br.com.fiap.core.ports;

import java.math.BigDecimal;

public interface PagamentoServicoExternoPort {
    Boolean efetuarPagamento(String qrcode, Long idPedido, BigDecimal valor);
}
