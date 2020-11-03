package ds.ThirdAssignment;

public class Node {
    private final int x;
    private final int y;
    Node previousNode;

    public Node(int x, int y, Node previousNode) {
        this.x = x;
        this.y = y;
        this.previousNode = previousNode;
    }

    public int getX() { return x; }

    public int getY() {
        return y;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}