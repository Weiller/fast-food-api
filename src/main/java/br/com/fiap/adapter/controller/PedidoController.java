package br.com.fiap.adapter.controller;

import br.com.fiap.adapter.controller.command.AdicionarItemCommand;
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
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Operation(summary = "obter pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar todos os pedidos", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))})})
    @GetMapping
    public List<PedidoDto> obterPedidos() {
        return pedidoServicePort.obterPedidos()
                .stream()
                .map(PedidoConverter::converterDomainToDto)
                .toList();
    }

    @Operation(summary = "criar um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação no cadastro do pedido", content = @Content)})
    @PutMapping("/{pedidoId}/adicionar")
    public PedidoDto adicionarItem(@PathVariable("pedidoId") Long pedidoId, @RequestBody AdicionarItemCommand command) {
        Pedido pedido = pedidoServicePort.adicionarItem(PedidoConverter.converterItemCommandToItemPedido(command, pedidoId));
        return PedidoConverter.converterDomainToDto(pedido);
    }

    @Operation(summary = "cancelar pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido cancelado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Pedido não existe!", content = @Content)})

    @PutMapping("/{id}/cancelar")
    public PedidoDto cancelarPedido(@PathVariable("id") Long id) {
        Pedido pedido = pedidoServicePort.cancelarPedido(id);
        return PedidoConverter.converterDomainToDto(pedido);
    }

}
