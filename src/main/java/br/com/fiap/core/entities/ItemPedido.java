package br.com.fiap.core.entities;

public class ItemPedido {

    private Long id;
    private Long produtoId;
    private Long pedidoId;
    private Integer quantidade;

    public ItemPedido(Long id, Long produtoId, Long pedidoId, Integer quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.pedidoId = pedidoId;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public static class Builder {
        private Long id;
        private Long produtoId;
        private Long pedidoId;
        private Integer quantidade;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder produto(Long produtoId) {
            this.produtoId = produtoId;
            return this;
        }

        public Builder pedido(Long pedidoId) {
            this.pedidoId = pedidoId;
            return this;
        }

        public Builder quantidade(Integer quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public ItemPedido build() {
            return new ItemPedido(id, produtoId, pedidoId, quantidade);
        }
    }

}
