package UserInterface;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardPanel extends JPanel 
{
	public BoardPanel()
	{
		setSize(500, 500);
		setBackground(Color.RED);
		setBounds(230, 20, 500, 500);
		setLayout(new GridLayout(10, 10));
		
		
		
	}
	
	public void setBoard(String[][] cellz)
    {
		printBoard(cellz);
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++) 
			{
				BoardTile boardTile = new BoardTile();
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
