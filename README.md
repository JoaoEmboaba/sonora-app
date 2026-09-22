# Sonora — Fase 05

Projeto derivado da `sonora-fase03`, evoluído para a Fase 05.

## O que foi implementado

- Modelagem UML das associações do Sonora em `docs/diagrama-classes.png`.
- Troca dos arrays de `Playlist` por `ArrayList<Musica>`.
- Troca dos arrays de `Plataforma` por `ArrayList<Musica>` e `ArrayList<Usuario>`.
- Remoção das capacidades fixas de 100 músicas por playlist e 500 músicas/usuários por plataforma.
- `Usuario` passou a manter `ArrayList<Usuario>` para representar a associação reflexiva de usuários que segue.
- Implementação de `seguir`, `deixarDeSeguir` e `getQuantidadeSeguindo`.
- Menu do `App` atualizado para listar usuários, seguir, deixar de seguir e listar os usuários seguidos.
- Busca de usuários por ID adicionada à `Plataforma` para suportar as operações do menu.
- Testes JUnit 6 mantidos e adaptados para a nova estrutura, incluindo testes da associação reflexiva e da ausência de limite fixo.

## Diagrama de classes

Arquivo entregue:

```text
docs/diagrama-classes.png
```

O diagrama contém os quatro relacionamentos exigidos e também o relacionamento `Playlist -> Musica` já apresentado como exemplo na atividade.

### 1. Plataforma e Musica

| Item | Definição |
|---|---|
| Papel em `Plataforma` | `plataforma` |
| Papel em `Musica` | `acervo` |
| Nome | `Plataforma cadastra Musica` |
| Multiplicidade em `Plataforma` | `1` |
| Multiplicidade em `Musica` | `0..*` |
| Navegabilidade | `Plataforma -> Musica` |

**Justificativa:** uma instância de `Plataforma` mantém seu acervo de músicas e pode cadastrar zero ou muitas músicas. Cada música considerada no acervo pertence à plataforma representada no relacionamento. A navegação é da plataforma para as músicas porque é a plataforma que mantém a coleção no código; `Musica` não possui uma referência de volta para `Plataforma`.

### 2. Plataforma e Usuario

| Item | Definição |
|---|---|
| Papel em `Plataforma` | `plataforma` |
| Papel em `Usuario` | `usuarios` |
| Nome | `Plataforma registra Usuario` |
| Multiplicidade em `Plataforma` | `1` |
| Multiplicidade em `Usuario` | `0..*` |
| Navegabilidade | `Plataforma -> Usuario` |

**Justificativa:** uma plataforma pode registrar zero ou muitos usuários. O código mantém essa relação em `ArrayList<Usuario>` dentro de `Plataforma`. A navegação é unidirecional porque `Usuario` não precisa conhecer a `Plataforma` à qual está cadastrado.

### 3. Usuario e Playlist

| Item | Definição |
|---|---|
| Papel em `Usuario` | `dono` |
| Papel em `Playlist` | `playlists` |
| Nome | `Usuario cria Playlist` |
| Multiplicidade em `Usuario` | `1` |
| Multiplicidade em `Playlist` | `0..*` |
| Navegabilidade | `Usuario -> Playlist` |

**Justificativa:** uma playlist possui exatamente um dono (`dono`), enquanto um usuário pode ser dono de zero ou muitas playlists. No código, `Playlist` mantém a referência para seu `Usuario dono`. A navegação foi representada como `Usuario -> Playlist` para expressar que o usuário cria e possui suas playlists.

### 4. Usuario e Usuario — associação reflexiva

| Item | Definição |
|---|---|
| Papel na origem | `seguindo` |
| Papel no destino | `seguidores` |
| Nome | `Usuario segue Usuario` |
| Multiplicidade na origem | `0..*` |
| Multiplicidade no destino | `0..*` |
| Navegabilidade | `Usuario -> Usuario` |

**Justificativa:** um usuário pode seguir zero ou muitos outros usuários e também pode ser seguido por zero ou muitos usuários. A associação é reflexiva porque as duas pontas pertencem à classe `Usuario`. No código desta fase, a coleção mantida explicitamente é `seguindo`, portanto a navegação implementada é do usuário para os usuários que ele segue. Não existe uma coleção de seguidores armazenada separadamente.

### Relacionamento de exemplo — Playlist e Musica

| Item | Definição |
|---|---|
| Papel em `Playlist` | `playlist` |
| Papel em `Musica` | `musicas` |
| Nome | `Playlist contém Musica` |
| Multiplicidade em `Playlist` | `1` |
| Multiplicidade em `Musica` | `0..*` |
| Navegabilidade | `Playlist -> Musica` |

A implementação correspondente é `ArrayList<Musica> musicas` dentro de `Playlist`.

## Associação reflexiva no código

`Usuario` possui:

```java
private final ArrayList<Usuario> seguindo;
```

E os métodos:

```java
public void seguir(Usuario outro)
public void deixarDeSeguir(Usuario outro)
public int getQuantidadeSeguindo()
```

Regras implementadas:

- não permite seguir `null`;
- não permite seguir a si mesmo;
- não adiciona o mesmo usuário duas vezes;
- `deixarDeSeguir` remove o usuário da coleção, caso esteja presente;
- `getQuantidadeSeguindo()` usa `size()` da `ArrayList`.

## ArrayList

### Playlist

Antes:

```java
private final Musica[] musicas;
private int quantidade;
```

Agora:

```java
private final ArrayList<Musica> musicas;
```

A quantidade passou a ser obtida por `musicas.size()`, a inserção usa `add`, a consulta usa `get` e a remoção usa `remove`.

### Plataforma

Antes havia arrays com capacidade fixa de 500 posições.

Agora:

```java
private final ArrayList<Musica> musicas;
private final ArrayList<Usuario> usuarios;
```

As buscas percorrem as coleções com `for-each` e os totais usam `size()`.

### Usuario

A associação reflexiva é armazenada em:

```java
private final ArrayList<Usuario> seguindo;
```

## Testes

Os testes anteriores foram adaptados para o novo comportamento. Como não existe mais capacidade fixa, os testes de limite agora verificam que a coleção consegue ultrapassar as antigas capacidades de 100 músicas na playlist e 500 músicas na plataforma.

Também foram adicionados testes para:

- seguir outro usuário;
- impedir seguir a si mesmo;
- impedir `null`;
- não duplicar um usuário seguido;
- deixar de seguir;
- tentar deixar de seguir alguém que não está na lista;
- buscar usuário por ID.

## Como executar

Requisitos:

- Java 21
- Maven

Executar os testes:

```bash
mvn test
```

Executar a aplicação:

```bash
mvn compile
java -cp target/classes App
```
