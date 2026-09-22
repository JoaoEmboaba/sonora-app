import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    private Usuario dono;
    private Playlist playlist;
    private Musica musica1;
    private Musica musica2;
    private Musica musica3;

    @BeforeEach
    void setUp() {
        dono = new Usuario("João", "joao@sonora.com");
        playlist = new Playlist("Favoritas", dono);
        musica1 = new Musica("Música 1", 100, "Artista 1", "Álbum");
        musica2 = new Musica("Música 2", 200, "Artista 2", "Álbum");
        musica3 = new Musica("Música 3", 300, "Artista 3", "Álbum");
    }

    @Test
    @DisplayName("Adicionar música em playlist com espaço retorna true e aumenta a quantidade")
    void deveAdicionarMusicaComEspacoDisponivel() {
        assertTrue(playlist.adicionar(musica1));
        assertEquals(1, playlist.getQuantidade());
        assertSame(musica1, playlist.getNaPosicao(0));
    }

    @Test
    @DisplayName("Adicionar várias músicas em playlist com espaço mantém a quantidade correta")
    void deveAdicionarVariasMusicas() {
        assertTrue(playlist.adicionar(musica1));
        assertTrue(playlist.adicionar(musica2));
        assertTrue(playlist.adicionar(musica3));
        assertEquals(3, playlist.getQuantidade());
    }

    @Test
    @DisplayName("Adicionar música nula deve lançar IllegalArgumentException")
    void deveRejeitarMusicaNula() {
        assertThrows(IllegalArgumentException.class,
                () -> playlist.adicionar(null));
        assertEquals(0, playlist.getQuantidade());
    }

    @Test
    @DisplayName("ArrayList permite adicionar mais de 100 músicas sem limite fixo")
    void devePermitirMaisDeCemMusicas() {
        for (int i = 0; i < 101; i++) {
            assertTrue(playlist.adicionar(new Musica("Música " + i, 100, "Artista", "Álbum")));
        }

        assertEquals(101, playlist.getQuantidade());
    }

    @Test
    @DisplayName("Posição válida devolve a música correta")
    void deveRetornarMusicaDaPosicaoValida() {
        playlist.adicionar(musica1);
        playlist.adicionar(musica2);

        assertSame(musica2, playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("Índice negativo deve lançar IndexOutOfBoundsException")
    void deveLancarExcecaoParaIndiceNegativo() {
        playlist.adicionar(musica1);

        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.getNaPosicao(-1));
    }

    @Test
    @DisplayName("Índice além da quantidade deve lançar IndexOutOfBoundsException")
    void deveLancarExcecaoParaIndiceAlemDaQuantidade() {
        playlist.adicionar(musica1);

        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("Remoção de posição válida reorganiza a lista sem deixar buraco")
    void deveRemoverEReorganizarPlaylist() {
        playlist.adicionar(musica1);
        playlist.adicionar(musica2);
        playlist.adicionar(musica3);

        assertTrue(playlist.removerNaPosicao(1));

        assertEquals(2, playlist.getQuantidade());
        assertSame(musica1, playlist.getNaPosicao(0));
        assertSame(musica3, playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("Remover a primeira posição reorganiza as músicas seguintes")
    void deveReorganizarAoRemoverPrimeiraPosicao() {
        playlist.adicionar(musica1);
        playlist.adicionar(musica2);
        playlist.adicionar(musica3);

        assertTrue(playlist.removerNaPosicao(0));

        assertSame(musica2, playlist.getNaPosicao(0));
        assertSame(musica3, playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("Índice inválido na remoção deve lançar IndexOutOfBoundsException")
    void deveLancarExcecaoAoRemoverIndiceInvalido() {
        playlist.adicionar(musica1);

        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.removerNaPosicao(5));
    }

    @Test
    @DisplayName("Cada música adicionada aumenta a quantidade")
    void deveAumentarQuantidadeAoAdicionar() {
        assertEquals(0, playlist.getQuantidade());
        playlist.adicionar(musica1);
        assertEquals(1, playlist.getQuantidade());
        playlist.adicionar(musica2);
        assertEquals(2, playlist.getQuantidade());
    }
}
