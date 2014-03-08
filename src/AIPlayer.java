import java.util.Random;

/**
 * 
 * A random AI player extends Player's functionality.  AI Players have mostly the same constructor and instance variables as the human player.
 * however, they also have the getMove() function which essentially acts as a random strategy.  New strategies can be added by adding more methods
 * to this class.
 */


public class AIPlayer extends Player {
	Random rand;
	
	AIPlayer(Board game, String piece) {
		super(game, piece);
		this.rand = new Random();
	}
	
	
	int getMove(int max) {
		/**
		 * Gets a random move within the list of legalMoves kept by the game.
		 */
		return this.game.legalMoves.get(this.rand.nextInt(max));
	}
	
}
