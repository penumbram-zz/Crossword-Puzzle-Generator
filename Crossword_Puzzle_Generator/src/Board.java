import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Board {
	
	String[][] cells;
	int myWidth;
	int myHeight;
	
	// Desktop git push check
	
	
	
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	
	
	public Board(int width, int height) 
	{
		myWidth = width;
		myHeight = height;
		cells = new String[width][height];
	}

	public void fillCells() // fill empty cells with random letters
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
	
	public void fillBoardWithRandomBlackCells()
	{
		for (int i = 0; i < myWidth; i++)
		{
			for (int j = 0; j <myHeight; j++) 
			{
				 Random r = new Random();
				 int a = r.nextInt(5);
				 a = 1; //TODO NO RANDOM CELLS AT THE MOMENT, ALL BLANK
				 if (a == 0)
				 {
					 cells[i][j] = "X";
				 }
				 else
				 {
					 cells[i][j] = " ";
				 }
			}
		}
	}
	
	public void fillBoardWithWords()
	{
		System.out.println();
		System.out.println();
		ArrayList<int[]> someList = new ArrayList<>();
		
		for (int k = 0; k < myHeight; k++) 
		{
			for (int i = 0; i < myWidth; i++) 
			{
				if (cells[k][i] == " ")
				{
					int[] temp = new int[2];
					temp[0] = k;
					temp[1] = i;
					someList.add(temp);
				}
				if (cells[k][i] == "X" || i == (myWidth-1))
				{
					int l = someList.size();
					
					if (l == 1)
					{
						someList.clear();
						continue;
					}
					
					String firstWord = Dictionary.letterLists.get(l).get(12);
					System.out.println(firstWord);
					
					for (int j = 0; j < someList.size(); j++)
					{
						cells[someList.get(j)[0]][someList.get(j)[1]] = Character.toString(firstWord.charAt(j));
					}
					Dictionary.letterLists.get(someList.size()).remove(firstWord);
					log("HIT HERE");
					
					ArrayList<int[]> verticalList = new ArrayList<>();
					for (int m = 0; m < myWidth; m++)
					{
						log("i = " + i);
						for (int n = 0; n < myWidth; n++)
						{
							int[] temp2 = new int[2];
							temp2[0] = n;
							temp2[1] = m;
							verticalList.add(temp2);	
						}
						
						//PRINT VERTICAL LIST
						log("");
						log("");
						

						for (String word : Dictionary.letterLists.get(verticalList.size()))
						{
							//log("Word: " + word);
							if (firstWord.substring(m, m+1).equalsIgnoreCase(word.substring(0, 1)))
							{
								for (int o = 0; o < verticalList.size(); o++)
								{
									cells[verticalList.get(o)[0]][verticalList.get(o)[1]] = Character.toString(word.charAt(o));
									log("o = " + o);
									log("cell[" + String.valueOf(verticalList.get(o)[0]) + "][" + String.valueOf(verticalList.get(o)[1]) + "]" + " = " + Character.toString(word.charAt(o)));
								}
								
								Dictionary.letterLists.get(verticalList.size()).remove(word);
								log("Wrote " + word + " and now moving to the next word");
								break;
							}
						}
						verticalList.clear();
					}
					return;
				}
			}
			someList.clear();
		}
	}
	public void log(String s)
	{
		System.out.println(s);
	}
}
