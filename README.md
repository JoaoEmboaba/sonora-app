# Sonora — Fase 05

Implementação da fase de **herança**, partindo do projeto anterior.

## Parte A — Superclasse `Conteudo`

`Conteudo` concentra os atributos e comportamentos comuns aos conteúdos:

- `id`, gerado por contador estático;
- `titulo`, com validação de nulo/vazio;
- `duracaoSegundos`, com validação para valores maiores que zero;
- getters e setters com validação;
- `reproduzir()`, herdado pelas subclasses;
- `toString()` com a representação comum.

O `setId(int)` é `protected`, conforme solicitado, permitindo acesso às subclasses sem expor o setter ao restante do sistema.

## Parte B — Herança

A hierarquia implementada é:

```text
Conteudo
├── Musica
└── Podcast
```

### `Musica`

Herda `id`, `titulo`, `duracaoSegundos`, `reproduzir()` e `toString()` de `Conteudo` e acrescenta:

- `artista`;
- `album`.

O construtor utiliza `super(titulo, duracaoSegundos)` antes de inicializar os atributos específicos.

### `Podcast`

Herda de `Conteudo` e acrescenta:

- `apresentador`;
- `numeroEpisodio`, que deve ser maior ou igual a 1.

## Parte C — Sobrescrita

`Musica` e `Podcast` sobrescrevem `toString()` com `@Override` e utilizam `super.toString()` para reaproveitar a representação definida em `Conteudo`.

`reproduzir()` não é sobrescrito nas subclasses: ele é herdado diretamente de `Conteudo`.

## Parte D — App

O `App` demonstra a hierarquia criando:

- duas músicas;
- um podcast.

Em seguida, chama `reproduzir()` nos três objetos e imprime os objetos, demonstrando o comportamento herdado e os `toString()` específicos.

O menu anterior continua disponível, incluindo cadastro, busca, playlists e associação de seguidores.

## Fase anterior preservada

`Playlist` e `Plataforma` continuam utilizando `ArrayList`, conforme a fase anterior. A coleção da associação reflexiva de `Usuario` também é mantida.

Nesta fase, `Playlist` e `Plataforma` continuam trabalhando especificamente com `Musica`, conforme a restrição da atividade de não introduzir polimorfismo de coleções antes da fase correspondente.

## Testes

Os testes anteriores foram adaptados para o novo construtor de `Musica`, e foram adicionados testes para:

- `Conteudo`;
- herança de `Musica`;
- `Podcast`;
- validações dos atributos específicos;
- sobrescrita de `toString()`;
- herança de `reproduzir()`.

Execute com:

```bash
mvn test
```

O projeto utiliza Java 21 e JUnit 6.0.3.
