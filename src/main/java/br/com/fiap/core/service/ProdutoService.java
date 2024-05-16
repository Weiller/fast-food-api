package br.com.fiap.core.service;

import br.com.fiap.core.converter.ProdutoConverter;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.entities.ProdutoCategoriaEnum;
import br.com.fiap.core.dto.ProdutoDto;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.ports.ProdutoRepositoryPort;
import br.com.fiap.core.ports.ProdutoServicePort;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public ProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public Produto salvar(Produto produto) {
        preValidar(produto);
        return produtoRepositoryPort.salvar(produto);
    }

    private static void preValidar(Produto produto) {
        if(Objects.isNull(produto.categoria())) {
            throw new BusinessException("A categoria está vazia ou está sendo passado um tipo inválido.");
        }

        if(Objects.isNull(produto.valor())) {
            throw new BusinessException("É obrigatório informar o valor do produto.");
        }

        if(produto.valor().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("O valor do produto deve ser maior que zero.");
        }

        if(Objects.isNull(produto.nome())) {
            throw new BusinessException("É obrigatório informar o nome do produto.");
        }

        if(Objects.isNull(produto.descricao())) {
            throw new BusinessException("É obrigatório informar a descrição do produto.");
        }
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
