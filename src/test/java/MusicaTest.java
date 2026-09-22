import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class MusicaTest {

    private Musica musica;

    @BeforeEach
    void setUp() {
        musica = new Musica("Bohemian Rhapsody", "Queen", 355);
    }

    @Test
    @DisplayName("Duração com minutos e segundos")
    void deveFormatarDuracaoComMinutosESegundos() {
        musica = new Musica("Música", "Artista", 125);
        assertEquals("02:05", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração redonda em minutos")
    void deveFormatarDuracaoRedondaEmMinutos() {
        musica = new Musica("Música", "Artista", 90);
        assertEquals("01:30", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Menos de um minuto, com zero à esquerda")
    void deveFormatarMenosDeUmMinuto() {
        musica = new Musica("Música", "Artista", 5);
        assertEquals("00:05", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Dois dígitos nos minutos")
    void deveFormatarDezMinutos() {
        musica = new Musica("Música", "Artista", 600);
        assertEquals("10:00", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Valor logo abaixo de dez minutos")
    void deveFormatarValorLogoAbaixoDeDezMinutos() {
        musica = new Musica("Música", "Artista", 599);
        assertEquals("09:59", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Título vazio deve ser rejeitado")
    void deveRejeitarTituloVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("", "Queen", 355));
    }

    @Test
    @DisplayName("Título nulo deve ser rejeitado")
    void deveRejeitarTituloNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica(null, "Queen", 355));
    }

    @Test
    @DisplayName("Artista vazio deve ser rejeitado")
    void deveRejeitarArtistaVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "", 355));
    }

    @Test
    @DisplayName("Duração zero deve ser rejeitada")
    void deveRejeitarDuracaoZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "Queen", 0));
    }

    @Test
    @DisplayName("Duração negativa deve ser rejeitada")
    void deveRejeitarDuracaoNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "Queen", -10));
    }

    @Test
    @DisplayName("Dados válidos criam a música")
    void deveCriarMusicaComDadosValidos() {
        assertNotNull(musica);
        assertTrue(musica.getId() > 0);
    }

    @Test
    @DisplayName("Cada chamada de reproduzir aumenta o contador em um")
    void reproduzirDeveIncrementarContadorEmUm() {
        assertEquals(0, musica.getReproducoes());

        musica.reproduzir();
        assertEquals(1, musica.getReproducoes());

        musica.reproduzir();
        assertEquals(2, musica.getReproducoes());

        musica.reproduzir();
        assertEquals(3, musica.getReproducoes());
    }
}
