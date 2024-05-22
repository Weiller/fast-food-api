package br.com.fiap.adapter.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdicionarItemCommand {
    private Long pedidoId;
    private Long produtoId;
}
