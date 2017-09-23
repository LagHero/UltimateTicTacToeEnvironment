package environment;

import java.util.Arrays;

/**
 * Created by Aaron Gehri on 9/16/2017.
 */
public class MetaBoard {
    /** Board[Rows][Columns] */
    private final Board[][] smallBoards = new Board[3][3];
    /** Owner[Rows][Columns] */
    private final Owner[][] boardOwners = new Owner[3][3];

    MetaBoard(){
        super();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                setOwner(row, col, Owner.Empty);
                setSmallBoard(row, col);
            }
        }
    }

    void setOwner(int row, int col, Owner owner){
        boardOwners[row][col] = owner;
    }

    void setSmallBoard(int row, int col){
        smallBoards[row][col] = new Board();
    }

    void setOwner(Move move, Owner owner){
        setOwner(move.getRow(), move.getCol(), owner);
    }

    void setOwner(MetaMove metaMove, Owner owner){
        Board smallBoard = getSmallBoard(metaMove.getMetaBoardMove().getRow(), metaMove.getMetaBoardMove().getCol());
        smallBoard.setOwner(metaMove.getSmallBoardMove().getRow(), metaMove.getSmallBoardMove().getCol(), owner);
    }

    public Owner getOwner(int row, int col){
        return boardOwners[row][col];
    }

    public Board getSmallBoard(int row, int col){
        return smallBoards[row][col];
    }

    public Owner getOwner(Move move){
        return getOwner(move.getRow(), move.getCol());
    }

    public Board getSmallBoard(Move move){
        return getSmallBoard(move.getRow(), move.getCol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaBoard board = (MetaBoard) o;

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
            String[] smallBoard1 = getSmallBoard(row, 0).toString().split(String.format("%n"));
            String[] smallBoard2 = getSmallBoard(row, 1).toString().split(String.format("%n"));
            String[] smallBoard3 = getSmallBoard(row, 2).toString().split(String.format("%n"));
            for (int boardRow = 0; boardRow < smallBoard1.length; boardRow++){
                sb.append(smallBoard1[boardRow]);
                sb.append(" | ");
                sb.append(smallBoard2[boardRow]);
                sb.append(" | ");
                sb.append(smallBoard3[boardRow]);
                sb.append(String.format("%n"));
            }
            if(row < 2){
                sb.append("------+-------+------");
                sb.append(String.format("%n"));
            }
        }
        return sb.toString();
    }

}
