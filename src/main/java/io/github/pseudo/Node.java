package io.github.pseudo;

import java.util.ArrayList;

public class Node {
    private final int index;
    private final ArrayList<Link> links;
    private KindNode kind;

    public Node(int index)
    {
        this.index = index;
        this.links = new ArrayList<Link>();
        this.kind = KindNode.Normal;
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public void setAsPaserelle()
    {
        this.kind = KindNode.Normal;
    }

    public boolean isPaserelle()
    {
        return this.kind == KindNode.Paserelle;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (index != other.index)
            return false;
        return true;
    }

}


enum KindNode {
    Paserelle,
    Normal
}