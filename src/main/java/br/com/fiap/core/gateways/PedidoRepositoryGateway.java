package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.Pedido;
import br.com.fiap.core.enums.StatusPedidoEnum;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryGateway {

    Pedido salvar(Pedido pedido);

    List<Pedido> obterPedidos(List<StatusPedidoEnum> statusList);

    Optional<Pedido> obterPorId(Long id);
}
