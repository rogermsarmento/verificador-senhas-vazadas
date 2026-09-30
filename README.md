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

O programa:

- executa no terminal;
- solicita uma senha ao usuário;
- armazena a entrada em uma variável.

Nesta primeira versão, a senha era exibida novamente apenas para fins
didáticos, permitindo visualizar o funcionamento da entrada de dados.

---

### V0.1.0 — Geração do hash SHA-1

Nesta versão, o programa passou a:

- converter a senha para bytes utilizando UTF-8;
- gerar o hash SHA-1 utilizando `MessageDigest`;
- converter o resultado para representação hexadecimal;
- produzir um hash SHA-1 de 40 caracteres.

Exemplo:

`abc`

gera:

`A9993E364706816ABA3E25717850C26C9CD0D89D`

> SHA-1 não é recomendado para armazenamento moderno de senhas.
> Neste projeto, ele é utilizado porque faz parte do protocolo de consulta
> da API Pwned Passwords.

---

### V0.2.0 — Preparação para k-anonymity

Nesta versão, o hash SHA-1 passou a ser dividido em duas partes:

- **Prefixo:** primeiros 5 caracteres;
- **Sufixo:** 35 caracteres restantes.

Exemplo:

`A9993E364706816ABA3E25717850C26C9CD0D89D`

é dividido em:

- Prefixo: `A9993`
- Sufixo: `E364706816ABA3E25717850C26C9CD0D89D`

Essa divisão prepara o programa para consultar a API sem enviar a senha
ou o hash SHA-1 completo.

---

## Versão Atual

### V0.3.0 — Consulta à API Pwned Passwords

Nesta versão, o programa passa a realizar sua primeira comunicação com
um serviço externo.

Após gerar o hash SHA-1 e separar o prefixo e o sufixo, o programa utiliza
o prefixo de 5 caracteres para realizar uma requisição HTTP à API
Pwned Passwords.

Fluxo atual:

Senha  
↓  
SHA-1  
↓  
Prefixo + Sufixo  
↓  
Envio do prefixo  
↓  
API Pwned Passwords  
↓  
Resposta HTTP  
↓  
Exibição da resposta bruta

A consulta utiliza um endereço no seguinte formato:

`https://api.pwnedpasswords.com/range/PREFIXO`

Somente o prefixo de 5 caracteres é utilizado na consulta.

A API retorna diversas linhas no formato:

`SUFIXO:QUANTIDADE`

Exemplo conceitual:

```text
0018A45C4D1DEF81644B54AB7F969B88D65:3
002D8E9D51B740A5B7C1B57A8286A1747F0:12
003A5C4D983C14E41A98D94C3C8E712FB61:7