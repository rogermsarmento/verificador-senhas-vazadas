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

Principais características:

- entrada de dados pelo terminal;
- utilização de `Scanner`;
- armazenamento da senha em uma variável `String`.

---

### V0.1.0 — Geração do hash SHA-1

O programa passou a gerar localmente o hash SHA-1 da senha.

Principais características:

- utilização de `MessageDigest`;
- conversão da senha para bytes utilizando UTF-8;
- geração do SHA-1;
- conversão dos bytes para representação hexadecimal.

> SHA-1 não é recomendado para armazenamento moderno de senhas.
> Neste projeto, ele é utilizado porque faz parte do protocolo de consulta
> empregado pelo Pwned Passwords.

---

### V0.2.0 — Preparação para k-anonymity

O hash SHA-1 passou a ser dividido em:

- **prefixo:** primeiros 5 caracteres;
- **sufixo:** 35 caracteres restantes.

Somente o prefixo é necessário para realizar posteriormente a consulta
ao serviço Pwned Passwords.

---

### V0.3.0 — Consulta à API Pwned Passwords

O programa passou a realizar uma requisição HTTP ao serviço Pwned Passwords.

Principais características:

- construção da URL utilizando o prefixo do hash;
- utilização de `URI`;
- utilização de `HttpClient`;
- criação de uma requisição `GET` com `HttpRequest`;
- recebimento da resposta com `HttpResponse`;
- leitura do código de status HTTP;
- recebimento da resposta bruta da API.

Nesta etapa, a resposta ainda não era interpretada pelo programa.

---

# Versão Atual

## V0.4.0 — Processamento da resposta da API

Nesta versão, o programa passa a interpretar a resposta recebida do
Pwned Passwords.

A API retorna diversas linhas no formato:

```text
SUFIXO:QUANTIDADE
```

O programa percorre essas linhas e compara os sufixos retornados com
o sufixo SHA-1 calculado localmente.

### Fluxo da versão

```text
Senha
  │
  ▼
SHA-1
  │
  ├──────────────┐
  ▼              ▼
Prefixo        Sufixo
  │              │
  ▼              │
API              │
  │              │
  ▼              │
Resposta         │
  │              │
  ▼              │
Separar linhas   │
  │              │
  ▼              │
SUFIXO:QUANTIDADE
  │              │
  ▼              │
Comparar ◄───────┘
  │
  ├── encontrado
  │      │
  │      ▼
  │   quantidade de ocorrências
  │
  └── não encontrado
```

### Processamento da resposta

O corpo da resposta HTTP é inicialmente armazenado em uma `String`:

```java
String corpoResposta = response.body();
```

Em seguida, a resposta é dividida em linhas:

```java
String[] linhas = corpoResposta.split("\\R");
```

Cada linha é novamente dividida utilizando `:`:

```java
String[] partes = linha.split(":");
```

Obtendo:

```text
partes[0] → sufixo
partes[1] → quantidade
```

O sufixo retornado pela API é comparado com o sufixo calculado
localmente:

```java
if (sufixo.equals(sufixoRetornado))
```

Quando ocorre uma correspondência, o programa armazena a quantidade
de ocorrências e encerra a busca.

### Conceitos abordados

- processamento de `String`;
- método `split()`;
- arrays de `String`;
- `for` aprimorado;
- comparação de `String` com `equals()`;
- variáveis `boolean`;
- estruturas `if/else`;
- operador lógico `!`;
- comando `break`;
- conversão de `String` para `int` com `Integer.parseInt()`;
- processamento de respostas de uma API.

### Resultado

Quando a senha é encontrada:

```text
ATENÇÃO: senha encontrada em vazamentos conhecidos.
Quantidade de ocorrências: XXXXX
```

Quando não é encontrada:

```text
Senha não encontrada nos vazamentos consultados.
```

> Não encontrar uma senha na base consultada não significa que ela seja
> necessariamente segura. O resultado indica apenas que ela não foi
> localizada nos dados consultados pelo programa.

---

# Próxima Versão

## V1.0.0 — Verificador completo no console

A próxima versão consolidará o primeiro grande marco do projeto:
um verificador de senhas funcional executado integralmente no terminal.

A V1.0.0 deverá organizar e finalizar o fluxo:

```text
Entrada da senha
      ↓
Geração do SHA-1
      ↓
Preparação para k-anonymity
      ↓
Consulta HTTP
      ↓
Processamento da resposta
      ↓
Resultado para o usuário
```

Ainda manteremos a implementação procedural para consolidar o
funcionamento completo antes da etapa de modularização.

---

## Tecnologias

- Java
- NetBeans
- API Pwned Passwords
- Git
- GitHub

---

## Licença

Este projeto é distribuído sob a licença MIT.