package br.com.fiap.infrastructure.controllers.presenters;

import br.com.fiap.core.entities.Cliente;
import br.com.fiap.infrastructure.dtos.ClienteDto;

public class ClientePresenter {


    public static ClienteDto converterClienteToDto(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.id())
                .nome(cliente.nome())
                .cpf(cliente.cpf())
                .email(cliente.email())
                .build();
    }

}
