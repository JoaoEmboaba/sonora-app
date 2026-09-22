import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaTest {

    private Plataforma plataforma;
    private Musica musica1;
    private Musica musica2;

    @BeforeEach
    void setUp() {
        plataforma = new Plataforma();
        musica1 = new Musica("Bohemian Rhapsody", "Queen", 355);
        musica2 = new Musica("Imagine", "John Lennon", 183);
        plataforma.cadastrarMusica(musica1);
        plataforma.cadastrarMusica(musica2);
    }

    @Test
    @DisplayName("Música cadastrada é encontrada pelo título")
    void deveEncontrarMusicaPorTitulo() {
        assertSame(musica1, plataforma.buscarMusica("Bohemian Rhapsody"));
    }

    @Test
    @DisplayName("Música cadastrada é encontrada pelo id")
    void deveEncontrarMusicaPorId() {
        assertSame(musica2, plataforma.buscarMusicaPorId(musica2.getId()));
    }

    @Test
    @DisplayName("Busca por título inexistente retorna null")
    void deveRetornarNullQuandoTituloNaoExiste() {
        assertNull(plataforma.buscarMusica("Título que não existe"));
    }

    @Test
    @DisplayName("Busca por id inexistente retorna null")
    void deveRetornarNullQuandoIdNaoExiste() {
        assertNull(plataforma.buscarMusicaPorId(-999));
    }

    @Test
    @DisplayName("Busca por título ignora diferença entre maiúsculas e minúsculas")
    void deveEncontrarTituloSemConsiderarMaiusculas() {
        assertSame(musica1, plataforma.buscarMusica("bohemian rhapsody"));
    }

    @Test
    @DisplayName("Cadastro de música aumenta o total de músicas")
    void deveAumentarTotalAoCadastrarMusica() {
        Plataforma novaPlataforma = new Plataforma();
        assertEquals(0, novaPlataforma.getTotalMusicas());

        assertTrue(novaPlataforma.cadastrarMusica(new Musica("Nova", "Artista", 120)));
        assertEquals(1, novaPlataforma.getTotalMusicas());
    }
    @Test
    @DisplayName("ArrayList permite cadastrar mais de 500 músicas")
    void devePermitirMaisDeQuinhentasMusicas() {
        Plataforma novaPlataforma = new Plataforma();

        for (int i = 0; i < 501; i++) {
            assertTrue(novaPlataforma.cadastrarMusica(
                    new Musica("Música " + i, "Artista", 120)
            ));
        }

        assertEquals(501, novaPlataforma.getTotalMusicas());
    }

    @Test
    @DisplayName("ArrayList permite cadastrar mais de 500 usuários")
    void devePermitirMaisDeQuinhentosUsuarios() {
        Plataforma novaPlataforma = new Plataforma();

        for (int i = 0; i < 501; i++) {
            assertTrue(novaPlataforma.cadastrarUsuario(
                    new Usuario("Usuário " + i, "usuario" + i + "@sonora.com")
            ));
        }

        assertEquals(501, novaPlataforma.getTotalUsuarios());
    }

    @Test
    @DisplayName("Busca de usuário por id retorna o usuário cadastrado")
    void deveEncontrarUsuarioPorId() {
        Usuario usuario = new Usuario("Ana", "ana@sonora.com");
        plataforma.cadastrarUsuario(usuario);

        assertSame(usuario, plataforma.buscarUsuarioPorId(usuario.getId()));
    }

    @Test
    @DisplayName("Busca de usuário por id inexistente retorna null")
    void deveRetornarNullQuandoUsuarioNaoExiste() {
        assertNull(plataforma.buscarUsuarioPorId(-999));
    }

}
