package UserInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Generator.Board;

public class BoardOption extends JPanel
{
	String[][] myBoardCells = null;
	Dimension myDimension = null;
	BoardPanel bp;
	
	public BoardOption(String[][] boardCells,Dimension dimension)
	{	
		myBoardCells = boardCells;
		myDimension = dimension;
		
		setPreferredSize(dimension);
		setBackground(Color.GREEN);
		setLayout(null);
		setBoardCells(boardCells);
	}
	
	public String[][] getBoardCells()
	{
		return myBoardCells;
	}
	
	public void setBoardCells(String[][] boardCells)
	{
		if (bp != null)
			remove(bp);
		this.myBoardCells = boardCells;
		bp = new BoardPanel(myDimension.width,myDimension.height,boardCells.length,boardCells.length,40,40,0,0);
		bp.setBoard(boardCells);
		add(bp);
		validate();
    	repaint();
    	bp.validate();
    	bp.repaint();
	}
	
}
