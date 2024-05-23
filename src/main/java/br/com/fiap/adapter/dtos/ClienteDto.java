package br.com.fiap.adapter.dtos;

import lombok.Builder;

@Builder
public record ClienteDto(Long id,
                         String nome,
                         String email,
                         String cpf) {
}
