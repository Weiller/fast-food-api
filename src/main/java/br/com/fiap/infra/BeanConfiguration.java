package br.com.fiap.infra;

import br.com.fiap.core.ports.ClienteRepositoryPort;
import br.com.fiap.core.ports.ClienteServicePort;
import br.com.fiap.core.ports.ProdutoRepositoryPort;
import br.com.fiap.core.ports.ProdutoServicePort;
import br.com.fiap.core.service.ClienteService;
import br.com.fiap.core.service.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteServicePort clienteServiceImpl(ClienteRepositoryPort todoARepositoryPort) {
        return new ClienteService(todoARepositoryPort);
    }

    @Bean
    public ProdutoServicePort produtoServiceImpl(ProdutoRepositoryPort todoARepositoryPort) {
        return new ProdutoService(todoARepositoryPort);
    }
}
