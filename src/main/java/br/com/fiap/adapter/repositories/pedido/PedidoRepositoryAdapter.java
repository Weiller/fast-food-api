package br.com.fiap.adapter.repositories.pedido;

import br.com.fiap.adapter.controller.converter.PedidoConverter;
import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.ports.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido salvar(Pedido pedido) {
        var novoPedido = pedidoRepository.save(PedidoConverter.converterPedidoToEntity(pedido));
        return PedidoConverter.converterPedidoEntityToPedido(novoPedido);
    }

    @Override
    public List<Pedido> obterPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoConverter::converterPedidoEntityToPedido)
                .toList();
    }
}
