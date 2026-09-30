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

Nesta versão, o programa:

- executa no terminal;
- solicita uma senha ao usuário;
- armazena a entrada em uma variável;
- exibe a senha recebida apenas para fins didáticos.

> A exibição da senha foi utilizada somente para demonstrar o funcionamento
> da entrada de dados nesta primeira versão.

---

## Versão Atual

### V0.1.0 — Geração do hash SHA-1

Nesta versão, o programa deixa de exibir diretamente a senha informada e
passa a gerar sua representação utilizando o algoritmo SHA-1.

O programa:

- solicita uma senha ao usuário;
- converte a senha para bytes utilizando UTF-8;
- gera o hash SHA-1 utilizando `MessageDigest`;
- percorre os bytes resultantes;
- converte cada byte para sua representação hexadecimal;
- monta e exibe o hash SHA-1 de 40 caracteres.

### Exemplo

Entrada:

`abc`

Resultado:

`A9993E364706816ABA3E25717850C26C9CD0D89D`

### Conceitos abordados

- `MessageDigest`;
- funções hash;
- SHA-1;
- `byte[]`;
- UTF-8;
- representação hexadecimal;
- `StringBuilder`;
- `for-each`.

> **Observação:** SHA-1 não é recomendado como mecanismo moderno para
> armazenamento de senhas. Neste projeto, ele é utilizado porque faz parte
> do protocolo de consulta utilizado pela API Pwned Passwords.

---

## Próxima Versão

### V0.2.0 — Implementação do k-anonymity

Na próxima versão, o hash SHA-1 será dividido em duas partes:

- **prefixo:** os 5 primeiros caracteres;
- **sufixo:** os 35 caracteres restantes.

Essa separação preparará o programa para consultar a API Pwned Passwords
sem transmitir a senha ou o hash SHA-1 completo.

Exemplo:

`A9993E364706816ABA3E25717850C26C9CD0D89D`

será dividido em:

- Prefixo: `A9993`
- Sufixo: `E364706816ABA3E25717850C26C9CD0D89D`

---

## Tecnologias

- Java
- NetBeans
- Git
- GitHub

---

## Licença

Este projeto é distribuído sob a licença MIT.