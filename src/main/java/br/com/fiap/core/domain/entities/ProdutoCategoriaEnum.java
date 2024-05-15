package br.com.fiap.core.domain.entities;

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
}
