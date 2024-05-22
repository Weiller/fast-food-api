CREATE SEQUENCE sq_item_pedido
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE item_pedido
(
    id         BIGINT PRIMARY KEY,
    id_produto BIGINT  NOT NULL,
    id_pedido  BIGINT  NOT NULL,
    quantidade INTEGER NOT NULL
);

ALTER TABLE item_pedido ADD CONSTRAINT fk_item_produto_produto FOREIGN KEY (id_produto) REFERENCES fastfood.produto (id);
ALTER TABLE item_pedido ADD CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES fastfood.pedido (id);