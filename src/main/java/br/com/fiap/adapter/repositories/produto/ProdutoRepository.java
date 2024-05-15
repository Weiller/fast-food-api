package br.com.fiap.adapter.repositories.produto;

import br.com.fiap.core.domain.entities.ProdutoCategoriaEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(ProdutoCategoriaEnum categoria);
}
