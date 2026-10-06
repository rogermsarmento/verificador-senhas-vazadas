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

## V1.0.0 — Verificador de Senhas Vazadas em Console

A **V1.0.0** representa a primeira versão funcional e estável da aplicação em console.

Nesta versão, todas as funcionalidades desenvolvidas anteriormente foram integradas em um único fluxo de execução.

### Principais implementações

- validação da entrada da senha;
- utilização de `do-while`;
- utilização de `isBlank()`;
- gerenciamento do `Scanner` com `try-with-resources`;
- geração local do hash SHA-1;
- utilização explícita de UTF-8;
- separação do hash em prefixo e sufixo;
- consulta à API Pwned Passwords;
- utilização de `HttpClient`, `HttpRequest` e `HttpResponse`;
- processamento da resposta da API;
- identificação da quantidade de ocorrências;
- verificação do código de status HTTP;
- utilização do `switch` com sintaxe moderna (`case ->`);
- tratamento de `NoSuchAlgorithmException`;
- tratamento de `IOException`;
- tratamento de `InterruptedException`;
- apresentação de mensagens amigáveis ao usuário.

Embora funcional, grande parte das responsabilidades da aplicação ainda estava concentrada no método `main()`.

Essa característica motivou a modularização realizada na V1.1.0.

---

# 🚀 Versão Atual

## V1.1.0 — Modularização com Métodos

A **V1.1.0** reorganiza internamente a aplicação utilizando métodos.

O comportamento externo do programa permanece essencialmente o mesmo da V1.0.0. A principal mudança está na **estrutura e organização do código**.

Na versão anterior, diferentes responsabilidades estavam concentradas no método `main()`.

Na V1.1.0, essas responsabilidades foram separadas em métodos específicos, tornando o código mais organizado, legível e preparado para as próximas evoluções do projeto.

---

## ✨ Principais Implementações da V1.1.0

- criação de métodos para diferentes responsabilidades;
- redução das responsabilidades do método `main()`;
- utilização de parâmetros e argumentos;
- utilização de diferentes tipos de retorno;
- utilização de métodos `void`;
- passagem de objetos como argumentos;
- utilização de `return`;
- propagação de exceções utilizando `throws`;
- separação entre processamento e apresentação;
- organização do fluxo principal da aplicação;
- eliminação de algumas variáveis de controle desnecessárias;
- melhoria da legibilidade;
- preparação para a futura transição para Programação Orientada a Objetos.

---

## 🧩 Métodos Implementados

A classe `Principal` passa a possuir a seguinte organização:

```text
Principal
│
├── main(String[] args)
│
├── exibirCabecalho()
├── lerSenha(Scanner sc)
├── gerarHash(String senha)
├── obterPrefixo(String hash)
├── obterSufixo(String hash)
├── consultarApi(String prefixo)
├── exibirStatusHttp(int statusCode)
├── buscarQuantidade(String corpoResposta, String sufixo)
└── exibirResultado(int quantidadeEncontrada)
```

Cada método possui uma responsabilidade específica.

| Método | Responsabilidade |
|---|---|
| `main()` | Coordenar o fluxo principal da aplicação |
| `exibirCabecalho()` | Apresentar o cabeçalho inicial |
| `lerSenha()` | Ler e validar a senha informada pelo usuário |
| `gerarHash()` | Gerar o hash SHA-1 da senha |
| `obterPrefixo()` | Obter os 5 primeiros caracteres do hash |
| `obterSufixo()` | Obter os 35 caracteres restantes do hash |
| `consultarApi()` | Realizar a consulta à API Pwned Passwords |
| `exibirStatusHttp()` | Apresentar informações sobre o status HTTP |
| `buscarQuantidade()` | Procurar o sufixo e retornar o número de ocorrências |
| `exibirResultado()` | Apresentar o resultado final ao usuário |

---

## 🧭 O `main()` como Orquestrador

Na V1.0.0, o método `main()` realizava diretamente grande parte das operações da aplicação.

Conceitualmente:

```text
main()
 │
 ├── leitura da senha
 ├── validação
 ├── geração do SHA-1
 ├── separação prefixo/sufixo
 ├── criação do cliente HTTP
 ├── construção da requisição
 ├── comunicação com a API
 ├── interpretação do status HTTP
 ├── processamento da resposta
 └── apresentação do resultado
```

Na V1.1.0, o `main()` passa a coordenar métodos responsáveis por essas operações:

```text
main()
 │
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

Assim, o método principal passa a funcionar principalmente como um **orquestrador do fluxo da aplicação**.

---

## 🔄 Fluxo Modularizado

```text
exibirCabecalho()
       ↓
   lerSenha()
       ↓
   gerarHash()
       ↓
 ┌─────┴─────────┐
 ↓               ↓
obterPrefixo()  obterSufixo()
 ↓               ↓
 └───────┬───────┘
         ↓
   consultarApi()
         ↓
 exibirStatusHttp()
         ↓
  status HTTP 200?
      /       \
    sim       não
     ↓         ↓
buscarQuantidade()
     ↓
exibirResultado()
```

A organização em métodos permite compreender o fluxo geral da aplicação sem precisar observar imediatamente os detalhes internos de cada operação.

---

## 📥 Parâmetros e Argumentos

A V1.1.0 permite trabalhar explicitamente a diferença entre **parâmetros** e **argumentos**.

Por exemplo:

```java
static String gerarHash(String senha)
```

Nesse caso:

```java
String senha
```

é o parâmetro definido pelo método.

Quando fazemos:

```java
String hash = gerarHash(senha);
```

o valor passado na chamada é o argumento.

Outro exemplo utiliza dois parâmetros:

```java
static int buscarQuantidade(
        String corpoResposta,
        String sufixo
)
```

e sua chamada pode ser realizada com:

```java
int quantidadeEncontrada =
        buscarQuantidade(corpoResposta, sufixo);
```

---

## ↩️ Retorno de Métodos

Alguns métodos precisam produzir um valor que será utilizado posteriormente.

Por exemplo:

```java
static String obterPrefixo(String hash) {
    return hash.substring(0, 5);
}
```

O método recebe uma `String` e retorna outra `String`.

Sua utilização ocorre da seguinte forma:

```java
String prefixo = obterPrefixo(hash);
```

Conceitualmente:

```text
hash
 ↓
obterPrefixo()
 ↓
String
 ↓
prefixo
```

---

## 🚫 Métodos `void`

Nem todos os métodos precisam retornar valores.

Por exemplo:

```java
static void exibirCabecalho() {
    System.out.println("==============================");
    System.out.println("   VERIFICADOR DE SENHAS");
    System.out.println("==============================");
    System.out.println();
}
```

Esse método apenas executa uma ação.

Outro exemplo:

```java
static void exibirResultado(int quantidadeEncontrada)
```

Ele recebe uma informação, mas sua responsabilidade é apenas apresentá-la ao usuário.

---

## 🧪 Diferentes Tipos de Métodos

A V1.1.0 permite observar diferentes combinações de parâmetros e retornos.

### Recebe parâmetro e retorna valor

```java
static String obterPrefixo(String hash)
```

### Recebe parâmetro e não retorna valor

```java
static void exibirResultado(int quantidadeEncontrada)
```

### Não recebe parâmetro e não retorna valor

```java
static void exibirCabecalho()
```

### Recebe parâmetro, retorna valor e pode propagar exceções

```java
static HttpResponse<String> consultarApi(String prefixo)
        throws IOException, InterruptedException
```

Essas diferentes situações permitem compreender melhor a construção e utilização de métodos em Java.

---

## 🔎 Busca da Quantidade de Ocorrências

Na V1.0.0, o processamento da resposta utilizava duas variáveis para controlar a busca:

```java
boolean encontrado;
int quantidadeEncontrada;
```

Na V1.1.0, essa lógica foi encapsulada no método:

```java
static int buscarQuantidade(
        String corpoResposta,
        String sufixo
)
```

Quando o sufixo é encontrado, o método retorna diretamente sua quantidade:

```java
if (sufixo.equals(sufixoRetornado)) {
    return quantidade;
}
```

Caso todo o conteúdo seja percorrido sem encontrar o sufixo:

```java
return 0;
```

Assim:

```text
quantidade > 0
      ↓
senha encontrada


quantidade == 0
      ↓
senha não encontrada
```

Essa alteração também permite demonstrar que `return` encerra a execução do método, eliminando nesse ponto a necessidade de uma variável booleana e do comando `break`.

---

## ⌨️ Validação da Entrada

A responsabilidade pela leitura e validação da senha passa a ser encapsulada em:

```java
static String lerSenha(Scanner sc)
```

O método utiliza:

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

return senha;
```

O `Scanner` criado no `main()` é passado para o método:

```java
String senha = lerSenha(sc);
```

Isso permite reutilizar o mesmo objeto responsável pela entrada de dados.

A senha não é modificada com `trim()`, pois espaços no início ou no final podem fazer parte da senha original.

---

## 📡 Consulta à API

A comunicação HTTP passa a ser encapsulada no método:

```java
static HttpResponse<String> consultarApi(String prefixo)
        throws IOException, InterruptedException
```

Esse método é responsável por:

- construir a URL;
- criar o `HttpClient`;
- construir o `HttpRequest`;
- enviar a requisição GET;
- retornar o `HttpResponse<String>`.

Conceitualmente:

```text
prefixo
   ↓
consultarApi()
   ↓
HttpClient
   ↓
HttpRequest
   ↓
GET
   ↓
HttpResponse<String>
   ↓
return
```

---

## 📡 Status HTTP

A apresentação dos códigos HTTP foi encapsulada em:

```java
static void exibirStatusHttp(int statusCode)
```

O método utiliza o `switch` com a sintaxe moderna do Java:

```java
switch (statusCode) {

    case 200 ->
        System.out.println(
                "HTTP 200 - OK: requisição realizada com sucesso."
        );

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
                "HTTP " + statusCode
                + " - código de resposta não tratado."
        );
}
```

O código:

```text
HTTP 200 - OK
```

indica que a requisição HTTP foi realizada com sucesso.

Isso **não significa que a senha foi encontrada ou que ela é segura**.

---

## ⚠️ Propagação e Tratamento de Exceções

A modularização também permite observar a propagação de exceções entre métodos.

O método:

```java
static String gerarHash(String senha)
        throws NoSuchAlgorithmException
```

pode propagar:

```java
NoSuchAlgorithmException
```

Já:

```java
static HttpResponse<String> consultarApi(String prefixo)
        throws IOException, InterruptedException
```

pode propagar:

```java
IOException
InterruptedException
```

Essas exceções continuam sendo tratadas no fluxo principal da aplicação.

Conceitualmente:

```text
main()
  ↓
consultarApi()
  ↓
client.send()
  ↓
IOException / InterruptedException
  ↓
throws
  ↓
main()
  ↓
catch
```

Isso permite separar duas responsabilidades:

```text
método
   ↓
executa sua operação

main()
   ↓
coordena o fluxo e trata falhas
```

---

## 🔐 k-anonymity

O projeto continua utilizando o modelo de consulta por **k-anonymity** empregado pela API Pwned Passwords.

A senha não é enviada diretamente.

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
     ↓
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

# 🧠 Conceitos Trabalhados até a V1.1.0

Durante a evolução do projeto foram utilizados conceitos como:

### Fundamentos de Java

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
- `for` aprimorado.

### Manipulação de dados

- métodos de `String`;
- `isBlank()`;
- `substring()`;
- `split()`;
- `equals()`;
- `StringBuilder`;
- conversão entre tipos;
- `Integer.parseInt()`;
- codificação UTF-8.

### Métodos

- declaração de métodos;
- chamada de métodos;
- parâmetros;
- argumentos;
- tipos de retorno;
- `return`;
- `void`;
- escopo de variáveis;
- passagem de objetos como argumentos;
- separação de responsabilidades;
- modularização.

### Segurança e comunicação

- hashing;
- SHA-1;
- k-anonymity;
- requisições HTTP;
- método GET;
- códigos de status HTTP;
- consumo de API.

### Tratamento de recursos e exceções

- `try-catch`;
- `try-with-resources`;
- `throws`;
- propagação de exceções;
- `NoSuchAlgorithmException`;
- `IOException`;
- `InterruptedException`.

---

# 🏗️ Estrutura Atual

A aplicação continua utilizando uma abordagem procedural.

Todos os métodos permanecem na classe:

```java
public class Principal
```

e são declarados como métodos `static`.

A principal diferença em relação à V1.0.0 é a distribuição das responsabilidades:

```text
Principal
│
├── main()
│    └── coordena o fluxo
│
├── exibirCabecalho()
│    └── apresentação inicial
│
├── lerSenha()
│    └── entrada e validação
│
├── gerarHash()
│    └── geração do SHA-1
│
├── obterPrefixo()
│    └── preparação para k-anonymity
│
├── obterSufixo()
│    └── preparação para comparação local
│
├── consultarApi()
│    └── comunicação HTTP
│
├── exibirStatusHttp()
│    └── apresentação do status
│
├── buscarQuantidade()
│    └── processamento da resposta
│
└── exibirResultado()
     └── apresentação do resultado
```

---

## 🧱 Modularização não é Programação Orientada a Objetos

A V1.1.0 introduz **modularização com métodos**, mas a aplicação ainda não foi reorganizada utilizando classes próprias para representar diferentes responsabilidades.

Portanto:

```text
V1.0.0
Aplicação procedural
com lógica concentrada no main()
        ↓
V1.1.0
Aplicação procedural
modularizada com métodos
        ↓
V2.0.0
Introdução à
Programação Orientada a Objetos
```

Essa separação é proposital.

Primeiro, o projeto demonstra como um programa procedural pode ser organizado utilizando métodos.

Na próxima grande evolução, essas responsabilidades poderão ser distribuídas entre diferentes classes e objetos.

---

# 🔜 Próxima Versão

## V2.0.0 — Introdução à Programação Orientada a Objetos

A próxima grande versão terá como objetivo transformar gradualmente a aplicação procedural modularizada em uma aplicação estruturada segundo os princípios da **Programação Orientada a Objetos**.

As responsabilidades atualmente concentradas na classe `Principal` poderão ser distribuídas entre diferentes classes.

A V2.0.0 permitirá trabalhar conceitos como:

- classes;
- objetos;
- atributos;
- métodos de instância;
- instanciação;
- construtores;
- encapsulamento;
- relacionamento entre objetos;
- separação de responsabilidades entre classes.

A ideia será evoluir de:

```text
Principal
│
├── main()
├── gerarHash()
├── consultarApi()
├── buscarQuantidade()
└── ...
```

para uma estrutura em que diferentes objetos possuam responsabilidades específicas.

Conceitualmente:

```text
Principal
    │
    ▼
objetos responsáveis
por diferentes tarefas
    │
    ├── processamento
    ├── consulta
    └── resultado
```

A definição exata das classes será realizada durante o desenvolvimento da V2.0.0.

A interface gráfica ainda não será o foco dessa versão.

A aplicação continuará inicialmente em console para que a introdução à Programação Orientada a Objetos possa ser estudada separadamente da implementação com Java Swing.

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

A estratégia adotada permite estudar separadamente:

```text
fundamentos
    ↓
aplicação procedural
    ↓
modularização
    ↓
Programação Orientada a Objetos
    ↓
interface gráfica
```

Dessa forma, cada nova versão introduz novos conceitos sem esconder as limitações e decisões existentes nas versões anteriores.

---

## 📄 Licença

Este projeto é distribuído sob a licença **MIT**.