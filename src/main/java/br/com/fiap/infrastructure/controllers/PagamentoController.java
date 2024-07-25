package br.com.fiap.infrastructure.controllers;

import br.com.fiap.core.entities.Pedido;
import br.com.fiap.core.gateways.PagamentoServiceGateway;
import br.com.fiap.infrastructure.controllers.commands.RealizarPagamentoCommand;
import br.com.fiap.infrastructure.controllers.presenters.PagamentoPresenter;
import br.com.fiap.infrastructure.dtos.PagamentoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoServiceGateway pagamentoServiceGateway;

    @Operation(summary = "Webhook que recebe confirmação de pagamento se foi aprovado ou recusado através da enum StatusPagamentoEnum com o tipo A ou R")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento realizado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Pagamento não efetuado!", content = @Content)})

    @PutMapping("/{id}")
    public PagamentoDto atualizarPedidoSePagamentoAprovado(@RequestBody RealizarPagamentoCommand command) {
        Pedido pedido = pagamentoServiceGateway.atualizarPedidoSePagamentoAprovado(command.getPedidoId(), command.getStatusPagamento());
        return PagamentoPresenter.converterPedidoToPagamentoDto(pedido);
    }

    @Operation(summary = "Obtem o status do pagamento do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do pagamento obtido com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Status do pagamento não informado", content = @Content)})

    @GetMapping("/{id}/obter-status-pagamento")
    public String obterStatusPagamento(Long idPedido){
        return pagamentoServiceGateway.obterSituacaoPagamento(idPedido);
    }
}
