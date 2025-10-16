CREATE TABLE usuario (
  idusuario SERIAL PRIMARY KEY,
  nome VARCHAR(255),
  email VARCHAR(255),
  senha VARCHAR(255),
  data_de_cadastro TIMESTAMP
);