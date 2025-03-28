package io.github.pseudo;

public class Link {
    private final Node node1, node2;
    private boolean isClosed;

    public Link(Node node1, Node node2) {
        isClosed = false;
        this.node1 = node1;
        this.node2 = node2;
    }

    public void close() {
        if (!canBeClose())
            throw new RuntimeException("safe link cannot be close");
        isClosed = true;
        node1.removeLink(this);
        node2.removeLink(this);
    }

    public boolean canBeClose() {
        return !isClosed && (node1.isPaserelle() || node2.isPaserelle());
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    @Override
    public String toString() {
        return node1.getIndex() + " " + node2.getIndex();
    }

}
