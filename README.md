# 🔐 Verificador de Senhas Vazadas

Projeto desenvolvido em **Java** com o objetivo de verificar se uma senha aparece em bases de dados de senhas conhecidas por terem sido expostas em vazamentos.

A aplicação utiliza a API **Pwned Passwords**, do serviço Have I Been Pwned, realizando a consulta por meio do modelo de **k-anonymity**.

O projeto também possui finalidade didática, sendo desenvolvido de forma incremental para demonstrar a evolução de uma aplicação Java desde uma implementação procedural simples até uma aplicação estruturada com Programação Orientada a Objetos e interface gráfica.

---

## 🎯 Objetivo do Projeto

O objetivo é desenvolver gradualmente uma aplicação capaz de:

- receber uma senha informada pelo usuário;
- gerar localmente o hash SHA-1 da senha;
- utilizar o modelo de k-anonymity para realizar a consulta;
- consultar a API Pwned Passwords;
- verificar se a senha aparece em vazamentos conhecidos;
- informar a quantidade de ocorrências encontradas;
- tratar possíveis erros durante a execução;
- evoluir progressivamente a estrutura do código.

---

## ⚙️ Funcionamento

A aplicação não envia a senha diretamente para a API.

Primeiramente, o hash SHA-1 é calculado localmente.

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

Somente os **5 primeiros caracteres** do hash são enviados para a API.

A API retorna os sufixos dos hashes que possuem aquele mesmo prefixo, juntamente com a quantidade de ocorrências.

A aplicação compara localmente o sufixo do hash da senha com os sufixos retornados.

---

## 🔄 Fluxo da Aplicação

```text
Senha
  ↓
Validação da entrada
  ↓
Conversão para UTF-8
  ↓
Geração do SHA-1
  ↓
Separação do hash
  ↓
Prefixo (5 caracteres)
  +
Sufixo (35 caracteres)
  ↓
Consulta HTTP utilizando somente o prefixo
  ↓
Resposta da API
  ↓
Comparação local dos sufixos
  ↓
Resultado
```

---

# 📚 Histórico de Versões

## V0.0.0 — Entrada básica da senha

Primeira versão do projeto.

### Principais implementações

- criação da estrutura inicial da aplicação;
- leitura da senha pelo console;
- utilização da classe `Scanner`;
- armazenamento da senha em uma variável `String`.

---

## V0.1.0 — Geração do hash SHA-1

Introdução ao processo de geração do hash da senha.

### Principais implementações

- utilização da classe `MessageDigest`;
- utilização do algoritmo SHA-1;
- conversão da senha para bytes utilizando UTF-8;
- geração do hash da senha;
- utilização de `byte[]`;
- utilização de `StringBuilder`;
- conversão dos bytes para representação hexadecimal.

---

## V0.2.0 — Preparação para k-anonymity

Preparação do hash para utilização do modelo de consulta por prefixo.

### Principais implementações

- conversão do hash para `String`;
- utilização do método `substring()`;
- separação dos 5 primeiros caracteres do SHA-1;
- armazenamento do prefixo;
- armazenamento dos 35 caracteres restantes como sufixo;
- introdução ao conceito de k-anonymity.

Exemplo:

```text
SHA-1:
A9993E364706816ABA3E25717850C26C9CD0D89D

Prefixo:
A9993

Sufixo:
E364706816ABA3E25717850C26C9CD0D89D
```

---

## V0.3.0 — Consulta à API Pwned Passwords

Primeira comunicação da aplicação com um serviço externo.

### Principais implementações

- utilização da classe `HttpClient`;
- utilização da classe `HttpRequest`;
- utilização da classe `HttpResponse`;
- criação de uma requisição HTTP;
- utilização do método HTTP GET;
- construção da URL de consulta;
- envio do prefixo do hash para a API;
- recebimento da resposta HTTP.

A consulta segue o formato:

```text
/range/{prefixo}
```

Exemplo conceitual:

```text
/range/A9993
```

---

## V0.4.0 — Processamento da resposta da API

Implementação da análise dos dados retornados pela API.

### Principais implementações

- obtenção do corpo da resposta HTTP;
- separação da resposta em linhas;
- utilização de `split()`;
- separação entre sufixo e quantidade de ocorrências;
- utilização de `Integer.parseInt()`;
- utilização do `for` aprimorado;
- comparação entre Strings utilizando `equals()`;
- utilização de variável booleana para controle;
- utilização de `break`;
- identificação da quantidade de ocorrências da senha.

Cada linha retornada pela API possui conceitualmente o formato:

```text
SUFIXO:QUANTIDADE
```

A aplicação procura pelo sufixo correspondente ao hash calculado localmente.

---

# 🚀 Versão Atual

## V1.0.0 — Verificador de Senhas Vazadas em Console

A **V1.0.0** representa a primeira versão funcional da aplicação.

Nesta versão, todas as funcionalidades desenvolvidas nas versões anteriores foram integradas em um fluxo completo de execução.

O usuário informa uma senha, o programa gera localmente seu hash SHA-1 e consulta a API Pwned Passwords utilizando o modelo de k-anonymity.

A aplicação também passa a validar a entrada, interpretar o status da resposta HTTP e tratar possíveis falhas durante sua execução.

---

## ✨ Principais Implementações da V1.0.0

- validação da entrada da senha;
- utilização de `do-while`;
- utilização de `isBlank()`;
- gerenciamento do `Scanner` com `try-with-resources`;
- geração local do hash SHA-1;
- utilização explícita de UTF-8;
- separação do hash em prefixo e sufixo;
- consulta à API Pwned Passwords;
- utilização de `HttpClient`;
- utilização de `HttpRequest`;
- utilização de `HttpResponse`;
- envio de requisição HTTP GET;
- processamento da resposta da API;
- identificação da quantidade de ocorrências;
- verificação do código de status HTTP;
- utilização do `switch` com sintaxe moderna (`case ->`);
- tratamento de `NoSuchAlgorithmException`;
- tratamento de `IOException`;
- tratamento de `InterruptedException`;
- apresentação de mensagens amigáveis ao usuário.

---

## ⌨️ Validação da Entrada

A aplicação não permite que uma senha vazia ou composta somente por espaços seja consultada.

A validação utiliza:

```java
do {

    System.out.print("Digite uma senha: ");
    senha = sc.nextLine();

    if (senha.isBlank()) {
        System.out.println(
                "A senha não pode ser vazia. Tente novamente."
        );

        System.out.println();
    }

} while (senha.isBlank());
```

O método `isBlank()` verifica se a `String` está vazia ou contém somente espaços em branco.

A senha não é modificada com `trim()`, pois espaços no início ou no final podem fazer parte da senha original.

---

## 📡 Status HTTP

Após realizar a consulta, a aplicação verifica o código HTTP retornado pelo servidor.

O código:

```text
HTTP 200 - OK
```

indica que a requisição foi processada com sucesso.

Isso **não significa que a senha foi encontrada ou que ela é segura**. Significa apenas que a comunicação HTTP produziu uma resposta de sucesso.

Alguns códigos tratados pela aplicação são:

| Código | Significado | Descrição |
|---:|---|---|
| `200` | OK | Requisição realizada com sucesso |
| `400` | Bad Request | Requisição inválida |
| `404` | Not Found | Recurso não encontrado |
| `429` | Too Many Requests | Muitas requisições |
| `500` | Internal Server Error | Erro interno do servidor |

Para os códigos diferentes de `200`, a aplicação utiliza um `switch` com a sintaxe moderna do Java:

```java
switch (statusCode) {

    case 400 ->
        System.out.println(
                "HTTP 400 - Bad Request: requisição inválida."
        );

    case 404 ->
        System.out.println(
                "HTTP 404 - Not Found: recurso não encontrado."
        );

    case 429 ->
        System.out.println(
                "HTTP 429 - Too Many Requests: muitas requisições."
        );

    case 500 ->
        System.out.println(
                "HTTP 500 - Internal Server Error: erro interno do servidor."
        );

    default ->
        System.out.println(
                "HTTP " + statusCode + " - erro na requisição."
        );
}
```

---

## ⚠️ Tratamento de Exceções

A aplicação trata diferentes situações que podem ocorrer durante sua execução.

### `NoSuchAlgorithmException`

Pode ocorrer durante a obtenção da implementação do algoritmo utilizado pelo `MessageDigest`.

```java
MessageDigest.getInstance("SHA-1");
```

### `IOException`

Pode ocorrer durante operações de entrada/saída, incluindo problemas relacionados à comunicação HTTP.

### `InterruptedException`

Pode ocorrer caso a operação que aguarda a resposta HTTP seja interrompida.

Essas exceções são tratadas com blocos `catch` específicos, evitando que erros técnicos sejam apresentados diretamente ao usuário como um grande *stack trace*.

---

## 🔐 k-anonymity

O projeto utiliza o modelo de consulta por **k-anonymity** empregado pela API Pwned Passwords.

A senha:

```text
MinhaSenha
```

não é enviada diretamente.

O processo ocorre localmente:

```text
Senha
   ↓
SHA-1
   ↓
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   ↓
┌─────────┬─────────────────────────────────────┐
│ Prefixo │                Sufixo               │
│ 5 chars │               35 chars              │
└─────────┴─────────────────────────────────────┘
     │
     │ enviado
     ▼
    API

Sufixo permanece local
```

A API recebe apenas o prefixo.

Depois que a resposta é recebida, a comparação com o sufixo é realizada localmente pela aplicação.

---

# 🔐 Observações sobre Segurança

O algoritmo **SHA-1 não é recomendado para armazenamento de senhas**.

Ele é utilizado neste projeto porque faz parte do mecanismo de consulta adotado pela API Pwned Passwords.

Neste projeto:

- a senha é processada localmente;
- o hash SHA-1 é calculado localmente;
- a senha original não é enviada na consulta;
- o hash SHA-1 completo não é enviado na consulta;
- somente os 5 primeiros caracteres do hash são utilizados para realizar a consulta;
- a comparação final ocorre localmente.

O uso desse mecanismo reduz a exposição da informação durante a consulta.

---

## ⚠️ Senha não encontrada não significa senha segura

Uma mensagem como:

```text
Senha não encontrada nos vazamentos consultados.
```

não significa:

```text
Senha segura.
```

Ela significa apenas que o hash correspondente não foi encontrado entre os registros retornados pela base consultada.

A segurança de uma senha envolve outros fatores, como comprimento, previsibilidade, reutilização e práticas adequadas de autenticação.

---

# 🧠 Conceitos Trabalhados até a V1.0.0

Durante a evolução do projeto foram utilizados conceitos como:

- variáveis;
- `String`;
- arrays de bytes;
- `Scanner`;
- entrada de dados;
- estruturas condicionais;
- `if-else`;
- `switch`;
- `switch` com `case ->`;
- estruturas de repetição;
- `do-while`;
- `for` aprimorado;
- métodos de `String`;
- `isBlank()`;
- `substring()`;
- `split()`;
- `equals()`;
- `StringBuilder`;
- conversão entre tipos;
- `Integer.parseInt()`;
- codificação UTF-8;
- hashing;
- SHA-1;
- k-anonymity;
- requisições HTTP;
- método GET;
- códigos de status HTTP;
- consumo de API;
- tratamento de exceções;
- `try-catch`;
- `try-with-resources`.

---

# 🏗️ Estrutura Atual

Apesar de funcional, a aplicação ainda concentra grande parte de suas responsabilidades no método:

```java
public static void main(String[] args)
```

O `main()` atualmente é responsável por atividades como:

```text
main()
 │
 ├── leitura da senha
 │
 ├── validação
 │
 ├── geração do SHA-1
 │
 ├── separação prefixo/sufixo
 │
 ├── criação do cliente HTTP
 │
 ├── construção da requisição
 │
 ├── comunicação com a API
 │
 ├── interpretação do status HTTP
 │
 ├── processamento da resposta
 │
 └── apresentação do resultado
```

A aplicação funciona, mas o método principal começa a acumular muitas responsabilidades.

Essa limitação será utilizada como motivação para a próxima evolução do projeto.

---

# 🔜 Próxima Versão

## V1.1.0 — Modularização

A próxima versão terá como objetivo reorganizar o código da aplicação, reduzindo as responsabilidades atualmente concentradas no método `main()`.

A aplicação continuará utilizando uma abordagem procedural, mas as diferentes responsabilidades serão distribuídas em métodos.

Algumas funcionalidades candidatas à modularização são:

```text
lerSenha()
gerarHash()
obterPrefixo()
obterSufixo()
consultarApi()
processarResposta()
exibirStatusHttp()
exibirResultado()
```

A ideia será transformar gradualmente:

```text
main()
 │
 ├── faz tudo
 ├── faz tudo
 ├── faz tudo
 └── faz tudo
```

em:

```text
main()
 │
 ├── lerSenha()
 ├── gerarHash()
 ├── consultarApi()
 ├── processarResposta()
 └── exibirResultado()
```

Essa reorganização permitirá trabalhar conceitos como:

- declaração de métodos;
- parâmetros;
- argumentos;
- retorno de métodos;
- `void`;
- escopo de variáveis;
- reutilização de código;
- separação de responsabilidades.

A **V1.1.0** continuará sendo uma aplicação procedural.

A introdução de **classes, objetos, atributos e métodos de instância** ficará para uma evolução posterior do projeto.

---

# 🗺️ Evolução do Projeto

```text
V0.0.0
Entrada da senha
    ↓
V0.1.0
Geração do SHA-1
    ↓
V0.2.0
Prefixo + Sufixo
    ↓
V0.3.0
Consulta à API
    ↓
V0.4.0
Processamento da resposta
    ↓
V1.0.0
Aplicação funcional em console
    ↓
V1.1.0
Modularização com métodos
    ↓
V2.0.0
Programação Orientada a Objetos
    ↓
V3.x
Interface gráfica com Swing
```

---

## 👨‍🏫 Contexto Acadêmico

Projeto desenvolvido como material didático para a disciplina de **Programação Orientada a Objetos (POO)**.

A evolução incremental das versões permite observar como um programa inicialmente simples pode ser progressivamente melhorado até atingir uma estrutura mais organizada e orientada a objetos.

---

## 📄 Licença

Este projeto é distribuído sob a licença **MIT**.