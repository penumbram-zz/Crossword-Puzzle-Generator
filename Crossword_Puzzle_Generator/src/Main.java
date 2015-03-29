
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Board board = new Board(4,4);
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
