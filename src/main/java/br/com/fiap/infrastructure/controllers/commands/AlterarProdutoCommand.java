package br.com.fiap.infrastructure.controllers.commands;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlterarProdutoCommand {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private String categoria;
}
