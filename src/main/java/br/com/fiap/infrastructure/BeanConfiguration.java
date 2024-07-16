package br.com.fiap.infrastructure;

import br.com.fiap.core.gateways.*;
import br.com.fiap.core.usecases.ClienteUseCase;
import br.com.fiap.core.usecases.PedidoUseCase;
import br.com.fiap.core.usecases.ProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteServiceGateway clienteServiceImpl(ClienteRepositoryGateway todoARepositoryPort) {
        return new ClienteUseCase(todoARepositoryPort);
    }

    @Bean
    public ProdutoServiceGateway produtoServiceImpl(ProdutoRepositoryGateway todoARepositoryPort) {
        return new ProdutoUseCase(todoARepositoryPort);
    }

    @Bean
    public PedidoServiceGateway pedidoServiceImpl(PedidoRepositoryGateway pedidoRepositoryGateway, ProdutoRepositoryGateway produtoRepositoryGateway,
                                                  PagamentoServicoExternoGateway pagamentoServicoExternoGateway, NotificacaoSonoraGateway notificacaoSonoraGateway) {
        return new PedidoUseCase(pedidoRepositoryGateway, produtoRepositoryGateway, pagamentoServicoExternoGateway, notificacaoSonoraGateway);
    }
}
