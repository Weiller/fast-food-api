package br.com.fiap.infrastructure.controllers;

import br.com.fiap.infrastructure.controllers.commands.CriarClienteCommand;
import br.com.fiap.infrastructure.controllers.converters.ClienteConverter;
import br.com.fiap.infrastructure.controllers.presenters.ClientePresenter;
import br.com.fiap.infrastructure.dtos.ClienteDto;
import br.com.fiap.core.entities.Cliente;
import br.com.fiap.core.gateways.ClienteServiceGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteServiceGateway clienteServiceGateway;

    public ClienteController(ClienteServiceGateway clienteServiceGateway) {
        this.clienteServiceGateway = clienteServiceGateway;
    }

    @Operation(summary = "Retornar um cliente pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Cliente não encontrado", content = @Content) })
    @GetMapping("/{cpf}")
    public ClienteDto getClienteByCpf(@PathVariable("cpf") String cpf) {
        return ClientePresenter.converterClienteToDto(clienteServiceGateway.getClienteByCpf(cpf));
    }

    @Operation(summary = "Salvar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação no cadastro do cliente", content = @Content) })
    @PostMapping
    public ClienteDto salvar(@RequestBody CriarClienteCommand command) {
        return ClientePresenter.converterClienteToDto(clienteServiceGateway.salvar(ClienteConverter.converterCommandToCliente(command)));
    }

}
