package UserInterface;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.RepeatBehavior;

import Observer.AnimationObserver;
import Observer.AnimationSubject;
import Observer.Observer;
import Utility.Singleton;
import Utility.Utils;

public class BoardFrame extends JFrame implements AnimationSubject
{
	public BoardPanel boardPanel;
	public BoardSelectionPanel boardSelectionPanel;
	public BoardEditorPanel boardEditorPanel;
	
	private ArrayList<AnimationObserver> observers;
	float opacity;
	public void setValue(float newValue)
	{
		this.opacity = newValue;
		notifyObservers();
	//	setOpacity(newValue);
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
     //   setOpacity(0f);
        getContentPane().setBackground(Color.GRAY);
        BufferedImage backgroundImage = Utils.getImage("resources/images/office_bg.png", 960,540);
        setContentPane(new ImagePanel(backgroundImage));
		//boardPanel = new BoardPanel(500,500,10,10,50,50);
		boardSelectionPanel = new BoardSelectionPanel();
		
		registerObserver(boardSelectionPanel);
		/*registerObserver(boardSelectionPanel.boardOptionPanel);
		registerObserver(boardSelectionPanel.boardOptionPanel.previous);
		registerObserver(boardSelectionPanel.boardOptionPanel.current);
		registerObserver(boardSelectionPanel.boardOptionPanel.next);
		registerObserver(boardSelectionPanel.boardOptionPanel.previous.bp);
		registerObserver(boardSelectionPanel.boardOptionPanel.current.bp);
		registerObserver(boardSelectionPanel.boardOptionPanel.next.bp);
		for (int i = 0; i < boardSelectionPanel.boardOptionPanel.previous.bp.tiles.length; i++) {
			for (int j = 0; j < boardSelectionPanel.boardOptionPanel.previous.bp.tiles.length; j++) {
				registerObserver(boardSelectionPanel.boardOptionPanel.previous.bp.tiles[i][j]);
			}
		}
		for (int i = 0; i < boardSelectionPanel.boardOptionPanel.current.bp.tiles.length; i++) {
			for (int j = 0; j < boardSelectionPanel.boardOptionPanel.current.bp.tiles.length; j++) {
				registerObserver(boardSelectionPanel.boardOptionPanel.current.bp.tiles[i][j]);
			}
		}
		for (int i = 0; i < boardSelectionPanel.boardOptionPanel.next.bp.tiles.length; i++) {
			for (int j = 0; j < boardSelectionPanel.boardOptionPanel.next.bp.tiles.length; j++) {
				registerObserver(boardSelectionPanel.boardOptionPanel.next.bp.tiles[i][j]);
			}
		}*/
		boardEditorPanel = new BoardEditorPanel();
		add(boardSelectionPanel);
		//add(boardPanel);
        
        
      Timeline colorTimeline = new Timeline(this);
		colorTimeline.addPropertyToInterpolate("value", 0.0f,
				1.0f);
		colorTimeline.setDuration(10000);
		colorTimeline.play(); 
		
	}

	@Override
	public void registerObserver(AnimationObserver observer) {
		observers.add(observer);
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
