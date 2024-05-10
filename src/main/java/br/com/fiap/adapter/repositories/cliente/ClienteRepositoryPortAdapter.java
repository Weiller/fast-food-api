package br.com.fiap.adapter.repositories.cliente;

import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.ports.ClienteRepositoryPort;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepositoryPortAdapter implements ClienteRepositoryPort {

    private final ClienteRepository clienteRepository;

    public ClienteRepositoryPortAdapter(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void salvar(Cliente cliente) {
        clienteRepository.save(new ClienteEntity());
    }

    @Override
    public List<Cliente> getClientes(Long idCliente) {
        ClienteEntity clienteEntity = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return List.of();
    }

}
