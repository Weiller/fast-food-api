package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.domain.enums.StatusPedidoEnum;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);

    List<Pedido> obterPedidos(List<StatusPedidoEnum> statusList);

    Optional<Pedido> obterPorId(Long id);
}
