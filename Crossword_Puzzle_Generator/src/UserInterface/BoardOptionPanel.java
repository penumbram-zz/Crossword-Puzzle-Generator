package UserInterface;

import Generator.Board;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardOptionPanel extends JPanel 
{
	public BoardOptionPanel()
	{
		setSize(200, 200);
		setBackground(Color.BLUE);
		
		
		
		Board board = new Board(5,5);
		board.initBoard();
		board.fillBoardWithFixedBlackCells();
		BoardPanel bp = new BoardPanel(200,200,5,5,100,100,20,20);
		bp.setBoard(board.cells);
		add(bp);
		//bp.setBounds(400, 400, 50, 50);
		bp.setVisible(true);
		setVisible(true);
	}
}
