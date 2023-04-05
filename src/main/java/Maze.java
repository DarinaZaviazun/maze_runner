import java.awt.image.BufferedImage;

public class Maze {

    public static final int CELL_SIZE = 14;
    public static final int BORDER_SIZE = 2;
    private final int COLOR_WHITE = 0xFFFFFF;

    private final boolean[][] mazeMatrix;
    private final int[][] mazeMatrixColors;
    private final int width;
    private final int height;

    public Maze(BufferedImage mazeImg) {
        this.height = mazeImg.getHeight();
        this.width = mazeImg.getWidth();
        boolean[][] mazeMatrix = new boolean[this.height][this.width];
        int[][] mazeMatrixColors = new int[this.height][this.width];
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                int rgbColor = mazeImg.getRGB(h, w);
                mazeMatrixColors[w][h] = rgbColor;
                mazeMatrix[w][h] = (rgbColor & COLOR_WHITE) != COLOR_WHITE;
            }
        }
        this.mazeMatrix = mazeMatrix;
        this.mazeMatrixColors = mazeMatrixColors;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean[][] getMazeMatrix() {
        return mazeMatrix;
    }

    public int[][] getMazeMatrixColors() {
        return mazeMatrixColors;
    }
}
