package br.com.fiap.adapter.controller;

import br.com.fiap.adapter.controller.command.AlterarProdutoCommand;
import br.com.fiap.adapter.controller.command.CriarProdutoCommand;
import br.com.fiap.adapter.controller.converter.ProdutoConverter;
import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.dto.ProdutoDto;
import br.com.fiap.core.ports.ProdutoServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoServicePort produtoServicePort;

    public ProdutoController(ProdutoServicePort produtoServicePort) {
        this.produtoServicePort = produtoServicePort;
    }

    @Operation(summary = "Salvar um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação no cadastro do produto", content = @Content)})
    @PostMapping
    public Produto salvar(@RequestBody CriarProdutoCommand command) {
        return produtoServicePort.salvar(ProdutoConverter.converterCommandToProduto(command));
    }

    @Operation(summary = "Alterar um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação na alteração do produto", content = @Content)})
    @PutMapping
    public Produto alterar(@RequestBody AlterarProdutoCommand command) {
        return produtoServicePort.salvar(ProdutoConverter.converterAlterarCommandToProduto(command));
    }

    @Operation(summary = "Excluir um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excluído com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de negócio na exclusão.", content = @Content)})
    @DeleteMapping("/{id}")
    public Produto excluir(@PathVariable("id") Long id) {
        return produtoServicePort.excluir(id);
    }

    @Operation(summary = "Retornar uma lista de produtos pela categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornou uma lista", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Categoria não encontrada", content = @Content)})
    @GetMapping("/{categoria}")
    public List<ProdutoDto> getProdutosPorCategoria(@PathVariable("categoria") String categoria) {
        return produtoServicePort.getProdutosPorCategoria(categoria);
    }

}
