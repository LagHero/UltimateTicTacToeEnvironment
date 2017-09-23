package environment;

import player.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron Gehri on 9/15/2017.
 */
public class GameService {

    private final IPlayer player1;
    private final IPlayer player2;
    private final MetaBoard metaBoard;
    private MetaMove previousMove = null;

    public GameService(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.metaBoard = new MetaBoard();

//        System.out.println(String.format("Tic Tac Toe setup for player 1 (%s) and player 2 (%s)",
//                player1.getName(), player2.getName()));
    }

    public IPlayer start(boolean debug){
        if(debug){System.out.println("Starting Game!");}

        IPlayer currentTurn = player1;
        IPlayer winner = null;
        int moveCount = 0;
        while(!isGameOver(metaBoard)){
            moveCount++;
            List<MetaMove> availableMoves = getAvailableMoves(metaBoard);
            MetaMove metaMove = currentTurn.makeMove(metaBoard, new ArrayList<MetaMove>(availableMoves));
            previousMove = metaMove;
            if(!availableMoves.contains(metaMove)){
                if(debug){System.out.println(String.format("Invalid Move: %s", metaMove.toString()));}

                winner = getOtherPayer(currentTurn);
                break;
            }
            if(debug){System.out.println(String.format("%s moved(%d): %s",currentTurn.getName(), moveCount, metaMove.toString()));}
            metaBoard.setOwner(metaMove, getOwner(currentTurn));
            if(debug){System.out.println(String.format("Move %d:%n%s%n",moveCount, metaBoard.toString()));}
            // Check if the current player won the small board.
            if(getWinner(metaBoard.getSmallBoard(metaMove.getMetaBoardMove())) != null){
                metaBoard.setOwner(metaMove.getMetaBoardMove(), getOwner(currentTurn));
            }
            // Check for a game winner.
            winner = getWinner(metaBoard);
            currentTurn = getOtherPayer(currentTurn);
        }
        // Game Over
        if(debug){System.out.println("Game Over!");}
        if(winner == null){
            if(debug){System.out.println("Cat game!");}
            player1.setGameResult(false);
            player2.setGameResult(false);
        }else {
            if(debug){System.out.println(String.format("The winner is %s!%n", winner.getName()));}
            winner.setGameResult(true);
            getOtherPayer(winner).setGameResult(false);
        }
        return winner;
    }

    private Owner getOwner(IPlayer currentTurn) {
        if(currentTurn.equals(player1)){
            return Owner.Player1;
        }else if(currentTurn.equals(player2)){
            return Owner.Player2;
        }
        return Owner.Empty;
    }

    private IPlayer getPlayer(Owner owner) {
        if(Owner.Player1.equals(owner)){
            return player1;
        }else if(Owner.Player2.equals(owner)){
            return player2;
        }
        return null;
    }

    private IPlayer getOtherPayer(IPlayer currentTurn) {
        if(currentTurn.equals(player1)){
            return player2;
        }
        return player1;
    }

    private boolean isGameOver(MetaBoard board) {
        return getWinner(board) != null || getAvailableMoves(board).isEmpty();
    }

    private IPlayer getWinner(MetaBoard metaBoard) {
        for(Move[] winningMove : winningMoves){
            Owner[] owners = new Owner[3];
            owners[0] = metaBoard.getOwner(winningMove[0]);
            owners[1] = metaBoard.getOwner(winningMove[1]);
            owners[2] = metaBoard.getOwner(winningMove[2]);
            if(!Owner.Empty.equals(owners[0])
                    && owners[0].equals(owners[1])
                    && owners[1].equals(owners[2])){
                return getPlayer(owners[0]);
            }
        }
        return null;
    }

    private IPlayer getWinner(Board board) {
        for (Move[] winningMove : winningMoves) {
            Owner[] owners = new Owner[3];
            owners[0] = board.getOwner(winningMove[0]);
            owners[1] = board.getOwner(winningMove[1]);
            owners[2] = board.getOwner(winningMove[2]);
            if (!Owner.Empty.equals(owners[0])
                    && owners[0].equals(owners[1])
                    && owners[1].equals(owners[2])) {
                return getPlayer(owners[0]);
            }
        }
        return null;
    }

    private List<MetaMove> getAvailableMoves(MetaBoard metaBoard) {
        List<MetaMove> availableMoves = new ArrayList<>();
        if(previousMove == null) {
            return getAllAvailableMoves(metaBoard);
        }else{
            Move previousSmallBoardMove = previousMove.getSmallBoardMove();
            Board availableSmallBoard = metaBoard.getSmallBoard(previousSmallBoardMove);
            if(getWinner(availableSmallBoard) == null) {
                for (Move smallBoardMove : getAvailableMoves(availableSmallBoard)) {
                    availableMoves.add(new MetaMove(previousSmallBoardMove, smallBoardMove));
                }
            }else{
                // The small board to play in is closed, get all moves.
                return getAllAvailableMoves(metaBoard);
            }
        }
        return availableMoves;
    }

    private List<MetaMove> getAllAvailableMoves(MetaBoard metaBoard) {
        List<MetaMove> availableMoves = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Move metaBoardMove = new Move(row, col);
                Board smallBoard = metaBoard.getSmallBoard(metaBoardMove);
                if (getWinner(smallBoard) == null) {
                    for (Move smallBoardMove : getAvailableMoves(smallBoard)) {
                        availableMoves.add(new MetaMove(metaBoardMove, smallBoardMove));
                    }
                } else {
                    // This small board has a winner, no available moves.
                }
            }
        }
        return availableMoves;
    }

    private List<Move> getAvailableMoves(Board board) {
        List<Move> availableMoves = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Move move = new Move(row, col);
                if(Owner.Empty.equals(board.getOwner(move))){
                    availableMoves.add(move);
                }
            }
        }
        return availableMoves;
    }

    private static final List<Move[]> winningMoves = new ArrayList<>();
    static {
        Move[] set = new Move[3];
        set[0] = new Move(0, 0);
        set[1] = new Move(0, 1);
        set[2] = new Move(0, 2);
        winningMoves.add(set);
        set = new Move[3];
        set[0] = new Move(1, 0);
        set[1] = new Move(1, 1);
        set[2] = new Move(1, 2);
        winningMoves.add(set);
        set = new Move[3];
        set[0] = new Move(2, 0);
        set[1] = new Move(2, 1);
        set[2] = new Move(2, 2);
        winningMoves.add(set);
        set = new Move[3];
        set[0] = new Move(0, 0);
        set[1] = new Move(1, 0);
        set[2] = new Move(2, 0);
        winningMoves.add(set);
        set = new Move[3];
        set[0] = new Move(0, 1);
        set[1] = new Move(1, 1);
        set[2] = new Move(2, 1);
        winningMoves.add(set);
        set = new Move[3];
        set[0] = new Move(0, 2);
        set[1] = new Move(1, 2);
        set[2] = new Move(2, 2);
        winningMoves.add(set);
        set = new Move[3];
        set[0] = new Move(0, 0);
        set[1] = new Move(1, 1);
        set[2] = new Move(2, 2);
        winningMoves.add(set);
        set = new Move[3];
        set[0] = new Move(0, 2);
        set[1] = new Move(1, 1);
        set[2] = new Move(2, 0);
        winningMoves.add(set);
    }
}
