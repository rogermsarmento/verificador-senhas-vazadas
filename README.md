# 🔐 Verificador de Senhas Vazadas

Aplicação desenvolvida em **Java** para verificar se uma senha aparece em bases de dados de credenciais conhecidas por terem sido expostas em vazamentos.

O projeto utiliza a API **Pwned Passwords**, do serviço Have I Been Pwned, realizando as consultas por meio do modelo de **k-anonymity**, sem enviar a senha ou seu hash completo para o serviço externo.

---

## 🎯 Objetivo do Projeto

O Verificador de Senhas Vazadas permite consultar se uma determinada senha já apareceu em vazamentos conhecidos.

A aplicação realiza todo o processamento inicial da senha localmente e envia para a API apenas uma pequena parte do hash gerado, reduzindo a exposição das informações durante a consulta.

Entre as principais funcionalidades estão:

- leitura e validação da senha;
- geração local do hash SHA-1;
- consulta à API Pwned Passwords utilizando k-anonymity;
- identificação da quantidade de ocorrências da senha;
- tratamento de respostas HTTP e falhas de comunicação;
- separação entre interface, regras da aplicação e serviços externos.

---

## ⚙️ Funcionamento

A senha informada pelo usuário é convertida localmente para um hash **SHA-1**, representado por 40 caracteres hexadecimais.

O hash é dividido em duas partes:

```text
SHA-1
│
├── Prefixo → primeiros 5 caracteres
│
└── Sufixo → 35 caracteres restantes
```

Somente o prefixo é enviado para a API Pwned Passwords.

A API retorna os sufixos correspondentes aos hashes que possuem aquele mesmo prefixo, acompanhados da quantidade de ocorrências registradas.

A comparação entre o sufixo da senha informada e os valores retornados pela API é realizada localmente pela aplicação.

---

## 🔄 Fluxo da Aplicação

```text
Senha informada pelo usuário
            │
            ▼
     Validação da entrada
            │
            ▼
      Geração do SHA-1
            │
            ▼
   Separação do hash
      /           \
 prefixo         sufixo
    │               │
    ▼               │
API Pwned Passwords │
    │               │
    ▼               │
Lista de sufixos    │
    │               │
    └───────┬───────┘
            ▼
     Comparação local
            │
            ▼
 Resultado da verificação
```

---

## 📋 Requisitos

Para executar o projeto, é necessário:

- **Java JDK 17** ou superior;
- **Apache NetBeans** ou outra IDE compatível com projetos Java;
- **Git**, para clonagem e gerenciamento do repositório;
- conexão com a Internet para acesso à API Pwned Passwords.

Não é necessário instalar bibliotecas externas para realizar as requisições HTTP, pois a aplicação utiliza recursos disponíveis no próprio Java.

---

## 🚀 Instalação e Execução

### 1. Clonar o repositório

```bash
git clone https://github.com/rogermsarmento/verificador-senhas-vazadas.git
```

### 2. Acessar o diretório do projeto

```bash
cd verificador-senhas-vazadas
```

### 3. Abrir o projeto no NetBeans

No NetBeans:

```text
File
  ↓
Open Project
  ↓
verificador-senhas-vazadas
```

### 4. Executar a aplicação

A classe principal do projeto é:

```text
br.edu.principal.Principal
```

No NetBeans:

```text
Run
  ↓
Run Project
```

### 5. Informar a senha

A aplicação solicitará uma senha pelo console e realizará a consulta à base de vazamentos.

---

## 🔒 Segurança e Privacidade

A senha informada pelo usuário não é enviada diretamente para a API.

O processamento ocorre da seguinte forma:

```text
Senha
  │
  ▼
SHA-1 gerado localmente
  │
  ├── primeiros 5 caracteres ──► API
  │
  └── restante do hash ─────────► permanece local
```

A consulta utiliza o modelo de **k-anonymity** adotado pelo serviço Pwned Passwords.

O uso de SHA-1 neste projeto ocorre exclusivamente porque esse algoritmo faz parte do protocolo utilizado pela API para consulta de senhas comprometidas.

**SHA-1 não é recomendado para armazenamento de senhas em sistemas reais.**

Além disso, uma senha não encontrada na base consultada **não deve ser considerada automaticamente segura**. O resultado indica apenas que ela não foi identificada entre os vazamentos disponíveis no serviço consultado.

---

# 📚 Histórico de Versões

| Versão | Descrição |
|---|---|
| **V0.0.0** | Estrutura inicial e entrada da senha pelo console |
| **V0.1.0** | Geração local do hash SHA-1 |
| **V0.2.0** | Separação entre prefixo e sufixo para utilização de k-anonymity |
| **V0.3.0** | Integração com a API Pwned Passwords |
| **V0.4.0** | Processamento da resposta e identificação das ocorrências |
| **V1.0.0** | Primeira versão funcional do verificador em console |
| **V1.1.0** | Modularização das funcionalidades da aplicação |
| **V2.0.0** | Reestruturação da aplicação utilizando Programação Orientada a Objetos |
| **V2.1.0** | Separação da interface de console e representação do resultado da verificação |
| **V3.0.0** | Preparação da arquitetura para interfaces gráficas Swing e centralização da criação das dependências |

---

# 🚀 Versão Atual

## V3.0.0 — Base para Interface Gráfica

A V3.0.0 prepara a arquitetura da aplicação para a implementação de interfaces gráficas com **Java Swing**.

A principal alteração desta versão é a introdução da classe:

```text
AppFactory
```

responsável por centralizar a criação e configuração das dependências utilizadas pelo verificador.

A aplicação continua funcional por meio da interface de console. Nenhum componente gráfico é introduzido nesta versão.

Dessa forma, a V3.0.0 estabelece um ponto comum para o desenvolvimento de duas abordagens independentes:

- interface construída com o **GUI Builder do NetBeans**;
- interface construída diretamente em **código Java**.

---

# 🏗️ Estrutura da V3.0.0

```text
src/
└── br/
    └── edu/
        ├── config/
        │   └── AppFactory.java
        │
        ├── model/
        │   └── ResultadoVerificacao.java
        │
        ├── principal/
        │   └── Principal.java
        │
        ├── service/
        │   ├── HashService.java
        │   └── PwnedPasswordService.java
        │
        ├── ui/
        │   └── ConsoleUI.java
        │
        └── verificador/
            └── VerificadorSenha.java
```

## Responsabilidades

| Classe | Responsabilidade |
|---|---|
| `Principal` | Inicializar e coordenar a execução da aplicação em console |
| `AppFactory` | Criar e configurar as dependências utilizadas pela aplicação |
| `ConsoleUI` | Realizar a interação com o usuário pelo console |
| `ResultadoVerificacao` | Representar o resultado retornado pela verificação |
| `VerificadorSenha` | Coordenar o processo de verificação da senha |
| `HashService` | Gerar e dividir o hash SHA-1 |
| `PwnedPasswordService` | Consultar e processar os dados da API Pwned Passwords |

---

# 🔄 Evolução da Arquitetura

## V1.0.0

A aplicação possuía o fluxo completo de verificação em console, concentrando diferentes responsabilidades em uma mesma estrutura.

```text
Principal
   │
   ├── entrada
   ├── hash
   ├── requisição HTTP
   ├── processamento
   └── saída
```

## V1.1.0

As principais funcionalidades foram separadas em métodos, reduzindo a concentração de código no método `main`.

```text
Principal
   │
   ├── métodos de entrada
   ├── métodos de processamento
   ├── métodos de consulta
   └── métodos de saída
```

## V2.0.0

A aplicação passou a utilizar classes com responsabilidades específicas.

```text
Principal
    │
    ▼
VerificadorSenha
    │
    ├───────────────┐
    ▼               ▼
HashService   PwnedPasswordService
```

## V2.1.0

A interação com o console foi separada da lógica de verificação e o resultado passou a ser representado por um objeto específico.

```text
              Principal
              /       \
             ▼         ▼
       ConsoleUI   VerificadorSenha
                         │
                 ┌───────┴────────┐
                 ▼                ▼
           HashService   PwnedPasswordService
```

O resultado da verificação é transportado por:

```text
VerificadorSenha
       │
       ▼
ResultadoVerificacao
       │
       ▼
Principal
       │
       ▼
ConsoleUI
```

## V3.0.0

A criação das dependências foi centralizada na `AppFactory`.

```text
              Principal
              /       \
             ▼         ▼
       ConsoleUI     AppFactory
                         │
                         ▼
                  VerificadorSenha
                    /          \
                   ▼            ▼
            HashService   PwnedPasswordService
```

Com isso, os detalhes necessários para construir um `VerificadorSenha` ficam centralizados em um único ponto da aplicação.

---

# 🔜 Próxima Evolução

A partir da V3.0.0, o desenvolvimento da interface gráfica será dividido em **duas linhas independentes**.

```text
                         V3.0.0
                            │
              ┌─────────────┴─────────────┐
              ▼                           ▼
       GUI Builder                  Swing por código
       do NetBeans
              │                           │
        V3.1.0-GUI                  V3.1.0-CODE
              │                           │
        V3.2.0-GUI                  V3.2.0-CODE
              │                           │
        V3.3.0-GUI                  V3.3.0-CODE
```

As duas linhas utilizarão o mesmo núcleo da aplicação e buscarão implementar os mesmos requisitos funcionais.

## GUI Builder

A primeira abordagem utilizará o **GUI Builder do NetBeans** para construção da interface.

Essa linha será mantida na branch:

```text
swing-gui-builder
```

A implementação utilizará a estrutura gráfica gerenciada pelo NetBeans, incluindo os arquivos `.form` associados às telas.

## Swing por código

A segunda abordagem construirá a interface diretamente por meio de **código Java**.

Essa linha será mantida na branch:

```text
swing-code
```

Os componentes Swing, layouts e eventos serão configurados diretamente no código-fonte, sem utilização do GUI Builder.

## Comparação

As duas implementações serão mantidas separadamente para permitir a comparação entre as abordagens em aspectos como:

- estrutura do projeto;
- organização do código;
- utilização de layouts;
- tratamento de eventos;
- código gerado automaticamente;
- controle sobre os componentes;
- manutenção;
- produtividade no desenvolvimento.

As duas linhas partirão exatamente da mesma versão:

```text
v3.0.0
```

---

# 📄 Licença

Este projeto é distribuído sob a licença **MIT**.

Consulte o arquivo `LICENSE` para mais informações.