package br.com.fiap.adapter.controller.converter;

import br.com.fiap.adapter.controller.command.CriarClienteCommand;
import br.com.fiap.adapter.repositories.cliente.ClienteEntity;
import br.com.fiap.core.domain.entities.Cliente;

public class ClienteConverter {

    public static Cliente converterCommandToCliente(CriarClienteCommand command) {
        return new Cliente(null, command.getNome(), command.getEmail(), command.getCpf());
    }

    public static ClienteEntity converterClienteToEntity(Cliente cliente) {
        return new ClienteEntity(cliente.nome(), cliente.cpf(), cliente.email());
    }

}
