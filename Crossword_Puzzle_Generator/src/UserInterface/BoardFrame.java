package UserInterface;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import Observer.AnimationObserver;
import Observer.AnimationSubject;
import Utility.Singleton;
import Utility.Utils;

public class BoardFrame extends JFrame implements AnimationSubject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BoardPanel boardPanel;
	public BoardSelectionPanel boardSelectionPanel;
	public BoardEditorPanel boardEditorPanel;
	
	private ArrayList<AnimationObserver> observers;
	float opacity = 0f;
	public void setValue(float newValue)
	{
		this.opacity = newValue;
		notifyObservers();
		//setOpacity(newValue);
	}
	
	public BoardFrame()
	{
		Singleton.getInstance().boardFrame = this;
		observers = new ArrayList<AnimationObserver>();
		setLayout(null);
        setSize(960, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setBackground(Color.GRAY);
        BufferedImage backgroundImage = Utils.getImage("resources/images/office_bg.png", 960,540);
        setContentPane(new ImagePanel(backgroundImage));
		boardSelectionPanel = new BoardSelectionPanel();
		boardEditorPanel = new BoardEditorPanel();
		add(boardSelectionPanel);
		add(boardEditorPanel);
		boardEditorPanel.setVisible(false);
	}
	
	public void boardSelected(BoardOption boardOption)
	{
		boardSelectionPanel.setVisibility(false);
		boardEditorPanel.editSelectedBoard(boardOption.myBoardCells);
		boardEditorPanel.setVisible(true);
	}

	@Override
	public void registerObserver(AnimationObserver observer) {
		observers.add(observer);
		if (observer instanceof FadingPanel)
		{
			for (AnimationObserver animationObserver : ((FadingPanel) observer).animatingObservers)
			{
				registerObserver(animationObserver);
			}
		}
	}

	@Override
	public void removeObserver(AnimationObserver observer) {
		int i = observers.indexOf(observer);
		if (i >= 0)
			observers.remove(i);
	}

	@Override
	public void notifyObservers() {
		for (AnimationObserver observer : observers)
			observer.update(this.opacity);
	}
	
}
