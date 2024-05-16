package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.enums.ProdutoCategoriaEnum;
import java.util.List;

public interface ProdutoRepositoryPort {

    Produto salvar(Produto produto);

    List<Produto> getProdutosPorCategoria(ProdutoCategoriaEnum categoria);

    Produto getProdutoById(Long id);

    Produto excluir(Produto produto);
}
