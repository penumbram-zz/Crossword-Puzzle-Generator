package Generator;


import java.awt.EventQueue;
import java.util.logging.Logger;

import UserInterface.MainFrame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		
		MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
		
		
		Board board = new Board(10,10);
		/*board.initBoard();
		board.fillBoardWithFixedBlackCells();
		mainFrame.boardFrame.boardPanel.setBoard(board.cells);
		*/
		boolean b = true;
		if (b)
			return;
		
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
