package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Spring;
import javax.swing.SpringLayout;

import Generator.Board;
import Generator.Dictionary;
import Observer.BoardObserver;
import Utility.Singleton;
import Utility.Utils;

public class BoardGeneratorPanel extends FadingPanel implements BoardObserver {
	SpringLayout springLayout;
	private BoardPanel bp;
	String[][] cells;
	Button buttonGenerate;
	Button buttonBack;
	Board board = null;
	boolean regenerate = false;
	
	public BoardGeneratorPanel(String[][] cells)
	{
		this.cells = cells;
		setSize(920, 500);
		setOpaque(false);
		setBounds(20, 20, 920, 500);
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		bp = new BoardPanel(400,400,cells.length,cells.length,100,100,50,50);
		
		bp.setBoard(cells);
		bp.setClickable(false);
		add(bp);
		bp.setVisible(true);
		setVisible(true);
		
		springLayout.putConstraint(SpringLayout.EAST, bp, 710, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, bp, 110, SpringLayout.NORTH, this);
		
		buttonGenerate = new Button(null);
		buttonGenerate.setText("Generate");
		add(buttonGenerate);
		buttonGenerate.setPreferredSize(new Dimension(100, 30));
		buttonGenerate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Utils.logg("Gonna generate board here");
				setGenerationParameters();
				generate();
			}
		});
		
		buttonBack = new Button(null);
		buttonBack.setText("Back");
		add(buttonBack);
		buttonBack.setPreferredSize(new Dimension(100, 30));
		buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Utils.logg("Back");
				goBack();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, buttonBack, 50, SpringLayout.SOUTH, buttonGenerate);
	}
	
	private void goBack()
	{
		Singleton.getInstance().boardFrame.setVisible(false);
		Singleton.getInstance().boardFrame.remove(this);
		Singleton.getInstance().boardFrame.add(Singleton.getInstance().boardFrame.boardSelectionPanel);
		Singleton.getInstance().boardFrame.boardSelectionPanel.setVisible(true);
	}
	
	private void setGenerationParameters()
	{
		board = new Board(cells.length,cells.length);
		Dictionary dict = new Dictionary();
		dict.fillDictionary();
		board.setCells(cells);
		board.saveCells(cells);
		board.registerObserver(this);
	}
	
	private void generate()
	{
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() 
			{
				board.searchLongestPaths();
				board.fillLongestWords();
				board.fillRestOfWords();
				fillEmptyCellsWithBlackCells();
				board.fillEmptyCellsWithBlackCells();
				Utils.logg("AFTER");
				board.printBoard(board.cells);
				ArrayList<int[]> validation = board.validate();
				if (validation == null)
					regenerate = false;
				else
				{
					String problem = "";
					for (int[] acell : validation)
					{
						problem += board.cells[acell[0]][acell[1]];	
					}
					Utils.logg("PROBLEM PROBLEM PROBLEM PROBLEM PROBLEM");
					Utils.logg(problem);
					board.ruledOutWords.add(problem);
					regenerate = true;
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
		if (regenerate)
		{
			board.clearAll();
			generate();
		}
	}
	
	private void fillEmptyCellsWithBlackCells()
	{
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				if (cells[i][j] == " ")
				{
					bp.tiles[i][j].setBackground(Color.BLACK);
					bp.tiles[i][j].invalidate();
					bp.tiles[i][j].repaint();
					Utils.logg("here1");
				}
			}
		}
	}
	
	@Override
	public void update(ArrayList<int[]> list, String word) {
		Utils.logg(word);
		for (int i = 0; i < list.size(); i++)
		{
			Utils.logg("[" + list.get(i)[0] + "][" + list.get(i)[1] + "]");
			bp.tiles[list.get(i)[0]][list.get(i)[1]].label.setText(String.valueOf(word.charAt(i)));
		}
		
	}
}
