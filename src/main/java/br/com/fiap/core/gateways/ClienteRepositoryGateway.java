package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.Cliente;
import java.util.Optional;

public interface ClienteRepositoryGateway {

    Cliente salvar(Cliente cliente);

    Optional<Cliente> getClienteByCpf(String cpf);
}
