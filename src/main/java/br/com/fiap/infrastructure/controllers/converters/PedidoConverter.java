package br.com.fiap.infrastructure.controllers.converters;

import br.com.fiap.infrastructure.controllers.commands.AdicionarItemCommand;
import br.com.fiap.infrastructure.controllers.commands.CriarPedidoCommand;
import br.com.fiap.infrastructure.controllers.commands.RemoverItemCommand;
import br.com.fiap.infrastructure.dtos.ItemPedidoDto;
import br.com.fiap.infrastructure.dtos.PedidoDto;
import br.com.fiap.infrastructure.repositories.pedido.ItemPedidoEntity;
import br.com.fiap.infrastructure.repositories.pedido.PedidoEntity;
import br.com.fiap.infrastructure.repositories.pedido.PedidoRepository;
import br.com.fiap.infrastructure.repositories.produto.ProdutoRepository;
import br.com.fiap.core.entities.ItemPedido;
import br.com.fiap.core.entities.Pedido;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PedidoConverter {

    private PedidoRepository pedidoRepository;

    private ProdutoRepository produtoRepository;

    public PedidoConverter(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public static ItemPedido converterAdicionarItemCommandToItemPedido(AdicionarItemCommand adicionarItemCommand, Long pedidoId) {
        return new ItemPedido.Builder()
                .produto(adicionarItemCommand.getProdutoId())
                .pedido(pedidoId)
                .quantidade(adicionarItemCommand.getQuantidade())
                .build();
    }

    public static ItemPedido converterRemoverItemCommandToItemPedido(RemoverItemCommand removerItemCommand, Long pedidoId) {
        return new ItemPedido.Builder()
                .produto(removerItemCommand.getProdutoId())
                .pedido(pedidoId)
                .quantidade(removerItemCommand.getQuantidade())
                .build();
    }

    public static Pedido converterCommandToPedido(CriarPedidoCommand command) {
        return new Pedido.Builder()
                .clienteId(command.getClienteId())
                .valor(BigDecimal.ZERO)
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
}
