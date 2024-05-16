package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Produto;
import br.com.fiap.core.dto.ProdutoDto;
import java.util.List;

public interface ProdutoServicePort {

    Produto salvar(Produto produto);

    List<ProdutoDto> getProdutosPorCategoria(String categoria);

}
