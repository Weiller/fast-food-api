package br.com.fiap.core.ports;

public interface PagamentoServicoExternoPort {
    Boolean efetuarPagamento(String qrcode);
}
