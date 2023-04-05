import java.util.HashMap;
import java.util.Map;

public class Arrow {
    private final static int COLOR_RED = 0xFF0000;
    private final static int COLOR_WHITE = 0xFFFFFF;

    private final static Map<Direction, int[][]> arrows = new HashMap<>();

    private Arrow() {}

    static int[][] getMatrix(Direction direction) throws Exception {
        int[][] result = arrows.get(direction);
        if (result == null) {
            result = createMatrix(direction);
            arrows.put(direction, result);
        }
        return result;
    }

    private static int[][] getBasicArrow(final int cellSize) {
        final int[][] arrowInColor = new int[cellSize][cellSize];
        int center = cellSize / 2;

        for (int i = 2; i < cellSize - 2; i++) {
            arrowInColor[i][center] = COLOR_RED;
        }
        for (int i = 3; i < center; i++) {
            arrowInColor[i][center - (i - 2)] = COLOR_RED;
            arrowInColor[i][center - (i - 3)] = COLOR_RED;
            arrowInColor[i][center + (i - 2)] = COLOR_RED;
            arrowInColor[i][center + (i - 3)] = COLOR_RED;
        }

        for (int h = 0; h < cellSize; h++) {
            for (int w = 0; w < cellSize; w++) {
                if (arrowInColor[h][w] != COLOR_RED) {
                    arrowInColor[h][w] = COLOR_WHITE;
                }
            }
        }
        return arrowInColor;
    }
    private static int[][] createMatrix(Direction direction) throws Exception {
        int cellSize = Maze.CELL_SIZE;
        switch (direction) {
            case NORTH:
                return getBasicArrow(cellSize);
            case SOUTH:
                int[][] basicArrowS = getMatrix(Direction.NORTH);
                int[][] resultS = new int[cellSize][cellSize];
                for (int h = 0; h < cellSize; h++) {
                    for (int w = 0; w < cellSize; w++) {
                        resultS[h][w] = basicArrowS[cellSize - h - 1][w];
                    }
                }
                return resultS;
            case WEST:
                int[][] basicArrowW = getMatrix(Direction.NORTH);
                int[][] resultW = new int[cellSize][cellSize];
                for (int h = 0; h < cellSize; h++) {
                    for (int w = 0; w < cellSize; w++) {
                        resultW[w][h] = basicArrowW[h][cellSize - w - 1];
                    }
                }
                return resultW;
            case EAST:
                int[][] basicArrowE = getMatrix(Direction.NORTH);
                int[][] resultE = new int[cellSize][cellSize];
                for (int h = 0; h < cellSize; h++) {
                    for (int w = 0; w < cellSize; w++) {
                        resultE[w][h] = basicArrowE[cellSize - h - 1][w];
                    }
                }
                return resultE;
            default:
                throw new Exception("No such type of arrow!");
        }
    }
}
