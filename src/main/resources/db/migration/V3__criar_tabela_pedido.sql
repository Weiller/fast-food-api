CREATE SEQUENCE sq_pedido
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;


CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    valor DECIMAL(19, 2),
    situacao_pagamento VARCHAR(40) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_hora_pagamento TIMESTAMP,
    data_hora_criacao TIMESTAMP NOT NULL,
    data_hora_entrega TIMESTAMP,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);