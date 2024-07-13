package br.com.fiap.adapter.external;

import br.com.fiap.adapter.controller.command.RealizarPagamentoCommand;
import br.com.fiap.adapter.dtos.PedidoDto;
import br.com.fiap.core.domain.enums.SituacaoPagamentoEnum;
import br.com.fiap.core.domain.enums.StatusPagamentoEnum;
import br.com.fiap.core.ports.PagamentoServicoExternoPort;
import java.math.BigDecimal;
import java.util.Objects;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PagamentoServicoExterno implements PagamentoServicoExternoPort {

    @Override
    public Boolean efetuarPagamento(String qrcode, Long idPedido, BigDecimal valor) {
        //SIMULAÇÃO ENDPOINT EXTERNO ENVIANDO O ID DO PEDIDO E O STATUS DO PAGAMENTO
        // SUPOSIÇÃO DE CHAMADA EM ENDPOINT EXTERNO DEVOLVENDO O STATUS DE PAGAMENTO COMO APROVADO
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<RealizarPagamentoCommand> requestEntity = new HttpEntity<>(new RealizarPagamentoCommand(idPedido, StatusPagamentoEnum.A), headers);
        ResponseEntity<PedidoDto> responseEntity = restTemplate.exchange("http://localhost:8080/api/pagamentos/" + idPedido, HttpMethod.PUT, requestEntity, PedidoDto.class);

        return Objects.requireNonNull(responseEntity.getBody()).situacaoPagamento().equals(SituacaoPagamentoEnum.PAGO);
    }
}
