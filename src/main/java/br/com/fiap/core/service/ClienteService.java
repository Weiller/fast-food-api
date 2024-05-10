package br.com.fiap.core.service;

import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.ports.ClienteRepositoryPort;
import br.com.fiap.core.ports.ClienteServicePort;
import java.util.List;

public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort clienteServicePort;

    public ClienteService(ClienteRepositoryPort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    @Override
    public List<Cliente> getClientes(Long idCliente) {
        return clienteServicePort.getClientes(idCliente);
    }

    @Override
    public Cliente getClienteById(Long idCliente) {
        return null;
    }

    @Override
    public void salvar(Long idCliente) {
        clienteServicePort.salvar(new Cliente(1L));

        System.out.println("Salvando cliente");
    }
}
