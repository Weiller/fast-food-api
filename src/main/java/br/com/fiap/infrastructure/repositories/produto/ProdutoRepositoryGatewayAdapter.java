package br.com.fiap.infrastructure.repositories.produto;

import br.com.fiap.infrastructure.controllers.converters.ProdutoConverter;
import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.gateways.ProdutoRepositoryGateway;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRepositoryGatewayAdapter implements ProdutoRepositoryGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoRepositoryGatewayAdapter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity produtoEntity = produtoRepository.save(ProdutoConverter.converterProdutoToEntity(produto));

        return ProdutoConverter.converterEntityToProduto(produtoEntity);
    }

    @Override
    public List<Produto> getProdutosPorCategoria(ProdutoCategoriaEnum categoria) {
        List<ProdutoEntity> produtosList = produtoRepository.findByCategoria(categoria);

        if (!produtosList.isEmpty()) {
            return ProdutoConverter.converterListaEntityToProduto(produtosList);
        }

        return List.of();
    }

    @Override
    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id).map(ProdutoConverter::converterEntityToProduto).orElse(null);
    }

    @Override
    public Produto excluir(Produto produto) {
        produtoRepository.deleteById(produto.getId());

        return produto;
    }
}
