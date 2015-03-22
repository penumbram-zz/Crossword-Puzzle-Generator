
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Board board = new Board(4,4);
		board.fillBoardWithRandomBlackCells();
		board.printBoard();
		
		Dictionary dict = new Dictionary();
		dict.fillDictionary();
		//dict.printDictionary();
		Dictionary.printLetters(1);
		board.fillBoardWithWords();
		board.printBoard();
	}
}
