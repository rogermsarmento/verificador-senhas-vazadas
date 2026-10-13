# 🔐 Verificador de Senhas Vazadas

Aplicação desenvolvida em **Java** para verificar se uma senha já apareceu em vazamentos de dados conhecidos, utilizando a API **Pwned Passwords**, do serviço Have I Been Pwned.

O projeto faz parte da **FortyTwo Lab** e está sendo desenvolvido de forma incremental, acompanhando a evolução dos conceitos apresentados na disciplina de **Programação Orientada a Objetos**.

---

## 🚀 Objetivo

Desenvolver uma aplicação capaz de verificar se uma senha já apareceu em vazamentos de dados conhecidos, preservando a privacidade da senha durante a consulta.

O projeto também tem como objetivo demonstrar, de forma incremental, conceitos relacionados a:

- desenvolvimento em Java;
- consumo de APIs HTTP;
- geração de hashes;
- modularização;
- Programação Orientada a Objetos;
- separação de responsabilidades;
- interfaces de usuário;
- Java Swing;
- desenvolvimento de interfaces com NetBeans GUI Builder.

---

## 🔎 Como funciona

A aplicação utiliza a API **Pwned Passwords**, que permite consultar hashes de senhas encontradas em vazamentos de dados.

A consulta utiliza o modelo de **k-anonymity**, evitando o envio da senha ou do hash completo para o serviço.

O fluxo básico é:

```text
Senha
  │
  ▼
SHA-1
  │
  ▼
Hash completo
  │
  ├── primeiros 5 caracteres ──► API Pwned Passwords
  │
  └── restante do hash
                               │
                               ▼
                    Lista de sufixos retornados
                               │
                               ▼
                         Comparação local
                               │
                               ▼
                   Quantidade de ocorrências
```

Somente os **cinco primeiros caracteres do hash SHA-1** são enviados para a API.

A comparação com o restante do hash é realizada localmente.

---

## 🔒 Segurança e privacidade

A senha digitada pelo usuário **não é enviada para a API**.

O processo ocorre da seguinte forma:

1. a senha é transformada localmente em um hash SHA-1;
2. somente os cinco primeiros caracteres do hash são enviados;
3. a API retorna os hashes que possuem o mesmo prefixo;
4. a comparação final é realizada localmente.

> **Importante:** SHA-1 não é recomendado para armazenamento de senhas. Ele é utilizado neste projeto porque faz parte do protocolo de consulta da API Pwned Passwords.

O uso de k-anonymity reduz a exposição da informação durante a consulta, mas não deve ser interpretado como garantia absoluta de anonimato.

Além disso, uma senha não encontrada na base não significa necessariamente que ela seja segura. Significa apenas que ela não foi encontrada nos vazamentos conhecidos consultados pelo serviço.

---

## 🖥️ Interface gráfica

A versão atual utiliza **Java Swing** com interface construída por meio do **NetBeans GUI Builder**.

A interface permite:

- digitar uma senha;
- validar entradas vazias;
- realizar a consulta;
- identificar senhas encontradas em vazamentos;
- apresentar a quantidade de ocorrências;
- formatar valores utilizando separadores de milhares;
- informar quando uma senha não é encontrada;
- apresentar mensagens em caso de erro na consulta.

Exemplo de resultado:

```text
Senha encontrada 210.461.208 vezes em vazamentos conhecidos.
```

Quando nenhuma ocorrência é encontrada:

```text
Senha não encontrada em vazamentos conhecidos.
```

---

## 🧱 Arquitetura

A aplicação está organizada em diferentes responsabilidades:

```text
TelaPrincipal
      │
      ▼
  AppFactory
      │
      ▼
VerificadorSenha
   /          \
  ▼            ▼
HashService   PwnedPasswordService
   \            /
    \          /
     ▼        ▼
  ResultadoVerificacao
          │
          ▼
     TelaPrincipal
```

### Principais classes

| Classe | Responsabilidade |
|---|---|
| `TelaPrincipal` | Entrada e apresentação gráfica |
| `ConsoleUI` | Entrada e apresentação pelo console |
| `AppFactory` | Criação e configuração das dependências |
| `VerificadorSenha` | Coordenação do processo de verificação |
| `HashService` | Geração do SHA-1 e separação do prefixo e sufixo |
| `PwnedPasswordService` | Comunicação e processamento da resposta da API |
| `ResultadoVerificacao` | Representação do resultado da consulta |

A interface gráfica não conhece detalhes relacionados à geração do hash, comunicação HTTP ou processamento da resposta da API.

---

## 📁 Estrutura do projeto

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
        │   │
        │   └── swing/
        │       ├── TelaPrincipal.java
        │       └── TelaPrincipal.form
        │
        └── verificador/
            └── VerificadorSenha.java
```

O arquivo `TelaPrincipal.form` contém os metadados utilizados pelo NetBeans GUI Builder para construção e edição visual da interface.

---

## 📋 Requisitos

Para executar e desenvolver o projeto, recomenda-se:

- **JDK 17 ou superior**;
- **Apache NetBeans**;
- **Git**;
- conexão com a Internet para realização das consultas;
- acesso à API Pwned Passwords.

---

## 📥 Clonando o projeto

```bash
git clone https://github.com/rogermsarmento/verificador-senhas-vazadas.git
```

Entre no diretório:

```bash
cd verificador-senhas-vazadas
```

Para acessar especificamente a versão atual da linha GUI Builder:

```bash
git checkout swing-gui-builder
```

O projeto pode então ser aberto no Apache NetBeans.

---

## 🧪 Exemplos de uso

Uma senha muito comum pode retornar:

```text
Senha encontrada 210.461.208 vezes em vazamentos conhecidos.
```

O número representa a quantidade de ocorrências registrada na base consultada e pode mudar ao longo do tempo.

Uma senha que não esteja presente na base retornará:

```text
Senha não encontrada em vazamentos conhecidos.
```

Esse resultado não deve ser interpretado como garantia de que a senha seja forte ou segura.

---

## 🗂️ Histórico de versões

| Versão | Descrição |
|---|---|
| **V0.0.0** | Entrada básica de senha pelo console |
| **V0.1.0** | Geração do hash SHA-1 |
| **V0.2.0** | Separação do hash em prefixo e sufixo para k-anonymity |
| **V0.3.0** | Requisição HTTP para a API Pwned Passwords |
| **V0.4.0** | Processamento da resposta da API |
| **V1.0.0** | Verificador completo executado pelo console |
| **V1.1.0** | Modularização das funcionalidades |
| **V2.0.0** | Introdução à Programação Orientada a Objetos |
| **V2.1.0** | Separação da interface de console |
| **V3.0.0** | Preparação da arquitetura para interfaces Swing |
| **V3.1.0-GUI** | Primeira interface gráfica utilizando NetBeans GUI Builder |
| **V3.2.0-GUI** | Integração da interface gráfica com a lógica real de verificação |

---

## 🌿 Estratégia de branches

A partir da **V3.0.0**, o projeto foi dividido em duas linhas de desenvolvimento:

```text
                         V3.0.0
                            │
               ┌────────────┴────────────┐
               │                         │
               ▼                         ▼
      swing-gui-builder              swing-code
               │                         │
        V3.1.0-GUI                V3.1.0-CODE
               │                         │
        V3.2.0-GUI                V3.2.0-CODE
               │                         │
        V3.3.0-GUI                V3.3.0-CODE
```

### `swing-gui-builder`

Desenvolvimento da interface utilizando os recursos visuais do NetBeans GUI Builder.

### `swing-code`

Desenvolvimento da mesma interface utilizando código Java Swing escrito manualmente.

As duas abordagens partem da mesma base arquitetural, permitindo posteriormente comparar as estratégias de desenvolvimento.

---

## 📌 Versão atual — V3.2.0-GUI

A **V3.2.0-GUI** integra a interface criada na versão anterior com a lógica real da aplicação.

Nesta versão foram implementados:

- captura da senha por meio de `JPasswordField`;
- validação de entrada vazia;
- integração da `TelaPrincipal` com `AppFactory`;
- utilização de `VerificadorSenha`;
- consulta real ao serviço Pwned Passwords;
- utilização de `ResultadoVerificacao`;
- apresentação de senhas encontradas;
- apresentação de senhas não encontradas;
- formatação da quantidade de ocorrências;
- tratamento de falhas na geração do hash;
- tratamento de erros de comunicação;
- tratamento de interrupções da consulta.

O fluxo atual é:

```text
Senha digitada
      │
      ▼
TelaPrincipal
      │
      ▼
AppFactory
      │
      ▼
VerificadorSenha
      │
      ├── HashService
      │
      └── PwnedPasswordService
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

A aplicação já é funcional, porém a consulta HTTP ainda é realizada de forma síncrona na thread da interface gráfica.

---

## 🔜 Próxima versão — V3.3.0-GUI

A próxima versão terá como objetivo finalizar a linha desenvolvida com o NetBeans GUI Builder e preparar a aplicação para execução e testes por outros usuários.

Entre as melhorias planejadas estão:

- execução da consulta em segundo plano;
- utilização de `SwingWorker`;
- evitar bloqueio da Event Dispatch Thread;
- indicação visual de consulta em andamento;
- desabilitação temporária do botão durante a consulta;
- reativação do botão após a conclusão;
- melhorias no tratamento visual dos resultados e erros;
- configuração da interface gráfica como fluxo principal de execução;
- revisão das instruções de instalação e execução;
- testes do projeto após clonagem em outro ambiente.

Ao final da V3.3.0-GUI, a aplicação deverá estar preparada para ser clonada, aberta e testada em outra máquina.

---

## ⚠️ Limitação atual

Na V3.2.0-GUI, a consulta HTTP é executada diretamente durante o tratamento do evento do botão.

Em conexões lentas ou indisponíveis, a interface pode ficar temporariamente sem responder.

Essa limitação será tratada na **V3.3.0-GUI** com a introdução de execução assíncrona utilizando `SwingWorker`.

---

## 📄 Licença

Este projeto é distribuído sob a licença **MIT**.

Consulte o arquivo `LICENSE` para mais informações.