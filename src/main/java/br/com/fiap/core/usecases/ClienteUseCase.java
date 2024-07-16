package br.com.fiap.core.usecases;

import br.com.fiap.core.commons.CpfValidator;
import br.com.fiap.core.entities.Cliente;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.gateways.ClienteRepositoryGateway;
import br.com.fiap.core.gateways.ClienteServiceGateway;
import java.util.Objects;

public class ClienteUseCase implements ClienteServiceGateway {

    private final ClienteRepositoryGateway clienteServiceGateway;

    public ClienteUseCase(ClienteRepositoryGateway clienteServiceGateway) {
        this.clienteServiceGateway = clienteServiceGateway;
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        return clienteServiceGateway.getClienteByCpf(cpf).orElseThrow(() -> new BusinessException("Cliente não encontrado"));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        preValidar(cliente);
        return clienteServiceGateway.salvar(cliente);
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

        clienteServiceGateway.getClienteByCpf(cliente.cpf()).ifPresent(c -> {
            throw new BusinessException("Cliente já cadastrado");
        });
    }
}
