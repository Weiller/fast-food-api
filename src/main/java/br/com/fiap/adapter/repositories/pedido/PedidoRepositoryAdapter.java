package br.com.fiap.adapter.repositories.pedido;

import br.com.fiap.adapter.controller.converter.PedidoConverter;
import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.domain.enums.StatusPedidoEnum;
import br.com.fiap.core.ports.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoRepository pedidoRepository;

    private final PedidoConverter pedidoConverter;

    @Override
    public Pedido salvar(Pedido pedido) {
        var novoPedido = pedidoRepository.save(pedidoConverter.converterPedidoToEntity(pedido));
        return PedidoConverter.converterPedidoEntityToPedido(novoPedido);
    }

    @Override
    public List<Pedido> obterPedidos(List<StatusPedidoEnum> statusList) {
        return pedidoRepository.findByStatus(statusList)
                .stream()
                .map(PedidoConverter::converterPedidoEntityToPedido)
                .toList();
    }

    @Override
    @Transactional
    public Optional<Pedido> obterPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoConverter::converterPedidoEntityToPedido);
    }
}
