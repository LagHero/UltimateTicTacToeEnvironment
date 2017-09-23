package environment;

import java.util.Arrays;

/**
 * Created by Aaron Gehri on 9/16/2017.
 */
public class Board {
    /** Owner[Rows][Columns] */
    private final Owner[][] boardOwners = new Owner[3][3];

    Board(){
        super();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                setOwner(row, col, Owner.Empty);
            }
        }
    }

    void setOwner(int row, int col, Owner owner){
        boardOwners[row][col] = owner;
    }

    void setOwner(Move move, Owner owner){
        setOwner(move.getRow(), move.getCol(), owner);
    }

    public Owner getOwner(int row, int col){
        return boardOwners[row][col];
    }

    public Owner getOwner(Move move){
        return getOwner(move.getRow(), move.getCol());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return Arrays.deepEquals(boardOwners, board.boardOwners);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(boardOwners);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            if(row > 0){
                sb.append("-+-+-");
                sb.append(String.format("%n"));
            }
            for (int col = 0; col < 3; col++) {
                if(col > 0){
                    sb.append("|");
                }
                sb.append(getOwner(row, col));
            }
            sb.append(String.format("%n"));
        }
        return sb.toString();
    }
}
