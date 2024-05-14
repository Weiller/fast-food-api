package br.com.fiap.core.domain.entities;

public record Cliente(Long id,
                      String nome,
                      String email,
                      String cpf) {

    public static class Builder {
        private Long id;
        private String nome;
        private String email;
        private String cpf;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Cliente build() {
            return new Cliente(id, nome, email, cpf);
        }
    }
}
