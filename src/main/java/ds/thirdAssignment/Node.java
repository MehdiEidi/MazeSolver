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

    /**
     * Gets x axis of the node.
     * @return x axis of the node.
     */
    public int getX() { return x; }

    /**
     * Gets y axis of the node.
     * @return y axis of the node.
     */
    public int getY() { return y; }

    /**
     * Gets the previous node that is connected this node.
     * @return the previous node that is connected to this node.
     */
    public Node getPreviousNode() { return previousNode; }

    /**
     * String representation of a node.
     * @return string representation of a node.
     */
    public String toString() { return "(" + this.x + ", " + this.y + ")"; }
}