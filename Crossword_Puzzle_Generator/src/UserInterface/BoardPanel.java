package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Generator.Board;

public class BoardPanel extends JPanel 
{
	private int tileWidth;
	private int tileHeight;
	BoardTile[][] tiles;
	
	public BoardPanel(int width,int height,int row, int column,int tileWidth, int tileHeight,int x,int y)
	{
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		setBounds(x, y, width,height);
		setLayout(new GridLayout(row, column));
	}
	
	public void setBoard(String[][] cellz)
    {
		tiles = new BoardTile[cellz.length][cellz.length];
		System.out.println("yoyoyoyo");
		Board.printBoard(cellz);
		for (int i = 0; i < cellz.length; i++)
		{
			for (int j = 0; j < cellz.length; j++) 
			{
				BoardTile boardTile = new BoardTile(tileWidth,tileHeight);
				System.out.println(cellz[i][j]);
				if (cellz[i][j].equalsIgnoreCase("X"))
					boardTile.setBackground(Color.BLACK);
				else
					boardTile.setBackground(Color.WHITE);
				tiles[i][j] = boardTile;
				add(boardTile);
			}
		}
    }
	
	public void setClickable(boolean b)
	{
		if (b)
		{
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles.length; j++) {
					tiles[i][j].setClickable(b);
				}
			}
		}
	}
	
}
