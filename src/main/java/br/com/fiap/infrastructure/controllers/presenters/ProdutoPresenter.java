package br.com.fiap.infrastructure.controllers.presenters;

import br.com.fiap.infrastructure.dtos.ProdutoDto;
import br.com.fiap.core.entities.Produto;

public class ProdutoPresenter {

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
