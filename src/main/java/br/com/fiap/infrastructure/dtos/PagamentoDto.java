package br.com.fiap.infrastructure.dtos;

import lombok.Builder;

@Builder
public record PagamentoDto(Long id,
                           String situacaoPagamento) {
}