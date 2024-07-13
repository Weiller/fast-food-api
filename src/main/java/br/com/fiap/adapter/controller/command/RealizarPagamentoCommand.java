package br.com.fiap.adapter.controller.command;

import br.com.fiap.core.domain.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealizarPagamentoCommand {

    private Long pedidoId;
    private StatusPagamentoEnum statusPagamento;

}
