package br.com.fiap.adapter.repositories.produto;

import br.com.fiap.adapter.controller.converter.ProdutoConverter;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.ports.ProdutoRepositoryPort;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRepositoryPortAdapter implements ProdutoRepositoryPort {

    private final ProdutoRepository produtoRepository;

    public ProdutoRepositoryPortAdapter(ProdutoRepository produtoRepository) {
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
