package UserInterface;

import CommandPattern.Command;
import CommandPattern.Invoker;
import CommandPattern.SelectBoardCommand;
import Utility.Singleton;
import Utility.Utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicArrowButton;

public class BoardOptionPanel extends FadingPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Invoker invoker = new Invoker();
	Image bgImage = Utils.getImage("resources/images/tahta_BG.png", 800, 400);
	public BoardOption previous;
	public BoardOption current;
	public BoardOption next;
	public BoardSelectorModel selector;
	Button jbutton;
	
	public BoardOptionPanel()
	{
		SpringLayout springLayout = new SpringLayout();
		setPreferredSize(new Dimension(800,400));
		setLayout(springLayout);
		
		selector = new BoardSelectorModel();
		
		previous = new BoardOption(Singleton.getInstance().boards.get(0),new Dimension(150,150),0);
		selector.registerObserver(previous);
		current = new BoardOption(Singleton.getInstance().boards.get(1),new Dimension(200,200),1);
		selector.registerObserver(current);
		next = new BoardOption(Singleton.getInstance().boards.get(2), new Dimension(150,150),2);
		selector.registerObserver(next);
		
		add(previous);
		add(current);
		add(next);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, current,
                0,
                SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, current,
                0,
                SpringLayout.VERTICAL_CENTER, this);
		
		springLayout.putConstraint(SpringLayout.WEST, next,
                40,
                SpringLayout.EAST, current);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, next,
                0,
                SpringLayout.VERTICAL_CENTER, this);
		
		springLayout.putConstraint(SpringLayout.EAST, previous,
                -40,
                SpringLayout.WEST, current);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, previous,
                0,
                SpringLayout.VERTICAL_CENTER, this);
		
		BasicArrowButton left = new BasicArrowButton(BasicArrowButton.WEST);
		BasicArrowButton right = new BasicArrowButton(BasicArrowButton.EAST);
		jbutton = new Button(Utils.getImage("resources/images/buttons.png",161,90),Utils.getImage("resources/images/buttons_glow.png",161,90));
		jbutton.setText("SELECT");
		add(jbutton);
		add(left);
		add(right);
		
		springLayout.putConstraint(SpringLayout.WEST, left, 0, SpringLayout.WEST, current);
		springLayout.putConstraint(SpringLayout.NORTH, left, 30, SpringLayout.SOUTH, current);
		springLayout.putConstraint(SpringLayout.EAST, right, 0, SpringLayout.EAST, current);
		springLayout.putConstraint(SpringLayout.NORTH, right, 30, SpringLayout.SOUTH, current);
		springLayout.putConstraint(SpringLayout.NORTH, jbutton, 10, SpringLayout.SOUTH, current);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbutton, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go Left");
				selector.go(false);
			}
		});
		right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Go Right");
				selector.go(true);
			}
		});
		
		final Command selectBoard = new SelectBoardCommand(current);
		jbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("select board: ");
				invoker.setCommand(selectBoard);
				invoker.trigger();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
	    super.paintComponent(g);
	    g.drawImage(bgImage, 0, 0, null);
	}
}
