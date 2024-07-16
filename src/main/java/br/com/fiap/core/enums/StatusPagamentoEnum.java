package br.com.fiap.core.enums;

import java.util.Optional;

public enum StatusPagamentoEnum {

    A("APROVADO"),
    R("RECUSADO");
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    StatusPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public static Optional<StatusPagamentoEnum> fromString(String value) {
        try {
            return Optional.of(StatusPagamentoEnum.valueOf(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
