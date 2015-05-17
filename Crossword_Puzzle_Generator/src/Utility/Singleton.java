package Utility;

import java.util.ArrayList;

import UserInterface.BoardFrame;

public class Singleton {
	
	   private static Singleton singleton = new Singleton( );
	   public ArrayList<String[][]> boards;
	   public BoardFrame boardFrame;
	   /* A private Constructor prevents any other 
	    * class from instantiating.
	    */
	   private Singleton()
	   { 
		   boards = new ArrayList<String[][]>();
	   }
	   
	   /* Static 'instance' method */
	   public static Singleton getInstance( ) {
	      return singleton;
	   }
	   /* Other methods protected by singleton-ness */
	   protected static void demoMethod( ) {
	      System.out.println("demoMethod for singleton"); 
	   }
	}