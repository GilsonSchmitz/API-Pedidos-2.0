CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    id_cliente VARCHAR(255) NOT NULL,
    id_endereco VARCHAR(255) NOT NULL,
    data_pedido TIMESTAMP NOT NULL,
    cupom_desconto VARCHAR(255),
    valor_frete  NUMERIC(10, 2),
    valor_total  NUMERIC(10, 2) NOT NULL,
    forma_pgto VARCHAR(255),
    codigo_rastreio VARCHAR(255)
);

CREATE TABLE itemPedido (
id SERIAL PRIMARY KEY,
id_pedido INTEGER,
id_produto VARCHAR(255),
quantidade VARCHAR(255),
valor  NUMERIC(10, 2),
opcao_garantia BOOLEAN
);

