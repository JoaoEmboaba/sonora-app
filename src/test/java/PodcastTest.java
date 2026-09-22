import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PodcastTest {

    @Test
    @DisplayName("Podcast deve aceitar dados válidos")
    void deveCriarPodcastValido() {
        Podcast podcast = new Podcast("Tecnologia e Java", 1800, "Ana Souza", 1);

        assertEquals("Tecnologia e Java", podcast.getTitulo());
        assertEquals(1800, podcast.getDuracaoSegundos());
        assertEquals("Ana Souza", podcast.getApresentador());
        assertEquals(1, podcast.getNumeroEpisodio());
    }

    @Test
    @DisplayName("Número do episódio menor que 1 deve ser rejeitado")
    void deveRejeitarNumeroEpisodioInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Podcast("Tecnologia", 100, "Ana", 0));
    }

    @Test
    @DisplayName("Apresentador vazio deve ser rejeitado")
    void deveRejeitarApresentadorVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Podcast("Tecnologia", 100, "", 1));
    }

    @Test
    @DisplayName("Podcast deve herdar o método reproduzir de Conteudo")
    void deveReproduzir() {
        Podcast podcast = new Podcast("Tecnologia", 100, "Ana", 1);

        assertDoesNotThrow(podcast::reproduzir);
    }

    @Test
    @DisplayName("toString de Podcast reutiliza toString de Conteudo")
    void deveSobrescreverToString() {
        Podcast podcast = new Podcast("Tecnologia", 100, "Ana", 7);

        String texto = podcast.toString();

        assertTrue(texto.contains("Tecnologia"));
        assertTrue(texto.contains("100s"));
        assertTrue(texto.contains("Episódio 7"));
        assertTrue(texto.contains("Ana"));
    }
}
