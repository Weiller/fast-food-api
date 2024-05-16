package br.com.fiap.core.converter;

import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.dto.ProdutoDto;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoConverter {

    public static ProdutoDto converterProdutoToProdutoDto(Produto produto) {
        return new ProdutoDto.Builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .categoria(produto.getCategoria().getDescricao())
                .build();
    }

    public static List<ProdutoDto> converterListaProdutoToProdutoDto(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoConverter::converterProdutoToProdutoDto)
                .collect(Collectors.toList());
    }
}
