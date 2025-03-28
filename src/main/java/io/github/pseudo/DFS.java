package io.github.pseudo;

public class DFS {

    private static int MAX_URGENCE = -1;
    private static Node urgentestNode = null;

    public static Link foundMaxUrgentNode(Node node, int nbNodes) {
        MAX_URGENCE = Integer.MIN_VALUE;
        urgentestNode = null;
        int[] visited = new int[nbNodes + 1];
        for (int i = 0; i < visited.length; i++)
            visited[i] = Integer.MAX_VALUE;
        foundMaxUrgentNode(visited, node, 0);
        System.err.println(MAX_URGENCE);
        System.err.println(nbClosableLink(urgentestNode));
        return urgentestNode.getLinks()
                .stream()
                .filter(l -> l.canBeClose())
                .findAny()
                .get();
    }

    private static void foundMaxUrgentNode(int[] visited, Node node, int nbBonusAction) {
        if (node.isPaserelle())
            return;
        if (canDoBonusAction(node)) {
            if (nbBonusAction >= visited[node.getIndex()])
                return;
            visited[node.getIndex()] = nbBonusAction;
            nbBonusAction++;
        } else {
            int score = nbClosableLink(node) - nbBonusAction;
            if (visited[node.getIndex()] != Integer.MAX_VALUE && score <= visited[node.getIndex()])
                return;
            visited[node.getIndex()] = score;
            if (score > MAX_URGENCE) {
                MAX_URGENCE = score;
                urgentestNode = node;
            }
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
