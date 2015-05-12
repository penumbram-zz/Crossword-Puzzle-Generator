package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardTile extends JPanel implements MouseListener
{
	Color myColor;
	Boolean clickable = false;
	public BoardTile(int width,int height)
	{
		setSize(width,height);
		//setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        setVisible(true);
        addMouseListener(this);
	}
	
	public Color getColor()
	{
		return myColor;
	}
	
	public void setClickable(boolean b)
	{
		this.clickable = b;
	}

	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		this.myColor = bg;
	}
	
	//MOUSE
	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.clickable)
		{
			if(this.myColor == Color.BLACK)
				this.setBackground(Color.WHITE);
			else
				this.setBackground(Color.BLACK);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	//MOUSE
}
