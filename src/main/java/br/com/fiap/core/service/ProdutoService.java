package br.com.fiap.core.service;

import br.com.fiap.core.converter.ProdutoConverter;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.entities.ProdutoCategoriaEnum;
import br.com.fiap.core.dto.ProdutoDto;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.ports.ProdutoRepositoryPort;
import br.com.fiap.core.ports.ProdutoServicePort;
import java.util.List;
import java.util.stream.Stream;

public class ProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public ProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public List<ProdutoDto> getProdutosPorCategoria(String categoria) {
        boolean categoriaValida = Stream.of(ProdutoCategoriaEnum.values())
                .anyMatch(c -> c.name().equals(categoria.toUpperCase()));

        if (!categoriaValida) {
            throw new BusinessException("Categoria inválida.");
        }

        List<Produto> produtoList = produtoRepositoryPort.getProdutosPorCategoria(ProdutoCategoriaEnum.valueOf(categoria.toUpperCase()));
        if (produtoList.isEmpty()) {
            throw new BusinessException("Não foram encontrados produtos nessa categoria.");
        }

        return ProdutoConverter.converterListaProdutoToProdutoDto(produtoList);
    }

}
