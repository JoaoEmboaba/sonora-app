import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConteudoTest {

    @Test
    @DisplayName("Conteúdo deve aceitar título e duração válidos")
    void deveAceitarDadosValidos() {
        Conteudo conteudo = new Conteudo("Conteúdo de teste", 120);

        assertTrue(conteudo.getId() > 0);
        assertEquals("Conteúdo de teste", conteudo.getTitulo());
        assertEquals(120, conteudo.getDuracaoSegundos());
    }

    @Test
    @DisplayName("Título nulo deve ser rejeitado por Conteudo")
    void deveRejeitarTituloNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Conteudo(null, 120));
    }

    @Test
    @DisplayName("Título vazio deve ser rejeitado por Conteudo")
    void deveRejeitarTituloVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Conteudo("", 120));
    }

    @Test
    @DisplayName("Duração zero deve ser rejeitada por Conteudo")
    void deveRejeitarDuracaoZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Conteudo("Teste", 0));
    }

    @Test
    @DisplayName("Duração negativa deve ser rejeitada por Conteudo")
    void deveRejeitarDuracaoNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new Conteudo("Teste", -10));
    }

    @Test
    @DisplayName("Setter de título deve validar e alterar o título")
    void deveAlterarTitulo() {
        Conteudo conteudo = new Conteudo("Original", 100);
        conteudo.setTitulo("Novo título");

        assertEquals("Novo título", conteudo.getTitulo());
        assertThrows(IllegalArgumentException.class, () -> conteudo.setTitulo(""));
    }

    @Test
    @DisplayName("Setter de duração deve validar e alterar a duração")
    void deveAlterarDuracao() {
        Conteudo conteudo = new Conteudo("Teste", 100);
        conteudo.setDuracaoSegundos(200);

        assertEquals(200, conteudo.getDuracaoSegundos());
        assertThrows(IllegalArgumentException.class, () -> conteudo.setDuracaoSegundos(0));
    }

    @Test
    @DisplayName("Reproduzir deve usar a representação do conteúdo")
    void deveReproduzir() {
        Conteudo conteudo = new Conteudo("Teste", 100);

        assertDoesNotThrow(conteudo::reproduzir);
    }

    @Test
    @DisplayName("toString deve apresentar id, título e duração")
    void deveFormatarToString() {
        Conteudo conteudo = new Conteudo("Teste", 100);

        String texto = conteudo.toString();

        assertTrue(texto.contains("[" + conteudo.getId() + "]"));
        assertTrue(texto.contains("Teste"));
        assertTrue(texto.contains("100s"));
    }
}
