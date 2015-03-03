import java.util.Random;


public class Board {
	
	String[][] cells;
	int myWidth;
	int myHeight;
	
	
	
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	
	
	public Board(int width, int height) 
	{
		myWidth = width;
		myHeight = height;
		cells = new String[width][height];
	}

	public void fillCells()
	{
		for (int i = 0; i < myWidth; i++)
		{
			for (int j = 0; j <myHeight; j++) 
			{
				 Random r = new Random();
				cells[i][j] = new StringBuilder().append(alphabet.charAt(r.nextInt(alphabet.length()))).toString();
			}
		}
	}
	
	
	
	public void printBoard()
	{
		System.out.println();
		for (int i = 0; i < cells.length; i++)
		{
			System.out.print("|");
			for (int j = 0; j < cells[i].length; j++) 
				System.out.print(cells[i][j] + "|");
			System.out.println();
		}
	}
	

}
