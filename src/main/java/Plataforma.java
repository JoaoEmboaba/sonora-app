public class Plataforma {
    private static final int CAPACIDADE_MAXIMA = 500;

    private final Musica[] musicas;
    private final Usuario[] usuarios;
    private int totalMusicas;
    private int totalUsuarios;

    public Plataforma() {
        musicas = new Musica[CAPACIDADE_MAXIMA];
        usuarios = new Usuario[CAPACIDADE_MAXIMA];
        totalMusicas = 0;
        totalUsuarios = 0;
    }

    public boolean cadastrarMusica(Musica musica) {
        if (musica == null || totalMusicas >= CAPACIDADE_MAXIMA) {
            return false;
        }

        musicas[totalMusicas] = musica;
        totalMusicas++;
        return true;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuario == null || totalUsuarios >= CAPACIDADE_MAXIMA) {
            return false;
        }

        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        return true;
    }

    public Musica buscarMusicaPorId(int id) {
        return buscarMusica(id);
    }

    public Musica buscarMusica(String titulo) {
        if (titulo == null) {
            return null;
        }

        for (int i = 0; i < totalMusicas; i++) {
            if (musicas[i].getTitulo().equalsIgnoreCase(titulo)) {
                return musicas[i];
            }
        }

        return null;
    }

    private Musica buscarMusica(int id) {
        for (int i = 0; i < totalMusicas; i++) {
            if (musicas[i].getId() == id) {
                return musicas[i];
            }
        }

        return null;
    }

    public int getTotalMusicas() {
        return totalMusicas;
    }

    public int getTotalUsuarios() {
        return totalUsuarios;
    }

    public Musica getMusicaNaPosicao(int indice) {
        if (indice < 0 || indice >= totalMusicas) {
            throw new IndexOutOfBoundsException("Índice de música inválido: " + indice);
        }
        return musicas[indice];
    }
}
