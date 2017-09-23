package player;

import environment.MetaBoard;
import environment.MetaMove;

import java.util.List;

/**
 * Created by Aaron Gehri on 9/16/2017.
 */
public interface IPlayer {

    public String getName();

    public MetaMove makeMove(MetaBoard metaBoard, List<MetaMove> availableMoves);

    public void setGameResult(boolean isWinner);
}
