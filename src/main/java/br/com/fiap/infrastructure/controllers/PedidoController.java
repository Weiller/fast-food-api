package br.com.fiap.infrastructure.controllers;

import br.com.fiap.infrastructure.controllers.commands.AdicionarItemCommand;
import br.com.fiap.infrastructure.controllers.commands.CriarPedidoCommand;
import br.com.fiap.infrastructure.controllers.commands.RemoverItemCommand;
import br.com.fiap.infrastructure.controllers.converters.PedidoConverter;
import br.com.fiap.infrastructure.dtos.PedidoDto;
import br.com.fiap.core.entities.Pedido;
import br.com.fiap.core.gateways.PedidoServiceGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    private final PedidoServiceGateway pedidoServiceGateway;

    @Operation(summary = "criar um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação no cadastro do pedido", content = @Content)})
    @PostMapping
    public PedidoDto salvar(@RequestBody CriarPedidoCommand command) {
        Pedido pedido = pedidoServiceGateway.salvar(PedidoConverter.converterCommandToPedido(command));
        return PedidoConverter.converterDomainToDto(pedido);
    }

    @Operation(summary = "obter pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar todos os pedidos", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))})})
    @GetMapping
    public List<PedidoDto> obterPedidos() {
        return pedidoServiceGateway.obterPedidos()
                .stream()
                .map(PedidoConverter::converterDomainToDto)
                .toList();
    }

    @Operation(summary = "adicionar item no pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item adicionado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação na adição do pedido", content = @Content)})
    @PutMapping("/{pedidoId}/adicionar")
    public PedidoDto adicionarItem(@PathVariable("pedidoId") Long pedidoId, @RequestBody AdicionarItemCommand command) {
        Pedido pedido = pedidoServiceGateway.adicionarItem(PedidoConverter.converterAdicionarItemCommandToItemPedido(command, pedidoId));
        return PedidoConverter.converterDomainToDto(pedido);
    }

    @Operation(summary = "Remover um item do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item removido com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação no cadastro do pedido", content = @Content)})
    @DeleteMapping("/{pedidoId}/remover")
    public PedidoDto removerItem(@PathVariable("pedidoId") Long pedidoId, @RequestBody RemoverItemCommand command) {
        Pedido pedido = pedidoServiceGateway.removerItem(PedidoConverter.converterRemoverItemCommandToItemPedido(command, pedidoId));
        return PedidoConverter.converterDomainToDto(pedido);
    }


    @Operation(summary = "Endpoint para efetuar o checkout do pedido e chamar a api externa para efetuar pagamento.. " +
            "essa api externa enviará a informação para o webhook se o pagamento foi aprovado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido efetuado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Pedido não efetuado!", content = @Content)})

    @PutMapping("/{id}/checkout")
    public PedidoDto checkoutPedido(@PathVariable("id") Long id) {
        Pedido pedido = pedidoServiceGateway.checkoutPedido(id);
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
        Pedido pedido = pedidoServiceGateway.cancelarPedido(id);
        return PedidoConverter.converterDomainToDto(pedido);
    }

    @Operation(summary = "efetuar entrega")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega realizada com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Entrega não realizada!", content = @Content)})

    @PutMapping("/{id}/entrega")
    public PedidoDto efetuarEntrega(@PathVariable("id") Long id) {
        Pedido pedido = pedidoServiceGateway.efetuarEntrega(id);
        return PedidoConverter.converterDomainToDto(pedido);
    }

    @Operation(summary = "atualizar pedido para pronto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado para pronto", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pedido.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Pedido não atualizado!", content = @Content)})

    @PutMapping("/{id}/atualizar-pedido-pronto")
    public PedidoDto atualizarPedidoPronto(@PathVariable("id") Long id) {
        Pedido pedido = pedidoServiceGateway.atualizarPedidoPronto(id);
        return PedidoConverter.converterDomainToDto(pedido);
    }

}
