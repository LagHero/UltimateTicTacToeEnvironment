package environment;

/**
 * Created by Aaron Gehri on 9/16/2017.
 */
public class Move {
    private final int row;
    private final int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (row != move.row) return false;
        return col == move.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
