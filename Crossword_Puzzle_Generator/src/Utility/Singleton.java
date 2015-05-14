package Utility;

import java.util.ArrayList;

public class Singleton {
	
	   private static Singleton singleton = new Singleton( );
	   public ArrayList<String[][]> boards;
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