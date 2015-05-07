package UserInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

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
		boardOptionPanel.setPreferredSize(new Dimension(200,200));
		add(boardOptionPanel);
		
		springLayout.putConstraint(SpringLayout.WEST, boardOptionPanel,
                50,
                SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, boardOptionPanel,
                50,
                SpringLayout.NORTH, this);
		
	}
}
