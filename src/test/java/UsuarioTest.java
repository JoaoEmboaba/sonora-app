import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario joao;
    private Usuario maria;
    private Usuario pedro;

    @BeforeEach
    void setUp() {
        joao = new Usuario("João", "joao@sonora.com");
        maria = new Usuario("Maria", "maria@sonora.com");
        pedro = new Usuario("Pedro", "pedro@sonora.com");
    }

    @Test
    @DisplayName("Seguir outro usuário adiciona o usuário à lista de seguindo")
    void deveSeguirOutroUsuario() {
        joao.seguir(maria);

        assertEquals(1, joao.getQuantidadeSeguindo());
        assertSame(maria, joao.getSeguindo().get(0));
    }

    @Test
    @DisplayName("Não deve adicionar duas vezes o mesmo usuário seguido")
    void naoDeveSeguirDuasVezes() {
        joao.seguir(maria);
        joao.seguir(maria);

        assertEquals(1, joao.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("Usuário não pode seguir a si mesmo")
    void naoDeveSeguirASiMesmo() {
        assertThrows(IllegalArgumentException.class, () -> joao.seguir(joao));
        assertEquals(0, joao.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("Usuário não pode seguir um usuário nulo")
    void naoDeveSeguirUsuarioNulo() {
        assertThrows(IllegalArgumentException.class, () -> joao.seguir(null));
        assertEquals(0, joao.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("Deixar de seguir remove o usuário da lista de seguindo")
    void deveDeixarDeSeguir() {
        joao.seguir(maria);
        joao.seguir(pedro);

        joao.deixarDeSeguir(maria);

        assertEquals(1, joao.getQuantidadeSeguindo());
        assertSame(pedro, joao.getSeguindo().get(0));
    }

    @Test
    @DisplayName("Deixar de seguir um usuário que não está sendo seguido não altera a lista")
    void deveIgnorarUsuarioNaoSeguido() {
        joao.seguir(maria);

        joao.deixarDeSeguir(pedro);

        assertEquals(1, joao.getQuantidadeSeguindo());
        assertSame(maria, joao.getSeguindo().get(0));
    }

    @Test
    @DisplayName("Deixar de seguir usuário nulo deve lançar IllegalArgumentException")
    void naoDeveDeixarDeSeguirUsuarioNulo() {
        assertThrows(IllegalArgumentException.class, () -> joao.deixarDeSeguir(null));
    }
}
