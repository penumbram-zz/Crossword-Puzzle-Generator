package UserInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Generator.Board;
import Utility.Singleton;

public class BoardSelectionPanel extends JPanel 
{
	public BoardSelectionPanel()
	{
		setSize(920, 500);
		setBackground(Color.RED);
		setBounds(20, 20, 920, 500);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		
		
		BoardOptionPanel boardOptionPanel = new BoardOptionPanel();
		add(boardOptionPanel);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boardOptionPanel,
                0,
                SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, boardOptionPanel,
                0,
                SpringLayout.VERTICAL_CENTER, this);
		
	}
}
