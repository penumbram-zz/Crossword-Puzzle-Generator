
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Board board = new Board(5,5);
		board.initBoard();
		board.fillBoardWithFixedBlackCells();
		board.searchLongestPaths();
		
		board.printBoard();
		
		
		Dictionary dict = new Dictionary();
		dict.fillDictionary();
		board.fillLongestWords();
		//dict.printDictionary();
		//board.fillBoardWithWords();
		board.printBoard();
	}
}
