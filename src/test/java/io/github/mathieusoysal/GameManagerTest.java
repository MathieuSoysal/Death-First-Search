package io.github.mathieusoysal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.entities.Link;
import io.github.mathieusoysal.strategies.DFS;

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
    void testSetAsPasserelle() {
        int nbNoeuds = 5;
        GameManager gameManager = new GameManager(nbNoeuds);
        gameManager.addLink(0, 1);
        gameManager.setAsPaserelle(1);

        assertTrue(gameManager.getNode(1).isGateway());
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

        Link link = gameManager.getNearestClosableLink(1, new DFS());
        assertEquals(link.getNode1().getIndex(), 1);
        assertEquals(link.getNode2().getIndex(), 2);
    }

    @Test
    void testGetNearestClosableLink_hard() {
        int nbNoeuds = 15;
        GameManager gameManager = new GameManager(nbNoeuds);
        gameManager.addLink(0, 1);

        gameManager.addLink(1, 2);
        gameManager.setAsPaserelle(2);

        gameManager.addLink(1, 3);
        gameManager.addLink(3, 4);
        gameManager.addLink(3, 5);
        gameManager.setAsPaserelle(4);
        gameManager.setAsPaserelle(5);

        gameManager.addLink(0, 6);
        gameManager.addLink(6, 7);
        gameManager.addLink(7, 8);
        gameManager.addLink(7, 9);
        gameManager.setAsPaserelle(8);
        gameManager.setAsPaserelle(9);

        Link link = gameManager.getNearestClosableLink(0, new DFS());
        assertEquals(link.getNode1().getIndex(), 3);
    }

    @Test
    void testGetNearestClosableLink_nullPointerCheck() {
        int nbNoeuds = 15;
        GameManager gameManager = new GameManager(nbNoeuds);
        gameManager.addLink(0, 1);
        gameManager.addLink(0, 2);
        gameManager.addLink(0, 3);
        gameManager.addLink(2, 3);
        gameManager.addLink(2, 6);
        gameManager.addLink(1, 7);
        gameManager.addLink(1, 3);
        gameManager.addLink(7, 3);
        gameManager.addLink(7, 4);
        gameManager.addLink(3, 4);
        gameManager.addLink(3, 5);
        gameManager.addLink(6, 3);
        gameManager.addLink(6, 5);

        gameManager.setAsPaserelle(4);
        gameManager.setAsPaserelle(5);

        Link link = gameManager.getNearestClosableLink(0, new DFS());
        assertEquals(link.getNode1().getIndex(), 3);
    }
}
