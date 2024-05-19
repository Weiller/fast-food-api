package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Pedido;

public interface PedidoRepositoryPort {
    Pedido salvar(Pedido pedido);
}
