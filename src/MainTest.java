
import static org.junit.Assert.*;

/**
 * @author Benjamin Liu
 * Unit Tests for Tic Tac Toe. Note, some tests combine smaller tests.
 * For instance, the testCheckValid moves tests move validity for cases where
 * players have moved a piece into place or if a move is malformed. 
 * 
 */

import org.junit.Test;


public class MainTest {

	Board brd = new Board();
	Player testPlayer = new Player(brd, "x");
	AIPlayer computer = Main.compPlayer(testPlayer, brd);
	@Test
	public void testCompPlayer() {
		assertEquals(computer.piece, "o");
	}

	@Test
	public void testCheckValidMove() {
		assertFalse(Main.checkValidMove("123", brd));
		assertTrue(Main.checkValidMove("1", brd));
		testPlayer.placePiece(4);
		assertFalse(Main.checkValidMove("4", brd));
	}

	@Test
	public void testCheckValidPiece() {
		assertFalse(Main.checkValidPiece("abc"));
		assertTrue(Main.checkValidPiece("x"));
	}
	
	@Test
	public void testBoard() {
		for(int i = 0; i < brd.height; i++){
			for(int j = 0; j < brd.width; j++) {
				assertTrue(brd.gameBoard[i*brd.width + j] == null);
			}
		}
	}
	
	@Test
	public void testPlacePiece() {
		testPlayer.placePiece(2);
		computer.placePiece(4);
		assertEquals(brd.gameBoard[1],"x");
		assertEquals(brd.gameBoard[3], "o");
	}
	
	@Test
	public void testLegalMoves() {
		testPlayer.placePiece(3);
		assertFalse(brd.legalMove(3));
		assertTrue(brd.legalMove(2));
		assertFalse(brd.legalMove(10));
	}
	
	@Test
	public void testCheckDiagonals() {
		brd.gameBoard[0] = "x";
		brd.gameBoard[4] = "x";
		brd.gameBoard[8] = "x";
		assertTrue(brd.checkDiagonals());
	}
	
	@Test
	public void testCheckDiagonals2() {
		brd.gameBoard[2] = "x";
		brd.gameBoard[4] = "x";
		brd.gameBoard[6] = "x";
		assertTrue(brd.checkDiagonals());
	}
	
	@Test
	public void testVerticals() {
		brd.gameBoard[1] = "x";
		brd.gameBoard[4] = "x";
		brd.gameBoard[7] = "x";
		assertTrue(brd.checkVerticals());
	}
	
	@Test
	public void testHorizontals() {
		brd.gameBoard[0] = "x";
		brd.gameBoard[1] = "x";
		brd.gameBoard[2] = "x";
		assertTrue(brd.checkHorizontals());
	}
	
	@Test
	public void testTies() {
		brd.gameBoard[0] = "x";
		brd.gameBoard[1] = "x";
		brd.gameBoard[2] = "o";
		brd.gameBoard[3] = "o";
		brd.gameBoard[4] = "o";
		brd.gameBoard[5] = "x";
		brd.gameBoard[6] = "x";
		brd.gameBoard[7] = "o";
		brd.gameBoard[8] = "x";
		assertTrue(brd.checkTies());
		assertTrue(brd.gameEnd());
	}
}
