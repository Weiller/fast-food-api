package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.Cliente;

public interface ClienteServiceGateway {

    Cliente getClienteByCpf(String cpf);

    Cliente salvar(Cliente cliente);
}
