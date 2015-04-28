import java.util.logging.Logger;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		
		
		
		Board board = new Board(8,8);
		board.initBoard();
		board.fillBoardWithFixedBlackCells();
		board.searchLongestPaths();
		
		board.printBoard();
		
		
		Dictionary dict = new Dictionary();
		dict.fillDictionary();
		board.fillLongestWords();
		board.printBoard();
		board.fillRestOfWords();
		board.fillEmptyCellsWithBlackCells();
		board.printBoard();
	}
}
