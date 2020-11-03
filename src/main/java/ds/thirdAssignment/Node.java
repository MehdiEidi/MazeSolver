package ds.thirdAssignment;

public class Node {
    private final int x;
    private final int y;
    private final Node previousNode;

    /**
     * Constructs a node.
     * @param x x axis of the node.
     * @param y y axis of the node.
     * @param previousNode the previous node of the current node.
     */
    public Node(int x, int y, Node previousNode) {
        this.x = x;
        this.y = y;
        this.previousNode = previousNode;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public Node getPreviousNode() { return previousNode; }

    public String toString() { return "(" + this.x + ", " + this.y + ")"; }
}