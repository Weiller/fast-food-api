package br.com.fiap.adapter.controller.converter;

import br.com.fiap.adapter.repositories.produto.ProdutoEntity;
import br.com.fiap.core.domain.entities.Produto;
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

}
