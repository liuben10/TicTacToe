import java.util.ArrayList;

/**
 * 
 * @author Benjamin Liu
 * Board.java.  Class that maintains the current game state. Also
 * contains the methods for checking for legal moves, and whether or not a
 * terminal state has been achieved.  The Board always keeps track of all legal moves at a certain state.
 *
 */

public class Board {
	/*
	 * 1-D array representing the game board
	 */
	String[] gameBoard;
	int height = 3;
	int width = 3;
	ArrayList<Integer> legalMoves = new ArrayList<Integer>();
	
	public Board() {
		this.gameBoard = new String[this.height * this.width];
		for(int i = 1; i <= this.height * this.width; i++) {
			legalMoves.add(i);
		}
	}
	
	
	public boolean legalMove(int move) {
		/*
		 * A move is a legal move if it is contained in the list of legal moves.
		 */
		return this.legalMoves.contains(new Integer(move));
	}
	
	public boolean gameEnd() {
		/*
		 * Checks if there is a victory situation in some column, row, or diagonal, or if there i a tie.
		 */
		return (checkVerticals() || checkHorizontals() || checkDiagonals() || checkTies());
	}
	
	public boolean checkTies() {
		/*
		 * Ties occur if there are no winning conditions and no empty spots to play anymore.
		 */
		return (allOccupied() && !checkVerticals() && !checkHorizontals() && !checkDiagonals());
	}
	
	public boolean allOccupied() {
		/*
		 * Checks if there are any empty steps left.
		 */
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if ((this.gameBoard[i * this.height + j]) == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkDiagonals() {
		/*
		 * Checks if any diagonals will result in a victory.  In Tic Tac Toe, ther are only 2 diagonals.
		 */
		int corners[] = {0, this.width-1};
		for(int i = 0; i < 2; i++) {
			if (checkDiagonal(corners[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkDiagonal(int i) {
		/*
		 * Checks a single diagonal, either the diagonal with topmost square at 0 or topmost square at 2.
		 * All Checking algorithms, checkDiagonal, checkColumn, and checkSingleRow will check using the same transitivity principle
		 * of equality.  a == b and b == c => a == c.  Therefore, all we do a single pass checking the current vs. the previous square
		 * to determine if all elements are the same in a row/column/diagonal.
		 */
		int xcount = i;
		int ycount = 1;
		if (this.gameBoard[i] == null) {
			return false;
		}
		String initialpiece = this.gameBoard[i];
		if (i == 2) {
			xcount -= 1;
			while(ycount < this.height && 0 <= xcount) {
				String square = this.gameBoard[ycount * this.width + xcount];
				if (square == null) {
					return false;
				}
				if (square.equals(initialpiece)) {
					initialpiece = square;
					xcount -= 1;
					ycount += 1;
				} else {
					return false;
				}
			}
		} else {
			xcount += 1;
			while(ycount < this.height && xcount < this.width) {
				String square = this.gameBoard[ycount * this.width + xcount];
				if ( square == null) {
					return false;
				}
				if (square.equals(initialpiece)) {
					initialpiece = this.gameBoard[ycount * this.width + xcount];
					xcount += 1;
					ycount += 1;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkHorizontals() {
		/*
		 * iterates through all rows and checks if all elements are the same for a row.
		 */
		for (int j = 0; j <= ((this.height * this.width - 1) - (this.width - 1)) ; j += (this.width)) {
			if (checkSingleRow(j)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkSingleRow(int j) {
		/*
		 * Checks a single row to see if all squares are the same piece in a row.
		 * If null, it automatically fails because a piece can still be placed there.
		 */
		if (this.gameBoard[j] == null) {
			return false;
		}
		String initialPiece = this.gameBoard[j];
		for( int k = j+1; k < this.width + j; k++) {
			String square = this.gameBoard[k];
			if (square == null) {
				return false;
			}
			if (square.equals(initialPiece)) {
				initialPiece = square;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkVerticals() {
		/*
		 * Iterates through all columns and checks if
		 * a single column contains all the same pieces.
		 */
		for(int i = 0; i < this.width; i++){
			if (checkSingleColumn(i)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkSingleColumn(int i) {
		/*
		 * Checks for a single column to see whether or not all the squares are the same.
		 * If null, it automatically fails because a piece can still be placed there.
		 */
		if (this.gameBoard[i] == null) {
			return false;
		}
		String initialPiece = this.gameBoard[i];
		for (int k = 1; k < this.height; k++) {
			String square = this.gameBoard[k * this.width + i];
			if (square == null) {
				return false;
			}
			if (square.equals(initialPiece)) {
				initialPiece = square;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public void printBoard() {
		/* 
		 * Prints the board display.
		 * the dash '-' indicates a space that a piece can be played.
		 * the 'x' indicates the 'x' piece is occupying the square and the x-player is controlling it.
		 * the 'o' indicates the 'o' piece is occupying the square and the o-player is controlling it.
		 */
		for (int i = 0; i < this.height; i++) {
			String rowString = "";
			for (int j = 0; j < this.width; j++) {
				rowString += " " + (i * this.height + j+1) + ":";
				if ((this.gameBoard[i * this.height + j]) == null) {
					rowString += "- ";
				} else {
					rowString += this.gameBoard[i * this.height + j] + " ";
				}
			}
			System.out.println(rowString);
		}
	}
	

}
