package UserInterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.scene.control.Label;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.RepeatBehavior;
import org.pushingpixels.trident.Timeline.TimelineState;
import org.pushingpixels.trident.callback.TimelineCallback;

import Utility.Utils;

public class Button extends JButton implements ActionListener,MouseListener
{
	BufferedImage image;
	BufferedImage glowImage;
	JLabel label;
	SpringLayout springLayout;
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
	        addMouseListener(this);
	        springLayout = new SpringLayout();
	        setLayout(springLayout);
	        timeline = new Timeline(this);
			timeline.addPropertyToInterpolate("glowValue", 0.0f,
					1.0f);
			timeline.setDuration(500);
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
	
	public void setText(String text)
	{
		if (label != null)
			remove(label);
		label = new JLabel(text);
		label.setFont(new Font("Helvetica", Font.BOLD, label.getFont().getSize()));
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, label.getWidth(), label.getHeight());
		add(label);
		if (springLayout != null)
		{
			springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, this);
			springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, label, 0, SpringLayout.VERTICAL_CENTER, this);
		}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		chargeGlow();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		dischargeGlow();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
