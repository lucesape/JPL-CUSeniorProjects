package mars.algorithm;

import mars.coordinate.Coordinate;
import mars.rover.MarsRover;
import mars.out.TerminalOutput;
import mars.map.TerrainMap;
import java.util.*;

import java.util.ArrayList;

/**
 * Class which implements the path-finding algorithm without a limited field of view.
 */
public class AlgorithmUnlimitedScopeNonRecursive extends Algorithm {

    /*
     * Default constructor for an AlgorithmUnlimitedScopeNonRecursive.
     *
     * @param map The terrain map
     * @param rover The rover
     */
    public AlgorithmUnlimitedScopeNonRecursive(TerrainMap m, MarsRover r) {
        map = m;
        rover = r;
    }

    public void findPath() {
        Coordinate startPosition = rover.getStartPosition();
        Coordinate endPosition= rover.getEndPosition();

        List<Node> openList = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();

        Node startNode = new Node(startPosition);
        Node goalNode = new Node(endPosition);

        startNode.setGScore(0);
        startNode.setFScore(estimateHeuristic(startNode, goalNode));
        startNode.setParent(null);

        openList.add(startNode);

        // While the open set isn't empty...
        while (!openList.isEmpty()) {
            // Get the node with the lowest F score.
            Node currentNode = getLowestFScore(openList);

            if (currentIsGoal(currentNode, goalNode)) {
                constructPath(currentNode);
                break;
            }

            openList.remove(currentNode);
            closedList.add(currentNode);

            // Get the list of neighbor nodes.
            List<Node> neighborList = getNeighbors(currentNode);
            // For each neighbor node...
            for (Node neighbor: neighborList) {
                // Ignore neighbors if it's too steep and we can't go there.
                if (!rover.canTraverse(currentNode.getPosition(), neighbor.getPosition())) {
                    closedList.add(currentNode);
                }
                // Ignore neighbors if we've already evaluated them.
                if (isNodeInList(neighbor, closedList)) {
                    continue;
                }
                // If we're at an undiscovered node...
                if (!isNodeInList(neighbor, openList)) {
                    openList.add(neighbor);
                }

                // Gets tentative G score.
                double tentativeGScore = currentNode.getGScore() + distBetween(currentNode, neighbor);

                // If the tentative G score is higher than the neighbor's G score, this is
                // not a better path.
                if (tentativeGScore >= neighbor.getGScore()) {
                    continue;
                }

                // If we get to this point, the path is optimal up to this point.
                // So, we record the current Node for the full path.
                neighbor.setParent(currentNode);
                neighbor.setGScore(tentativeGScore);
                neighbor.setFScore(neighbor.getGScore() + estimateHeuristic(neighbor, goalNode));
            }
        }

        output = new TerminalOutput(fullPath);
    }

    private boolean isNodeInList(Node node, List<Node> list) {
        int x = node.getPosition().getX();
        int y = node.getPosition().getY();
        for (Node n : list) {
            int tmpX = n.getPosition().getX();
            int tmpY = n.getPosition().getY();
            if ((x == tmpX) && (y == tmpY)) {return true;}
        }
        return false;
    }

    private void constructPath(Node currentNode) {
        while (currentNode != null) {
            fullPath.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
    }

    private double distBetween(Node currentNode, Node neighborNode) {
        int currentX = currentNode.getPosition().getX();
        int currentY = currentNode.getPosition().getY();
        int neighborX = neighborNode.getPosition().getX();
        int neighborY = neighborNode.getPosition().getY();

        return Math.sqrt(Math.pow(currentX - neighborX, 2) + Math.pow(currentY - neighborY, 2));
    }

    private List<Node> getNeighbors(Node currentNode) {
        // TODO
        // Check if on edge of map - breaks if we're on the pixel right next
        // to the edge.
        List<Node> neighborNodeList = new ArrayList<Node>();

        Coordinate currentCoordinate = currentNode.getPosition();
        int currentX = currentCoordinate.getX();
        int currentY = currentCoordinate.getY();

        Coordinate coordinateLeft = new Coordinate(currentX - 1, currentY);
        Coordinate coordinateUpLeft = new Coordinate(currentX - 1, currentY + 1);
        Coordinate coordinateUp = new Coordinate(currentX, currentY + 1);
        Coordinate coordinateUpRight = new Coordinate(currentX + 1, currentY + 1);
        Coordinate coordinateRight = new Coordinate(currentX + 1, currentY);
        Coordinate coordinateDownRight = new Coordinate(currentX + 1, currentY - 1);
        Coordinate coordinateDown = new Coordinate(currentX, currentY - 1);
        Coordinate coordinateDownLeft = new Coordinate(currentX - 1, currentY - 1);

        Node nodeLeft = new Node(coordinateLeft);
        Node nodeUpLeft = new Node(coordinateUpLeft);
        Node nodeUp = new Node(coordinateUp);
        Node nodeUpRight = new Node(coordinateUpRight);
        Node nodeRight = new Node(coordinateRight);
        Node nodeDownRight = new Node(coordinateDownRight);
        Node nodeDown = new Node(coordinateDown);
        Node nodeDownLeft = new Node(coordinateDownLeft);

        neighborNodeList.add(nodeLeft);
        neighborNodeList.add(nodeUpLeft);
        neighborNodeList.add(nodeUp);
        neighborNodeList.add(nodeUpRight);
        neighborNodeList.add(nodeRight);
        neighborNodeList.add(nodeDownRight);
        neighborNodeList.add(nodeDown);
        neighborNodeList.add(nodeDownLeft);

        return neighborNodeList;
    }

    private Node getLowestFScore(List<Node> list) {
        Node lowestNode = list.get(0);
        for (Node n : list) {
            if (n.getFScore() < lowestNode.getFScore()) {
                lowestNode = n;
            }
        }
        return lowestNode;
    }

    private double estimateHeuristic(Node currentNode, Node goalNode) {
        // Manhattan estimate. Using only horizontal/vertical distances as
        // opposed to "how to crow flies".

        Coordinate currentPosition = currentNode.getPosition();
        double currentXPos = currentPosition.getX();
        double currentYPos = currentPosition.getY();

        Coordinate goalPosition = goalNode.getPosition();
        double goalXPos = goalPosition.getX();
        double goalYPos = goalPosition.getY();

        return Math.abs(currentXPos - goalXPos) + Math.abs(currentYPos - goalYPos);
    }

    private boolean currentIsGoal(Node currentNode, Node goalNode) {
        double currentX = currentNode.getPosition().getX();
        double currentY = currentNode.getPosition().getY();
        double goalX = goalNode.getPosition().getX();
        double goalY = goalNode.getPosition().getY();

        if ((currentX == goalX) && (currentY == goalY)) {return true;}
        else {return false;}
    }

}
