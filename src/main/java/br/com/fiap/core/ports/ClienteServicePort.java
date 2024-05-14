package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Cliente;

public interface ClienteServicePort {

    Cliente getClienteByCpf(String cpf);

    Cliente salvar(Cliente cliente);
}
