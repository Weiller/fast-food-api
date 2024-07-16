package br.com.fiap.infrastructure.repositories.pedido;

import br.com.fiap.infrastructure.repositories.produto.ProdutoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM_PEDIDO", schema = "FASTFOOD")
@SequenceGenerator(name = "SQ_ITEM_PEDIDO", sequenceName = "FASTFOOD.SQ_ITEM_PEDIDO", allocationSize = 1, initialValue = 1)
public class ItemPedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITEM_PRODUTO")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    @Column(name = "quantidade")
    private Integer quantidade;

}
