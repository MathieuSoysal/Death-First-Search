package io.github.mathieusoysal.strategies;

import io.github.mathieusoysal.entities.Node;

public class ScoreCalculator {
    public static int calculateScore(Node node, int nbBonusAction) {
        return nbClosableLink(node) - nbBonusAction;
    }

    private static int nbClosableLink(Node node) {
        return (int) (node.getLinks().stream().filter(link -> link.canBeClose()).count());
    }
}
