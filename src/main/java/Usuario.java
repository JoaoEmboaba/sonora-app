import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int proximoId = 1;

    private final int id;
    private final String nome;
    private final String email;
    private final ArrayList<Usuario> seguindo;

    public Usuario(String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido: não pode ser vazio.");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException(
                    "E-mail inválido: deve ser preenchido e conter @."
            );
        }

        this.id = proximoId++;
        this.nome = nome;
        this.email = email;
        this.seguindo = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void seguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inválido: não pode ser nulo.");
        }

        if (this == outro) {
            throw new IllegalArgumentException("Um usuário não pode seguir a si mesmo.");
        }

        if (!seguindo.contains(outro)) {
            seguindo.add(outro);
        }
    }

    public void deixarDeSeguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inválido: não pode ser nulo.");
        }

        seguindo.remove(outro);
    }

    public int getQuantidadeSeguindo() {
        return seguindo.size();
    }

    public List<Usuario> getSeguindo() {
        return List.copyOf(seguindo);
    }

    @Override
    public String toString() {
        return id + " - " + nome + " <" + email + ">";
    }
}
