# Sistema de Pedidos

Este projeto é um sistema de pedidos, projetado para atender às necessidades de restaurantes de fast food. Ele permite que os usuários façam pedidos de alimentos e bebidas, acompanhem o status de seus pedidos e realizem o pagamento online.

## Docker Setup - Ambiente local

### Requisitos para execução local

- **Docker**: Para instalar o Docker, siga as instruções no [site oficial](https://docs.docker.com/get-docker/).
- **Docker Compose**: Para instalar o Docker Compose, siga as instruções no [site oficial](https://docs.docker.com/compose/install/).

As imagens utilizadas para a construção e execução da aplicação foram escolhidas com base na compatibilidade com processadores AMD e ARM.

### Execução

Para executar o projeto deve executar o comando 

```bash
docker compose up
```

### Definição do docker compose

#### Etapa de Construção

1. Utiliza a imagem `maven:3.8-openjdk-17` como base.
2. Define o diretório de trabalho para `/app`.
3. Copia todos os arquivos do diretório atual para `/app`.
4. Executa o comando `mvn clean install -DskipTests` para compilar a aplicação, pulando os testes.

#### Etapa de Execução

1. Utiliza a imagem `openjdk:17` como base.
2. Define o maintainer do projeto como "Grupo 47".
3. Define o diretório de trabalho para `/app`.
4. Copia o artefato gerado na etapa de construção para o diretório de trabalho.
5. Define o comando de entrada para executar o jar da aplicação.
6. Expõe a porta `8080` para acesso externo.


## Funcionalidades

O sistema é composto por várias funcionalidades, incluindo:

- Criação de pedidos: Os usuários podem criar novos pedidos, adicionando itens de um menu pré-definido.
- Acompanhamento de pedidos: Os usuários podem acompanhar o status de seus pedidos em tempo real.
- Pagamento online: Os usuários podem realizar o pagamento de seus pedidos online, através de várias opções de pagamento.

## Controladores

O sistema é composto por três controladores principais:

- `ClienteController`: Responsável por lidar com as solicitações HTTP relacionadas ao Cliente.
- `ProdutoController`: Responsável por lidar com as solicitações HTTP relacionadas ao Produto.
- `PedidoController`: Responsável por lidar com as solicitações HTTP relacionadas ao Pedido.

Cada controlador tem vários métodos que correspondem a diferentes endpoints da API. Para mais detalhes sobre cada controlador e seus métodos, consulte a documentação específica de cada controlador logo abaixo.


# ClienteController

A classe `ClienteController` é responsável por lidar com as solicitações HTTP relacionadas ao Cliente. A API está disponível no caminho `/api/clientes`.

## Métodos

### getClienteByCpf

Este método é responsável por obter um cliente pelo CPF. O caminho completo da API é `/api/clientes/{cpf}`.

**Parâmetros:**

- `cpf`: O CPF do cliente a ser obtido.

**Respostas:**

- `200`: Cliente encontrado. Retorna um objeto `ClienteDto`.
- `400`: Parâmetros inválidos.
- `412`: Cliente não encontrado.

### salvar

Este método é responsável por salvar um novo cliente. O caminho completo da API é `/api/clientes`.

**Parâmetros:**

- `CriarClienteCommand`: O objeto contendo as informações do cliente a ser salvo.

**Respostas:**

- `200`: Cliente cadastrado com sucesso. Retorna um objeto `ClienteDto`.
- `400`: Parâmetros inválidos.
- `412`: Erro de validação no cadastro do cliente.

# ProdutoController

A classe `ProdutoController` é responsável por lidar com as solicitações HTTP relacionadas ao Produto. A API está disponível no caminho `/api/produtos`.

## Métodos

### salvar

Este método é responsável por salvar um novo produto. O caminho completo da API é `/api/produtos`.

**Parâmetros:**

- `CriarProdutoCommand`: O objeto contendo as informações do produto a ser salvo.

**Respostas:**

- `200`: Produto cadastrado com sucesso. Retorna um objeto `ProdutoDto`.
- `400`: Parâmetros inválidos.
- `412`: Erro de validação no cadastro do produto.

### alterar

Este método é responsável por alterar um produto. O caminho completo da API é `/api/produtos`.

**Parâmetros:**

- `AlterarProdutoCommand`: O objeto contendo as informações do produto a ser alterado.

**Respostas:**

- `200`: Produto alterado com sucesso. Retorna um objeto `ProdutoDto`.
- `400`: Parâmetros inválidos.
- `412`: Erro de validação na alteração do produto.

### excluir

Este método é responsável por excluir um produto pelo id. O caminho completo da API é `/api/produtos/{id}`.

**Parâmetros:**

- `id`: O id do produto a ser excluído.

**Respostas:**

- `200`: Produto excluído com sucesso. Retorna um objeto `ProdutoDto`.
- `400`: Parâmetros inválidos.
- `412`: Erro de negócio na exclusão.

### getProdutosPorCategoria

Este método é responsável por retornar uma lista de produtos pela categoria. O caminho completo da API é `/api/produtos/{categoria}`.

**Parâmetros:**

- `categoria`: A categoria dos produtos a serem obtidos.

**Respostas:**

- `200`: Retornou uma lista de produtos. Retorna uma lista de objetos `ProdutoDto`.
- `400`: Parâmetros inválidos.
- `412`: Categoria não encontrada.

# PedidoController

A classe `PedidoController` é responsável por lidar com as solicitações HTTP relacionadas ao Pedido. A API está disponível no caminho `/api/pedidos`.

## Métodos

### salvar

Este método é responsável por criar um novo pedido. O caminho completo da API é `/api/pedidos`.

**Parâmetros:**

- `CriarPedidoCommand`: O objeto contendo as informações do pedido a ser criado.

**Respostas:**

- `200`: Pedido criado com sucesso. Retorna um objeto `PedidoDto`.
- `400`: Parâmetros inválidos.
- `412`: Erro de validação no cadastro do pedido.

### obterPedidosEmAndamento

Este método é responsável por obter pedidos em andamento. O caminho completo da API é `/api/pedidos`.

**Respostas:**

- `200`: Listar todos os pedidos. Retorna uma lista de objetos `PedidoDto`.

### adicionarItem

Este método é responsável por adicionar um item no pedido. O caminho completo da API é `/api/pedidos/{pedidoId}/adicionar`.

**Parâmetros:**

- `pedidoId`: O id do pedido.
- `AdicionarItemCommand`: O objeto contendo as informações do item a ser adicionado.

**Respostas:**

- `200`: Item adicionado com sucesso. Retorna um objeto `PedidoDto`.
- `400`: Parâmetros inválidos.
- `412`: Erro de validação na adição do pedido.

### removerItem

Este método é responsável por remover um item do pedido. O caminho completo da API é `/api/pedidos/{pedidoId}/remover`.

**Parâmetros:**

- `pedidoId`: O id do pedido.
- `RemoverItemCommand`: O objeto contendo as informações do item a ser removido.

**Respostas:**

- `200`: Item removido com sucesso. Retorna um objeto `PedidoDto`.
- `400`: Parâmetros inválidos.
- `412`: Erro de validação no cadastro do pedido.

### cancelarPedido

Este método é responsável por cancelar um pedido. O caminho completo da API é `/api/pedidos/{id}/cancelar`.

**Parâmetros:**

- `id`: O id do pedido.

**Respostas:**

- `200`: Pedido cancelado com sucesso. Retorna um objeto `PedidoDto`.
- `400`: Parâmetros inválidos.
- `412`: Pedido não existe.

### realizarPagamento

Este método é responsável por realizar o pagamento de um pedido. O caminho completo da API é `/api/pedidos/{id}/pagamento`.

**Parâmetros:**

- `id`: O id do pedido.

**Respostas:**

- `200`: Pagamento realizado com sucesso. Retorna um objeto `PedidoDto`.
- `400`: Parâmetros inválidos.
- `412`: Pagamento não efetuado.

### efetuarEntrega

Este método é responsável por efetuar a entrega de um pedido. O caminho completo da API é `/api/pedidos/{id}/entrega`.

**Parâmetros:**

- `id`: O id do pedido.

**Respostas:**

- `200`: Entrega realizada com sucesso. Retorna um objeto `PedidoDto`.
- `400`: Parâmetros inválidos.
- `412`: Entrega não realizada.

### atualizarPedidoPronto

Este método é responsável por atualizar o status de um pedido para pronto. O caminho completo da API é `/api/pedidos/{id}/atualizar-pedido-pronto`.

**Parâmetros:**

- `id`: O id do pedido.

**Respostas:**

- `200`: Pedido atualizado para pronto. Retorna um objeto `PedidoDto`.
- `400`: Parâmetros inválidos.
- `412`: Pedido não atualizado.