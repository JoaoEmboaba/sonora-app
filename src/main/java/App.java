import java.util.Scanner;

public class App {
    private static final Scanner teclado = new Scanner(System.in);
    private static final Plataforma plataforma = new Plataforma();

    private static Usuario usuarioAtual;
    private static Playlist playlistAtual;

    public static void main(String[] args) {
        popularAcervo();

        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarMusica();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    criarPlaylistEAdicionar();
                    break;
                case 4:
                    buscarPorId();
                    break;
                case 5:
                    buscarPorTitulo();
                    break;
                case 6:
                    reproduzirMusica();
                    break;
                case 7:
                    listarAcervo();
                    break;
                case 8:
                    demonstrarPlaylist();
                    break;
                case 9:
                    listarUsuarios();
                    break;
                case 10:
                    seguirUsuario();
                    break;
                case 11:
                    deixarDeSeguirUsuario();
                    break;
                case 12:
                    listarSeguindo();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Encerrando o Sonora.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        teclado.close();
    }

    private static void popularAcervo() {
        try {
            plataforma.cadastrarMusica(new Musica("Blinding Lights", "The Weeknd", 200));
            plataforma.cadastrarMusica(new Musica("Imagine", "John Lennon", 183));
            plataforma.cadastrarMusica(new Musica("Hotel California", "Eagles", 391));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao popular acervo: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("=== Sonora ===");
        System.out.println("1 - Cadastrar música manualmente");
        System.out.println("2 - Cadastrar usuário");
        System.out.println("3 - Criar playlist e adicionar músicas");
        System.out.println("4 - Buscar música por id");
        System.out.println("5 - Buscar música por título");
        System.out.println("6 - Reproduzir uma música");
        System.out.println("7 - Listar acervo");
        System.out.println("8 - Demonstração da Fase 02");
        System.out.println("9 - Listar usuários");
        System.out.println("10 - Seguir usuário");
        System.out.println("11 - Deixar de seguir usuário");
        System.out.println("12 - Listar quem estou seguindo");
        System.out.println("0 - Sair");
    }

    private static void cadastrarMusica() {
        try {
            System.out.print("Título: ");
            String titulo = teclado.nextLine();

            System.out.print("Artista: ");
            String artista = teclado.nextLine();

            int duracao = lerInteiro("Duração em segundos: ");

            Musica musica = new Musica(titulo, artista, duracao);

            if (plataforma.cadastrarMusica(musica)) {
                System.out.println("Música cadastrada com sucesso: " + musica);
            } else {
                System.out.println("Não foi possível cadastrar: acervo cheio.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        } finally {
            System.out.println("Operação de cadastro de música finalizada.");
        }
    }

    private static void cadastrarUsuario() {
        try {
            System.out.print("Nome: ");
            String nome = teclado.nextLine();

            System.out.print("E-mail: ");
            String email = teclado.nextLine();

            Usuario usuario = new Usuario(nome, email);

            if (plataforma.cadastrarUsuario(usuario)) {
                usuarioAtual = usuario;
                System.out.println("Usuário cadastrado: " + usuario);
            } else {
                System.out.println("Não foi possível cadastrar: limite de usuários atingido.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    private static void criarPlaylistEAdicionar() {
        try {
            if (usuarioAtual == null) {
                throw new IllegalStateException("Cadastre um usuário antes de criar uma playlist.");
            }

            System.out.print("Nome da playlist: ");
            String nome = teclado.nextLine();

            playlistAtual = new Playlist(nome, usuarioAtual);

            System.out.println("Digite os IDs das músicas para adicionar.");
            System.out.println("Digite 0 para terminar.");

            while (true) {
                int id = lerInteiro("ID da música: ");

                if (id == 0) {
                    break;
                }

                Musica musica = plataforma.buscarMusicaPorId(id);

                if (musica == null) {
                    System.out.println("Música não encontrada.");
                } else if (playlistAtual.adicionar(musica)) {
                    System.out.println("Adicionada: " + musica.getTitulo());
                } else {
                    System.out.println("Playlist cheia.");
                    break;
                }
            }

            System.out.println("Playlist criada com " + playlistAtual.getQuantidade() + " música(s).");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível criar a playlist: " + e.getMessage());
        }
    }

    private static void buscarPorId() {
        int id = lerInteiro("ID: ");
        Musica musica = plataforma.buscarMusicaPorId(id);

        if (musica == null) {
            System.out.println("Música não encontrada.");
        } else {
            System.out.println(musica);
        }
    }

    private static void buscarPorTitulo() {
        System.out.print("Título: ");
        String titulo = teclado.nextLine();

        Musica musica = plataforma.buscarMusica(titulo);

        if (musica == null) {
            System.out.println("Música não encontrada.");
        } else {
            System.out.println(musica);
        }
    }

    private static void reproduzirMusica() {
        try {
            int id = lerInteiro("ID da música: ");
            Musica musica = plataforma.buscarMusicaPorId(id);

            if (musica == null) {
                System.out.println("Música não encontrada.");
                return;
            }

            musica.reproduzir();
            System.out.println("Tocando: " + musica.getTitulo());
            System.out.println("Reproduções: " + musica.getReproducoes());
        } catch (NumberFormatException e) {
            System.out.println("O ID precisa ser um número.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Posição inválida.");
        } finally {
            System.out.println("Operação de reprodução finalizada.");
        }
    }

    private static void listarAcervo() {
        System.out.println();
        System.out.println("=== Acervo (" + plataforma.getTotalMusicas() + " música(s)) ===");

        for (int i = 0; i < plataforma.getTotalMusicas(); i++) {
            System.out.println(plataforma.getMusicaNaPosicao(i));
        }
    }

    private static void demonstrarPlaylist() {
        try {
            if (usuarioAtual == null) {
                usuarioAtual = new Usuario("Usuário Demonstração", "demo@sonora.com");
                plataforma.cadastrarUsuario(usuarioAtual);
            }

            Playlist playlist = new Playlist("Demonstração", usuarioAtual);

            Musica m1 = plataforma.buscarMusicaPorId(1);
            Musica m2 = plataforma.buscarMusicaPorId(2);
            Musica m3 = plataforma.buscarMusicaPorId(3);

            playlist.adicionar(m1);
            playlist.adicionar(m2);
            playlist.adicionar(m3);

            System.out.println("Quantidade: " + playlist.getQuantidade());
            System.out.println("Duração total: " + playlist.getDuracaoTotalSegundos() + " segundos");

            System.out.println("Testando remoção do meio...");
            playlist.removerNaPosicao(1);

            for (int i = 0; i < playlist.getQuantidade(); i++) {
                System.out.println("Posição " + i + ": " + playlist.getNaPosicao(i).getTitulo());
            }

            System.out.println("Testando reproduzirTudo()...");
            playlist.reproduzirTudo();

            for (int i = 0; i < playlist.getQuantidade(); i++) {
                Musica musica = playlist.getNaPosicao(i);
                System.out.println(musica.getTitulo() + ": " + musica.getReproducoes() + " reprodução(ões)");
            }

            System.out.println("Testando índice inválido...");
            try {
                playlist.getNaPosicao(50);
            } catch (NumberFormatException e) {
                System.out.println("Número inválido.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException tratada: " + e.getMessage());
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro na demonstração: " + e.getMessage());
        } finally {
            System.out.println("Demonstração finalizada.");
        }
    }


    private static void listarUsuarios() {
        System.out.println();
        System.out.println("=== Usuários (" + plataforma.getTotalUsuarios() + ") ===");

        if (plataforma.getTotalUsuarios() == 0) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : plataforma.getUsuarios()) {
            String marcador = usuario == usuarioAtual ? " (usuário atual)" : "";
            System.out.println(usuario + marcador);
        }
    }

    private static void seguirUsuario() {
        try {
            validarUsuarioAtual();

            int id = lerInteiro("ID do usuário que deseja seguir: ");
            Usuario outro = plataforma.buscarUsuarioPorId(id);

            if (outro == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }

            usuarioAtual.seguir(outro);
            System.out.println("Agora você segue: " + outro.getNome());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível seguir o usuário: " + e.getMessage());
        }
    }

    private static void deixarDeSeguirUsuario() {
        try {
            validarUsuarioAtual();

            int id = lerInteiro("ID do usuário que deseja deixar de seguir: ");
            Usuario outro = plataforma.buscarUsuarioPorId(id);

            if (outro == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }

            usuarioAtual.deixarDeSeguir(outro);
            System.out.println("Operação concluída para: " + outro.getNome());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível deixar de seguir o usuário: " + e.getMessage());
        }
    }

    private static void listarSeguindo() {
        try {
            validarUsuarioAtual();

            System.out.println();
            System.out.println("=== Seguindo (" + usuarioAtual.getQuantidadeSeguindo() + ") ===");

            if (usuarioAtual.getQuantidadeSeguindo() == 0) {
                System.out.println("Você não segue nenhum usuário.");
                return;
            }

            for (Usuario usuario : usuarioAtual.getSeguindo()) {
                System.out.println(usuario);
            }
        } catch (IllegalStateException e) {
            System.out.println("Não foi possível listar: " + e.getMessage());
        }
    }

    private static void validarUsuarioAtual() {
        if (usuarioAtual == null) {
            throw new IllegalStateException("Cadastre um usuário antes de usar esta opção.");
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número.");
            }
        }
    }
}
