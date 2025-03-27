package io.github.pseudo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class GameManager {
    private final Node[] nodes;
    private int nbNoeuds;

    public GameManager(int nbNoeuds) {
        this.nbNoeuds = nbNoeuds;
        this.nodes = IntStream
            .rangeClosed(0, nbNoeuds)
            .mapToObj(Node::new)
            .toArray(Node[]::new);
    }

    public void addLink(int node1, int node2)
    {
        Link link = new Link(nodes[node1], nodes[node2]);
        nodes[node1].addLink(link);
        nodes[node2].addLink(link);
    }

    public void setAsPaserelle(int node)
    {
        nodes[node].setAsPaserelle();
    }


}
