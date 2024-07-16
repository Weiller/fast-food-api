package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import java.util.List;

public interface ProdutoRepositoryGateway {

    Produto salvar(Produto produto);

    List<Produto> getProdutosPorCategoria(ProdutoCategoriaEnum categoria);

    Produto getProdutoById(Long id);

    Produto excluir(Produto produto);
}
