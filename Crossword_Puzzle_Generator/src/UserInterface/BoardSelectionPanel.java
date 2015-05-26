package UserInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SpringLayout;

import Utility.FileUtils;
import Utility.Utils;

public class BoardSelectionPanel extends FadingPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public void setVisibility(boolean b)
	{
		if (b)
		{
			try {
				FileUtils.readFile();
				boardOptionPanel = new BoardOptionPanel();
				setVisible(true);
				Utils.logg("herereere2");
			/*	Timeline fadeInTimeline = new Timeline(this);
			      fadeInTimeline.addPropertyToInterpolate("Fadev", 0.0f,
							1.0f);
			      fadeInTimeline.setDuration(2000);
			      fadeInTimeline.play();*/
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			setVisible(false);
		/*	Timeline fadeInTimeline = new Timeline(this);
			fadeInTimeline.addPropertyToInterpolate("Fadev", 1.0f,
						0.0f);
			fadeInTimeline.setDuration(10);
			fadeInTimeline.play();
			TimerTask tt = new TimerTask() {
				
				@Override
				public void run() {
					Utils.logg("herereere");
					setVisible(false);
				}
			};
		      timer.schedule(tt, 2000);*/
		}
	}
}
