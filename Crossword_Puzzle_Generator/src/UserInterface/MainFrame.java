package UserInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.pushingpixels.trident.Timeline;

public class MainFrame extends JFrame 
{
	public BoardFrame boardFrame;
	
	    public MainFrame()  {

	        initUI();
	        boardFrame = new BoardFrame();
	    }

	    private void initUI()  {
	        setLayout(null);
	        setTitle("Simple example");
	        setSize(960, 540);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        //setExtendedState(JFrame.MAXIMIZED_BOTH);
	        setUndecorated(true);
	        BufferedImage backgroundImage = getImage("resources/images/bg.png", 960,540);
	        this.setContentPane(new ImagePanel(backgroundImage));
	        Button button = new Button(getImage("resources/images/defter.png",256,192));
	        add(button);
	        button.setBounds(100, 360, 256, 192);
	        
	        button.addActionListener(new ActionListener()
	        {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button clicked");
				
				boardFrame.setVisible(true);
			}
	        });
	        
	    }
	    
	    private BufferedImage getImage(String imageLocation,int width,int height)
	    {
	    	BufferedImage image = null;
	        try {
	        	 image = ImageIO.read(new File(imageLocation));
			} catch (Exception e) {
				e.printStackTrace();
			}
	        int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
	        
	        BufferedImage resizeImage = resizeImage(image, type,width,height);
	        return resizeImage;
	    }
	    
	    private static BufferedImage resizeImage(BufferedImage originalImage, int type,int IMG_WIDTH,int IMG_HEIGHT){
	    	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	    	Graphics2D g = resizedImage.createGraphics();
	    	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	    	g.dispose();
	     
	    	return resizedImage;
	        }
}
