package UserInterface;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.RepeatBehavior;
import org.pushingpixels.trident.Timeline.TimelineState;
import org.pushingpixels.trident.callback.TimelineCallback;

public class Button extends JButton implements ActionListener
{
	BufferedImage image;
	BufferedImage glowImage;
	float alpha = 0f;
	Timeline timeline;
	
	public Button(BufferedImage anImage) {
	        if (anImage != null)
	        {
	        	image = anImage;
		        setOpaque(false);
		        setContentAreaFilled(false);
		        setFocusPainted(false);
		        setBorderPainted(false);
		        addActionListener(this);
			}
	}
	
	public Button(BufferedImage anImage,BufferedImage glowImage) {
        if (anImage != null && glowImage != null)
        {
        	image = anImage;
        	this.glowImage = glowImage;
	        setOpaque(false);
	        setContentAreaFilled(false);
	        setFocusPainted(false);
	        setBorderPainted(false);
	        addActionListener(this);
	        
	        timeline = new Timeline(this);
			timeline.addPropertyToInterpolate("glowValue", 0.0f,
					1.0f);
			timeline.setDuration(1000);
		}
}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image, 0, 0, null);
	    if (glowImage != null)
	    	g.drawImage(getImageWithAlpha(glowImage, this.alpha), 0,0,null);
	}
	
	public static BufferedImage getImageWithAlpha(BufferedImage src, float alpha)
    {
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(),
                                               BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        int rule = AlphaComposite.SRC_OVER;
        AlphaComposite ac = AlphaComposite.getInstance(rule, alpha);
        g2.setComposite(ac);
        g2.drawImage(src, null, 0, 0);
        g2.dispose();
        return dest;
    }

	@Override
	public Dimension getPreferredSize() {
	    if (image != null)
	    	return new Dimension(image.getWidth(), image.getHeight());
	    return super.getPreferredSize();
	}
	
	public void setGlowValue(float newValue)
	{
		this.alpha = newValue;
		repaint();
	}
	
	public void chargeGlow()
	{
		if (glowImage != null)
		{
			timeline.addPropertyToInterpolate("glowValue", this.alpha,
					1.0f);
			timeline.cancel();
			timeline.play();
		}
	}
	public void dischargeGlow()
	{
		if (glowImage != null)
		{
			timeline.addPropertyToInterpolate("glowValue", 0.0f,
					this.alpha);
			timeline.cancel();
			timeline.replayReverse();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		final Timeline _timeline = new Timeline(this);
		_timeline.addPropertyToInterpolate("glowValue", 1.0f,
				0.0f);
		_timeline.setDuration(200);
		_timeline.play();
		_timeline.playLoop(4, RepeatBehavior.REVERSE);
	}
}
