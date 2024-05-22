package br.com.fiap.adapter.repositories.pedido;

import br.com.fiap.core.domain.enums.SituacaoPagamentoEnum;
import br.com.fiap.core.domain.enums.StatusPedidoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long clienteId;
    private BigDecimal valor;
    private SituacaoPagamentoEnum situacaoPagamento;
    private StatusPedidoEnum status;
    private LocalDateTime dataHoraPagamento;
    private  LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraEntrega;
}
