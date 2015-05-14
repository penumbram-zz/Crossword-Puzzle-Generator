package UserInterface;

import Generator.Board;
import Utility.Singleton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicArrowButton;

public class BoardOptionPanel extends JPanel 
{
	BoardOption previous;
	BoardOption current;
	BoardOption next;
	int lastBoardIndex = 0;
	boolean rightBlocked = false;
	boolean leftBlocked = false;
	public BoardOptionPanel()
	{
		SpringLayout springLayout = new SpringLayout();
		setPreferredSize(new Dimension(800,400));
		setBackground(Color.YELLOW);
		setLayout(springLayout);
		
		
		
		previous = new BoardOption(Singleton.getInstance().boards.get(0),new Dimension(150,150));
		current = new BoardOption(Singleton.getInstance().boards.get(1),new Dimension(200,200));
		next = new BoardOption(Singleton.getInstance().boards.get(2), new Dimension(150,150));
		lastBoardIndex = 2;
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
		JButton jbutton = new JButton("Select");
		add(jbutton);
		add(left);
		add(right);
		
		springLayout.putConstraint(SpringLayout.WEST, left, 0, SpringLayout.WEST, current);
		springLayout.putConstraint(SpringLayout.NORTH, left, 30, SpringLayout.SOUTH, current);
		springLayout.putConstraint(SpringLayout.EAST, right, 0, SpringLayout.EAST, current);
		springLayout.putConstraint(SpringLayout.NORTH, right, 30, SpringLayout.SOUTH, current);
		springLayout.putConstraint(SpringLayout.NORTH, jbutton, 20, SpringLayout.SOUTH, current);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbutton, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go Left");
				go(false);
			}
		});
		right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Go Right");
				go(true);
			}
		});
		
		jbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("select board: ");
				Board.printBoard(current.getBoardCells());
			}
		});
	}


	public void go(boolean right)
	{
		if (leftBlocked && !right)
			return;
		if (rightBlocked && right)
			return;
		
		if (right)
			lastBoardIndex++;
		else
			lastBoardIndex--;
		
		if (lastBoardIndex-2 >= 0 && lastBoardIndex -2 < Singleton.getInstance().boards.size())
		{
			previous.setBoardCells(Singleton.getInstance().boards.get(lastBoardIndex-2));
			previous.setVisible(true);
			leftBlocked = false;
		}
		else
		{
			previous.setVisible(false);
			leftBlocked = true;
		}
		if (lastBoardIndex-1 >= 0 && lastBoardIndex-1 < Singleton.getInstance().boards.size())
		{
			current.setBoardCells(Singleton.getInstance().boards.get(lastBoardIndex-1));
		}
		
		if (lastBoardIndex < Singleton.getInstance().boards.size())
		{
			next.setBoardCells(Singleton.getInstance().boards.get(lastBoardIndex));
			next.setVisible(true);
			rightBlocked = false;
		}
		else
		{
			rightBlocked = true;
			next.setVisible(false);
		}
	}
}
