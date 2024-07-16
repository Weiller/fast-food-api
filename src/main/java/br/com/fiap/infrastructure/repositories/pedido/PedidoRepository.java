package br.com.fiap.infrastructure.repositories.pedido;

import br.com.fiap.core.enums.StatusPedidoEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("SELECT p FROM PedidoEntity p JOIN FETCH p.itens WHERE p.status in (:status) order by p.dataHoraCriacao desc")
    List<PedidoEntity> findByStatus(List<StatusPedidoEnum> status);

}
