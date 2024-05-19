package br.com.fiap.adapter.controller;

import br.com.fiap.adapter.controller.command.CriarPedidoCommand;
import br.com.fiap.adapter.controller.converter.PedidoConverter;
import br.com.fiap.adapter.dtos.PedidoDto;
import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.ports.PedidoServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoServicePort pedidoServicePort;

    @Operation(summary = "criar um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação no cadastro do pedido", content = @Content)})
    @PostMapping
    public PedidoDto salvar(@RequestBody CriarPedidoCommand command) {
        Pedido pedido = pedidoServicePort.salvar(PedidoConverter.converterCommandToPedido(command));
        return PedidoConverter.converterDomainToDto(pedido);
    }

}
