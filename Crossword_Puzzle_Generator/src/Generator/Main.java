package Generator;

import java.io.IOException;

import UserInterface.MainFrame;
import Utility.FileUtils;


public class Main {
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) 
	{	
		try 
		{
			FileUtils.readFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
	}
}
