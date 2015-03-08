
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Board board = new Board(5,5);
		board.fillBoardWithRandomBlackCells();
		board.printBoard();
		
		Dictionary dict = new Dictionary();
		dict.fillDictionary();
		//dict.printDictionary();
		dict.printLetters(1);
		board.fillBoardWithWords();
		board.printBoard();
	}
}
