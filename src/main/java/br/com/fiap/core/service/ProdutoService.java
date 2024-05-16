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

        if (produto.getId() != null) {
            return alterar(produto);
        } else {
            return criar(produto);
        }
    }

    private Produto criar(Produto produto) {
        return produtoRepositoryPort.salvar(produto);
    }

    private Produto alterar(Produto produto) {
        Produto produtoSalvo = getProdutoById(produto.getId());

        if (Objects.isNull(produtoSalvo)) {
            throw new BusinessException("Erro ao alterar, produto não encontrado no banco de dados.");
        }

        produtoSalvo.alterar(produto);
        return produtoRepositoryPort.salvar(produtoSalvo);
    }

    private static void preValidar(Produto produto) {
        if(Objects.isNull(produto.getCategoria())) {
            throw new BusinessException("A categoria está vazia ou está sendo passado um tipo inválido.");
        }

        if(Objects.isNull(produto.getValor())) {
            throw new BusinessException("É obrigatório informar o valor do produto.");
        }

        if(produto.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("O valor do produto deve ser maior que zero.");
        }

        if(Objects.isNull(produto.getNome())) {
            throw new BusinessException("É obrigatório informar o nome do produto.");
        }

        if(Objects.isNull(produto.getDescricao())) {
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

    @Override
    public Produto getProdutoById(Long id) {
        return produtoRepositoryPort.getProdutoById(id);
    }

    @Override
    public Produto excluir(Long id) {
        Produto produto = getProdutoById(id);

        if(Objects.isNull(produto)) {
            throw new BusinessException("Não foi possível excluir. Produto não encontrado.");
        }
        return produtoRepositoryPort.excluir(produto);
    }

}
