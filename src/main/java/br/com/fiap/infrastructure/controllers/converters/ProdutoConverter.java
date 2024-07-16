package br.com.fiap.infrastructure.controllers.converters;

import br.com.fiap.infrastructure.controllers.commands.AlterarProdutoCommand;
import br.com.fiap.infrastructure.controllers.commands.CriarProdutoCommand;
import br.com.fiap.infrastructure.repositories.produto.ProdutoEntity;
import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.dtos.ProdutoDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoConverter {

    public static Produto converterEntityToProduto(ProdutoEntity produto) {
        return new Produto.Builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .categoria(produto.getCategoria())
                .build();
    }

    public static List<Produto> converterListaEntityToProduto(List<ProdutoEntity> produtos) {
        return produtos.stream()
                .map(ProdutoConverter::converterEntityToProduto)
                .collect(Collectors.toList());
    }

    public static Produto converterCommandToProduto(CriarProdutoCommand command) {
        return new Produto.Builder()
                .nome(command.getNome())
                .descricao(command.getDescricao())
                .valor(command.getValor())
                .categoria(ProdutoCategoriaEnum.fromString(command.getCategoria()).orElse(null))
                .build();
    }

    public static Produto converterAlterarCommandToProduto(AlterarProdutoCommand command) {
        return new Produto.Builder()
                .id(command.getId())
                .nome(command.getNome())
                .descricao(command.getDescricao())
                .valor(command.getValor())
                .categoria(ProdutoCategoriaEnum.fromString(command.getCategoria()).orElse(null))
                .build();
    }

    public static ProdutoEntity converterProdutoToEntity(Produto produto) {
        return ProdutoEntity.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .categoria(produto.getCategoria())
                .dataInclusao(LocalDateTime.now())
                .build();
    }

    public static ProdutoDto converterProdutoToDto(Produto produto) {
        return new ProdutoDto.Builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .categoria(produto.getCategoria().getDescricao())
                .build();
    }
}
