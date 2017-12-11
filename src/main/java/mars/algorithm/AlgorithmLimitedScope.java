package mars.algorithm;

import mars.coordinate.Coordinate;
import mars.rover.MarsRover;
import mars.out.TerminalOutput;
import mars.map.TerrainMap;

import java.util.ArrayList;

/**
 * Class which implements the path-finding algorithm with a limited field of view.
 */
public class AlgorithmLimitedScope extends Algorithm {

    ArrayList<Coordinate> path = new ArrayList<Coordinate>();

    /*
     * Default constructor for an AlgorithmUnlimitedScopeNonRecursive.
     *
     * @param map The terrain map
     * @param rover The rover
     */
    public AlgorithmLimitedScope(TerrainMap m, MarsRover r) {
        map = m;
        rover = r;
    }

    public void findPath() {
        //find a path

        //---A "blank" algorithm-----------------------------------
        path.add(rover.getStartPosition());
        path.add(rover.getEndPosition());

        output = new TerminalOutput(path);
        //---------------------------------------------------------
    }
}
