# 🔐 Verificador de Senhas Vazadas

Aplicação desenvolvida em Java para verificar se uma senha já apareceu em vazamentos de dados conhecidos.

A verificação utiliza a API **Pwned Passwords**, do serviço **Have I Been Pwned**, empregando o modelo de consulta por **k-anonymity**. Dessa forma, a senha digitada pelo usuário não é enviada diretamente para a API.

O projeto faz parte da **FortyTwo Lab** e é desenvolvido de forma incremental, com versões que representam a evolução da aplicação desde uma implementação simples em console até interfaces gráficas utilizando Java Swing.

---

## 🎯 Objetivo do Projeto

Desenvolver uma aplicação capaz de verificar se uma senha está presente em bases de senhas comprometidas, utilizando uma arquitetura organizada e separando progressivamente as responsabilidades da aplicação.

O projeto evolui por diferentes etapas, incluindo:

- entrada de dados pelo console;
- geração de hash SHA-1;
- consulta à API Pwned Passwords;
- processamento da resposta da API;
- modularização da aplicação;
- aplicação de Programação Orientada a Objetos;
- separação entre interface e lógica de negócio;
- desenvolvimento de interface gráfica com Java Swing.

---

## ⚙️ Funcionamento

A aplicação utiliza o mecanismo de consulta por **k-anonymity** disponibilizado pela API Pwned Passwords.

O processo ocorre da seguinte forma:

1. o usuário informa uma senha;
2. a aplicação gera localmente o hash SHA-1 da senha;
3. o hash é dividido em:
   - prefixo: primeiros 5 caracteres;
   - sufixo: caracteres restantes;
4. somente o prefixo é enviado para a API;
5. a API retorna uma lista de sufixos correspondentes ao prefixo informado;
6. a aplicação compara localmente o sufixo do hash com os valores retornados;
7. caso exista correspondência, é apresentada a quantidade de ocorrências encontradas.

A senha original e o hash completo não são enviados para a API.

---

## 🔄 Fluxo da Aplicação

```text
Senha
  │
  ▼
Geração do SHA-1
  │
  ▼
Hash completo
  │
  ├── Prefixo (5 caracteres)
  │        │
  │        ▼
  │   Pwned Passwords API
  │        │
  │        ▼
  │   Lista de sufixos
  │
  └── Sufixo local
           │
           ▼
       Comparação
           │
           ▼
       Resultado
```

---

## 📋 Requisitos

Para executar e desenvolver o projeto, recomenda-se:

- JDK 17 ou superior;
- Apache NetBeans;
- Git;
- conexão com a Internet para consulta à API Pwned Passwords.

---

## 🚀 Instalação e Execução

Clone o repositório:

```bash
git clone https://github.com/rogermsarmento/verificador-senhas-vazadas.git
```

Acesse a pasta do projeto:

```bash
cd verificador-senhas-vazadas
```

Abra o projeto no Apache NetBeans.

Na linha de desenvolvimento atual com GUI Builder, a interface gráfica está disponível na classe:

```text
br.edu.ui.swing.TelaPrincipal
```

A aplicação em console permanece disponível por meio da classe:

```text
br.edu.principal.Principal
```

---

## 🔒 Segurança e Privacidade

A aplicação não envia a senha digitada pelo usuário para a API.

O SHA-1 é calculado localmente e somente os cinco primeiros caracteres do hash são utilizados na consulta remota.

O uso de SHA-1 neste projeto ocorre exclusivamente porque esse é o formato utilizado pelo protocolo de consulta da API Pwned Passwords.

> SHA-1 não é recomendado para armazenamento seguro de senhas.

Em aplicações reais, senhas devem ser armazenadas utilizando algoritmos específicos para derivação e armazenamento seguro de credenciais.

O mecanismo de k-anonymity reduz a exposição das informações durante a consulta, mas não deve ser interpretado como garantia absoluta de anonimato.

Da mesma forma, uma senha não encontrada na base consultada não significa necessariamente que seja uma senha segura. O resultado indica apenas que ela não foi localizada no conjunto de vazamentos consultado pelo serviço.

---

# 📚 Histórico de Versões

### V0.0.0 — Entrada de senha

Primeira versão da aplicação executada em console.

- leitura de senha utilizando `Scanner`;
- estrutura inicial do projeto;
- execução pela IDE.

### V0.1.0 — Geração do hash

Introdução da geração do hash SHA-1 da senha.

- utilização de `MessageDigest`;
- conversão do hash para representação hexadecimal.

### V0.2.0 — k-Anonymity

Preparação do hash para utilização no modelo de consulta da API.

- separação do hash em prefixo e sufixo;
- utilização dos cinco primeiros caracteres como prefixo;
- manutenção do restante do hash apenas localmente.

### V0.3.0 — Consulta à API

Introdução da comunicação HTTP com a API Pwned Passwords.

- utilização de `HttpClient`;
- criação da requisição HTTP;
- envio do prefixo para o endpoint da API;
- obtenção do código de status HTTP.

### V0.4.0 — Processamento da resposta

Implementação do processamento dos dados retornados pela API.

- leitura do corpo da resposta;
- separação das linhas retornadas;
- comparação dos sufixos;
- obtenção da quantidade de ocorrências.

### V1.0.0 — Verificador completo em console

Conclusão do fluxo completo de verificação.

- entrada da senha;
- geração do hash;
- consulta à API;
- processamento da resposta;
- apresentação do resultado ao usuário;
- tratamento dos principais códigos HTTP.

### V1.1.0 — Modularização

Separação das responsabilidades da aplicação em métodos e serviços específicos.

- redução das responsabilidades da classe principal;
- organização do código;
- criação de serviços para geração de hash e consulta à API.

### V2.0.0 — Programação Orientada a Objetos

Reorganização da aplicação utilizando classes com responsabilidades específicas.

Principais classes:

- `HashService`;
- `PwnedPasswordService`;
- `VerificadorSenha`;
- `ResultadoVerificacao`.

A lógica de verificação passa a ser independente da forma utilizada para interação com o usuário.

### V2.1.0 — Separação da interface de console

Criação da classe `ConsoleUI`.

A responsabilidade pela interação com o usuário no terminal deixa de pertencer à classe principal.

A aplicação passa a apresentar uma separação mais clara entre:

```text
Interface
    │
    ▼
Lógica da aplicação
    │
    ▼
Serviços
```

### V3.0.0 — Base para interfaces gráficas Swing

Preparação da arquitetura para a introdução de interfaces gráficas.

Principais alterações:

- criação da classe `AppFactory`;
- centralização da criação das dependências da aplicação;
- remoção da responsabilidade de montagem dos serviços da classe `Principal`;
- preparação da estrutura para diferentes interfaces;
- manutenção da lógica de negócio independente da interface.

A partir desta versão, o desenvolvimento da interface Swing é dividido em duas linhas independentes:

```text
V3.0.0
   │
   ├── swing-gui-builder
   │       ├── V3.1.0-GUI
   │       ├── V3.2.0-GUI
   │       └── V3.3.0-GUI
   │
   └── swing-code
           ├── V3.1.0-CODE
           ├── V3.2.0-CODE
           └── V3.3.0-CODE
```

A primeira linha utiliza o **NetBeans GUI Builder**.

A segunda linha implementará a mesma interface utilizando **código Java Swing manual**, permitindo posteriormente comparar as duas abordagens.

### V3.1.0-GUI — Primeira interface com NetBeans GUI Builder

Introdução da primeira interface gráfica da aplicação utilizando Java Swing e o NetBeans GUI Builder.

Principais alterações:

- criação do pacote `br.edu.ui.swing`;
- criação da classe `TelaPrincipal` como `JFrame Form`;
- construção da interface utilizando o NetBeans GUI Builder;
- utilização de `JPanel`, `JLabel`, `JPasswordField` e `JButton`;
- organização dos componentes da interface;
- padronização dos nomes das variáveis dos componentes;
- criação dos arquivos `TelaPrincipal.java` e `TelaPrincipal.form`;
- manutenção da lógica de negócio desacoplada da interface gráfica.

Nesta versão, a interface gráfica ainda não está integrada à lógica de verificação de senhas.

---

# 🚀 Versão Atual

## V3.1.0-GUI

A V3.1.0-GUI introduz a primeira interface gráfica da aplicação utilizando **Java Swing** e o **NetBeans GUI Builder**.

A tela principal possui:

- título e descrição da aplicação;
- campo para entrada da senha utilizando `JPasswordField`;
- botão `Verificar senha`;
- área destinada à apresentação do resultado.

A estrutura visual é representada pelos arquivos:

```text
br.edu.ui.swing
├── TelaPrincipal.java
└── TelaPrincipal.form
```

O arquivo `TelaPrincipal.java` contém a classe Java que representa o `JFrame`, enquanto o arquivo `TelaPrincipal.form` mantém as informações utilizadas pelo NetBeans GUI Builder para construção e edição visual do formulário.

A interface utiliza uma estrutura semelhante a:

```text
TelaPrincipal [JFrame]
└── pnlPrincipal [JPanel]
    ├── lblTitulo [JLabel]
    ├── lblDescricao [JLabel]
    ├── lblSenha [JLabel]
    ├── txtSenha [JPasswordField]
    ├── btnVerificar [JButton]
    ├── lblResultadoTitulo [JLabel]
    └── lblResultado [JLabel]
```

Nesta etapa, o botão ainda não executa a verificação. A integração entre a interface gráfica e a lógica existente será realizada nas próximas versões da linha GUI Builder.

---

# 🏗️ Estrutura da V3.1.0-GUI

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
        │   ├── ConsoleUI.java
        │   └── swing/
        │       ├── TelaPrincipal.java
        │       └── TelaPrincipal.form
        │
        └── verificador/
            └── VerificadorSenha.java
```

Os pacotes:

```text
br.edu.ui
br.edu.ui.swing
```

possuem responsabilidades distintas.

`br.edu.ui` mantém a interface de console, enquanto `br.edu.ui.swing` concentra a interface gráfica desenvolvida com Java Swing.

---

# 🔄 Evolução da Arquitetura

A aplicação evoluiu progressivamente de uma implementação concentrada em uma única classe para uma estrutura com responsabilidades separadas.

```text
V0.x
│
│  Implementação procedural
│
▼
V1.x
│
│  Modularização
│
▼
V2.x
│
│  Programação Orientada a Objetos
│  + separação da interface
│
▼
V3.0.0
│
│  AppFactory
│  + base independente de interface
│
├───────────────────────────────┐
│                               │
▼                               ▼
GUI Builder                 Swing manual
│                               │
▼                               ▼
V3.1.0-GUI                  V3.1.0-CODE
```

As duas linhas partem da mesma base arquitetural da V3.0.0.

Isso permitirá comparar posteriormente aspectos como:

- quantidade de código produzido;
- legibilidade;
- facilidade de manutenção;
- controle sobre os componentes;
- organização do layout;
- produtividade no desenvolvimento;
- dependência das ferramentas da IDE.

---

# 🔜 Próxima Evolução

## V3.2.0-GUI

A próxima versão da linha GUI Builder realizará a integração inicial entre a interface gráfica e a lógica existente da aplicação.

O objetivo será permitir que:

```text
Senha digitada
      │
      ▼
TelaPrincipal
      │
      ▼
VerificadorSenha
      │
      ▼
Serviços
      │
      ▼
ResultadoVerificacao
      │
      ▼
TelaPrincipal
      │
      ▼
lblResultado
```

A senha informada na interface será encaminhada ao processo de verificação e o resultado será apresentado na própria janela.

A lógica de negócio continuará independente da interface gráfica.

---

# 📄 Licença

Este projeto é distribuído sob a licença MIT.

Consulte o arquivo `LICENSE` para mais informações.