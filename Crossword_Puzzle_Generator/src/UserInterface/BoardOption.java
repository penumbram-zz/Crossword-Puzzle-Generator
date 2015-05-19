package UserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Generator.Board;
import Observer.AnimationObserver;
import Observer.DisplayBoard;
import Observer.Observer;
import Utility.Singleton;
import Utility.Utils;

public class BoardOption extends FadingPanel implements Observer, DisplayBoard
{
	String[][] myBoardCells = null;
	Dimension myDimension = null;
	public BoardPanel bp;
	int internalIndex = -1;
	int lastIndex;
	
	private boolean selected;
	
	public BoardOption(String[][] boardCells,Dimension dimension,int internalIndex)
	{	
		myBoardCells = boardCells;
		myDimension = dimension;
		
		setPreferredSize(dimension);
		setBackground(Color.GREEN);
		setLayout(null);
		this.internalIndex = internalIndex;
		this.myBoardCells = boardCells;
		display();
	}
	
	public String[][] getBoardCells()
	{
		return myBoardCells;
	}
	
	public void select()
	{
		this.selected = true;
		System.out.println("Selected: ");
		Board.printBoard(myBoardCells);
		
		Singleton.getInstance().boardFrame.boardSelected(this);
	}
	
	public void deselect()
	{
		this.selected = false;
		System.out.println("Deselected");
	}

	@Override
	public void update(int index,HashMap<String, Boolean> map) 
	{	
		if (!(index-(2-internalIndex) >= 0 && index-(2-internalIndex) < Singleton.getInstance().boards.size()))
		{	
			setVisible(false);
			Utils.logg("null");
			return;
		}
		
		this.myBoardCells = Singleton.getInstance().boards.get(index-(2-internalIndex));
		
		display();
	}
	
	@Override
	public void display() 
	{
		if (bp != null)
			remove(bp);
		setVisible(true);
		int i = ((int)(this.myDimension.getWidth() / this.myBoardCells.length));
		bp = new BoardPanel(myDimension.width,myDimension.height,this.myBoardCells.length,this.myBoardCells.length,i,i,0,0);
		bp.setBoard(this.myBoardCells);
		add(bp);
		validate();
    	repaint();
    	bp.validate();
    	bp.repaint();
	}

}
