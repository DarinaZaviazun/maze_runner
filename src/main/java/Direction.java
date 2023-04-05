public enum Direction {
    NORTH(0, -(Maze.CELL_SIZE + Maze.BORDER_SIZE)),
    SOUTH(0, (Maze.CELL_SIZE + Maze.BORDER_SIZE)),
    EAST((Maze.CELL_SIZE + Maze.BORDER_SIZE), 0),
    WEST(-(Maze.CELL_SIZE + Maze.BORDER_SIZE), 0);

    private final int x;
    private final int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getOpposite() throws Exception {
        switch (this) {
            case NORTH:
                return SOUTH;
            case EAST:
                return WEST;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            default: throw new Exception("No such direction!");
        }
    }
}
