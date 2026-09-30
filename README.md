# Verificador de Senhas

Projeto didático desenvolvido em Java para demonstrar, de forma incremental,
a construção de uma aplicação capaz de verificar se uma senha apareceu em
vazamentos de dados conhecidos.

O projeto evolui por versões, introduzindo gradualmente conceitos de
programação, segurança, comunicação com APIs, orientação a objetos e
interface gráfica com Java Swing.

---

## Histórico de Versões

### V0.0.0 — Entrada básica da senha

Primeira versão do projeto.

O programa solicita uma senha pelo terminal e armazena a entrada em uma
variável.

Nesta versão, a senha era exibida novamente apenas para fins didáticos,
permitindo demonstrar o funcionamento da entrada de dados.

---

### V0.1.0 — Geração do hash SHA-1

Introdução ao conceito de função hash.

Nesta versão, o programa:

- converte a senha para bytes utilizando UTF-8;
- gera o hash SHA-1 utilizando `MessageDigest`;
- converte os bytes resultantes para hexadecimal;
- produz um hash SHA-1 de 40 caracteres.

Exemplo:

`abc`

gera:

`A9993E364706816ABA3E25717850C26C9CD0D89D`

> **Observação:** SHA-1 não é recomendado para armazenamento moderno de
> senhas. Ele é utilizado neste projeto porque faz parte do protocolo de
> consulta da API Pwned Passwords.

---

## Versão Atual

### V0.2.0 — Preparação para k-anonymity

Nesta versão, o hash SHA-1 é dividido em duas partes para preparar uma
consulta segura à API Pwned Passwords.

O programa:

- gera o SHA-1 da senha;
- converte o resultado para hexadecimal;
- separa os 5 primeiros caracteres do hash como prefixo;
- mantém os 35 caracteres restantes como sufixo.

### Exemplo

Para a entrada:

`abc`

o SHA-1 é:

`A9993E364706816ABA3E25717850C26C9CD0D89D`

O programa realiza a divisão:

**Prefixo:**

`A9993`

**Sufixo:**

`E364706816ABA3E25717850C26C9CD0D89D`

A ideia é que somente o prefixo seja enviado futuramente para a API.

O sufixo permanecerá no computador do usuário.

### Conceitos abordados

- k-anonymity;
- `String`;
- `StringBuilder`;
- `toString()`;
- `substring()`;
- índices de uma `String`;
- separação entre dados locais e dados enviados a um serviço externo.

---

## Próxima Versão

### V0.3.0 — Consulta à API Pwned Passwords

Na próxima versão, o programa realizará sua primeira comunicação com um
serviço externo.

O prefixo de 5 caracteres será utilizado para realizar uma requisição HTTP
à API Pwned Passwords.

O objetivo inicial será apenas:

1. construir a URL da consulta;
2. enviar o prefixo;
3. realizar uma requisição HTTP;
4. receber a resposta da API;
5. exibir a resposta no terminal.

A análise dos dados retornados será realizada em uma versão posterior.

---

## Tecnologias

- Java
- NetBeans
- Git
- GitHub

---

## Licença

Este projeto é distribuído sob a licença MIT.