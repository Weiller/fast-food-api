package br.com.fiap.core.ports;

import br.com.fiap.core.domain.entities.Cliente;
import java.util.List;

public interface ClienteServicePort {

    List<Cliente> getClientes(Long idCliente);

    Cliente getClienteById(Long idCliente);

    void salvar(Long idCliente);

}
