# 🔐 Verificador de Senhas Vazadas

Aplicação desenvolvida em Java para verificar se uma senha já apareceu em vazamentos de dados conhecidos, utilizando a API **Pwned Passwords**, do serviço **Have I Been Pwned**.

O projeto faz parte da **FortyTwo Lab** e é desenvolvido de forma incremental, acompanhando a evolução dos conceitos apresentados na disciplina de Programação Orientada a Objetos.

## 🚀 Objetivo

Desenvolver uma aplicação capaz de verificar se uma senha já apareceu em vazamentos de dados conhecidos, aplicando conceitos de:

- programação em Java;
- consumo de APIs HTTP;
- funções hash;
- orientação a objetos;
- separação de responsabilidades;
- interfaces gráficas com Java Swing;
- versionamento com Git e GitHub;
- geração e distribuição de aplicações Java por meio de arquivos JAR.

O projeto evolui progressivamente de uma aplicação executada no terminal até uma aplicação com interface gráfica.

---

## 🔒 Como funciona

A aplicação utiliza o serviço **Pwned Passwords** por meio do modelo de **k-anonymity**.

O fluxo básico é:

1. o usuário informa uma senha;
2. a aplicação gera localmente o hash SHA-1;
3. o hash é dividido em:
   - prefixo: primeiros 5 caracteres;
   - sufixo: caracteres restantes;
4. somente o prefixo é enviado para a API;
5. a API retorna os sufixos correspondentes ao prefixo consultado;
6. a aplicação procura localmente pelo sufixo da senha;
7. caso seja encontrado, é apresentada a quantidade de ocorrências conhecidas.

Fluxo simplificado:

```text
Senha
  │
  ▼
SHA-1
  │
  ├── Prefixo ───────► API Pwned Passwords
  │                         │
  └── Sufixo                ▼
                      Lista de sufixos
                            │
                            ▼
                     Comparação local
                            │
                            ▼
                         Resultado
```

A senha completa e o hash SHA-1 completo não são enviados à API.

> O SHA-1 é utilizado porque faz parte do protocolo da API Pwned Passwords. Ele não deve ser utilizado como algoritmo para armazenamento seguro de senhas.

Também é importante observar que uma senha não encontrada na base consultada não pode ser considerada automaticamente segura. O resultado indica apenas que ela não foi localizada nos vazamentos conhecidos consultados pelo serviço.

---

## 🖥️ Interface gráfica

A versão atual utiliza **Java Swing**.

A interface foi construída utilizando o **GUI Builder do NetBeans** e possui:

- campo para digitação da senha;
- ocultação visual da senha com `JPasswordField`;
- botão para iniciar a verificação;
- validação de campo vazio;
- apresentação da quantidade de ocorrências;
- tratamento de erros de comunicação;
- formatação do número de ocorrências.

A aplicação é iniciada por:

```text
Principal
    │
    ▼
TelaPrincipal
```

A antiga interface de terminal permanece no projeto como parte da evolução didática da aplicação, mas não é mais a interface padrão de execução.

---

## 🧱 Arquitetura

A aplicação está organizada em classes com responsabilidades distintas.

| Classe | Responsabilidade |
|---|---|
| `Principal` | Ponto de entrada da aplicação |
| `TelaPrincipal` | Interface gráfica Swing |
| `ConsoleUI` | Interface de terminal das versões anteriores |
| `AppFactory` | Criação e montagem das dependências |
| `VerificadorSenha` | Coordenação do processo de verificação |
| `HashService` | Geração e divisão do hash SHA-1 |
| `PwnedPasswordService` | Comunicação e processamento da resposta da API |
| `ResultadoVerificacao` | Representação do resultado da consulta |

Fluxo principal da versão gráfica:

```text
Principal
    │
    ▼
TelaPrincipal
    │
    ▼
VerificadorSenha
   /          \
  ▼            ▼
HashService   PwnedPasswordService
                  │
                  ▼
          Pwned Passwords API
                  │
                  ▼
        ResultadoVerificacao
                  │
                  ▼
           TelaPrincipal
```

---

## 📁 Estrutura do projeto

```text
ProjetoVerificaSenhasVazadas/
│
├── src/
│   └── br/
│       └── edu/
│           ├── config/
│           │   └── AppFactory.java
│           │
│           ├── model/
│           │   └── ResultadoVerificacao.java
│           │
│           ├── principal/
│           │   └── Principal.java
│           │
│           ├── service/
│           │   ├── HashService.java
│           │   └── PwnedPasswordService.java
│           │
│           ├── ui/
│           │   ├── ConsoleUI.java
│           │   └── swing/
│           │       ├── TelaPrincipal.java
│           │       └── TelaPrincipal.form
│           │
│           └── verificador/
│               └── VerificadorSenha.java
│
├── nbproject/
│
├── release/
│   └── VerificadorSenhasVazadas-v3.3.0-gui.jar
│
├── .gitignore
├── LICENSE
└── README.md
```

O arquivo:

```text
TelaPrincipal.form
```

é utilizado pelo GUI Builder do NetBeans para armazenar as informações da construção visual do formulário.

As pastas `build/` e `dist/` são geradas automaticamente durante a compilação e não são versionadas.

A pasta:

```text
release/
```

é utilizada para disponibilizar versões executáveis do projeto.

---

## 📋 Requisitos

### Para executar o JAR

- Java 17 ou superior;
- conexão com a Internet.

### Para desenvolver o projeto

- JDK 17 ou superior;
- Apache NetBeans;
- Git;
- conexão com a Internet.

O projeto pode ser desenvolvido utilizando um JDK mais recente, mas o **Source/Binary Format** está configurado para **JDK 17**, permitindo a geração de bytecode compatível com Java 17 ou superior.

---

## 📥 Clonando o projeto

A implementação desenvolvida com o GUI Builder encontra-se na branch:

```text
swing-gui-builder
```

Para cloná-la diretamente:

```bash
git clone -b swing-gui-builder https://github.com/rogermsarmento/verificador-senhas-vazadas.git
```

Depois da clonagem:

1. abra o Apache NetBeans;
2. acesse `File → Open Project`;
3. selecione o diretório clonado;
4. aguarde o carregamento do projeto;
5. execute utilizando `Run Project` ou `F6`.

A classe principal da aplicação é:

```text
br.edu.principal.Principal
```

Ao executar o projeto, a interface gráfica será aberta automaticamente.

---

## 📦 Executando pelo JAR

A versão gráfica também está disponível como um arquivo JAR executável:

```text
release/
└── VerificadorSenhasVazadas-v3.3.0-gui.jar
```

Para executar o JAR é necessário possuir **Java 17 ou superior** instalado.

A partir da raiz do projeto, execute:

```bash
java -jar release/VerificadorSenhasVazadas-v3.3.0-gui.jar
```

Também é possível entrar na pasta `release`:

```bash
cd release
```

e executar:

```bash
java -jar VerificadorSenhasVazadas-v3.3.0-gui.jar
```

Dependendo da configuração do sistema operacional, o arquivo `.jar` também poderá ser iniciado diretamente pela interface gráfica.

A execução pelo terminal é recomendada para facilitar a identificação de eventuais mensagens de erro relacionadas à instalação ou à versão do Java.

---

## ▶️ Utilização

Após iniciar a aplicação:

1. informe uma senha;
2. clique em **Verificar senha**;
3. aguarde a consulta;
4. observe o resultado apresentado na interface.

Exemplo de senha encontrada:

```text
Senha encontrada 210.461.208 vezes em vazamentos conhecidos.
```

Exemplo de senha não encontrada:

```text
Senha não encontrada em vazamentos conhecidos.
```

O número apresentado depende das informações disponíveis no serviço no momento da consulta.

---

## 🧪 Evolução do projeto

| Versão | Descrição |
|---|---|
| `V0.0.0` | Entrada básica da senha pelo terminal |
| `V0.1.0` | Geração do hash SHA-1 |
| `V0.2.0` | Introdução ao k-anonymity e divisão do hash |
| `V0.3.0` | Requisição HTTP à API |
| `V0.4.0` | Processamento da resposta |
| `V1.0.0` | Verificador completo em terminal |
| `V1.1.0` | Modularização da aplicação |
| `V2.0.0` | Introdução à Programação Orientada a Objetos |
| `V2.1.0` | Separação da interface de console |
| `V3.0.0` | Base comum para interfaces gráficas |
| `V3.1.0-GUI` | Primeira interface utilizando GUI Builder |
| `V3.2.0-GUI` | Integração da interface com a lógica de verificação |
| `V3.3.0-GUI` | Finalização, compatibilidade e distribuição da aplicação gráfica |

---

## 🌿 Estratégia de branches

A partir da `V3.0.0`, o desenvolvimento das interfaces Swing foi dividido em duas linhas independentes:

```text
                       V3.0.0
                          │
              ┌───────────┴───────────┐
              │                       │
              ▼                       ▼
     swing-gui-builder            swing-code
              │                       │
        V3.1.0-GUI              V3.1.0-CODE
              │                       │
        V3.2.0-GUI              V3.2.0-CODE
              │                       │
        V3.3.0-GUI              V3.3.0-CODE
```

A branch:

```text
swing-gui-builder
```

utiliza o editor visual do NetBeans para construção da interface.

A branch:

```text
swing-code
```

é destinada à construção equivalente da interface Swing diretamente por código Java.

Essa estratégia permitirá comparar as duas formas de desenvolvimento mantendo como ponto de partida comum a versão `V3.0.0`.

---

## 📌 Versão atual

### V3.3.0-GUI — Aplicação gráfica final

Nesta versão:

- `Principal` é o ponto de entrada da aplicação;
- `TelaPrincipal` é responsável pela interface gráfica;
- a aplicação pode ser iniciada diretamente com `Run Project`;
- a lógica de negócio permanece separada da camada gráfica;
- `ConsoleUI` permanece no projeto como registro da evolução da aplicação;
- o projeto pode ser clonado e executado em outro ambiente;
- o Source/Binary Format foi definido como JDK 17;
- um JAR executável foi gerado e testado;
- o JAR de distribuição está disponível na pasta `release/`;
- a linha de desenvolvimento utilizando GUI Builder está concluída.

---

## 🔮 Próxima etapa

Com a linha **GUI Builder** concluída, o desenvolvimento seguirá pela branch:

```text
swing-code
```

A interface será reconstruída utilizando diretamente código Java, sem o editor visual do NetBeans.

O objetivo será implementar funcionalidades equivalentes às versões:

```text
V3.1.0-CODE
V3.2.0-CODE
V3.3.0-CODE
```

Ao final, as duas abordagens poderão ser comparadas:

```text
GUI Builder
     │
     ├── construção visual
     └── arquivos .java + .form

Swing por código
     │
     ├── construção programática
     └── arquivos .java
```

Melhorias relacionadas à execução assíncrona de operações, como o uso de `SwingWorker`, poderão ser estudadas em versões futuras.

---

## 📄 Licença

Este projeto é disponibilizado sob a licença MIT.