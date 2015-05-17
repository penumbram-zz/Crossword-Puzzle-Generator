package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Observer.AnimationObserver;
import Utility.Utils;

public class FadingPanel extends JPanel implements AnimationObserver {
	
	float opacity = 0f;
	ArrayList<BufferedImage> panelImages;
	Color backgroundColor = null;
	public FadingPanel()
	{
		panelImages = new ArrayList<BufferedImage>();
	}
	
	public void addImage(BufferedImage bufferedImage)
	{
		this.panelImages.add(bufferedImage);
	}
	
	@Override
	public void update(float opacity) {
		this.opacity = opacity;
		repaint();
	}

	@Override
	public void setBackground(Color arg0) {
		// TODO Auto-generated method stub
		this.backgroundColor = arg0;
		super.setBackground(arg0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		/*if (backgroundColor != null)
		{
			float r = backgroundColor.getRed()/255;
			float gr = backgroundColor.getGreen()/255;
			float b = backgroundColor.getBlue()/255;
			setBackground(new Color(r,gr,b,this.opacity));
		}*/
		for (BufferedImage bufferedImage : panelImages)
			g.drawImage(Button.getImageWithAlpha(bufferedImage, this.opacity), 0,0,null);
		
	}
	
}
