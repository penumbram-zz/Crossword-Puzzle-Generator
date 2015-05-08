package UserInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardTile extends JPanel 
{
	public BoardTile(int width,int height)
	{
		setSize(width,height);
		//setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        setVisible(true);
	}
}
