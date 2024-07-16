package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.dtos.ProdutoDto;
import java.util.List;

public interface ProdutoServiceGateway {

    Produto salvar(Produto produto);

    List<ProdutoDto> getProdutosPorCategoria(String categoria);

    Produto getProdutoById(Long id);

    Produto excluir(Long id);

}
