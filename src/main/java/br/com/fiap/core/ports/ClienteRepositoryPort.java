package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Cliente;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente salvar(Cliente cliente);

    Optional<Cliente> getClienteByCpf(String cpf);
}
