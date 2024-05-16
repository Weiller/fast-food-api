package br.com.fiap.adapter.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarPedidoCommand {
    private Long clienteId;;
    private BigDecimal valor;
}
