package br.com.fiap.adapter.repositories.cliente;

import br.com.fiap.adapter.controller.converter.ClienteConverter;
import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.ports.ClienteRepositoryPort;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepositoryPortAdapter implements ClienteRepositoryPort {

    private final ClienteRepository clienteRepository;

    public ClienteRepositoryPortAdapter(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepository.save(ClienteConverter.converterClienteToEntity(cliente));

        return ClienteConverter.converterEntityToCliente(clienteEntity);
    }

    @Override
    public Optional<Cliente> getClienteByCpf(String cpf) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findByCpf(cpf);

        return clienteEntity.map(ClienteConverter::converterEntityToCliente);

    }

}
