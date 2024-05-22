package br.com.fiap.core.ports;

import br.com.fiap.adapter.repositories.pedido.PedidoEntity;
import br.com.fiap.core.domain.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);

    List<Pedido> obterPedidos();

    Optional<Pedido> obterPorId(Long id);
}
