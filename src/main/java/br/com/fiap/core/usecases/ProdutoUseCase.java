package br.com.fiap.core.usecases;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.gateways.ProdutoRepositoryGateway;
import br.com.fiap.core.gateways.ProdutoServiceGateway;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ProdutoUseCase implements ProdutoServiceGateway {

    private final ProdutoRepositoryGateway produtoRepositoryGateway;

    public ProdutoUseCase(ProdutoRepositoryGateway produtoRepositoryGateway) {
        this.produtoRepositoryGateway = produtoRepositoryGateway;
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
        return produtoRepositoryGateway.salvar(produto);
    }

    private Produto alterar(Produto produto) {
        Produto produtoSalvo = getProdutoById(produto.getId());

        if (Objects.isNull(produtoSalvo)) {
            throw new BusinessException("Erro ao alterar, produto não encontrado no banco de dados.");
        }

        produtoSalvo.alterar(produto);
        return produtoRepositoryGateway.salvar(produtoSalvo);
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
    public List<Produto> getProdutosPorCategoria(String categoria) {
        boolean categoriaValida = Stream.of(ProdutoCategoriaEnum.values())
                .anyMatch(c -> c.name().equals(categoria.toUpperCase()));

        if (!categoriaValida) {
            throw new BusinessException("Categoria inválida.");
        }

        List<Produto> produtoList = produtoRepositoryGateway.getProdutosPorCategoria(ProdutoCategoriaEnum.valueOf(categoria.toUpperCase()));
        if (produtoList.isEmpty()) {
            throw new BusinessException("Não foram encontrados produtos nessa categoria.");
        }

        return produtoList;
    }

    @Override
    public Produto getProdutoById(Long id) {
        return produtoRepositoryGateway.getProdutoById(id);
    }

    @Override
    public Produto excluir(Long id) {
        Produto produto = getProdutoById(id);

        if(Objects.isNull(produto)) {
            throw new BusinessException("Não foi possível excluir. Produto não encontrado.");
        }
        return produtoRepositoryGateway.excluir(produto);
    }

}
