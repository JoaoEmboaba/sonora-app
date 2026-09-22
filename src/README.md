# Sonora

## Estrutura

```text
sonora-fase02/
├── src/
│   ├── App.java
│   ├── Musica.java
│   ├── Usuario.java
│   ├── Playlist.java
│   └── Plataforma.java
└── README.md
```

## Requisitos atendidos

### Fase 01

- IDs sequenciais e independentes para `Musica` e `Usuario`.
- Todos os atributos encapsulados.
- `id` e `reproducoes` não possuem setters públicos.
- `getDuracaoFormatada()` no formato `mm:ss`.
- `Playlist` com capacidade fixa de 100 músicas.
- `Plataforma` com arrays de capacidade 500.
- Adição retorna `false` quando a playlist está cheia.
- Remoção reorganiza o array.
- Busca por ID e por título usando sobrecarga.
- Uma mesma referência de `Musica` pode estar em várias playlists.
- Menu de console.

### Fase 02

- Validação dos construtores com `IllegalArgumentException`.
- Título/artista/nome não podem ser nulos ou vazios.
- Duração deve ser maior que zero.
- E-mail deve conter `@`.
- `Playlist` exige dono.
- `Playlist.adicionar(null)` lança `IllegalArgumentException`.
- Índices inválidos em `getNaPosicao` e `removerNaPosicao` lançam `IndexOutOfBoundsException`.
- Playlist cheia continua retornando `false`.
- Busca sem resultado continua retornando `null`.
- Entrada numérica usa `Integer.parseInt` dentro de `try/catch`.
- Há bloco com múltiplos `catch`.
- Há `finally` com propósito.

## Compilar e executar

No diretório `src`:

```bash
javac *.java
java App
```
