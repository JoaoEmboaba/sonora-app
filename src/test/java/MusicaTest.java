import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicaTest {

    private Musica musica;

    @BeforeEach
    void setUp() {
        musica = new Musica("Bohemian Rhapsody", 355, "Queen", "A Night at the Opera");
    }

    @Test
    @DisplayName("Duração com minutos e segundos")
    void deveFormatarDuracaoComMinutosESegundos() {
        musica.setDuracaoSegundos(125);
        assertEquals("02:05", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração redonda em minutos")
    void deveFormatarDuracaoRedondaEmMinutos() {
        musica.setDuracaoSegundos(90);
        assertEquals("01:30", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Menos de um minuto, com zero à esquerda")
    void deveFormatarMenosDeUmMinuto() {
        musica.setDuracaoSegundos(5);
        assertEquals("00:05", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Dois dígitos nos minutos")
    void deveFormatarDezMinutos() {
        musica.setDuracaoSegundos(600);
        assertEquals("10:00", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Valor logo abaixo de dez minutos")
    void deveFormatarValorLogoAbaixoDeDezMinutos() {
        musica.setDuracaoSegundos(599);
        assertEquals("09:59", musica.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Título vazio deve ser rejeitado")
    void deveRejeitarTituloVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("", 355, "Queen", "A Night at the Opera"));
    }

    @Test
    @DisplayName("Título nulo deve ser rejeitado")
    void deveRejeitarTituloNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica(null, 355, "Queen", "A Night at the Opera"));
    }

    @Test
    @DisplayName("Artista vazio deve ser rejeitado")
    void deveRejeitarArtistaVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", 355, "", "A Night at the Opera"));
    }

    @Test
    @DisplayName("Artista nulo deve ser rejeitado")
    void deveRejeitarArtistaNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", 355, null, "A Night at the Opera"));
    }

    @Test
    @DisplayName("Duração zero deve ser rejeitada")
    void deveRejeitarDuracaoZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", 0, "Queen", "A Night at the Opera"));
    }

    @Test
    @DisplayName("Duração negativa deve ser rejeitada")
    void deveRejeitarDuracaoNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", -1, "Queen", "A Night at the Opera"));
    }

    @Test
    @DisplayName("Álbum vazio deve ser rejeitado")
    void deveRejeitarAlbumVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", 355, "Queen", ""));
    }

    @Test
    @DisplayName("Álbum nulo deve ser rejeitado")
    void deveRejeitarAlbumNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", 355, "Queen", null));
    }

    @Test
    @DisplayName("Musica reutiliza título e duração de Conteudo")
    void deveHerdarDadosDeConteudo() {
        assertEquals("Bohemian Rhapsody", musica.getTitulo());
        assertEquals(355, musica.getDuracaoSegundos());
        assertEquals("Queen", musica.getArtista());
        assertEquals("A Night at the Opera", musica.getAlbum());
    }

    @Test
    @DisplayName("toString de Musica reutiliza toString de Conteudo")
    void deveSobrescreverToString() {
        String texto = musica.toString();

        assertTrue(texto.contains("Bohemian Rhapsody"));
        assertTrue(texto.contains("355s"));
        assertTrue(texto.contains("Queen"));
        assertTrue(texto.contains("A Night at the Opera"));
    }
}
