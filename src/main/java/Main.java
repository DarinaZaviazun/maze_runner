import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            String fileName = args[0];
            File image = new File(fileName);
            try {
                BufferedImage mazeImg = ImageIO.read(image);
                Maze maze = new Maze(mazeImg);
                Solution solution = new Solution(maze.getMazeMatrix());
                Map<Cell, Integer> cellValues = new HashMap<>();
                solution.gradingCoordinats(solution.getStart(), 0, maze, cellValues);
                Map<Cell, Direction> solutionWay = new HashMap<>();
                solution.restoreSolutionWay(solution.getFinish(), cellValues, solutionWay, maze);
                solution.drawColorsAndSave(maze, solutionWay, fileName);
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage() + " :: " + Arrays.toString(e.getStackTrace()));
            }
        } else {
            System.out.println("Please enter image name, e.g. maze1.png");
        }
    }
}
