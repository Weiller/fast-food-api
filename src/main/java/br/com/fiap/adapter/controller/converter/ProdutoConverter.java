package br.com.fiap.adapter.controller.converter;

import br.com.fiap.adapter.controller.command.AlterarProdutoCommand;
import br.com.fiap.adapter.controller.command.CriarProdutoCommand;
import br.com.fiap.adapter.repositories.produto.ProdutoEntity;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.entities.ProdutoCategoriaEnum;
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
                .build();
    }
}
