package mars.out;

import mars.coordinate.Coordinate;
import java.util.*;

import java.util.ArrayList;

/**
 * Class which writes a discovered path to the terminal.
 */
public class TerminalOutput extends Output {

    /*
     * Constructor for TerminalOutput.
     * It immediately prints the output.
     */
    public TerminalOutput(List<Coordinate> out) {
        resultList = out;
        writeToOutput();
    }

    public void writeToOutput() {
        System.out.println("\nOutput path: ");
        System.out.println("------------");
        int num = 1;
        for (int i = resultList.size(); i > 0; i--) {
            int x = resultList.get(i-1).getX();
            int y = resultList.get(i-1).getY();
            System.out.println(num + ". [" + x + ", " + y + "]");
            num++;
        }
        System.out.println("------------");
    }
}
