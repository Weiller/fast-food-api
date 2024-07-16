package br.com.fiap.infrastructure.repositories.cliente;

import br.com.fiap.infrastructure.controllers.converters.ClienteConverter;
import br.com.fiap.core.entities.Cliente;
import br.com.fiap.core.gateways.ClienteRepositoryGateway;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepositoryGatewayAdapter implements ClienteRepositoryGateway {

    private final ClienteRepository clienteRepository;

    public ClienteRepositoryGatewayAdapter(ClienteRepository clienteRepository) {
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
