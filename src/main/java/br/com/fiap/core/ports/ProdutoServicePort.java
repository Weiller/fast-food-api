package br.com.fiap.core.ports;

import br.com.fiap.core.dto.ProdutoDto;
import java.util.List;

public interface ProdutoServicePort {

    List<ProdutoDto> getProdutosPorCategoria(String categoria);

}
