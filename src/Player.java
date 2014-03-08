/**
 * 
 * @author Benjamin Liu
 *  Player class allows players to interact with the game. Players are initialized with the game board, and the piece they want to use to play.
 *  The piece is either 'x' or 'o'.
 */
public class Player {
	Board game;
	String piece;
	Player(Board game, String piece) {
		this.game = game;
		this.piece = piece;
	}
	
	void placePiece(int position) {
		/*
		 * Places a piece.  Upon piece placement, the square is removed from the list of legal squares.
		 */
		this.game.gameBoard[position-1] = this.piece;
		this.game.legalMoves.remove(new Integer(position));
	}

}
