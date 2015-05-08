package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardPanel extends JPanel 
{
	private int tileWidth;
	private int tileHeight;
	
	public BoardPanel(int width,int height,int row, int column,int tileWidth, int tileHeight,int x,int y)
	{
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.RED);
		setBounds(x, y, width,height);
		setLayout(new GridLayout(row, column));
	}
	
	public void setBoard(String[][] cellz)
    {
		printBoard(cellz);
		for (int i = 0; i < cellz.length; i++)
		{
			for (int j = 0; j < cellz.length; j++) 
			{
				BoardTile boardTile = new BoardTile(tileWidth,tileHeight);
				if (cellz[i][j] == "X")
					boardTile.setBackground(Color.BLACK);
				else
					boardTile.setBackground(Color.WHITE);

				add(boardTile);
			}
		}
    }
	
	public void printBoard(String[][] cells)
	{
		System.out.println(String.valueOf(cells.length));
		for (int i = 0; i < cells.length; i++)
		{
			System.out.print("|");
			for (int j = 0; j < cells.length; j++) 
				System.out.print(cells[j][i] + "|");
			System.out.println();
		}
	}
	
}
