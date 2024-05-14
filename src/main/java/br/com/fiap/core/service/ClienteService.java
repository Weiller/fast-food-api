package br.com.fiap.core.service;

import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.exceptions.BusinessException;
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
    public Cliente salvar(Cliente cliente) {
        preValidate(cliente);
        return clienteServicePort.salvar(cliente);
    }

    private static void preValidate(Cliente cliente) {
        if(cliente.nome() == null) {
            throw new BusinessException("É necessário informar o nome do cliente");
        }

        if(cliente.email() == null) {
            throw new BusinessException("É necessário informar o email do cliente");
        }

        if(cliente.cpf() == null) {
            throw new BusinessException("É necessário informar o cpf do cliente");
        }
    }
}
