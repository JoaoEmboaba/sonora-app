public class Usuario {
    private static int proximoId = 1;

    private final int id;
    private final String nome;
    private final String email;

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

    @Override
    public String toString() {
        return id + " - " + nome + " <" + email + ">";
    }
}
