import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private final ArrayList<Musica> musicas;
    private final ArrayList<Usuario> usuarios;

    public Plataforma() {
        musicas = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public boolean cadastrarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }

        musicas.add(musica);
        return true;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }

        usuarios.add(usuario);
        return true;
    }

    public Musica buscarMusicaPorId(int id) {
        return buscarMusica(id);
    }

    public Musica buscarMusica(String titulo) {
        if (titulo == null) {
            return null;
        }

        for (Musica musica : musicas) {
            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
                return musica;
            }
        }

        return null;
    }

    private Musica buscarMusica(int id) {
        for (Musica musica : musicas) {
            if (musica.getId() == id) {
                return musica;
            }
        }

        return null;
    }

    public int getTotalMusicas() {
        return musicas.size();
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public Musica getMusicaNaPosicao(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            throw new IndexOutOfBoundsException("Índice de música inválido: " + indice);
        }
        return musicas.get(indice);
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

    public Usuario getUsuarioNaPosicao(int indice) {
        if (indice < 0 || indice >= usuarios.size()) {
            throw new IndexOutOfBoundsException("Índice de usuário inválido: " + indice);
        }
        return usuarios.get(indice);
    }

    public List<Usuario> getUsuarios() {
        return List.copyOf(usuarios);
    }
}
