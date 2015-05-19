package UserInterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Generator.Board;
import Observer.AnimationObserver;
import Utility.FileUtils;
import Utility.Singleton;
import Utility.Utils;

public class BoardSelectionPanel extends FadingPanel
{
	public BoardOptionPanel boardOptionPanel;

	public BoardSelectionPanel()
	{
		setSize(920, 500);
		
		setBounds(20, 20, 920, 500);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setOpaque(false);
		
		boardOptionPanel = new BoardOptionPanel();
		add(boardOptionPanel);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boardOptionPanel,
                0,
                SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, boardOptionPanel,
                0,
                SpringLayout.VERTICAL_CENTER, this);
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		// TODO Auto-generated method stub
		super.setVisible(aFlag);
		if (aFlag)
		{
			try {
				FileUtils.readFile();
				boardOptionPanel = new BoardOptionPanel();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
