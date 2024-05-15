package br.com.fiap.core.domain.entities;

import java.math.BigDecimal;

public record Produto(Long id,
                      String nome,
                      String descricao,
                      BigDecimal valor,
                      ProdutoCategoriaEnum categoria) {

    public static class Builder {
        private Long id;
        private String nome;
        private String descricao;
        private BigDecimal valor;
        private ProdutoCategoriaEnum categoria;

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

        public Builder categoria(ProdutoCategoriaEnum categoria) {
            this.categoria = categoria;
            return this;
        }

        public Produto build() {
            return new Produto(id, nome, descricao, valor, categoria);
        }
    }

}
