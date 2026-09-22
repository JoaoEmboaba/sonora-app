# Sonora — Fase 03: Testes Unitários com JUnit 6

Projeto derivado da implementação da Fase 02. As classes de produção foram mantidas e a Fase 03 adiciona apenas a infraestrutura de testes, os planos de teste e os testes automatizados.

## Estrutura

```text
sonora-fase03/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       ├── App.java
    │       ├── Musica.java
    │       ├── Usuario.java
    │       ├── Playlist.java
    │       └── Plataforma.java
    └── test/
        └── java/
            ├── MusicaTest.java
            ├── PlaylistTest.java
            ├── PlataformaTest.java
            └── ContadoresIdTest.java
```

## JUnit

O projeto usa **JUnit 6.0.3** e Maven. O JUnit 6 requer Java 17 ou superior; este projeto está configurado para Java 21. O Surefire é usado para executar os testes. A documentação oficial do JUnit informa suporte ao JUnit Platform pelo Maven Surefire e exige uma versão compatível a partir da linha 3.0.0. 

## Parte 1 — Planos de teste

### PL01 — Validar `Musica.getDuracaoFormatada()`

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Duração com minutos e segundos | Música de 125 segundos | Deve resultar em `"02:05"` |
| 2 | Duração redonda em minutos | Música de 90 segundos | Deve resultar em `"01:30"` |
| 3 | Menos de um minuto, com zero à esquerda | Música de 5 segundos | Deve resultar em `"00:05"` |
| 4 | Dois dígitos nos minutos | Música de 600 segundos | Deve resultar em `"10:00"` |
| 5 | Valor logo abaixo de dez minutos | Música de 599 segundos | Deve resultar em `"09:59"` |

### PL02 — Validar construtor de `Musica` com dados inválidos

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Título vazio deve ser rejeitado | título `""`, artista `"Queen"`, duração `355` | Deve lançar `IllegalArgumentException` |
| 2 | Título nulo deve ser rejeitado | título `null`, artista `"Queen"`, duração `355` | Deve lançar `IllegalArgumentException` |
| 3 | Artista vazio deve ser rejeitado | título `"Bohemian Rhapsody"`, artista `""`, duração `355` | Deve lançar `IllegalArgumentException` |
| 4 | Duração zero deve ser rejeitada | título válido, artista válido, duração `0` | Deve lançar `IllegalArgumentException` |
| 5 | Duração negativa deve ser rejeitada | título válido, artista válido, duração `-10` | Deve lançar `IllegalArgumentException` |
| 6 | Dados válidos criam a música | título `"Bohemian Rhapsody"`, artista `"Queen"`, duração `355` | Objeto criado, com id maior que zero |

### PL03 — Validar `Playlist.adicionar(musica)`

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Adicionar música em playlist com espaço | Playlist vazia + uma música válida | Retorna `true` e quantidade passa para `1` |
| 2 | Adicionar várias músicas em playlist com espaço | Playlist + três músicas válidas | Cada chamada retorna `true` e quantidade chega a `3` |
| 3 | Adicionar música nula deve ser rejeitado | `playlist.adicionar(null)` | Lança `IllegalArgumentException` e quantidade permanece `0` |
| 4 | Adicionar até encher a playlist | 100 músicas válidas e uma 101ª música | As 100 primeiras retornam `true`; a 101ª retorna `false`; quantidade permanece `100` |

### PL04 — Validar `Playlist.getNaPosicao(indice)`

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Posição válida devolve a música certa | Playlist com duas músicas, índice `1` | Retorna a segunda música |
| 2 | Índice negativo | Playlist com uma música, índice `-1` | Lança `IndexOutOfBoundsException` |
| 3 | Índice além da quantidade | Playlist com uma música, índice `1` | Lança `IndexOutOfBoundsException` |

### PL05 — Validar `Playlist.removerNaPosicao(indice)`

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Remoção válida reorganiza o array | Três músicas e remoção da posição `1` | Retorna `true`, quantidade diminui e a terceira música passa para a posição `1` |
| 2 | Remoção da primeira posição reorganiza as seguintes | Três músicas e remoção da posição `0` | Retorna `true`; a segunda passa para `0` e a terceira para `1` |
| 3 | Índice inválido | Playlist com uma música, índice `5` | Lança `IndexOutOfBoundsException` |

### PL06 — Validar buscas da `Plataforma`

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Música cadastrada é encontrada pelo título | Título de música cadastrada | Retorna a mesma referência da música |
| 2 | Música cadastrada é encontrada pelo id | ID de música cadastrada | Retorna a mesma referência da música |
| 3 | Busca por título inexistente | Título que não existe no acervo | Retorna `null` |
| 4 | Busca por id inexistente | ID que não existe no acervo | Retorna `null` |

### PL07 — Validar `Musica.reproduzir()`

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Música começa sem reproduções | Nova música | `getReproducoes()` retorna `0` |
| 2 | Uma reprodução incrementa o contador | Uma chamada a `reproduzir()` | `getReproducoes()` retorna `1` |
| 3 | Três reproduções incrementam três vezes | Três chamadas a `reproduzir()` | `getReproducoes()` retorna `3` |

### PL08 — Bônus: contadores de ID

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | IDs de Música são sequenciais | Criar três músicas | O segundo ID é o primeiro + 1 e o terceiro é o segundo + 1 |
| 2 | IDs de Música e Usuário são independentes | Criar músicas e usuários alternadamente | Cada classe mantém sua própria sequência de IDs |
| 3 | Criar Usuário não altera o contador de Música | Criar música, usuário e outra música | O segundo ID de Música é o primeiro + 1 |

> Observação sobre PL08: os testes não exigem que o primeiro ID do processo de testes seja literalmente `1`, porque os campos `static` permanecem vivos durante toda a execução da JVM e os testes do JUnit não devem depender da ordem de execução. O que é verificado é o comportamento essencial: sequência e independência dos contadores.

## Parte 2 — Implementação em JUnit 6

Cada caso dos planos foi transformado em um método de teste com `@Test` e `@DisplayName`. Os casos normais usam asserções como `assertEquals`, `assertTrue`, `assertFalse`, `assertNull`, `assertNotNull` e `assertSame`. Os casos de exceção usam `assertThrows` verificando o tipo exato esperado.

Os cenários repetidos são preparados com `@BeforeEach` nas classes de teste de `Musica`, `Playlist` e `Plataforma`.

## Como executar

É necessário ter **Java 21** e **Maven** instalados.

Na raiz do projeto:

```bash
mvn test
```

O resultado esperado é uma execução totalmente verde, sem falhas.

Também é possível abrir o projeto em uma IDE com suporte a Maven/JUnit e executar os testes diretamente pela classe ou pelo projeto.

## Alterações na produção

A Fase 03 não reescreve as classes do Sonora. A implementação de `Musica`, `Usuario`, `Playlist`, `Plataforma` e `App` foi trazida da Fase 02. Os testes foram adicionados por cima dela para verificar automaticamente os comportamentos definidos no contrato.
