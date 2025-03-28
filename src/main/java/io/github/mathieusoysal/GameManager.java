package io.github.mathieusoysal;

import java.util.stream.IntStream;

import io.github.mathieusoysal.entities.Link;
import io.github.mathieusoysal.entities.Node;
import io.github.mathieusoysal.strategies.SearchAlgorithm;

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

    public void addLink(int node1, int node2) {
        Link link = new Link(nodes[node1], nodes[node2]);
        nodes[node1].addLink(link);
        nodes[node2].addLink(link);
    }

    public void setAsPaserelle(int node) {
        nodes[node].setAsPaserelle();
    }

    protected Node getNode(int index) {
        return nodes[index];
    }

    public Link getNearestClosableLink(int node, SearchAlgorithm strategy) {
        return strategy.execute(nodes[node], nbNoeuds);
    }

}
