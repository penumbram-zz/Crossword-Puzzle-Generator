import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Logger;


public class Dictionary 
{
	ArrayList<String> dictionary;
	ArrayList<String> _1_letters;
	ArrayList<String> _2_letters;
	ArrayList<String> _3_letters;
	ArrayList<String> _4_letters;
	ArrayList<String> _5_letters;
	ArrayList<String> _6_letters;
	ArrayList<String> _7_letters;
	ArrayList<String> _8_letters;
	ArrayList<String> _9_letters;
	ArrayList<String> _10_letters;
	ArrayList<String> _other_letters;
	static ArrayList<ArrayList<String>> letterLists;
	
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public Dictionary()
	{
		dictionary = new ArrayList<>();
		_1_letters = new ArrayList<>();
		_2_letters = new ArrayList<>();
		_3_letters = new ArrayList<>();
		_4_letters = new ArrayList<>();
		_5_letters = new ArrayList<>();
		_6_letters = new ArrayList<>();
		_7_letters = new ArrayList<>();
		_8_letters = new ArrayList<>();
		_9_letters = new ArrayList<>();
		_10_letters = new ArrayList<>();
		_other_letters = new ArrayList<>();
		
		letterLists = new ArrayList<>();
		letterLists.add(_other_letters);
		letterLists.add(_1_letters);
		letterLists.add(_2_letters);
		letterLists.add(_3_letters);
		letterLists.add(_4_letters);
		letterLists.add(_5_letters);
		letterLists.add(_6_letters);
		letterLists.add(_7_letters);
		letterLists.add(_8_letters);
		letterLists.add(_9_letters);
		letterLists.add(_10_letters);
	}
	
	public void fillDictionary()
	{
		try(BufferedReader br = new BufferedReader(new FileReader("resources/dictionaries/english_words.txt"))) 
		{
		    for(String line; (line = br.readLine()) != null; ) 
		    {
		        dictionary.add(line);
		        if (line.length() >= 1 && line.length() <= 10) 
		        	letterLists.get(line.length()).add(line);
		        else
		        	letterLists.get(0).add(line);
		    }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ArrayList<String> all_two_letter_combinations = new ArrayList<String>();
		for (int i = 0; i < alphabet.length(); i++)
		{
			String temp = String.valueOf(alphabet.charAt(i));
			for (int j = 0; j < alphabet.length(); j++)
			{
				String temp2 = String.valueOf(alphabet.charAt(j));
				String concat = temp + temp2;
				all_two_letter_combinations.add(concat);
			}
		}
		letterLists.get(2).addAll(all_two_letter_combinations);
	}
	
	public void printDictionary()
	{ 
		for(String s : dictionary)
		{
			System.out.println(s);
		}
	}
	
	
	
	public static void printLetters(int a)
	{
		ArrayList<String> myList;
		if (a >= 1 && a <= 10)
			myList = letterLists.get(a);
		else
			myList = letterLists.get(0);
		
		for(String s : myList)
			System.out.println(s);
	}
}
