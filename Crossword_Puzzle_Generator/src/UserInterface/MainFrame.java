package UserInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.pushingpixels.trident.Timeline;

public class MainFrame extends JFrame 
{
	Timer timer = new Timer();
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
	        final Button button = new Button(getImage("resources/images/defter.png",195,70),getImage("resources/images/defter_glow.png",195,70));
	        add(button);
	        button.setBounds(130, 420, 195, 70);	
	        
	        
	        button.addActionListener(new ActionListener()
	        {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stuff
				System.out.println("button clicked");
				
				timer.cancel(); //this will cancel the current task. if there is no active task, nothing happens
			    timer = new Timer();

			    TimerTask action = new TimerTask() {
			        public void run() {
			            boardFrame.setVisible(true);
			            System.out.println("timertask worked");
			        }

			    };

			    timer.schedule(action, 1000); //this starts the task
			}
	        });
	        button.addMouseListener(new MouseAdapter() {
	        	 @Override
				public void mouseExited(MouseEvent e) {
					super.mouseExited(e);
					deglowButton(button);
				}

				@Override
	             public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
	                glowButton(button);
	             }
			});
	        
	    }
	    
	    public static BufferedImage getImage(String imageLocation,int width,int height)
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
	    
	    private void glowButton(Button button)
	    {
	    	button.chargeGlow();
	    }
	    private void deglowButton(Button button)
	    {
	    	button.dischargeGlow();
	    }
}
