package Observer;

public interface AnimationSubject 
{
	public void registerObserver(AnimationObserver observer);
	public void removeObserver(AnimationObserver observer);
	public void notifyObservers();

}
