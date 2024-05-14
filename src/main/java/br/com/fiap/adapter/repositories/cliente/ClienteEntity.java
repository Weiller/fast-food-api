package br.com.fiap.adapter.repositories.cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "CLIENTE", schema = "FASTFOOD")
@SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "FASTFOOD.SQ_CLIENTE", allocationSize = 1, initialValue = 1)
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "DATA_INCLUSAO", nullable = false)
    private LocalDateTime dataInclusao = LocalDateTime.now();
}
