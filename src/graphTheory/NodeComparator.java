package graphTheory;

import graphTheory.Node;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node a, Node b) {
        return a.degreesFromCenter < b.degreesFromCenter ? -1 : a.degreesFromCenter == b.degreesFromCenter ? 0 : 1;
    }
}
