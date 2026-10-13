# 🔐 Verificador de Senhas Vazadas

Aplicação desenvolvida em **Java** para verificar se uma senha aparece em bases de dados de credenciais conhecidas por terem sido expostas em vazamentos.

O projeto utiliza a API **Pwned Passwords**, do serviço Have I Been Pwned, realizando as consultas por meio do modelo de **k-anonymity**, sem enviar a senha ou seu hash completo para o serviço externo.

---

## 🎯 Objetivo do Projeto

O **Verificador de Senhas Vazadas** permite consultar se uma senha já apareceu em vazamentos de dados conhecidos.

A aplicação realiza todo o processamento sensível localmente e utiliza a API Pwned Passwords apenas para consultar hashes que compartilham o mesmo prefixo.

Principais funcionalidades:

- leitura e validação da senha;
- geração local do hash SHA-1;
- separação do hash em prefixo e sufixo;
- consulta à API Pwned Passwords;
- processamento da resposta da API;
- comparação local dos hashes;
- identificação da quantidade de ocorrências;
- tratamento de erros de comunicação;
- apresentação do resultado ao usuário.

---

## ⚙️ Funcionamento

A senha informada pelo usuário **não é enviada diretamente para a API**.

Primeiramente, a aplicação calcula localmente o hash SHA-1 da senha.

Um SHA-1 possui 40 caracteres em representação hexadecimal.

Exemplo:

```text
A9993E364706816ABA3E25717850C26C9CD0D89D
```

O hash é dividido em duas partes:

```text
Prefixo: A9993
Sufixo:  E364706816ABA3E25717850C26C9CD0D89D
```

Somente os **5 primeiros caracteres** do hash são enviados para a API Pwned Passwords.

A consulta utiliza o endpoint:

```text
/range/{prefixo}
```

A API retorna os sufixos dos hashes que possuem o mesmo prefixo, juntamente com a quantidade de ocorrências registradas.

A comparação entre o sufixo da senha e os dados retornados é realizada **localmente pela aplicação**.

---

## 🔄 Fluxo da Aplicação

```text
Senha
  ↓
Validação da entrada
  ↓
Geração local do SHA-1
  ↓
Separação do hash
  ↓
┌──────────────┬─────────────────────────────────────┐
│   Prefixo    │               Sufixo                │
│ 5 caracteres │           35 caracteres             │
└──────────────┴─────────────────────────────────────┘
       ↓                         │
Consulta à API                   │
       ↓                         │
Resposta da API                  │
       ↓                         │
       └──────────┬──────────────┘
                  ↓
        Comparação local
                  ↓
      Quantidade de ocorrências
                  ↓
               Resultado
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

Abra o terminal e execute:

```bash
git clone https://github.com/rogermsarmento/verificador-senhas-vazadas.git
```

### 2. Acessar o diretório do projeto

```bash
cd verificador-senhas-vazadas
```

### 3. Abrir no NetBeans

No Apache NetBeans:

```text
File
  ↓
Open Project
  ↓
verificador-senhas-vazadas
```

Selecione o diretório clonado e abra o projeto.

### 4. Executar a aplicação

Execute a classe principal:

```text
br.edu.principal.Principal
```

No NetBeans, também é possível utilizar:

```text
Run → Run Project
```

A aplicação será iniciada no console e solicitará a senha que deverá ser verificada.

### 5. Informar a senha

Exemplo:

```text
Digite uma senha: ********
```

A aplicação realizará a consulta e apresentará o resultado no console.

---

## 🔐 Segurança e Privacidade

O projeto utiliza o modelo de consulta por **k-anonymity** adotado pela API Pwned Passwords.

Durante uma consulta:

- a senha é processada localmente;
- o SHA-1 é calculado localmente;
- a senha original não é enviada para a API;
- o hash SHA-1 completo não é enviado;
- somente os 5 primeiros caracteres do hash são utilizados na consulta;
- o sufixo permanece local;
- a comparação final é realizada pela própria aplicação.

O fluxo pode ser representado por:

```text
Senha
  ↓
SHA-1 calculado localmente
  ↓
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  ↓
┌─────────┬─────────────────────────────────────┐
│ Prefixo │               Sufixo                │
│ 5 chars │              35 chars               │
└─────────┴─────────────────────────────────────┘
     │                        │
     │                        └── permanece local
     ↓
Pwned Passwords API
```

> **Importante:** o algoritmo SHA-1 não é recomendado para armazenamento de senhas. Ele é utilizado neste projeto porque faz parte do mecanismo de consulta adotado pela API Pwned Passwords.

### ⚠️ Senha não encontrada não significa senha segura

Uma resposta indicando que a senha não foi encontrada significa apenas que seu hash não foi localizado entre os registros consultados.

Isso **não garante que a senha seja segura**.

A segurança de uma senha também depende de fatores como comprimento, previsibilidade, reutilização e boas práticas de autenticação.

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

---

# 🚀 Versão Atual

## V2.1.0 — Separação da Interface de Console

A **V2.1.0** aprimora a organização interna introduzida na V2.0.0, separando a interação com o usuário das classes responsáveis pelo processamento e pela regra de verificação.

As operações relacionadas ao console foram concentradas na classe:

```text
ConsoleUI
```

A classe `VerificadorSenha` permanece responsável pela coordenação da verificação, sem depender diretamente da interface utilizada para apresentar as informações.

Também foi criada a classe:

```text
ResultadoVerificacao
```

responsável por representar o resultado produzido pela verificação.

Ela mantém as informações:

```text
ResultadoVerificacao
│
├── statusCode
└── quantidadeEncontrada
```

Com essa estrutura, `VerificadorSenha` retorna um objeto contendo os dados necessários para que a camada de interface decida como apresentá-los ao usuário.

### Principais alterações

- criação do pacote `br.edu.ui`;
- criação da classe `ConsoleUI`;
- centralização das operações de entrada e saída do console;
- criação do pacote `br.edu.model`;
- criação da classe `ResultadoVerificacao`;
- remoção da apresentação direta de informações de `VerificadorSenha`;
- retorno estruturado do resultado da verificação;
- redução do acoplamento entre regra de negócio e interface;
- preparação da arquitetura para novas formas de interação com o usuário.

---

# 🏗️ Estrutura da V2.1.0

A aplicação está organizada nos seguintes pacotes:

```text
src/
└── br/
    └── edu/
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
| `Principal` | Inicializar os componentes e coordenar o fluxo principal da aplicação |
| `ConsoleUI` | Realizar a interação com o usuário por meio do console |
| `VerificadorSenha` | Coordenar o processo de verificação da senha |
| `ResultadoVerificacao` | Representar os dados produzidos pela verificação |
| `HashService` | Gerar o SHA-1 e separar o hash em prefixo e sufixo |
| `PwnedPasswordService` | Realizar a comunicação com a API e processar os dados retornados |

A arquitetura atual pode ser representada por:

```text
                   ┌───────────────┐
                   │   Principal   │
                   └───────┬───────┘
                           │
                 ┌─────────┴─────────┐
                 ▼                   ▼
            ConsoleUI         VerificadorSenha
                                     │
                           ┌─────────┴─────────┐
                           ▼                   ▼
                     HashService     PwnedPasswordService

VerificadorSenha → ResultadoVerificacao → Principal → ConsoleUI
```

`ResultadoVerificacao` permite transportar os dados da verificação sem criar uma dependência direta entre `VerificadorSenha` e `ConsoleUI`.

---

# 🔄 Evolução da Arquitetura

A arquitetura do projeto evoluiu progressivamente para reduzir a concentração de responsabilidades.

### V1.0.0

```text
Principal
│
└── main()
    ├── entrada
    ├── hash
    ├── consulta HTTP
    ├── processamento
    └── apresentação
```

A aplicação já era funcional, mas o fluxo estava concentrado no método `main()`.

### V1.1.0

```text
Principal
│
├── main()
├── exibirCabecalho()
├── lerSenha()
├── gerarHash()
├── obterPrefixo()
├── obterSufixo()
├── consultarApi()
├── exibirStatusHttp()
├── buscarQuantidade()
└── exibirResultado()
```

As funcionalidades foram modularizadas em métodos, ainda dentro da mesma classe.

### V2.0.0

```text
                     Principal
                         │
                         ▼
                  VerificadorSenha
                    /          \
                   /            \
                  ▼              ▼
          HashService    PwnedPasswordService
```

As responsabilidades de processamento e comunicação foram distribuídas entre classes específicas.

### V2.1.0

```text
                         Principal
                        /         \
                       ▼           ▼
                 ConsoleUI    VerificadorSenha
                                   /       \
                                  ▼         ▼
                           HashService   PwnedPasswordService
                                   \       /
                                    ▼     ▼
                              ResultadoVerificacao
```

A interface de console passa a possuir uma responsabilidade própria, enquanto o resultado da verificação é representado por um objeto independente.

Essa organização reduz o acoplamento entre a lógica da aplicação e a forma utilizada para interagir com o usuário.

---

# 🔜 Próxima Versão

## V3.0.0 — Interface Gráfica com Java Swing

A próxima evolução do projeto será a implementação de uma **interface gráfica utilizando Java Swing**.

A arquitetura atual permite que a nova interface utilize a mesma lógica de verificação existente, sem incorporar à interface os detalhes relacionados a SHA-1, k-anonymity ou comunicação HTTP.

A evolução prevista é:

```text
ConsoleUI ──────┐
                │
                ▼
         VerificadorSenha
                ▲
                │
SwingUI ────────┘
```

A V3.0.0 deverá incluir:

- criação da janela principal;
- utilização de componentes Java Swing;
- organização da interface com layouts;
- campo para entrada da senha;
- botão para iniciar a verificação;
- tratamento de eventos;
- integração com `VerificadorSenha`;
- apresentação gráfica do resultado;
- tratamento visual de erros e mensagens.

A lógica de geração do hash, consulta à API e processamento dos resultados permanecerá independente da interface gráfica.

---

## 📄 Licença

Este projeto é distribuído sob a licença **MIT**.