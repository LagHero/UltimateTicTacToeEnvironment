package player;

import environment.MetaBoard;
import environment.MetaMove;
import environment.Move;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Aaron Gehri on 9/16/2017.
 */
public class HumanPlayer implements IPlayer {

    private final String name;
    public HumanPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your player name: ");
        name = scanner.next();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MetaMove makeMove(MetaBoard metaBoard, List<MetaMove> availableMoves) {
        while (true) {
            int smallBoardRow = -1;
            int smallBoardColumn = -1;
            int moveRow = -1;
            int moveColumn = -1;
            while (true) {
                String smallBoardRowColumnString = null;
                String moveRowColumnString = null;
                try {
                    System.out.println(String.format("availableMoves: %s", availableMoves.toString()));
                    System.out.println(metaBoard.toString());
                    Scanner scanner = new Scanner(System.in);
                    if (canPlayInAnySmallBoard(availableMoves)) {
                        System.out.print("Please enter the small board to play on (row,column): ");
                        smallBoardRowColumnString = scanner.next();
                        String[] smallBoardRowColumn = smallBoardRowColumnString.split(",");
                        smallBoardRow = Integer.parseInt(smallBoardRowColumn[0].trim());
                        smallBoardColumn = Integer.parseInt(smallBoardRowColumn[1].trim());
                    } else {
                        System.out.print("Have to play in small board: ");
                        System.out.println(availableMoves.get(0).getMetaBoardMove().toString());
                        smallBoardRow = availableMoves.get(0).getMetaBoardMove().getRow();
                        smallBoardColumn = availableMoves.get(0).getMetaBoardMove().getCol();
                    }
                    System.out.print("Please enter your move  (row,column): ");
                    moveRowColumnString = scanner.next();
                    String[] moveRowColumn = moveRowColumnString.split(",");
                    moveRow = Integer.parseInt(moveRowColumn[0].trim());
                    moveColumn = Integer.parseInt(moveRowColumn[1].trim());
                } catch (Exception e) {
                    // Re-enter values
                }
                if (smallBoardRow < 0 || smallBoardRow > 2 || smallBoardColumn < 0 || smallBoardColumn > 2
                        || moveRow < 0 || moveRow > 2 || moveColumn < 0 || moveColumn > 2) {
                    System.out.println(String.format("Unable to parse '%s' and '%s' into a row (0-2) and column (0-2)",
                            String.valueOf(smallBoardRowColumnString), String.valueOf(moveRowColumnString)));
                } else {
                    break;
                }
            }
            Move metaBoardMove = new Move(smallBoardRow, smallBoardColumn);
            Move smallBoardMove = new Move(moveRow, moveColumn);
            MetaMove metaMove = new MetaMove(metaBoardMove, smallBoardMove);
            if (availableMoves.contains(metaMove)) {
                return metaMove;
            } else {
                System.out.println(String.format("This move is not available: %s", String.valueOf(metaMove)));
            }
        }
    }

    private boolean canPlayInAnySmallBoard(List<MetaMove> availableMoves) {
        Move metaBoardMove = null;
        for(MetaMove metaMove : availableMoves){
            if(metaBoardMove == null){
                metaBoardMove = metaMove.getMetaBoardMove();
            }else if(!metaBoardMove.equals(metaMove.getMetaBoardMove())){
                // Not equal, so we can play in any small board
                return true;
            }else{
                // Still equal so far, check the rest of the list.
            }
        }
        return false;
    }

    @Override
    public void setGameResult(boolean isWinner) {
        // Do Nothing
    }
}
