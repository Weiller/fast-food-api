package br.com.fiap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public enum StatusPedidoEnum {

    INICIADO("Iniciado"),
    ANDAMENTO("Andamento"),
    PRONTO("Pronto"),
    CANCELADO("Cancelado"),
    CONCLUIDO("Concluído"),
    ENTREGUE("Entregue");

    private String descricao;

    public static Optional<StatusPedidoEnum> fromString(String value) {
        try {
            return Optional.of(StatusPedidoEnum.valueOf(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
