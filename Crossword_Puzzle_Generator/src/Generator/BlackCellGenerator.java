package Generator;




public class BlackCellGenerator 
{
	String[][] cells;
	public BlackCellGenerator(String[][] cells)
	{
		this.cells = cells;
	}
	
	public void diagonal(boolean startingLeft)
	{
		int t;
		for (int i = 1; i < cells.length-1; i++) 
		{
			if (startingLeft)
				t = i;
			else
				t = cells.length-1-i;
			
			cells[t][i] = "X";
		}
	}
}
