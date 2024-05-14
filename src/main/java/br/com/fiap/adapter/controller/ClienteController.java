package br.com.fiap.adapter.controller;

import br.com.fiap.adapter.controller.command.CriarClienteCommand;
import br.com.fiap.adapter.controller.converter.ClienteConverter;
import br.com.fiap.core.domain.entities.Cliente;
import br.com.fiap.core.ports.ClienteServicePort;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteServicePort clienteServicePort;

    public ClienteController(ClienteServicePort clienteServicePort) {
        this.clienteServicePort = clienteServicePort;
    }

    @GetMapping("/{cpf}")
    public Cliente getClienteByCpf(@PathVariable("cpf") String cpf) {
        return clienteServicePort.getClienteByCpf(cpf);
    }

    @PostMapping
    public Cliente salvar(@RequestBody CriarClienteCommand command) {
        return clienteServicePort.salvar(ClienteConverter.converterCommandToCliente(command));
    }

}
