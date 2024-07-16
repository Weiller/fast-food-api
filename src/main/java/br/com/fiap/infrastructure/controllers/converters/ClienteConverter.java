package br.com.fiap.infrastructure.controllers.converters;

import br.com.fiap.infrastructure.controllers.commands.CriarClienteCommand;
import br.com.fiap.infrastructure.dtos.ClienteDto;
import br.com.fiap.infrastructure.repositories.cliente.ClienteEntity;
import br.com.fiap.core.entities.Cliente;
import java.time.LocalDateTime;

public class ClienteConverter {

    public static Cliente converterCommandToCliente(CriarClienteCommand command) {
        return new Cliente.Builder()
                .nome(command.getNome())
                .cpf(command.getCpf())
                .email(command.getEmail())
                .build();
    }

    public static ClienteEntity converterClienteToEntity(Cliente cliente) {
        return ClienteEntity.builder()
                .nome(cliente.nome())
                .cpf(cliente.cpf())
                .email(cliente.email())
                .dataInclusao(LocalDateTime.now())
                .build();
    }

    public static Cliente converterEntityToCliente(ClienteEntity cliente) {
        return new Cliente.Builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .build();
    }

    public static ClienteDto converterClienteToDto(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.id())
                .nome(cliente.nome())
                .cpf(cliente.cpf())
                .email(cliente.email())
                .build();
    }

}
