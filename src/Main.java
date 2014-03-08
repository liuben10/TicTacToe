import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Benjamin Liu
 * Simple TicTacToe with text Ui. This is the entry point into the program.  There is a Board.java
 * which keeps track of the board, and support for 1 human and 1 computer player. The computer runs a random
 * AI.
 *
 */


public class Main {
	public static void main(String...args) {
		/* Main Entry point to Program. 
		 * Contains the entire Driver loop that switches between players etc.
		 * Reads instructions from command line.
		 */
		Board game = new Board();
		/*
		 * (a) Initial board display is done in the beginning.
		 */
		System.out.println("=================");
		game.printBoard();
		System.out.println("=================");
		System.out.println("Simple Text UI TicTacToe by Benjamin Liu.\n");
		System.out.println("Begin by entering the character representing the piece and hitting enter.");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Player humanPlayer = pieceSelection(br, game);
			/*
			 * pieceSelection prompts the human player for a piece. The computer always selects the piece opposite
			 * from the human player by using the compPlayer method.
			 */
			AIPlayer computer = compPlayer(humanPlayer, game);
			game.printBoard();
			String input;
			System.out.println("Legal Moves: " + game.legalMoves );
			System.out.println("enter 'help' to see special commands");
			System.out.println("Enter your move below");
			/*
			 * Primary driver loop that accepts user input.
			 */
			while((input=br.readLine()) != null){
				/*
				 * 'end' and 'b' are special commands. 'end' ends the game, and 'b' prints the board.
				 */
				if (input.equals("end")) {
					break;
				} else if (input.equals("b")) {
					movePrompt(game);
					continue;
				} else if (input.equals("help")) {
					helpPrompt(game);
					continue;
				}
				if (!checkValidMove(input, game)) {
					/*
					 * Checks for valid moves. Moves must be integers between [1-9].
					 */
					System.out.println("Invalid Move! must be legal Move ");
					movePrompt(game);
					continue;
				}  else {
					humanPlayer.placePiece(Integer.parseInt(input));
					if (game.gameEnd()) {
						if(game.checkTies()) {
							printTieGame(game);
						} else {
						/*
						 * If the piece placed by the human results in victory, print victory and exit driver loop.
						 */
							printVictory(humanPlayer, game);
						}
						break;
					}
					/*
					 * move is a random move gotten from the list of legal moves kept by the game state.
					 * (c) computing the computer's move is done here. (d) is done once the computer places the move, it prints the board indicating
					 * the move that was played.
					 */
					int move = computer.getMove(game.legalMoves.size());
					System.out.println("Computer move: " + move);
					computer.placePiece(move);
					if (game.gameEnd()) {
						if(game.checkTies()) {
							printTieGame(game);
						} else {
						/*
						 * If the piece placed by the computer results in victory, print victory and exit driver loop with a win for the computer.
						 */
							printVictory(computer, game);
						}
						break;
					}
				}
				/*
				 * prompts the player for the next loop. Also prints the board, showing the computer's move.
				 */
				movePrompt(game);
			}
			System.out.println("GAME OVER");
		} catch (IOException io) {
			System.err.println("Invalid input-Catastrophic failure.");
		}
	}
	
	public static void movePrompt(Board game) {
		/*
		 * Simply prompts the user for a move.
		 */
		game.printBoard();
		System.out.println("Legal Moves: " + game.legalMoves );
		System.out.println("Enter your move below");
	}
	
	public static void helpPrompt(Board game) {
		/*
		 * Simply prints out the help prompt
		 */
		System.out.println("Place a piece by typing an integer 1-9 and hitting enter.");
		System.out.println("end : end will end the game immediately.  Must rerun program to start.");
		System.out.println("b : b will print the game board");
		System.out.println("help : help will reprint this dialogue");
		game.printBoard();
		System.out.println("Legal Moves: " + game.legalMoves );
		System.out.println("choose your move");
		
	}
	
	public static void printTieGame(Board game) {
		System.out.println("Tie Game!");
		game.printBoard();
		System.out.println("Final Position");
	}
	
	public static void printVictory(Player player, Board game) {
		/*
		 * Prints the victory message for the respective player.
		 */
		System.out.println("Game has ended");
		System.out.println(player.piece + " wins!");
		System.out.println("Final Position:");
		game.printBoard();
	}
	public static AIPlayer compPlayer(Player humanPlayer, Board game) {
		/*
		 * Selects the piece opposite from the humanPlayer. The game is the current game state, which is required for initialization.
		 */
		AIPlayer computer;
		if (humanPlayer.piece.equals("x")) {
			computer = new AIPlayer(game, "o");
		} else {
			computer = new AIPlayer(game, "x");
		}
		return computer;
	}
	
	public static boolean checkValidMove(String inmove, Board game) {
		/*
		 * Checks if the move is a number between 1-9, and also checks if the move is contained in the list of legal moves still available.
		 */
		return inmove.matches("[1-9]") && game.legalMove(Integer.parseInt(inmove));
	}
	

	
	public static Player pieceSelection(BufferedReader br, Board game) throws IOException {
		/*
		 * Prompts the user to select a specific piece, either x or o.  Also initializes the player with the game so the
		 * player can play that specific game.
		 */
		String inputPiece;
		System.out.println("Enter your piece selection below {x or o}:");
		inputPiece = br.readLine();
		while (!checkValidPiece(inputPiece)) {
			System.out.println("Invalid piece selected, must be 'x' or 'o'");
			System.out.println("Enter your piece selection below {x or o}:");
			inputPiece = br.readLine();
		}
		Player humanPlayer = new Player(game, inputPiece);
		return humanPlayer;
	}
	
	public static boolean checkValidPiece(String piece) {
		/*
		 * Makes sure that a piece the player selects is either an x or an o
		 */
		return (piece.equals("x") || piece.equals("o"));
	}
}
