package br.com.fiap.core.domain.entities;

import br.com.fiap.core.domain.enums.ProdutoCategoriaEnum;

import java.math.BigDecimal;

public class Produto {

    Long id;
    String nome;
    String descricao;
    BigDecimal valor;
    ProdutoCategoriaEnum categoria;

    public Produto(Long id, String nome, String descricao, BigDecimal valor, ProdutoCategoriaEnum categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public void alterar(Produto produto) {
        this.nome = produto.nome;
        this.descricao = produto.descricao;
        this.valor = produto.valor;
        this.categoria = produto.categoria;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ProdutoCategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(ProdutoCategoriaEnum categoria) {
        this.categoria = categoria;
    }
}
