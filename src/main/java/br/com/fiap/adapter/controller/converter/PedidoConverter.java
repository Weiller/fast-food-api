package br.com.fiap.adapter.controller.converter;

import br.com.fiap.adapter.controller.command.AdicionarItemCommand;
import br.com.fiap.adapter.controller.command.CriarPedidoCommand;
import br.com.fiap.adapter.dtos.PedidoDto;
import br.com.fiap.adapter.repositories.pedido.ItemPedidoEntity;
import br.com.fiap.adapter.repositories.pedido.PedidoEntity;
import br.com.fiap.adapter.repositories.pedido.PedidoRepository;
import br.com.fiap.adapter.repositories.produto.ProdutoRepository;
import br.com.fiap.core.domain.entities.ItemPedido;
import br.com.fiap.core.domain.entities.Pedido;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoConverter {

    private PedidoRepository pedidoRepository;

    private ProdutoRepository produtoRepository;

    public PedidoConverter(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public static ItemPedido converterItemCommandToItemPedido(AdicionarItemCommand adicionarItemCommand, Long pedidoId) {
        return new ItemPedido.Builder()
                .id(adicionarItemCommand.getProdutoId())
                .produto(adicionarItemCommand.getProdutoId())
                .pedido(pedidoId)
                .quantidade(adicionarItemCommand.getQuantidade())
                .build();
    }

    public static Pedido converterCommandToPedido(CriarPedidoCommand command) {
        return new Pedido.Builder()
                .clienteId(command.getClienteId())
                .valor(command.getValor())
                .build();
    }

    public PedidoEntity converterPedidoToEntity(Pedido pedido) {
        return PedidoEntity.builder()
                .id(pedido.getId())
                .clienteId(pedido.getClienteId())
                .valor(pedido.getValor())
                .situacaoPagamento(pedido.getSituacaoPagamento())
                .status(pedido.getStatus())
                .dataHoraPagamento(pedido.getDataHoraPagamento())
                .dataHoraCriacao(pedido.getDataHoraCriacao())
                .dataHoraEntrega(pedido.getDataHoraEntrega())
                .itens(pedido.getItens().stream().map(item -> ItemPedidoEntity.builder()
                        .id(item.getId())
                        .produto(produtoRepository.findById(item.getProdutoId()).get())
                        .pedido(pedidoRepository.findById(item.getPedidoId()).get())
                        .quantidade(item.getQuantidade())
                        .build()).toList())
                .build();
    }

    public static Pedido converterPedidoEntityToPedido(PedidoEntity pedido) {
        return new Pedido.Builder()
                .id(pedido.getId())
                .clienteId(pedido.getClienteId())
                .valor(pedido.getValor())
                .situacaoPagamento(pedido.getSituacaoPagamento())
                .status(pedido.getStatus())
                .dataHoraPagamento(pedido.getDataHoraPagamento())
                .dataHoraCriacao(pedido.getDataHoraCriacao())
                .dataHoraEntrega(pedido.getDataHoraEntrega())
                .itens(pedido.getItens().stream().map(item ->
                        new ItemPedido(item.getId(), item.getProduto().getId(), item.getPedido().getId(), item.getQuantidade()))
                        .collect(Collectors.toList()))
                .build();
    }

    public static PedidoDto converterDomainToDto(Pedido pedido) {
        return new PedidoDto(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getValor(),
                pedido.getSituacaoPagamento(),
                pedido.getStatus(),
                pedido.getDataHoraPagamento(),
                pedido.getDataHoraCriacao(),
                pedido.getDataHoraEntrega()
        );
    }
}
