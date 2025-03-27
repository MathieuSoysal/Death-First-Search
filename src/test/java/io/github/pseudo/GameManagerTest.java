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

    @Test
    void testGetNearestClosableLink() {
        int nbNoeuds = 5;
        GameManager gameManager = new GameManager(nbNoeuds);
        gameManager.addLink(0, 1);
        gameManager.addLink(1, 2);
        gameManager.addLink(2, 3);
        gameManager.addLink(3, 4);
        gameManager.addLink(4, 0);
        gameManager.setAsPaserelle(2);
        gameManager.setAsPaserelle(4);

        Link link = gameManager.getNearestClosableLink(1);
        assertEquals(link.getNode1().getIndex(), 1);
        assertEquals(link.getNode2().getIndex(), 2);
    }
}
