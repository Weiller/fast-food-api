package br.com.fiap.infrastructure.repositories.pedido;

import br.com.fiap.core.enums.SituacaoPagamentoEnum;
import br.com.fiap.core.enums.StatusPedidoEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "PEDIDO", schema = "FASTFOOD")
@SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "FASTFOOD.SQ_PEDIDO", allocationSize = 1, initialValue = 1)
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "situacao_pagamento")
    @Enumerated(EnumType.STRING)
    private SituacaoPagamentoEnum situacaoPagamento;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Column(name = "data_hora_pagamento")
    private LocalDateTime dataHoraPagamento;

    @Column(name = "data_hora_criacao")
    private LocalDateTime dataHoraCriacao;

    @Column(name = "data_hora_entrega")
    private LocalDateTime dataHoraEntrega;

    @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemPedidoEntity> itens;
}
