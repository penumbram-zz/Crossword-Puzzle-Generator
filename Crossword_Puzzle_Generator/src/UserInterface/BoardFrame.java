package UserInterface;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.RepeatBehavior;

public class BoardFrame extends JFrame {

	public BoardPanel boardPanel;
	public BoardSelectionPanel boardSelectionPanel;
	public BoardEditorPanel boardEditorPanel;
	
	public void setValue(float newValue)
	{
		setOpacity(newValue);
	}
	
	public BoardFrame()
	{
		setLayout(null);
        setSize(960, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0f);
        getContentPane().setBackground(Color.GRAY);
        BufferedImage backgroundImage = MainFrame.getImage("resources/images/office_bg.png", 960,540);
        setContentPane(new ImagePanel(backgroundImage));
		//boardPanel = new BoardPanel(500,500,10,10,50,50);
		//boardSelectionPanel = new BoardSelectionPanel();
        boardEditorPanel = new BoardEditorPanel();
		add(boardEditorPanel);
		//add(boardPanel);
        
        
        Timeline colorTimeline = new Timeline(this);
		colorTimeline.addPropertyToInterpolate("value", 0.0f,
				1.0f);
		colorTimeline.setDuration(4500);
		colorTimeline.play();
	}
}
