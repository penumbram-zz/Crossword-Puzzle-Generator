package UserInterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Observer.AnimationObserver;

public class FadingPanel extends JPanel implements AnimationObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float opacity = 1f;
	ArrayList<AnimationObserver> animatingObservers;
	Color backgroundColor = null;
	public FadingPanel()
	{
		animatingObservers = new ArrayList<AnimationObserver>();
	}
	
	@Override
	public void update(float opacity) {
		this.opacity = opacity;
		invalidate();
		repaint();
		validate();
	}

	@Override
	public void setBackground(Color arg0) {
		// TODO Auto-generated method stub
		this.backgroundColor = arg0;
		super.setBackground(arg0);
	}

/*	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
	//	if (backgroundColor != null)
	//	{
	//		float r = backgroundColor.getRed()/255;
	//		float gr = backgroundColor.getGreen()/255;
	//		float b = backgroundColor.getBlue()/255;
	//		setBackground(new Color(r,gr,b,this.opacity));
	//	}
		for (BufferedImage bufferedImage : panelImages)
			g.drawImage(Button.getImageWithAlpha(bufferedImage, this.opacity), 0,0,null);
		
	}*/
	
	public void setFadev(float newval)
	{
		this.opacity =newval;
		for (AnimationObserver animationObserver : animatingObservers) {
			if (animationObserver instanceof FadingPanel)
			{
				((FadingPanel)animationObserver).setFadev(newval);
			}
		}
		repaint();
	}

	@Override
	public void paint(Graphics arg0) {
		Graphics2D g = (Graphics2D) arg0;
		Composite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacity);
		g.setComposite(composite);
		super.paint(g);
	}



	@Override
	public Component add(Component comp) {
		// TODO Auto-generated method stub
		if (comp instanceof AnimationObserver)
		{
			animatingObservers.add((AnimationObserver)comp);
		}
		
		return super.add(comp);
	}

	@Override
	public void remove(Component comp) {
		if (comp instanceof AnimationObserver)
		{
			animatingObservers.remove((AnimationObserver)comp);
		}
		super.remove(comp);
	}
	
}
