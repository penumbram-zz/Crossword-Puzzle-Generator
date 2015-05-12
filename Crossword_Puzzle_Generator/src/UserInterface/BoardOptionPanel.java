package UserInterface;

import Generator.Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class BoardOptionPanel extends JPanel 
{
	public BoardOptionPanel()
	{
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.YELLOW);
		setLayout(null);
		
		
		Board board = new Board(5,5);
		board.initBoard();
		board.fillBoardWithFixedBlackCells();
		BoardPanel bp = new BoardPanel(200,200,5,5,40,40,50,50);
		bp.setBoard(board.cells);
		add(bp);
		bp.setVisible(true);
		setVisible(true);
		
	}
}
