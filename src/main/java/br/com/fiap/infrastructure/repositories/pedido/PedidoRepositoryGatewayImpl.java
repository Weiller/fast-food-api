package br.com.fiap.infrastructure.repositories.pedido;

import br.com.fiap.infrastructure.controllers.converters.PedidoConverter;
import br.com.fiap.core.entities.Pedido;
import br.com.fiap.core.enums.StatusPedidoEnum;
import br.com.fiap.core.gateways.PedidoRepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryGatewayImpl implements PedidoRepositoryGateway {

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
