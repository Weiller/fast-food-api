package br.com.fiap.core.domain.entities;

import br.com.fiap.core.domain.enums.SituacaoPagamentoEnum;
import br.com.fiap.core.domain.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {

   private Long id;
   private Long clienteId;
   private BigDecimal valor;
   private SituacaoPagamentoEnum situacaoPagamento;
   private StatusPedidoEnum status;
   private LocalDateTime dataHoraPagamento;
   private  LocalDateTime dataHoraCriacao;
   private LocalDateTime dataHoraEntrega;

    public Pedido(Long id,
                  Long clienteId,
                  BigDecimal valor,
                  SituacaoPagamentoEnum situacaoPagamento,
                  StatusPedidoEnum status,
                  LocalDateTime dataHoraPagamento,
                  LocalDateTime dataHoraCriacao,
                  LocalDateTime dataHoraEntrega) {
        this.id = id;
        this.clienteId = clienteId;
        this.valor = valor;
        this.situacaoPagamento = situacaoPagamento;
        this.status = status;
        this.dataHoraPagamento = dataHoraPagamento;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public SituacaoPagamentoEnum getSituacaoPagamento() {
        return situacaoPagamento;
    }

    public void setSituacaoPagamento(SituacaoPagamentoEnum situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraPagamento() {
        return dataHoraPagamento;
    }

    public void setDataHoraPagamento(LocalDateTime dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraEntrega() {
        return dataHoraEntrega;
    }

    public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public static class Builder {
        private Long id;
        private BigDecimal valor;
        private Long clienteId;
        private SituacaoPagamentoEnum situacaoPagamento;
        private StatusPedidoEnum status;
        private LocalDateTime dataHoraPagamento;
        private  LocalDateTime dataHoraCriacao;
        private LocalDateTime dataHoraEntrega;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder clienteId(Long clienteId) {
            this.clienteId = clienteId;
            return this;
        }

        public Builder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public Builder situacaoPagamento(SituacaoPagamentoEnum situacaoPagamento) {
            this.situacaoPagamento = situacaoPagamento;
            return this;
        }

        public Builder status(StatusPedidoEnum status) {
            this.status = status;
            return this;
        }
        public Builder dataHoraPagamento(LocalDateTime dataHoraPagamento) {
            this.dataHoraPagamento = dataHoraPagamento;
            return this;
        }

        public Builder dataHoraCriacao(LocalDateTime dataHoraCriacao) {
            this.dataHoraCriacao = dataHoraCriacao;
            return this;
        }

        public Builder dataHoraEntrega(LocalDateTime dataHoraEntrega) {
            this.dataHoraEntrega = dataHoraEntrega;
            return this;
        }

        public Pedido build() {
            return new Pedido(id, clienteId, valor, situacaoPagamento, status, dataHoraPagamento, dataHoraCriacao,dataHoraEntrega);
        }
    }

}