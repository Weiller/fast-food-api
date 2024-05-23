package br.com.fiap.adapter.external;

import br.com.fiap.core.ports.PagamentoServicoExternoPort;
import org.springframework.stereotype.Component;

@Component
public class PagamentoServicoExterno implements PagamentoServicoExternoPort {

    @Override
    public Boolean efetuarPagamento(String qrcode) {
        return true;
    }
}
