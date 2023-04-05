public class Cell {
    // for left upper corner
    private int x1;
    private int y1;
    // for right bottom corner
    private int x2;
    private int y2;

    public Cell(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public Cell move(Direction direction) {
        return new Cell(this.x1 + direction.getX(), this.y1 + direction.getY(),
                this.x2 + direction.getX(), this.y2 + direction.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell that = (Cell) o;
        return x1 == that.x1
                && y1 == that.y1
                && x2 == that.x2
                && y2 == that.y2;
    }

    @Override
    public int hashCode() {
        int result = x1;
        result = 31 * result + y1;
        result = 31 * result + x2;
        result = 31 * result + y2;
        return result;
    }
}
