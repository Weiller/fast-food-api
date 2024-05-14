package br.com.fiap.core.service;

import br.com.fiap.core.common.CpfValidator;
import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.ports.ClienteRepositoryPort;
import br.com.fiap.core.ports.ClienteServicePort;
import java.util.Objects;

public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort clienteServicePort;

    public ClienteService(ClienteRepositoryPort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        return clienteServicePort.getClienteByCpf(cpf).orElseThrow(() -> new BusinessException("Cliente não encontrado"));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        preValidar(cliente);
        return clienteServicePort.salvar(cliente);
    }

    private void preValidar(Cliente cliente) {
        if (Objects.isNull(cliente.nome())) {
            throw new BusinessException("É necessário informar o nome do cliente");
        }

        if (Objects.isNull(cliente.email())) {
            throw new BusinessException("É necessário informar o email do cliente");
        }

        if (Objects.isNull(cliente.cpf())) {
            throw new BusinessException("É necessário informar o cpf do cliente");
        }

        if(!CpfValidator.isValid(cliente.cpf())) {
            throw new BusinessException("Cpf inválido");
        }

        clienteServicePort.getClienteByCpf(cliente.cpf()).ifPresent(c -> {
            throw new BusinessException("Cliente já cadastrado");
        });
    }
}
