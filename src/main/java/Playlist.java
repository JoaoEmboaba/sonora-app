import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final String nome;
    private final Usuario dono;
    private final ArrayList<Musica> musicas;

    public Playlist(String nome, Usuario dono) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da playlist inválido: não pode ser vazio.");
        }
        if (dono == null) {
            throw new IllegalArgumentException("Dono inválido: a playlist precisa ter um dono.");
        }

        this.nome = nome;
        this.dono = dono;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Usuario getDono() {
        return dono;
    }

    public int getQuantidade() {
        return musicas.size();
    }

    public boolean adicionar(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Música inválida: não pode ser nula.");
        }

        musicas.add(musica);
        return true;
    }

    public Musica getNaPosicao(int indice) {
        validarIndice(indice);
        return musicas.get(indice);
    }

    public boolean removerNaPosicao(int indice) {
        validarIndice(indice);
        musicas.remove(indice);
        return true;
    }

    public int getDuracaoTotalSegundos() {
        int total = 0;

        for (Musica musica : musicas) {
            total += musica.getDuracaoSegundos();
        }

        return total;
    }

    public void reproduzirTudo() {
        for (Musica musica : musicas) {
            musica.reproduzir();
        }
    }

    public List<Musica> getMusicas() {
        return List.copyOf(musicas);
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            throw new IndexOutOfBoundsException(
                    "Índice inválido: " + indice + ". A playlist possui " + musicas.size() + " música(s)."
            );
        }
    }
}
