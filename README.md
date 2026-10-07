# 🔐 Verificador de Senhas Vazadas

Projeto desenvolvido em **Java** com o objetivo de verificar se uma senha aparece em bases de dados de senhas conhecidas por terem sido expostas em vazamentos.

A aplicação utiliza a API **Pwned Passwords**, do serviço Have I Been Pwned, realizando a consulta por meio do modelo de **k-anonymity**.

O projeto também possui finalidade didática, sendo desenvolvido de forma incremental para demonstrar a evolução de uma aplicação Java desde uma implementação procedural simples até uma aplicação estruturada com Programação Orientada a Objetos e, futuramente, interface gráfica.

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
VerificadorSenha
  ↓
Geração local do SHA-1
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
Quantidade de ocorrências
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

Introdução ao processo de geração local do hash da senha.

### Principais implementações

- utilização da classe `MessageDigest`;
- utilização do algoritmo SHA-1;
- conversão da senha para bytes utilizando UTF-8;
- utilização de `byte[]`;
- utilização de `StringBuilder`;
- conversão dos bytes para representação hexadecimal.

---

## V0.2.0 — Preparação para k-anonymity

Preparação do hash para utilização do modelo de consulta por prefixo.

### Principais implementações

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

- utilização de `HttpClient`;
- utilização de `HttpRequest`;
- utilização de `HttpResponse`;
- criação de requisição HTTP;
- utilização do método GET;
- construção da URL;
- envio somente do prefixo do hash;
- recebimento da resposta HTTP.

A consulta segue conceitualmente:

```text
/range/{prefixo}
```

---

## V0.4.0 — Processamento da resposta da API

Implementação da análise dos dados retornados pela API.

### Principais implementações

- obtenção do corpo da resposta HTTP;
- separação da resposta em linhas;
- utilização de `split()`;
- separação entre sufixo e quantidade;
- utilização de `Integer.parseInt()`;
- utilização do `for` aprimorado;
- comparação de Strings com `equals()`;
- identificação da quantidade de ocorrências.

Cada linha retornada possui conceitualmente:

```text
SUFIXO:QUANTIDADE
```

---

## V1.0.0 — Verificador de Senhas Vazadas em Console

Primeira versão funcional e estável da aplicação.

### Principais implementações

- integração das funcionalidades anteriores;
- validação da entrada;
- utilização de `do-while` e `isBlank()`;
- gerenciamento do `Scanner` com `try-with-resources`;
- geração local do SHA-1;
- consulta à API Pwned Passwords;
- processamento da resposta;
- identificação da quantidade de ocorrências;
- tratamento de códigos HTTP;
- utilização do `switch` com `case ->`;
- tratamento de exceções;
- apresentação de mensagens ao usuário.

Embora funcional, grande parte das responsabilidades ainda estava concentrada no método `main()`.

---

## V1.1.0 — Modularização com Métodos

Reorganização da aplicação funcional utilizando métodos específicos, ainda mantendo a abordagem procedural.

### Principais implementações

- criação de métodos para diferentes responsabilidades;
- redução das responsabilidades do `main()`;
- utilização de parâmetros e argumentos;
- utilização de diferentes tipos de retorno;
- utilização de métodos `void`;
- utilização de `return`;
- propagação de exceções com `throws`;
- separação inicial entre processamento e apresentação;
- organização do fluxo principal da aplicação.

A classe `Principal` passou a possuir:

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

Apesar da modularização, todos esses métodos ainda pertenciam à mesma classe e permaneciam `static`.

Essa limitação motivou a evolução para a V2.0.0.

---

# 🚀 Versão Atual

## V2.0.0 — Introdução à Programação Orientada a Objetos

A **V2.0.0** representa a transição do projeto de uma aplicação procedural modularizada para uma estrutura baseada em **Programação Orientada a Objetos**.

O comportamento principal da aplicação permanece o mesmo: receber uma senha, consultar a base Pwned Passwords e informar se ela aparece em vazamentos conhecidos.

A principal evolução está na **arquitetura interna**.

As responsabilidades anteriormente concentradas na classe `Principal` foram distribuídas entre diferentes classes e objetos.

---

## ✨ Principais Implementações da V2.0.0

- criação de classes com responsabilidades específicas;
- criação e instanciação de objetos;
- utilização de métodos de instância;
- introdução de atributos;
- utilização de construtores;
- utilização da palavra-chave `this`;
- aplicação de encapsulamento;
- utilização dos modificadores `public` e `private`;
- utilização de atributos `final`;
- organização das classes em pacotes;
- definição de responsabilidades entre classes;
- melhoria da coesão;
- criação de dependências entre objetos;
- injeção de dependência por construtor;
- criação da classe `VerificadorSenha`;
- separação entre interação com o usuário e regra de verificação;
- redução do conhecimento técnico necessário pela classe `Principal`.

---

# 🏗️ Estrutura da V2.0.0

As classes estão organizadas em pacotes de acordo com suas responsabilidades:

```text
src/
└── br/edu/
    ├── principal/
    │   └── Principal.java
    │
    ├── service/
    │   ├── HashService.java
    │   └── PwnedPasswordService.java
    │
    └── verificador/
        └── VerificadorSenha.java
```

A arquitetura pode ser representada por:

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

---

## 🧩 Responsabilidades das Classes

| Classe | Responsabilidade |
|---|---|
| `Principal` | Inicializar a aplicação, receber a entrada e apresentar o resultado |
| `VerificadorSenha` | Coordenar o processo completo de verificação |
| `HashService` | Gerar o SHA-1 e separar prefixo e sufixo |
| `PwnedPasswordService` | Realizar a consulta e interpretar os dados retornados pela API |

Essa divisão permite que cada classe tenha uma responsabilidade mais bem definida.

---

# 🔄 Evolução da Arquitetura

Na V1.1.0:

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

Todas as funcionalidades permaneciam na mesma classe.

Na V2.0.0:

```text
Principal
│
├── main()
├── exibirCabecalho()
├── lerSenha()
└── exibirResultado()

              │
              ▼

       VerificadorSenha
          /        \
         ▼          ▼
 HashService   PwnedPasswordService
```

A quantidade total de código não necessariamente diminui.

O objetivo da mudança é **organizar responsabilidades, melhorar a coesão e reduzir o acoplamento entre as diferentes partes da aplicação**.

---

# 🧱 Classes e Objetos

A V2.0.0 introduz explicitamente a criação de objetos próprios da aplicação.

Exemplo:

```java
HashService hashService = new HashService();
```

Nesse comando:

```text
HashService
     ↓
tipo da referência

hashService
     ↓
variável de referência

new HashService()
     ↓
criação do objeto
```

Outro objeto é criado para comunicação com a API:

```java
PwnedPasswordService pwnedPasswordService
        = new PwnedPasswordService(
                "https://api.pwnedpasswords.com/range/"
        );
```

Esses objetos são utilizados para construir o verificador:

```java
VerificadorSenha verificador
        = new VerificadorSenha(
                hashService,
                pwnedPasswordService
        );
```

---

# ⚙️ Métodos de Instância

Na V1.1.0, os métodos da aplicação eram declarados como `static`.

Exemplo:

```java
static String gerarHash(String senha)
```

Na V2.0.0, comportamentos relacionados a objetos passam a ser métodos de instância.

Exemplo:

```java
public String gerarHash(String senha)
```

A chamada passa a ocorrer através de um objeto:

```java
hashService.gerarHash(senha);
```

Conceitualmente:

```text
objeto
  ↓
hashService
  ↓
gerarHash()
```

---

# 📦 Organização em Pacotes

A V2.0.0 também reorganiza as classes em diferentes pacotes.

```text
br.edu.principal
└── Principal

br.edu.service
├── HashService
└── PwnedPasswordService

br.edu.verificador
└── VerificadorSenha
```

O pacote:

```text
br.edu.principal
```

contém o ponto de entrada e a interação em console.

O pacote:

```text
br.edu.service
```

concentra serviços técnicos utilizados pela aplicação.

O pacote:

```text
br.edu.verificador
```

contém a classe responsável por coordenar a regra principal de verificação.

Essa separação também permite observar na prática a utilização de `import` e os diferentes níveis de acesso entre classes de pacotes distintos.

---

# 🔒 Encapsulamento

A V2.0.0 introduz o conceito de encapsulamento.

Por exemplo, em `PwnedPasswordService`:

```java
private final String urlBase;
```

O atributo `urlBase` representa um detalhe interno do objeto.

Outras classes não precisam acessá-lo diretamente.

Em vez disso, utilizam os comportamentos públicos disponibilizados pela classe:

```java
public HttpResponse<String> consultarApi(String prefixo)
```

O mesmo princípio aparece em `VerificadorSenha`:

```java
private final HashService hashService;
private final PwnedPasswordService pwnedPasswordService;
```

Essas dependências fazem parte do estado interno do objeto.

> Encapsulamento não significa criar automaticamente getters e setters para todos os atributos.

Um dado deve ser exposto somente quando existir uma necessidade real para isso.

---

# 🔑 Modificadores de Acesso

Nesta versão são utilizados principalmente:

```text
public
private
```

`public` é utilizado nos comportamentos que precisam ser acessados por outras classes.

Exemplo:

```java
public String gerarHash(String senha)
```

`private` é utilizado para proteger detalhes internos:

```java
private final String urlBase;
```

Também foi estudado o acesso padrão, conhecido como **package-private**, que permite acesso entre classes pertencentes ao mesmo pacote.

---

# 🏗️ Construtores

Os construtores passaram a ser utilizados para definir o estado inicial dos objetos.

Exemplo:

```java
public PwnedPasswordService(String urlBase) {
    this.urlBase = urlBase;
}
```

Nesse caso, a URL é fornecida no momento da criação:

```java
new PwnedPasswordService(
        "https://api.pwnedpasswords.com/range/"
);
```

Outro exemplo ocorre em `VerificadorSenha`:

```java
public VerificadorSenha(
        HashService hashService,
        PwnedPasswordService pwnedPasswordService
) {
    this.hashService = hashService;
    this.pwnedPasswordService = pwnedPasswordService;
}
```

---

# 👉 Palavra-chave `this`

A palavra-chave `this` referencia o objeto atual.

Por exemplo:

```java
this.hashService = hashService;
```

Temos:

```text
this.hashService
      ↓
atributo do objeto

hashService
      ↓
parâmetro recebido pelo construtor
```

Portanto, o atributo do objeto recebe a referência fornecida através do construtor.

---

# 🔗 Dependências entre Objetos

A classe `VerificadorSenha` precisa de dois objetos para realizar sua responsabilidade:

```text
VerificadorSenha
       │
       ├── HashService
       └── PwnedPasswordService
```

Esses objetos são suas **dependências**.

Em vez de criá-los internamente, eles são recebidos pelo construtor:

```java
new VerificadorSenha(
        hashService,
        pwnedPasswordService
);
```

Essa abordagem representa uma forma simples de **injeção de dependência por construtor**.

Conceitualmente:

```text
Principal
   │
   ├── cria HashService
   │
   ├── cria PwnedPasswordService
   │
   └── entrega os objetos
              ↓
       VerificadorSenha
```

O `VerificadorSenha` sabe utilizar seus colaboradores, mas não precisa decidir como criá-los.

---

# 🔍 Classe `HashService`

A classe `HashService` concentra as operações relacionadas ao hash:

```text
HashService
│
├── gerarHash()
├── obterPrefixo()
└── obterSufixo()
```

Responsabilidades:

- gerar o SHA-1;
- converter a senha utilizando UTF-8;
- produzir a representação hexadecimal;
- obter os cinco primeiros caracteres;
- obter os 35 caracteres restantes.

Assim, detalhes como:

```java
MessageDigest
StandardCharsets
StringBuilder
```

não precisam ser conhecidos pela classe `Principal`.

---

# 📡 Classe `PwnedPasswordService`

A classe `PwnedPasswordService` concentra a comunicação com a API.

```text
PwnedPasswordService
│
├── urlBase
├── consultarApi()
└── buscarQuantidade()
```

Ela é responsável por:

- armazenar a URL base utilizada na consulta;
- criar o `HttpClient`;
- criar o `HttpRequest`;
- realizar a requisição GET;
- obter o `HttpResponse`;
- processar as linhas retornadas;
- procurar pelo sufixo correspondente;
- retornar a quantidade encontrada.

Dessa forma, detalhes como:

```java
URI
HttpClient
HttpRequest
```

ficam concentrados no serviço responsável pela comunicação externa.

---

# 🔎 Classe `VerificadorSenha`

A classe `VerificadorSenha` representa a coordenação da regra principal da aplicação.

Seu principal método é:

```java
public int verificar(String senha)
```

Internamente, o processo ocorre da seguinte forma:

```text
senha
  ↓
HashService
  ↓
SHA-1
  ↓
prefixo + sufixo
  ↓
PwnedPasswordService
  ↓
consulta à API
  ↓
comparação do sufixo
  ↓
quantidade
```

Com isso, a `Principal` não precisa conhecer a sequência detalhada necessária para verificar a senha.

Ela simplesmente solicita:

```java
int quantidadeEncontrada
        = verificador.verificar(senha);
```

---

# ↩️ Resultado Válido × Falha na Consulta

O método `verificar()` diferencia um resultado válido de uma falha durante a consulta.

```text
Consulta realizada corretamente
        │
        ├── senha encontrada
        │        ↓
        │   return quantidade > 0
        │
        └── senha não encontrada
                 ↓
             return 0
```

Por outro lado:

```text
Consulta não realizada corretamente
        │
        ▼
      throw
        │
        ▼
    IOException
```

Uma resposta HTTP de erro não deve ser interpretada como:

```java
return 0;
```

Isso faria a aplicação confundir:

```text
senha não encontrada
```

com:

```text
não foi possível verificar a senha
```

Por isso, quando a resposta HTTP não representa uma consulta bem-sucedida, o método executa:

```java
throw new IOException(
        "Erro HTTP ao consultar a API: " + statusCode
);
```

Assim:

| Situação | Comportamento |
|---|---|
| Consulta OK + senha encontrada | `return quantidade > 0` |
| Consulta OK + senha não encontrada | `return 0` |
| Consulta não concluída corretamente | `throw IOException` |

---

# ⚠️ Propagação e Tratamento de Exceções

A separação em classes mantém a propagação das exceções.

Por exemplo:

```text
HashService
    │
    │ NoSuchAlgorithmException
    ▼
VerificadorSenha
    │
    │ throws
    ▼
Principal
    │
    └── catch
```

Na comunicação:

```text
PwnedPasswordService
    │
    │ IOException
    │ InterruptedException
    ▼
VerificadorSenha
    │
    │ throws
    ▼
Principal
    │
    └── catch
```

Dessa forma, as classes responsáveis pelo processamento podem propagar as falhas, enquanto a camada de interação decide como apresentá-las ao usuário.

---

# 🧭 A `Principal` na V2.0.0

A classe `Principal` fica responsável principalmente por:

```text
Principal
│
├── criar os objetos
├── conectar suas dependências
├── exibir o cabeçalho
├── receber a senha
├── solicitar a verificação
└── apresentar o resultado
```

Seu fluxo principal passa a ser conceitualmente:

```text
criar objetos
     ↓
exibirCabecalho()
     ↓
lerSenha()
     ↓
verificador.verificar(senha)
     ↓
exibirResultado()
```

A `Principal` deixa de conhecer diretamente detalhes como:

```text
MessageDigest
StandardCharsets
URI
HttpClient
HttpRequest
HttpResponse
```

Isso reduz o acoplamento da interação com os detalhes técnicos da aplicação.

---

# 🔐 k-anonymity

O projeto continua utilizando o modelo de consulta por **k-anonymity** empregado pela API Pwned Passwords.

A senha não é enviada diretamente.

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

A API recebe somente o prefixo.

Depois que a resposta é recebida, a comparação com o sufixo é realizada localmente.

---

# 🔐 Observações sobre Segurança

O algoritmo **SHA-1 não é recomendado para armazenamento de senhas**.

Ele é utilizado neste projeto porque faz parte do mecanismo de consulta adotado pela API Pwned Passwords.

Neste projeto:

- a senha é processada localmente;
- o SHA-1 é calculado localmente;
- a senha original não é enviada na consulta;
- o SHA-1 completo não é enviado na consulta;
- somente os 5 primeiros caracteres são utilizados na consulta;
- a comparação final ocorre localmente.

O uso desse mecanismo reduz a exposição da informação durante a consulta.

---

## ⚠️ Senha não encontrada não significa senha segura

A mensagem:

```text
Senha não encontrada nos vazamentos consultados.
```

não significa:

```text
Senha segura.
```

Significa apenas que o hash correspondente não foi encontrado entre os registros consultados.

A segurança de uma senha envolve outros fatores, como comprimento, previsibilidade, reutilização e práticas adequadas de autenticação.

---

# 🧠 Conceitos Trabalhados até a V2.0.0

## Fundamentos de Java

- variáveis;
- `String`;
- arrays de bytes;
- `Scanner`;
- entrada de dados;
- estruturas condicionais;
- `if-else`;
- `switch`;
- `case ->`;
- estruturas de repetição;
- `do-while`;
- `for` aprimorado.

## Manipulação de Dados

- métodos de `String`;
- `isBlank()`;
- `substring()`;
- `split()`;
- `equals()`;
- `StringBuilder`;
- conversão entre tipos;
- `Integer.parseInt()`;
- UTF-8.

## Métodos

- declaração e chamada de métodos;
- parâmetros;
- argumentos;
- tipos de retorno;
- `return`;
- `void`;
- escopo;
- métodos `static`;
- métodos de instância;
- propagação com `throws`.

## Programação Orientada a Objetos

- classes;
- objetos;
- referências;
- instanciação com `new`;
- métodos de instância;
- atributos;
- construtores;
- `this`;
- `public`;
- `private`;
- acesso package-private;
- `final`;
- encapsulamento;
- organização em pacotes;
- responsabilidades;
- coesão;
- dependências entre objetos;
- injeção de dependência por construtor.

## Segurança e Comunicação

- hashing;
- SHA-1;
- k-anonymity;
- requisições HTTP;
- GET;
- códigos de status HTTP;
- consumo de API.

## Tratamento de Recursos e Exceções

- `try-catch`;
- `try-with-resources`;
- `throws`;
- `throw`;
- propagação de exceções;
- `NoSuchAlgorithmException`;
- `IOException`;
- `InterruptedException`.

---

# 🧱 Evolução: Procedural → POO

A evolução realizada até aqui pode ser resumida em:

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
Aplicação organizada
com classes e objetos
```

A V2.0.0 não utiliza herança, polimorfismo ou interfaces apenas para caracterizar o projeto como orientado a objetos.

Os conceitos são introduzidos conforme surgem necessidades reais durante a evolução da aplicação.

---

# 🔜 Próxima Versão

## V3.0.0 — Interface Gráfica com Java Swing

A próxima grande evolução será substituir a interação exclusiva pelo console por uma **interface gráfica utilizando Java Swing**.

A arquitetura criada na V2.0.0 prepara o projeto para essa mudança.

A futura interface poderá utilizar:

```java
VerificadorSenha
```

sem precisar conhecer diretamente os detalhes de:

```text
SHA-1
k-anonymity
HttpClient
HttpRequest
processamento da resposta
```

A lógica desenvolvida nas versões anteriores será preservada.

### Planejamento inicial

- criação da janela principal;
- introdução aos componentes Swing;
- utilização de layouts;
- criação do campo para entrada da senha;
- criação do botão de verificação;
- tratamento de eventos;
- integração da interface com `VerificadorSenha`;
- apresentação gráfica do resultado.

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
V3.0.0
Interface gráfica com Swing
    ↓
V3.1.0
Campo de senha, botão e eventos
    ↓
V3.2.0
Apresentação visual do resultado
    ↓
V3.3.0
UX e tratamento visual de erros
    ↓
V4.0.0
Versão final
```

---

## 🗺️ Roadmap

| Versão | Objetivo | Situação |
|---|---|---|
| V0.0.0 | Entrada básica da senha | ✅ |
| V0.1.0 | Geração do SHA-1 | ✅ |
| V0.2.0 | Prefixo e sufixo / k-anonymity | ✅ |
| V0.3.0 | Consulta HTTP | ✅ |
| V0.4.0 | Processamento da resposta | ✅ |
| V1.0.0 | Verificador funcional em console | ✅ |
| V1.1.0 | Modularização com métodos | ✅ |
| **V2.0.0** | **Introdução à POO e separação em classes** | **🚧 Atual** |
| V3.0.0 | Interface gráfica com Java Swing | 🔜 |
| V3.1.0 | Campo de senha, botão e eventos | ⏳ |
| V3.2.0 | Apresentação visual do resultado | ⏳ |
| V3.3.0 | UX e tratamento visual de erros | ⏳ |
| V4.0.0 | Versão final | ⏳ |

---

## 👨‍🏫 Contexto Acadêmico

Projeto desenvolvido como material didático para a disciplina de **Programação Orientada a Objetos (POO)**.

A evolução incremental permite observar como um programa inicialmente simples pode ser progressivamente reorganizado:

```text
fundamentos
    ↓
aplicação procedural
    ↓
modularização
    ↓
classes e objetos
    ↓
separação de responsabilidades
    ↓
interface gráfica
```

Dessa forma, cada nova versão introduz novos conceitos sem esconder as limitações e decisões existentes nas versões anteriores.

A estratégia também permite comparar diferentes formas de organização do mesmo problema, observando não apenas **como fazer o programa funcionar**, mas também **como estruturar melhor o software à medida que ele evolui**.

---

## 📄 Licença

Este projeto é distribuído sob a licença **MIT**.