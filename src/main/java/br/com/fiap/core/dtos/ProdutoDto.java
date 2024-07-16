package br.com.fiap.core.dtos;

import java.math.BigDecimal;

public record ProdutoDto(Long id,
                         String nome,
                         String descricao,
                         BigDecimal valor,
                         String descricaoCategoria) {

    public static class Builder {
        private Long id;
        private String nome;
        private String descricao;
        private BigDecimal valor;
        private String descricaoCategoria;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public Builder categoria(String descricaoCategoria) {
            this.descricaoCategoria = descricaoCategoria;
            return this;
        }

        public ProdutoDto build() {
            return new ProdutoDto(id, nome, descricao, valor, descricaoCategoria);
        }
    }

}
