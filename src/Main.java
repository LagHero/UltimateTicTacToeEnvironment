import environment.GameService;
import player.HumanPlayer;
import player.IPlayer;
import player.RandomPlayer;
import player.RobotPlayer;

/**
 * Created by Aaron Gehri on 9/16/2017.
 */
public class Main {

    private final static int GAMES_TO_PLAY = 100;

    public static void main(String[] args){
        IPlayer player1 = new HumanPlayer();
//        IPlayer player1 = new RobotPlayer();
        IPlayer player2 = new RandomPlayer();

        for(int i = 0; i < GAMES_TO_PLAY; i++) {
            GameService gameService = new GameService(player1, player2);
            IPlayer winner = gameService.start(true);
            if(winner != null) {
                System.out.println(winner.getName());
            }else{
                System.out.println("No Winner!");
            }
        }
    }
}
