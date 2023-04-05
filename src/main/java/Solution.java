import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class Solution {

    private Cell finish;
    private Cell start;

    public Solution(boolean[][] mazeMatrix) throws Exception {
        this.start = findEntry(mazeMatrix);
        this.finish = findFinish(mazeMatrix);
    }

    public Cell getFinish() {
        return finish;
    }

    public Cell getStart() {
        return start;
    }

    void gradingCoordinats(Cell cell, int value, Maze maze, Map<Cell, Integer> cellValues) throws Exception {
        cellValues.put(cell, value);
        for (Direction direction : Direction.values()) {
            Cell step = cell.move(direction);
            if (step.getX1() > 0 && step.getY1() > 0 && step.getX2() < maze.getWidth() && step.getY2() < maze.getHeight() &&
                    isNoWall(cell, direction, maze.getMazeMatrix())) {
                if (!cellValues.containsKey(step) && !isEnd(step)) {
                    gradingCoordinats(step, value + 1, maze, cellValues);
                } else {
                    if (isEnd(step)) {
                        cellValues.put(step, value + 1);
                    }
                }
            }
        }
    }

    private Cell findEntry(boolean[][] mazeMatrix) throws Exception {
        for (int a = 0; a < mazeMatrix.length; a++) {
            if (!mazeMatrix[0][a]) {
                return new Cell(a, Maze.BORDER_SIZE, a + (Maze.CELL_SIZE - 1), (Maze.CELL_SIZE + Maze.BORDER_SIZE - 1));
            }
        }
        throw new Exception("There is no entry on the top of the maze!");
    }

    private Cell findFinish(boolean[][] mazeMatrix) throws Exception {
        for (int a = 0; a < mazeMatrix.length; a++) {
            int height = mazeMatrix.length - 1;
            if (!mazeMatrix[height][a]) {
                return new Cell(a, (height - Maze.BORDER_SIZE - Maze.CELL_SIZE + 1), (a + Maze.CELL_SIZE - 1), (height - Maze.BORDER_SIZE));
            }
        }
        throw new Exception("There is no exit on the bottom of the maze!");
    }

    private boolean isEnd(Cell step) {
        return step.equals(this.finish);
    }

    private boolean isNoWall(Cell cell, Direction direction, boolean[][] mazeMatrice) throws Exception {
        switch (direction) {
            case NORTH:
                return !mazeMatrice[cell.getY1() - 1][cell.getX1()];
            case SOUTH:
                return !mazeMatrice[cell.getY2() + 1][cell.getX2()];
            case EAST:
                return !mazeMatrice[cell.getY2()][cell.getX2() + 1];
            case WEST:
                return !mazeMatrice[cell.getY1()][cell.getX1() - 1];
            default:
                throw new Exception("Impossible situation with walls!");
        }
    }

    public void restoreSolutionWay(Cell step, Map<Cell, Integer> cellValues,
                            Map<Cell, Direction> solutionWay, Maze maze) throws Exception {
        Integer valueOfCurrentCell = cellValues.get(step);
        if (valueOfCurrentCell != null) {
            for (Direction direction : Direction.values()) {
                Cell nextStep = step.move(direction);
                if (isNoWall(nextStep, direction.getOpposite(), maze.getMazeMatrix()) && cellValues.get(nextStep) != null
                        && cellValues.get(nextStep) == valueOfCurrentCell - 1 && !solutionWay.containsKey(step)) {
                    solutionWay.put(step, direction.getOpposite());
                    restoreSolutionWay(nextStep, cellValues, solutionWay, maze);
                } else {
                    if (valueOfCurrentCell == 0 && nextStep.getY1() < 0) {
                        solutionWay.put(step, direction.getOpposite());
                    }
                }
            }
        }
    }

    public void drawColorsAndSave(Maze maze, Map<Cell, Direction> solutionWay, String fileName) throws Exception {
        int[][] mazeMatrixColors = maze.getMazeMatrixColors();
        int width = maze.getWidth();
        int height = maze.getHeight();

        for (Map.Entry<Cell, Direction> cellWithDirection : solutionWay.entrySet()) {
            int[][] arrowMatrix = Arrow.getMatrix(cellWithDirection.getValue());
            for (int w = 0; w < Maze.CELL_SIZE; w++) {
                for (int h = 0; h < Maze.CELL_SIZE; h++) {
                    mazeMatrixColors[h + cellWithDirection.getKey().getY1()][w + cellWithDirection.getKey().getX1()] = arrowMatrix[h][w];
                }
            }
        }

        BufferedImage image = new BufferedImage(width, height, 1);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image.setRGB(j, i, mazeMatrixColors[i][j]);
            }
        }
        File output = new File("solved_" + new File(fileName).getName());
        ImageIO.write(image, "png", output);
    }
}
