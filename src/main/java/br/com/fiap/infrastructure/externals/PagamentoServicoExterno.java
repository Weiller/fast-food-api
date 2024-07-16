package br.com.fiap.infrastructure.externals;

import br.com.fiap.infrastructure.controllers.commands.RealizarPagamentoCommand;
import br.com.fiap.infrastructure.dtos.PedidoDto;
import br.com.fiap.core.enums.SituacaoPagamentoEnum;
import br.com.fiap.core.enums.StatusPagamentoEnum;
import br.com.fiap.core.gateways.PagamentoServicoExternoGateway;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PagamentoServicoExterno implements PagamentoServicoExternoGateway {

    @Override
    public Boolean efetuarPagamento(String qrcode, Long idPedido, BigDecimal valor) {
        //SIMULAÇÃO ENDPOINT EXTERNO ENVIANDO O ID DO PEDIDO E O STATUS DO PAGAMENTO
        // SUPOSIÇÃO DE CHAMADA EM ENDPOINT EXTERNO DEVOLVENDO O STATUS DE PAGAMENTO COMO APROVADO
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();

            //TODO Mudar url de localhost para o ip do cluster
            HttpEntity<RealizarPagamentoCommand> requestEntity = new HttpEntity<>(new RealizarPagamentoCommand(idPedido, StatusPagamentoEnum.A), headers);
            ResponseEntity<PedidoDto> responseEntity = restTemplate.exchange("http://"+ ip +":8080/api/pagamentos/" + idPedido, HttpMethod.PUT, requestEntity, PedidoDto.class);

            return Objects.requireNonNull(responseEntity.getBody()).situacaoPagamento().equals(SituacaoPagamentoEnum.PAGO);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
