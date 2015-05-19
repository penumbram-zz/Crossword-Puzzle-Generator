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

import Utility.Utils;

public class MainFrame extends JFrame 
{
	Timer timer = new Timer();
	public BoardFrame boardFrame;
	
	    public MainFrame()  
	    {
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
	        BufferedImage backgroundImage = Utils.getImage("resources/images/bg.png", 960,540);
	        this.setContentPane(new ImagePanel(backgroundImage));
	        final Button button = new Button(Utils.getImage("resources/images/defter.png",195,70),Utils.getImage("resources/images/defter_glow.png",195,70));
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
			        	boardFrame.add(boardFrame.boardSelectionPanel);
			        	boardFrame.remove(boardFrame.boardEditorPanel);
			            boardFrame.setVisible(true);
			            boardFrame.boardSelectionPanel.setVisible(true);
			        }

			    };

			    timer.schedule(action, 1000); //this starts the task
			}
	        });
	       
	        
	        
	        
	        final Button editButton = new Button(Utils.getImage("resources/images/edit_button.png",235,217),Utils.getImage("resources/images/edit_button_glow.png",235,217));
	        add(editButton);
	        editButton.setBounds(0, 120, 235, 217);	
	        editButton.addActionListener(new ActionListener()
	        {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stuff
				System.out.println("button clicked");
				
				timer.cancel(); //this will cancel the current task. if there is no active task, nothing happens
			    timer = new Timer();

			    TimerTask action = new TimerTask() {
			        public void run() {
			        	boardFrame.remove(boardFrame.boardSelectionPanel);
			        	boardFrame.add(boardFrame.boardEditorPanel);
			            boardFrame.setVisible(true);
			            boardFrame.boardEditorPanel.editNewBoard();
			            boardFrame.boardEditorPanel.setVisible(true);
			            System.out.println("timertask worked");
			        }

			    };

			    timer.schedule(action, 1000); //this starts the task
			}
	        });     
	    }
	    
	    
	    

}
