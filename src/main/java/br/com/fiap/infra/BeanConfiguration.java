package br.com.fiap.infra;

import br.com.fiap.core.ports.ClienteRepositoryPort;
import br.com.fiap.core.ports.ClienteServicePort;
import br.com.fiap.core.service.ClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteServicePort clienteServiceImpl(ClienteRepositoryPort todoARepositoryPort) {
        return new ClienteService(todoARepositoryPort);
    }
}
