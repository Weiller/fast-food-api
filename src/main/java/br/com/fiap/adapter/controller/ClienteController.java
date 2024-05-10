package br.com.fiap.adapter.controller;

import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.ports.ClienteServicePort;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    public ClienteController(ClienteServicePort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    @GetMapping("/{id}")
    public List<Cliente> getClientes(@PathVariable("id") Long id) {
        return clienteServicePort.getClientes(id);
    }

}
