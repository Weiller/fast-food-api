package br.com.fiap.core.service;

import br.com.fiap.core.domain.entities.Pedido;
import br.com.fiap.core.domain.enums.SituacaoPagamentoEnum;
import br.com.fiap.core.domain.enums.StatusPedidoEnum;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.ports.PedidoRepositoryPort;
import br.com.fiap.core.ports.PedidoServicePort;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoService implements PedidoServicePort {

    private  final PedidoRepositoryPort pedidoRepositoryPort;

    public PedidoService(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        if (pedido.getClienteId() == null) {
            throw new BusinessException("O cliente é obrigatório!");
        }
        iniciarPedido(pedido);
        return pedidoRepositoryPort.salvar(pedido);
    }

    @Override
    public List<Pedido> obterPedidos() {
        return pedidoRepositoryPort.obterPedidos();
    }

    private static void iniciarPedido(Pedido pedido) {
        pedido.setDataHoraCriacao(LocalDateTime.now());
        pedido.setSituacaoPagamento(SituacaoPagamentoEnum.PENDENTE);
        pedido.setStatus(StatusPedidoEnum.INICIADO);
    }
}
