package br.com.fiap.adapter.repositories.pedido;

import br.com.fiap.core.domain.enums.StatusPedidoEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("SELECT p FROM PedidoEntity p JOIN FETCH p.itens WHERE p.status = :status")
    List<PedidoEntity> findByStatus(StatusPedidoEnum status);

}
