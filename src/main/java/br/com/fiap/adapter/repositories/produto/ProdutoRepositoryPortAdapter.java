package br.com.fiap.adapter.repositories.produto;

import br.com.fiap.adapter.controller.converter.ProdutoConverter;
import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.entities.ProdutoCategoriaEnum;
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
    public List<Produto> getProdutosPorCategoria(ProdutoCategoriaEnum categoria) {
        List<ProdutoEntity> produtosList = produtoRepository.findByCategoria(categoria);

        if (!produtosList.isEmpty()) {
            return ProdutoConverter.converterListaEntityToProduto(produtosList);
        }

        return List.of();
    }
}
