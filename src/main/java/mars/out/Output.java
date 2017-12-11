package mars.out;

import mars.coordinate.Coordinate;

import java.util.*;

/**
 * Abstract class from which all output classes inherit.
 *
 */
public abstract class Output {

    List<Coordinate> resultList;

    public abstract void writeToOutput();
}
