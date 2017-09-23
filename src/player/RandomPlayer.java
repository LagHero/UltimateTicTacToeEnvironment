package player;

import environment.Board;
import environment.MetaBoard;
import environment.MetaMove;
import environment.Move;

import java.util.List;
import java.util.Random;

/**
 * Created by Aaron Gehri on 9/16/2017.
 */
public class RandomPlayer implements IPlayer {

    private final String name;
    public RandomPlayer() {
        Random r = new Random();
        int low = 1;
        int high = 100;
        int randomNumber = r.nextInt(high-low) + low;
        name =  String.format("Random Player %d", randomNumber);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MetaMove makeMove(MetaBoard metaBoard, List<MetaMove> availableMoves){
        Random r = new Random();
        int randomNumber = r.nextInt(availableMoves.size());
        return availableMoves.get(randomNumber);
    }

    @Override
    public void setGameResult(boolean isWinner) {
        // Do Nothing
    }

}
