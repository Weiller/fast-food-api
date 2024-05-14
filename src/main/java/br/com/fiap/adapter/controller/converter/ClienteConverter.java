package br.com.fiap.adapter.controller.converter;

import br.com.fiap.adapter.controller.command.CriarClienteCommand;
import br.com.fiap.adapter.repositories.cliente.ClienteEntity;
import br.com.fiap.core.domain.entities.Cliente;
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

}
