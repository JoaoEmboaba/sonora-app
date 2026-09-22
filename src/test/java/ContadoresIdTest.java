import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class ContadoresIdTest {

    @Test
    @DisplayName("IDs de Música são sequenciais")
    void idsDeMusicaDevemSerSequenciais() {
        Musica musica1 = new Musica("Música 1", "Artista", 100);
        Musica musica2 = new Musica("Música 2", "Artista", 100);
        Musica musica3 = new Musica("Música 3", "Artista", 100);

        assertEquals(musica1.getId() + 1, musica2.getId());
        assertEquals(musica2.getId() + 1, musica3.getId());
    }

    @Test
    @DisplayName("IDs de Música e Usuário usam contadores independentes")
    void idsDeMusicaEUsuarioDevemSerIndependentes() {
        Musica musica1 = new Musica("Música 1", "Artista", 100);
        Usuario usuario1 = new Usuario("Usuário 1", "usuario1@sonora.com");
        Musica musica2 = new Musica("Música 2", "Artista", 100);
        Usuario usuario2 = new Usuario("Usuário 2", "usuario2@sonora.com");

        assertEquals(musica1.getId() + 1, musica2.getId());
        assertEquals(usuario1.getId() + 1, usuario2.getId());
    }

    @Test
    @DisplayName("Criar Usuário não altera a sequência de IDs de Música")
    void usuarioNaoDeveAlterarContadorDeMusica() {
        Musica musica1 = new Musica("Música 1", "Artista", 100);
        Usuario usuario = new Usuario("Usuário", "usuario@sonora.com");
        Musica musica2 = new Musica("Música 2", "Artista", 100);

        assertNotNull(usuario);
        assertEquals(musica1.getId() + 1, musica2.getId());
    }
}
