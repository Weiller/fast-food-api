package br.com.fiap.adapter.repositories.produto;

import br.com.fiap.core.domain.enums.ProdutoCategoriaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;

import java.time.LocalDateTime;
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
@Table(name = "PRODUTO", schema = "FASTFOOD")
@SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "FASTFOOD.SQ_PRODUTO", allocationSize = 1, initialValue = 1)
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProdutoCategoriaEnum categoria;

    @Column(name = "DATA_INCLUSAO", nullable = false)
    private LocalDateTime dataInclusao = LocalDateTime.now();
}
