package io.github.pseudo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    void testGameManagerInitialization() {
        int nbNoeuds = 5;
        GameManager gameManager = new GameManager(nbNoeuds);

        assertNotNull(gameManager);
    }
}
