package Generator;



import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Logger;


public class Board {
	
	public String[][] cells;
	int myWidth;
	int myHeight;
	ArrayList<String> wordsInBoard = new ArrayList<String>();
	ArrayList<ArrayList<int[]>> longestPaths = new ArrayList<ArrayList<int[]>>();
	ArrayList<ArrayList<int[]>> otherPaths = new ArrayList<ArrayList<int[]>>();
	ArrayList<ArrayList<int[]>> allPaths = new ArrayList<ArrayList<int[]>>();
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
	
	public static void printBoard(String[][] _cells)
	{
		System.out.println("----BOARD----");
		for (int i = 0; i < _cells.length; i++)
		{
			System.out.print("|");
			for (int j = 0; j < _cells.length; j++) 
				System.out.print(_cells[i][j] + "|");
			System.out.println();
		}
		System.out.println("--BOARD END--");
	}
	
	public static String convertBoardToString(String[][] _cells)
	{
		String _final = "";
		for (int i = 0; i < _cells.length; i++)
		{
			_final += "|";
			for (int j = 0; j < _cells.length; j++) 
				_final += _cells[i][j] + "|";
			_final += "\n";
		}
		return _final;
	}
	
	public void fillBoardWithFixedBlackCells()
	{
		BlackCellGenerator bcg = new BlackCellGenerator(this.cells);
		bcg.diagonal(false);
	}
	
	public void fillEmptyCellsWithBlackCells()
	{
		for (int i = 0; i < myHeight; i++)
		{
			for (int j = 0; j <myWidth; j++) 
			{
					if (cells[j][i] == " ")
					{
						cells[j][i] = "X";
					}
			}
		}
	}
	
	public void initBoard()
	{
		for (int i = 0; i < myHeight; i++)
		{
			for (int j = 0; j <myWidth; j++) 
			{
					 cells[j][i] = " ";
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
				if ((cells[k][i] == "X" || i == (myWidth-1)) && !someList.isEmpty())
				{
					//placeHorizontalWord(someList);
				}
			}
			someList.clear();
		}
	}
	
	private int getRange(int[] coords,boolean vertical)
	{
		
		int x = coords[0];
		int y = coords[1];
		int count = 0;
		Boolean b = true;
		boolean firstRun = true;
		while(b)
		{
			if (vertical)
				y++;
			else
				x++;
			
			if (vertical && y >= myHeight)
			{
				if (firstRun)
					return 0;
				else
					return count;
			}
				
			else if (!vertical && x >= myWidth)
			{
				if (firstRun)
					return 0;
				else
					return count;
			}
			
			if (cells[x][y] != null && !cells[x][y].equalsIgnoreCase("X"))
			{
				count++;
			}
			else
			{
				b = false;
			}
			firstRun = false;
		}
		return count;
	}
	
	private void placeWord(ArrayList<int[]> someList,boolean isVertical)
	{
		int l = someList.size(); // width of the word
		
	 	if (l == 1) //if a single letter word, just quit
		{
			someList.clear();
			return;
		}
	
		//Random rnd = new Random();
		//int max = Dictionary.letterLists.get(l).size();
		//String firstWord = Dictionary.letterLists.get(l).get(rnd.nextInt(max + 1));
		String word = selectWordForPath(someList);
	 	
		if (word == null) 
			return;
		
		int a;
		int b;
		if (isVertical)
		{
			a = 0;
			b = 1;
		}
		else
		{
			a = 1;
			b = 0;
		}

		for (int j = 0; j < someList.size(); j++)
		{
			String le = Character.toString(word.charAt(j));
			cells[someList.get(j)[0]][someList.get(j)[1]] = Character.toString(word.charAt(j));
			log("Wrote: " + le + " To: " + "cells[" + someList.get(j)[0] + "][" + someList.get(j)[1] + "]");
		}
		wordsInBoard.add(word);
		Dictionary.letterLists.get(someList.size()).remove(word);
		
		//placeVerticalWordsWithHorizontalWord(firstWord,someList);
		
	}
	private void placeVerticalWordsWithHorizontalWord(String horizontalWord,ArrayList<int[]> coords)
	{
		//int myRange = getRange(coords.get(0),true);
		HashMap<String, Integer> scoresList = new HashMap<String, Integer>();
		
		for (String s : Dictionary.letterLists.get(coords.size()))
		{
			if (String.valueOf(s.charAt(0)).equalsIgnoreCase(String.valueOf(horizontalWord.charAt(0)))) // if 0/0 letter equals first letter of s (a.k.a. inspected word)
			{
				String[] letters = s.split(""); // get the letters of s
				int score = 0; // initialize score to 0
				for (String  s2 : Dictionary.letterLists.get(coords.size()))
				{
					if (String.valueOf(s2.charAt(0)).equalsIgnoreCase(letters[1]))
					{
						score++;
					}
				}
				scoresList.put(s, new Integer(score));
				
			}
		}
		log((Collections.max(scoresList.values())) + " - " + getWordsWithMaxScores(scoresList).toString()); // prints words with highest scores
		Random rnd = new Random();
		
		int index = rnd.nextInt(getWordsWithMaxScores(scoresList).size());
		String wordd = getWordsWithMaxScores(scoresList).get(index);
	//	Object myKey = scoresList.keySet().toArray()[index];
		log(wordd);
		//placeVerticalWord(wordd,coords);
	}
	
	private int wordScore(String word)
	{
		for (String w : Dictionary.letterLists.get(word.length()))
		{
			
		}
		return 0;
	}
	
	private ArrayList<String> getWordsWithMaxScores(HashMap<String, Integer> map)
	{
		ArrayList<String> result = new ArrayList<String>();
		 int maxValueInMap=(Collections.max(map.values()));  // This will return max value in the Hashmap
	        for (Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
	            if (entry.getValue()==maxValueInMap) {
	                result.add(entry.getKey());     // Print the key with max value
	            }
	        }
	        return result;
	}
	
	public void searchLongestPaths()
	{
		log("---------  Horizontal --------");
		ArrayList<int[]> path = new ArrayList<int[]>();
		ArrayList<ArrayList<int[]>> paths = new ArrayList<ArrayList<int[]>>();
		int i = 0;
		for (i = 0; i < myWidth; i++)
		{
			for (int j = 0; j < myHeight; j++)
			{
				if (cells[i][j] == " ")
				{
					path.add(newCoordinates(i, j));
				}
				else if (cells[i][j] == "X")
				{
					ArrayList<int[]> temp = new ArrayList<int[]>();
					temp.addAll(path);
					paths.add(temp);
					logCoords(path);
					log("");
					path.clear();
					continue;
				}
			}
			ArrayList<int[]> temp = new ArrayList<int[]>();
			temp.addAll(path);
			paths.add(temp);
			logCoords(path);
			log("");
			path.clear();
		}
		
		path.clear();
		log("---------  Vertical --------");
		
		for (i = 0; i < myHeight; i++)
		{
			for (int j = 0; j < myWidth; j++)
			{
				if (cells[i][j] == " ")
				{
					path.add(newCoordinates(j, i));
				}
				else if (cells[i][j] == "X")
				{
					ArrayList<int[]> temp = new ArrayList<int[]>();
					temp.addAll(path);
					paths.add(temp);
					logCoords(path);
					log("");
					path.clear();
					continue;
				}
			}
			ArrayList<int[]> temp = new ArrayList<int[]>();
			temp.addAll(path);
			paths.add(temp);
			logCoords(path);
			log("");
			path.clear();
		}
		
		log("Longest Paths: ");
		
		for (i = 0; i < paths.size(); i++)
		{
			if (paths.get(i).size() == myWidth) //TODO this only accepts width/height letter words as longest, tho there could be more categories
			{
				logCoords(paths.get(i));
				longestPaths.add(paths.get(i));
			}
			else
			{
				if (paths.get(i).size() >= 2)
				{
					otherPaths.add(paths.get(i));
				}
			}
		}
		Collections.sort(longestPaths, new Comparator<ArrayList>(){
		    public int compare(ArrayList a1, ArrayList a2) {
		        return a2.size() - a1.size(); // assumes you want biggest to smallest
		    }
		});
		Collections.sort(otherPaths, new Comparator<ArrayList>(){
		    public int compare(ArrayList a1, ArrayList a2) {
		        return a2.size() - a1.size(); // assumes you want biggest to smallest
		    }
		});
		
		allPaths.addAll(longestPaths);
		allPaths.addAll(otherPaths);
	}
	
	private int[] newCoordinates(int x, int y)
	{
		int[] temp = new int[2];
		temp[0] = x;
		temp[1] = y;
		return temp;
	}
	
	public void fillLongestWords()
	{
		for (int i = 0; i < longestPaths.size(); i++)
		{
			ArrayList<int[]> pathToFill = longestPaths.get(i);
			placeWord(pathToFill,isVertical(pathToFill));
			System.out.println(wordsInBoard);
			printBoard(cells);
		}
	}
	
	private boolean isVertical(ArrayList<int[]> path)
	{
		int a = -1;
		int b = -1;

		if (path.get(0)[0] == path.get(1)[0])
			return true;
		else if (path.get(0)[1] == path.get(1)[1])
			return false;
		else
		{
			log("ERROR ERROR ERROR ERROR ERROR ERROR ERROR");
			return false;
		}
			
	}
	
	//LOGGING
	public void log(String s)
	{
		System.out.println(s);
	}
	
	private String selectWordForPath(ArrayList<int[]> list)
	{
		log("Selecting Word For Path: ");
		logCoords(list);
	//	ArrayList<Integer> fullCells = new ArrayList<Integer>();
		String[] letters = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
		{
			if (cells[list.get(i)[0]][list.get(i)[1]].equalsIgnoreCase(" "))
			{
				log("free cell");
				letters[i] = " ";
			}
			else
			{
				log("full cell");
				letters[i] = cells[list.get(i)[0]][list.get(i)[1]];
			}
		}
		
		for (int i = 0; i < letters.length; i++) 
			log("letters[" + i + "] = " + letters[i]);
		
		ArrayList<String> candidateWords = new ArrayList<String>();
		for (String string : Dictionary.letterLists.get(letters.length))
		{
			boolean flag = true;
			for (int i = 0; i < letters.length; i++)
			{
				if (!(letters[i].equalsIgnoreCase(" ")) && !(String.valueOf(string.charAt(i)).equalsIgnoreCase(letters[i])))
				{
					flag = false;
				}
				
			}
			if (flag)
				candidateWords.add(string);
		}
		log(candidateWords.toString());
		
		HashMap<String, Integer> scoresList = new HashMap<String, Integer>();
		HashMap<int[], ArrayList<ArrayList<int[]>>> owningPaths = getOwningPaths(list);
		for (String s : candidateWords)
		{
			
			double[] letter_scores = new double [letters.length];
		/*	if (letters.length == 3 && todelete)
			{
				log("lets check it");
			}*/
			for (int i = 0; i < letters.length; i++)
			{
				ArrayList<ArrayList<int[]>> temp = owningPaths.get(list.get(i));
				/*
				log("todelete");
				for (int j = 0; j < temp.size(); j++) 
				{
					logCoords(temp.get(j));
				}
				log("todelete");
				*/
				//DELETE
				int score = 0;
				
				
		
				if (temp.size() > 0)
				{
					int intersection = intersectionPoint(list, temp.get(0));
					int _size = temp.get(0).size();
					//log(String.valueOf(_size));
					for (String a_word : Dictionary.letterLists.get(_size))
					{
						if (wordFitsPath(a_word, owningPaths.get(list.get(i)).get(0))) 
						{
							if (String.valueOf(s.charAt(i)).equalsIgnoreCase(String.valueOf(a_word.charAt(intersection))) )
							{
								score++;
							}
						}
						else if (isPathFull(owningPaths.get(list.get(i)).get(0)))
						{
							score = 1;
							break;
						}
					}
				}
				else
				{
					score = 1;
				}
				letter_scores[i] = score;
			}
			//NEW
			Statistics statistics = new Statistics(letter_scores);
			double variance = statistics.getVariance();
			
			double final_score = 1;
			for (int i = 0; i < letter_scores.length; i++) 
			{
				final_score = final_score * letter_scores[i];
			}
			final_score /= (variance + 1);
			// NEW
			scoresList.put(s, new Integer((int)final_score));
		}
		log(scoresList.toString());
		if (scoresList.values().isEmpty() || Collections.max(scoresList.values()) == 0)
		{
			log("This board has no solution as is");
			return null;
		}
		log((Collections.max(scoresList.values())) + " - " + getWordsWithMaxScores(scoresList).toString()); // prints words with highest scores
		Random rnd = new Random();
		
		int index = rnd.nextInt(getWordsWithMaxScores(scoresList).size());
		String wordd = getWordsWithMaxScores(scoresList).get(index);
		log(wordd);	
		return wordd;
	}
	
	private int intersectionPoint(ArrayList<int[]> owner,ArrayList<int[]> crosser)
	{
		for (int i = 0; i < owner.size(); i++)
		{
			int[] temp = owner.get(i);
			for (int j = 0; j < crosser.size(); j++)
			{
				int[] temp2 = crosser.get(j);
				
				if (temp[0] == temp2[0] && temp[1] == temp2[1])
				{
					return j;
				}
			}
		}
		return 0;
	}
	
	private boolean wordFitsPath(String word, ArrayList<int[]> path)
	{
		boolean flag = true;
		for (int i = 0; i < path.size(); i++)
		{
			String tile = cells[path.get(i)[0]][path.get(i)[1]];
			if (tile.equalsIgnoreCase(" ") || tile.equalsIgnoreCase(String.valueOf(word.charAt(i))))
				;
			else
			{
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	private boolean isPathFull(ArrayList<int[]> path)
	{
		boolean flag = true;
		for (int i = 0; i < path.size(); i++)
		{
			String tile = cells[path.get(i)[0]][path.get(i)[1]];
			if (tile == " ")
			{
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	private HashMap<int[], ArrayList<ArrayList<int[]>>> getOwningPaths(ArrayList<int[]> list)
	{
		HashMap<int[], ArrayList<ArrayList<int[]>>> hMap = new HashMap<int[], ArrayList<ArrayList<int[]>>>();
		
		for (int[] ls : list)
		{
			ArrayList<ArrayList<int[]>> owningPaths = new ArrayList<ArrayList<int[]>>();
			for (int i = 0; i < allPaths.size(); i++)
			{
				for (int j = 0; j < allPaths.get(i).size(); j++)
				{
					if (allPaths.get(i).get(j)[0] == ls[0] && allPaths.get(i).get(j)[1] == ls[1])
					{
						//log("tile is contained by path: ");
						//logCoords(allPaths.get(i));
						if (allPaths.get(i) != list)
						{
							owningPaths.add(allPaths.get(i));
						}
					}
				}
			}
			hMap.put(ls, owningPaths);
		}
		for (int[] ls : list)
		{
			ArrayList<ArrayList<int[]>> temp = hMap.get(ls);
			for (ArrayList<int[]> arrayList : temp) {
				logCoords(arrayList);
			}
		}
		
		log("LIST OF OWNING PATHS OF");
		logCoords(list);
		log("IS AS FOLLOWS: ");
		/*for (int i = 0; i < owningPaths.size(); i++)
		{
			logCoords(owningPaths.get(i));
			log("---");
		}*/
		return hMap;
		//return owningPaths;
	}
	public void fillRestOfWords()
	{
		log("-------------");
		log("-------------");
		log("-------------");
		log("other paths: ");
		for (ArrayList<int[]> path : otherPaths) 
		{
			log("path:");
			log("");
			logCoords(path);
			placeWord(path,isVertical(path));
			System.out.println(wordsInBoard);
			printBoard(cells);
		}
	}
	
	private void checkNeighbours(ArrayList<int[]> list)
	{
		ArrayList<ArrayList<int[]>> pathsToCheck = new ArrayList<ArrayList<int[]>>();
		
		if (isVertical(list))
		{
			for (int[] place : list)
			{
				int _x = place[0];
				int _y = place[1];
				
				//ToTheLeft	
				int xx = _x;
				int yy = _y;
						
				boolean b = true;
						
				ArrayList<int[]> path = new ArrayList<int[]>();
				path.add(place);
						
				while(b)
				{
					xx--;
						
					if (xx > 0 && xx < myWidth)
					{
						if (cells[xx][_y] == null && cells[xx][_y] == "X")
						{
							b = false;
							if (path.size() >= 2)
							{
								pathsToCheck.add(path);
							}
						}
						else
						{
							path.add(newCoordinates(xx, _y));
						}
					}
					else
					{
						if (path.size() >= 2)
						{
							pathsToCheck.add(path);
						}
						b = false;
					}
				}			
					//ToTheLeft
					//ToTheRight
					
				xx = _x;
				yy = _y;
					
				b = true;
					
				path = new ArrayList<int[]>();
				path.add(place);
					
				while(b)
				{
					xx++;
						
					if (xx < myWidth)
					{
						if (cells[xx][_y] == null && cells[xx][_y] == "X")
						{
							b = false;
							if (path.size() >= 2)
							{
								pathsToCheck.add(path);
							}
						}
						else
						{
							path.add(newCoordinates(xx, _y));
						}
					}
					else
					{
						if (path.size() >= 2)
						{
							pathsToCheck.add(path);
						}
						b = false;
					}
				}
					
				//ToTheRight
			}
		}
		
		for (ArrayList<int[]> arrayList : pathsToCheck) 
		{
			log("Neighbouring Check");
			logCoords(arrayList);
			log("Neighbouring Check");
		}
		
		/*
		
		
				
			//Downwards
				
			xx = _x;
			yy = _y;
				
			b = true;
			
			path = new ArrayList<int[]>();
			path.add(place);
				
			while(b)
			{
				yy++;
					
				if (yy < myHeight)
				{
					if (cells[_x][yy] == null && cells[_x][yy] == "X")
					{
						b = false;
						if (path.size() >= 2)
						{
							pathsToCheck.add(path);
						}
					}
					else
					{
						path.add(newCoordinates(_x, yy));
					}
				}
			}
				
			//Downwards
				
			//Upwards
				
			xx = _x;
			yy = _y;
				
			b = true;
				
			path = new ArrayList<int[]>();
			path.add(place);
				
			while(b)
			{
				yy--;
					
				if (yy > 0)
				{
					if (cells[_x][yy] == null && cells[_x][yy] == "X")
					{
						b = false;
						if (path.size() >= 2)
						{
							pathsToCheck.add(path);
						}
					}
					else
					{
						path.add(newCoordinates(_x, yy));
					}
				}
			}
			//Upwards				
		}
		*/
	}
	
	public void logCoords(ArrayList<int[]> coords)
	{
		for (int i = 0; i < coords.size(); i++)
		{
			log("cell[" + String.valueOf(coords.get(i)[0]) + "][" + String.valueOf(coords.get(i)[1]) + "]");
		}
	}
}
