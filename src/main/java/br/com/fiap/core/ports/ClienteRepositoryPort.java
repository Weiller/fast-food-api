package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Cliente;
import java.util.List;

public interface ClienteRepositoryPort {

    void salvar(Cliente cliente);

    List<Cliente> getClientes(Long idCliente);
}