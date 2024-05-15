package br.com.fiap.adapter.controller;

import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.ports.ClienteServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ClienteServicePort clienteServicePort;

    public ProdutoController(ClienteServicePort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    @Operation(summary = "Retornar um cliente pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Cliente não encontrado", content = @Content)})
    @GetMapping("/{cpf}")
    public Cliente getProdutosPorCategoria(@PathVariable("cpf") String cpf) {
        return clienteServicePort.getClienteByCpf(cpf);
    }

}
