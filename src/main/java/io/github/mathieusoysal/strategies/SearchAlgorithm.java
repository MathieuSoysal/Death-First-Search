package io.github.mathieusoysal.strategies;

import io.github.mathieusoysal.entities.Link;
import io.github.mathieusoysal.entities.Node;

@FunctionalInterface
/**
 * Strategy interface for executing a specific strategy on a node.
 * <p>
 * This interface defines a single method, {@code execute}, which takes a
 * {@link Node} and an integer representing the number of nodes as parameters.
 * Implementations of this interface should provide the specific logic for the
 * strategy in the {@code execute} method.
 * </p>
 */
public interface SearchAlgorithm {
    public Link execute(Node startNode, int nbNodes);
}
