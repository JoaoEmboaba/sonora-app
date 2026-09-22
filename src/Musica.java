public class Musica {
    private static int proximoId = 1;

    private final int id;
    private final String titulo;
    private final String artista;
    private final int duracaoSegundos;
    private int reproducoes;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido: não pode ser vazio.");
        }
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido: não pode ser vazio.");
        }
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException(
                    "Duração inválida: " + duracaoSegundos + ". A duração deve ser maior que zero."
            );
        }

        this.id = proximoId++;
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.reproducoes = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    public void reproduzir() {
        reproducoes++;
    }

    public String getDuracaoFormatada() {
        int minutos = duracaoSegundos / 60;
        int segundos = duracaoSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " - " + artista
                + " (" + getDuracaoFormatada() + ", reproduções: " + reproducoes + ")";
    }
}
