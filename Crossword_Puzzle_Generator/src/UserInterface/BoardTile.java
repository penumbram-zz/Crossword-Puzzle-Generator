package UserInterface;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardTile extends JPanel 
{
	public BoardTile()
	{
		setSize(50,50);
		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        setVisible(true);
	}
}
