package Generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import UserInterface.MainFrame;
import Utility.FileUtils;


public class Main {
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) 
	{	
		
		
		
		Board board = new Board(10,10);
		/*board.initBoard();
		board.fillBoardWithFixedBlackCells();
		mainFrame.boardFrame.boardPanel.setBoard(board.cells);
		*/
		try 
		{
			FileUtils.readFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
		
		boolean b = true;
		if (b)
			return;
		
		board.searchLongestPaths();
		
		Board.printBoard(board.cells);
		
		Dictionary dict = new Dictionary();
		dict.fillDictionary();
		board.fillLongestWords();
		Board.printBoard(board.cells);
		board.fillRestOfWords();
		board.fillEmptyCellsWithBlackCells();
		Board.printBoard(board.cells);
	}
}
