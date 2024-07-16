package br.com.fiap.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public enum SituacaoPagamentoEnum {

    PENDENTE("Pendente"),
    PAGO("Pago");

    private String descricao;

    public static Optional<SituacaoPagamentoEnum> fromString(String value) {
        try {
            return Optional.of(SituacaoPagamentoEnum.valueOf(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
