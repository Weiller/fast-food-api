package br.com.fiap.adapter.external;

import org.springframework.stereotype.Component;

@Component
public class PagamentoServicoExterno {

    public Boolean efetuarPagamento(String qrcode) {
        return true;
    }
}
