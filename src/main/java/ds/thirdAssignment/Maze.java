package ds.thirdAssignment;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Maze {
    private final int[][] mazeMatrix;

    /**
     * Constructs a maze using a matrix. In matrix, 0 means open space, 1 means block, 2 means exit.
     * @param mazeMatrix input maze represented as matrix.
     */
    public Maze(int[][] mazeMatrix) {
        validateMatrix(mazeMatrix);

        this.mazeMatrix = mazeMatrix;
    }

    //There must only be 0, 1 or 2 in the matrix.
    private void validateMatrix(int[][] matrix) {
        int numberOfExits = 0;

        for (int[] row : matrix) {
            for (int element : row) {
                if (element == 2) {
                    numberOfExits++;
                }

                if (!(element == 0 || element == 1 || element == 2)) {
                    throw new IllegalArgumentException("The given matrix is invalid. Available numbers are: 0, 1, 2");
                }
            }
        }

        if (numberOfExits > 1) {
            throw new IllegalArgumentException("There must be only one exit in the matrix.");
        }
    }

    //Checks to see if a node is an open space, which could be included in the path, or its a blocked space.
    private boolean isOpenSpace(Node node) {
        return (mazeMatrix[node.getX()][node.getY()] == 0 || mazeMatrix[node.getX()][node.getY()] == 2);
    }

    //Checks to see if a node is in range of the mazeMatrix or not.
    private boolean isInRange(Node node) {
        return (node.getX() >= 0 && node.getX() < mazeMatrix.length) && (node.getY() >= 0 && node.getY() < mazeMatrix[node.getX()].length);
    }

    //Backtracks and generates a path from start to exit.
    private String generatePath(Node node) {
        //For the reason that we trace the path from exit to start, for reversing the path and making it
        //a path from start to exit, we can use a stack.
        Stack<String> exitToStartPath = new Stack<>();

        while (node.getPreviousNode() != null) {
            if (node.getX() > node.getPreviousNode().getX()) {
                exitToStartPath.add("down");
            } else if (node.getX() < node.getPreviousNode().getX()) {
                exitToStartPath.add("up");
            } else if (node.getY() > node.getPreviousNode().getY()) {
                exitToStartPath.add("right");
            } else if (node.getY() < node.getPreviousNode().getY()) {
                exitToStartPath.add("left");
            }

            node = node.getPreviousNode();
        }

        StringBuilder startToExitPath = new StringBuilder();

        while (!exitToStartPath.isEmpty()) {
            startToExitPath.append(exitToStartPath.pop()).append(" | ");
        }

        return startToExitPath.toString();
    }

    //Creates a node above the currentNode.
    private Node up(Node currentNode) {
        return new Node(currentNode.getX(), currentNode.getY() + 1, currentNode);
    }

    //Creates a node below the currentNode.
    private Node down(Node currentNode) {
        return new Node(currentNode.getX(), currentNode.getY() - 1, currentNode);
    }

    //Creates a node in right side of the currentNode.
    private Node right(Node currentNode) {
        return new Node(currentNode.getX() + 1, currentNode.getY(), currentNode);
    }

    //Creates a node in left side of the currentNode.
    private Node left(Node currentNode) {
        return new Node(currentNode.getX() - 1, currentNode.getY(), currentNode);
    }

    //Moves in all possible directions and adds adjacent nodes to the process queue.
    private void moveOneLayerAhead(Queue<Node> queue, Node currentNode) {
        if (isInRange(right(currentNode)) && isOpenSpace(right(currentNode))) {
            //Marking the currentNode as a visited node. 3 means visited node in the matrix.
            mazeMatrix[currentNode.getX()][currentNode.getY()] = 3;

            queue.add(right(currentNode));
        }

        if (isInRange(left(currentNode)) && isOpenSpace(left(currentNode))) {
            //Marking the currentNode as a visited node. 3 means visited node in the matrix.
            mazeMatrix[currentNode.getX()][currentNode.getY()] = 3;

            queue.add(left(currentNode));
        }

        if (isInRange(up(currentNode)) && isOpenSpace(up(currentNode))) {
            //Marking the currentNode as a visited node. 3 means visited node in the matrix.
            mazeMatrix[currentNode.getX()][currentNode.getY()] = 3;

            queue.add(up(currentNode));
        }

        if (isInRange(down(currentNode)) && isOpenSpace(down(currentNode))) {
            //Marking the currentNode as a visited node. 3 means visited node in the matrix.
            mazeMatrix[currentNode.getX()][currentNode.getY()] = 3;

            queue.add(down(currentNode));
        }
    }

    /**
     * Solves the maze (finds a path from start to exit) using BFS algorithm.
     * @param startNode the starting node of the map.
     * @return a string containing the path.
     */
    public String solve(Node startNode) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(startNode);

        //Searching the maze layer by layer using BFS algorithm and stop the search once one of the paths
        //end to the exit node. Then we backtrack and generate the path.
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            //If the currentNode is the exit node (2 in matrix means exit node), we are finished.
            // So, we need to backtrack and generate the path.
            if (mazeMatrix[currentNode.getX()][currentNode.getY()] == 2) {
                return generatePath(currentNode);
            }

            //Each time we go one layer ahead and add the adjacent nodes to the queue.
            moveOneLayerAhead(queue, currentNode);
        }

        return "Oops! Didn't find the exit.";
    }

    public static void main(String[] args) {
        int[][] mazeMatrix = new int[][] { {0, 0, 1, 0, 1, 1, 0},
                                           {1, 0, 0, 1, 0, 0, 0},
                                           {1, 0, 0, 1, 0, 0, 1},
                                           {1, 1, 0, 0, 1, 0, 0},
                                           {0, 0, 0, 0, 1, 0, 1},
                                           {0, 1, 0, 1, 0, 0, 2},
                                           {0, 0, 0, 0, 0, 1, 0} };

        Maze maze = new Maze(mazeMatrix);

        System.out.println("\nFollow the path below to get to the cheese: \n");
        System.out.println(maze.solve(new Node(0, 0, null)));
    }
}