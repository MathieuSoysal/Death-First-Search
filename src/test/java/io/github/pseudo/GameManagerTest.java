package io.github.pseudo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    void testGameManagerInitialization() {
        int nbNoeuds = 5;
        GameManager gameManager = new GameManager(nbNoeuds);

        assertEquals(gameManager.getNode(0).getIndex(), 0);
    }

    @Test
    void testAddLink() {
        int nbNoeuds = 5;
        GameManager gameManager = new GameManager(nbNoeuds);
        gameManager.addLink(1, 2);

        assertEquals(gameManager.getNode(1).getLinks().size(), 1);
        assertEquals(gameManager.getNode(2).getLinks().size(), 1);
    }

    @Test
    void testSetAsPaserelle() {
        int nbNoeuds = 5;
        GameManager gameManager = new GameManager(nbNoeuds);
        gameManager.addLink(0, 1);
        gameManager.setAsPaserelle(1);

        assertTrue(gameManager.getNode(1).isPaserelle());
    }
}
