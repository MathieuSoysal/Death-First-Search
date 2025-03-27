package io.github.pseudo;

public class DFS {

    private static int MAX_URGENCE = -1;
    private static Node urgentestNode = null;

    public static Link foundMaxUrgentNode(Node node, int nbNodes) {
        MAX_URGENCE = -1;
        urgentestNode = null;
        boolean[] visited = new boolean[nbNodes + 1];
        foundMaxUrgentNode(visited, node, 1);
        return urgentestNode.getLinks()
                .stream()
                .filter(l -> l.canBeClose())
                .findAny()
                .get();
    }

    private static void foundMaxUrgentNode(boolean[] visited, Node node, int nbBonusAction) {
        if (visited[node.getIndex()])
            return;
        visited[node.getIndex()] = true;
        if (node.isPaserelle())
            return;
        if (canDoBonusAction(node))
            nbBonusAction++;
        else if (nbClosableLink(node) - nbBonusAction > MAX_URGENCE) {
            MAX_URGENCE = nbClosableLink(node) - nbBonusAction;
            urgentestNode = node;
        }
        for (Link link : node.getLinks()) {
            Node nextNode = link.getNode1() == node ? link.getNode2() : link.getNode1();
            foundMaxUrgentNode(visited, nextNode, nbBonusAction);
        }
    }

    private static boolean canDoBonusAction(Node node) {
        return node.getLinks().stream().allMatch(link -> !link.canBeClose());
    }

    private static int nbClosableLink(Node node) {
        return (int) (node.getLinks().stream().filter(link -> link.canBeClose()).count());
    }

}
