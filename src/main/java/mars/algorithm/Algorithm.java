package mars.algorithm;

import mars.rover.MarsRover;
import mars.out.Output;
import mars.map.TerrainMap;
import mars.coordinate.*;
import java.util.*;


/**
 * Abstract class from which all path-finding algorithms inherit.
 */
public abstract class Algorithm {

    TerrainMap map;
    MarsRover rover;
    Output output;
    List<Coordinate> fullPath = new ArrayList<Coordinate>();
    public abstract void findPath();

    public class Node {
        private Coordinate position;
        private Node parent;
        private double gScore = Double.POSITIVE_INFINITY;
        private double fScore = Double.POSITIVE_INFINITY;

        public Node(Coordinate _position) {
            position = _position;
        }
        public Coordinate getPosition() {
            return position;
        }
        public void setPosition(Coordinate _position) {
            position = _position;
        }
        public Node getParent() {
            return parent;
        }
        public void setParent(Node _parent) {
            parent = _parent;
        }
        public double getFScore() {
            return fScore;
        }
        public void setFScore(double _fScore) {
            fScore = _fScore;
        }
        public double getGScore() {
            return gScore;
        }
        public void setGScore(double _gScore) {
            gScore = _gScore;
        }
    }

}
