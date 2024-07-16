package br.com.fiap.core.enums;

import java.util.Optional;

public enum ProdutoCategoriaEnum {

    L("Lanche"),
    A("Acompanhamento"),
    B("Bebida"),
    S("Sobremesa");
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    ProdutoCategoriaEnum(String descricao) {
        this.descricao = descricao;
    }

    public static Optional<ProdutoCategoriaEnum> fromString(String value) {
        try {
            return Optional.of(ProdutoCategoriaEnum.valueOf(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
