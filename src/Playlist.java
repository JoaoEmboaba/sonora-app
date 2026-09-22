public class Playlist {
    private static final int CAPACIDADE_MAXIMA = 100;

    private final String nome;
    private final Usuario dono;
    private final Musica[] musicas;
    private int quantidade;

    public Playlist(String nome, Usuario dono) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da playlist inválido: não pode ser vazio.");
        }
        if (dono == null) {
            throw new IllegalArgumentException("Dono inválido: a playlist precisa ter um dono.");
        }

        this.nome = nome;
        this.dono = dono;
        this.musicas = new Musica[CAPACIDADE_MAXIMA];
        this.quantidade = 0;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getDono() {
        return dono;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean adicionar(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Música inválida: não pode ser nula.");
        }

        if (quantidade >= CAPACIDADE_MAXIMA) {
            return false;
        }

        musicas[quantidade] = musica;
        quantidade++;
        return true;
    }

    public Musica getNaPosicao(int indice) {
        validarIndice(indice);
        return musicas[indice];
    }

    public boolean removerNaPosicao(int indice) {
        validarIndice(indice);

        for (int i = indice; i < quantidade - 1; i++) {
            musicas[i] = musicas[i + 1];
        }

        quantidade--;
        musicas[quantidade] = null;
        return true;
    }

    public int getDuracaoTotalSegundos() {
        int total = 0;

        for (int i = 0; i < quantidade; i++) {
            total += musicas[i].getDuracaoSegundos();
        }

        return total;
    }

    public void reproduzirTudo() {
        for (int i = 0; i < quantidade; i++) {
            musicas[i].reproduzir();
        }
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= quantidade) {
            throw new IndexOutOfBoundsException(
                    "Índice inválido: " + indice + ". A playlist possui " + quantidade + " música(s)."
            );
        }
    }
}
