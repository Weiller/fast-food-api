package br.com.fiap.core.domain.entities;

public record Cliente(Long id,
                      String nome,
                      String email,
                      String cpf) {
    public Cliente(Long id) {
        this(id, null, null, null);
    }
}
