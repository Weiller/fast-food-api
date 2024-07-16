package br.com.fiap.infrastructure.repositories.produto;

import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(ProdutoCategoriaEnum categoria);
}
