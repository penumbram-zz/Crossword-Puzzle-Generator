package UserInterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Button extends JButton 
{
	BufferedImage image;

	public Button(BufferedImage anImage) {
	        image = anImage;
	        setOpaque(false);
	        setContentAreaFilled(false);
	        setFocusPainted(false);
	        setBorderPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image, 0, 0, null);
	}

	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(image.getWidth(), image.getHeight());
	}
}
