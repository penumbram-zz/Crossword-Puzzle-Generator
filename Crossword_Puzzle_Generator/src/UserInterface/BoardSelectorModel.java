package UserInterface;

import java.util.ArrayList;
import java.util.HashMap;

import Observer.Observer;
import Observer.Subject;
import Utility.Singleton;

public class BoardSelectorModel implements Subject
{
	int lastBoardIndex = 0;
	boolean rightBlocked = false;
	boolean leftBlocked = false;
	BoardOption[] options;
	private ArrayList<Observer> observers;
	String blocked = "None";
	HashMap<String, Boolean> map;
	
	public BoardSelectorModel()
	{
		map = new HashMap<String, Boolean>();
		map.put("LeftBlocked", false);
		map.put("RightBlocked", false);
		lastBoardIndex = 2;
		observers = new ArrayList<Observer>();
	}
	
	public void go(boolean right)
	{
		if (map.get("LeftBlocked") && !right)
			return;
		if (map.get("RightBlocked") && right)
			return;
		
		if (right)
			lastBoardIndex++;
		else
			lastBoardIndex--;
			
		updateBlockedValues();	
		notifyObservers();
		
		boolean b = true;
		if (b) {
			return;
		}
		
		
		if (lastBoardIndex-2 >= 0 && lastBoardIndex -2 < Singleton.getInstance().boards.size())
		{
		//	options[0].setBoardCells(Singleton.getInstance().boards.get(lastBoardIndex-2));
			options[0].setVisible(true);
			leftBlocked = false;
		}
		else
		{
			options[0].setVisible(false);
			leftBlocked = true;
		}
		if (lastBoardIndex-1 >= 0 && lastBoardIndex-1 < Singleton.getInstance().boards.size())
		{
			//options[1].setBoardCells(Singleton.getInstance().boards.get(lastBoardIndex-1));
		}
		
		if (lastBoardIndex < Singleton.getInstance().boards.size())
		{
		//	options[2].setBoardCells(Singleton.getInstance().boards.get(lastBoardIndex));
			options[2].setVisible(true);
			rightBlocked = false;
		}
		else
		{
			rightBlocked = true;
			options[2].setVisible(false);
		}
	}
	
	private void updateBlockedValues()
	{
		if (lastBoardIndex-2 >= 0 && lastBoardIndex -2 < Singleton.getInstance().boards.size())
			map.put("LeftBlocked", false);
		else
			map.put("LeftBlocked", true);
		if (lastBoardIndex < Singleton.getInstance().boards.size())
			map.put("RightBlocked", false);
		else
			map.put("RightBlocked", true);
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
		
	}

	@Override
	public void removeObserver(Observer observer) {
		int i = observers.indexOf(observer);
		if (i >= 0)
			observers.remove(i);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers)
			observer.update(lastBoardIndex,map);
	}
}
