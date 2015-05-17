package Observer;

import java.util.ArrayList;

public interface BoardSubject 
{
	public void registerObserver(BoardObserver observer);
	public void removeObserver(BoardObserver observer);
	void notifyObservers(ArrayList<int[]> list, String word);

}
