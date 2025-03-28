package io.github.mathieusoysal.strategies;

import static io.github.mathieusoysal.strategies.ScoreCalculator.calculateScore;

import io.github.mathieusoysal.entities.Link;
import io.github.mathieusoysal.entities.Node;

public class DFS implements SearchAlgorithm {

    private int highestUrgencyValue = -1;
    private Node mostUrgentNode = null;

    @Override
    public Link execute(Node currentNode, int nbNodes) {
        return foundMostUrgentLink(currentNode, nbNodes);
    }

    public Link foundMostUrgentLink(Node node, int nbNodes) {
        highestUrgencyValue = Integer.MIN_VALUE;
        int[] visited = new int[nbNodes + 1];
        for (int i = 0; i < visited.length; i++)
            visited[i] = Integer.MAX_VALUE;
        foundMaxUrgentNode(visited, node, 0);
        return mostUrgentNode.getLinks()
                .stream()
                .filter(l -> l.canBeClose())
                .findAny()
                .orElseThrow(() -> new RuntimeException("No closable link found"));
    }

    private void foundMaxUrgentNode(int[] historic, Node node, int nbBonusAction) {
        if (node.isGateway())
            return;
        if (canDoBonusAction(node)) {
            nbBonusAction++;
            if (nbBonusAction >= historic[node.getIndex()])
                return;
            historic[node.getIndex()] = nbBonusAction;
        } else {
            int score = calculateScore(node, nbBonusAction);
            if (score <= historic[node.getIndex()]
                    && historic[node.getIndex()] != Integer.MAX_VALUE)
                return;
            historic[node.getIndex()] = score;
            if (score > highestUrgencyValue) {
                highestUrgencyValue = score;
                mostUrgentNode = node;
            }
        }
        for (Link link : node.getLinks()) {
            Node nextNode = link.getNode1() == node ? link.getNode2() : link.getNode1();
            foundMaxUrgentNode(historic, nextNode, nbBonusAction);
        }
    }

    private static boolean canDoBonusAction(Node node) {
        return node.getLinks().stream().allMatch(link -> !link.canBeClose());
    }

}
