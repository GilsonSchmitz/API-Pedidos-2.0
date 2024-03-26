CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(20),
    dados_do_cartao VARCHAR (30),
    email VARCHAR(255),
    nome VARCHAR(100)
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    valor NUMERIC(10, 2)
);

CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    bairro VARCHAR(100),
    cep VARCHAR(8),
    cidade VARCHAR(100),
    complemento VARCHAR(255),
    numero INTEGER,
    rua VARCHAR(255)
);