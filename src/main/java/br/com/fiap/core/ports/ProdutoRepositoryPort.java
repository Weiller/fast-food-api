package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.domain.entities.ProdutoCategoriaEnum;
import java.util.List;

public interface ProdutoRepositoryPort {

    List<Produto> getProdutosPorCategoria(ProdutoCategoriaEnum categoria);
}
