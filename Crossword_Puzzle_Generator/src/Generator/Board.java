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

import Observer.AnimationObserver;
import Observer.BoardObserver;
import Observer.BoardSubject;


public class Board implements BoardSubject {
	private ArrayList<BoardObserver> observers = new ArrayList<BoardObserver>();
	public String[][] cells;
	public String[][] savedCells;
	int myWidth;
	int myHeight;
	public ArrayList<String> wordsInBoard = new ArrayList<String>();
	ArrayList<ArrayList<int[]>> longestPaths = new ArrayList<ArrayList<int[]>>();
	ArrayList<ArrayList<int[]>> otherPaths = new ArrayList<ArrayList<int[]>>();
	public ArrayList<ArrayList<int[]>> allPaths = new ArrayList<ArrayList<int[]>>();
	
	public ArrayList<String> ruledOutWords = new ArrayList<String>();
	// Desktop git push check
	
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public void clearAll()
	{
		wordsInBoard.clear();
		longestPaths.clear();
		otherPaths.clear();
		allPaths.clear();
	}
	
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
	
	public void setCells(String[][] cells)
	{
		this.cells = cells;
	}
	public void saveCells(String[][] cells)
	{
		String[][] temp = new String[cells.length][cells.length];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				temp[i][j] = cells[i][j];
			}
		}
		this.savedCells = temp;
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
	
	public String getWordsInBoard()
	{
		return wordsInBoard.toString();
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
	//	Dictionary.letterLists.get(someList.size()).remove(word);
		notifyObservers(someList,word);
		//placeVerticalWordsWithHorizontalWord(firstWord,someList);
		
	}


	
	private ArrayList<String> getWordsWithMaxScores(HashMap<String, Integer> map)
	{
		ArrayList<String> result = new ArrayList<String>();
		 int maxValueInMap=(Collections.max(map.values()));  
	        for (Entry<String, Integer> entry : map.entrySet()) {  
	            if (entry.getValue()==maxValueInMap) {
	                result.add(entry.getKey());
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
				if (cells[j][i] == " ")
				{
					path.add(newCoordinates(j, i));
				}
				else if (cells[j][i] == "X")
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
		
		log("Longest Paths: ");
		int topLength = 1;
		
		for (i = 0; i < paths.size(); i++)
		{
			if (paths.get(i).size() > topLength) 
			{
				topLength = paths.get(i).size();
			}
		}
		
		for (i = 0; i < paths.size(); i++)
		{
			if (paths.get(i).size() == topLength && topLength > 1) 
			{
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
			if (flag && !wordsInBoard.contains(string) && !ruledOutWords.contains(string))
				candidateWords.add(string);
		}
		log(candidateWords.toString());
		
		HashMap<String, Integer> scoresList = new HashMap<String, Integer>();
		HashMap<int[], ArrayList<ArrayList<int[]>>> owningPaths = getOwningPaths(list);
		for (String s : candidateWords)
		{
			
			double[] letter_scores = new double [letters.length];

			for (int i = 0; i < letters.length; i++)
			{
				ArrayList<ArrayList<int[]>> temp = owningPaths.get(list.get(i));

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
			if (final_score <1 && final_score > 0)
				final_score = 1;
			
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
		
	//	log("LIST OF OWNING PATHS OF");
	//	logCoords(list);
	//	log("IS AS FOLLOWS: ");
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
	
	
	public void logCoords(ArrayList<int[]> coords)
	{
		for (int i = 0; i < coords.size(); i++)
		{
			log("cell[" + String.valueOf(coords.get(i)[0]) + "][" + String.valueOf(coords.get(i)[1]) + "]");
		}
	}
	
	public ArrayList<int[]> validate()
	{
		longestPaths = new ArrayList<ArrayList<int[]>>();
		otherPaths = new ArrayList<ArrayList<int[]>>();
		allPaths = new ArrayList<ArrayList<int[]>>();
		
		log("---------  Horizontal --------");
		ArrayList<int[]> path = new ArrayList<int[]>();
		ArrayList<ArrayList<int[]>> paths = new ArrayList<ArrayList<int[]>>();
		int i = 0;
		for (i = 0; i < myWidth; i++)
		{
			for (int j = 0; j < myHeight; j++)
			{
				log(cells[j][i]);
				if (!cells[j][i].equals("X"))
				{
					path.add(newCoordinates(j, i));
				}
				else
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
				log(cells[i][j]);
				if (!cells[i][j].equals("X"))
				{
					path.add(newCoordinates(i, j));
				}
				else
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
		
		allPaths.addAll(longestPaths);
		allPaths.addAll(otherPaths);
		
		
		for (ArrayList<int[]> arrayList : allPaths)
		{
			String someWord = "";
			for (int[] intarr : arrayList) 
				someWord += cells[intarr[0]][intarr[1]];
			if (!Dictionary.letterLists.get(someWord.length()).contains(someWord))
				return arrayList;
			else
			{
				if (!wordsInBoard.contains(someWord))
				{
					wordsInBoard.add(someWord);
				}
			}
		}
		return null;
	}

	@Override
	public void registerObserver(BoardObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(BoardObserver observer) {
		int i = observers.indexOf(observer);
		if (i >= 0)
			observers.remove(i);
		
	}

	@Override
	public void notifyObservers(ArrayList<int[]> list, String word) {
		for (BoardObserver observer : observers)
			observer.update(list,word);
		
	}
}
