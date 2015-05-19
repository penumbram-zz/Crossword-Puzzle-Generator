package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utility.FileUtils;
import Utility.Singleton;
import Utility.Utils;
import Generator.Board;
import Generator.Main;

public class BoardEditorPanel extends JPanel 
{
	SpringLayout springLayout;
	private BoardPanel bp;
	BoardEditorPanel boardEditorPanel;
	Button buttonSave;
	JSpinner spinner;
	Button buttonGo;
	
	public BoardEditorPanel()
	{
		setSize(920, 500);
		setOpaque(false);
		setBounds(20, 20, 920, 500);
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		bp = new BoardPanel(400,400,5,5,100,100,0,0);
		String[][] cellz = Singleton.getInstance().boards.get(0);
		
		Board.printBoard(cellz);
		bp.setBoard(cellz);
		bp.setClickable(true);
		add(bp);
		bp.setVisible(true);
		setVisible(true);
		
		buttonSave = new Button(Utils.getImage("resources/images/buttons.png",161,90),Utils.getImage("resources/images/buttons_glow.png",161,90));
		buttonSave.setText("Save");
		add(buttonSave);
		buttonSave.setPreferredSize(new Dimension(100, 30));
		buttonSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] savetiles = new String[bp.tiles.length][bp.tiles.length];
				for (int i = 0; i < savetiles.length; i++) {
					for (int j = 0; j < savetiles.length; j++) {
						if (bp.tiles[i][j].getColor() == Color.WHITE)
							savetiles[i][j] = " ";
						else
							savetiles[i][j] = "X";
					}
				}
				saveBoardToFile(savetiles);
			}
		});
		
		buttonGo = new Button(Utils.getImage("resources/images/buttons.png",161,90),Utils.getImage("resources/images/buttons_glow.png",161,90));
		buttonGo.setText("Go");
		add(buttonGo);
		buttonGo.setPreferredSize(new Dimension(100, 30));
		buttonGo.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				String[][] finaltiles = new String[bp.tiles.length][bp.tiles.length];
				for (int i = 0; i < finaltiles.length; i++) {
					for (int j = 0; j < finaltiles.length; j++) {
						if (bp.tiles[i][j].getColor() == Color.WHITE)
							finaltiles[i][j] = " ";
						else
							finaltiles[i][j] = "X";
					}
				}
				Utils.logg("Go");
				Utils.logg("Go");
				Utils.logg("Go");
				Board board = new Board(finaltiles.length,finaltiles.length);
				board.cells = finaltiles;
				board.searchLongestPaths();
				for (ArrayList<int[]> path : board.allPaths) 
				{
					if (path.size() > 10)
					{
						JOptionPane.showMessageDialog(null, "Board contains at least 1 path that is longer than 10 letters");
						return;
					}
				}
				Board.printBoard(finaltiles);
				generator(finaltiles);
				
				//saveBoardToFile(savetiles);
			}
		});
		
		spinner = getSpinnerDemo();
		add(spinner);
		
	//	springLayout.putConstraint(SpringLayout.WEST, buttonSave, 100, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, buttonSave, 200, SpringLayout.VERTICAL_CENTER, this);
		setBoardPosition();
		springLayout.putConstraint(SpringLayout.WEST, spinner, 30, SpringLayout.EAST, buttonSave);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spinner, 0, SpringLayout.VERTICAL_CENTER, buttonSave);
		boardEditorPanel = this;
	}
	
	private void generator(String[][] cells)
	{
		setVisible(false);
		Singleton.getInstance().boardFrame.remove(this);
		Singleton.getInstance().boardFrame.add(new BoardGeneratorPanel(cells));
	}

	
	private void setBoardPosition()
	{
		springLayout.putConstraint(SpringLayout.EAST, bp, 710, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, bp, 95, SpringLayout.NORTH, this);
	}
	 private JSpinner getSpinnerDemo(){
	      SpinnerModel spinnerModel =
	         new SpinnerNumberModel(5, //initial value
	            0, //min
	            15, //max
	            1);//step
	      JSpinner spinner = new JSpinner(spinnerModel);
	      ((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
	      spinner.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	int val = Integer.valueOf(((JSpinner)e.getSource()).getValue().toString());
	        	changeBoard(val);
	         }
	      });
	      return spinner;
	   } 
	 
	 private void changeBoard(int val)
	 {
		remove(bp);
		System.out.println(val);
     	bp = new BoardPanel(400, 400, val, val, 40, 40, 50, 50);
     	Board b = new Board(val, val);
     	b.initBoard();
     	bp.setBoard(b.cells);
  		bp.setClickable(true);
  		add(bp);
  		setBoardPosition();
  		validate();
     	repaint();
     	bp.validate();
     	bp.repaint();
	 }
	 
	 public void editNewBoard()
	 {
		spinner.setVisible(true);
		buttonSave.setVisible(true);
		remove(bp);
     	bp = new BoardPanel(400, 400, 5, 5, 40, 40, 50, 50);
     	Board b = new Board(5, 5);
     	b.initBoard();
     	bp.setBoard(b.cells);
  		bp.setClickable(true);
  		add(bp);
  		setBoardPosition();
  		validate();
     	repaint();
     	bp.validate();
     	bp.repaint();
	 }
	 
	 public void editSelectedBoard(String[][] cells)
	 {
		spinner.setVisible(false);
		buttonSave.setVisible(false);
		remove(bp);
		int i = ((int)(400 / cells.length));
     	bp = new BoardPanel(400, 400, cells.length, cells.length, i, i, 0, 0);
     	Board b = new Board(cells.length, cells.length);
     	b.initBoard();
     	bp.setBoard(cells);
  		bp.setClickable(true);
  		add(bp);
  		setBoardPosition();
  		validate();
     	repaint();
     	bp.validate();
     	bp.repaint();
	 }
	 
	 private void saveBoardToFile(String[][] aBoard)
	 {
		 int newBoardNumber = -1;
		 	try {
				newBoardNumber = removeLineFromFile("resources/board templates/boards.txt", "0");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String boardString = Board.convertBoardToString(aBoard);
			if (newBoardNumber == -1)
				return;
			
			try {
				Writer output = new BufferedWriter(new FileWriter("resources/board templates/boards.txt", true));
				output.append(String.valueOf(newBoardNumber+1) + "\n");
				output.append(boardString);
				output.append("0");
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 public int removeLineFromFile(String file, String lineToRemove) {
		 int _return = -1;
		    try {
		 
		      File inFile = new File(file);
		      
		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return _return;
		      }
		       
		      //Construct the new file that will later be renamed to the original filename. 
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		      
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		      
		      String line = null;
		 
		      //Read from the original file and write to the new 
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {
		        
		        if (!line.trim().equals(lineToRemove)) 
		        {
		        	if (FileUtils.isNumeric(line))
		        	{
		        		_return = Integer.valueOf(line).intValue();
					}
		          pw.println(line);
		          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();
		      
		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return _return;
		      } 
		      
		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");
		      
		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		    return _return;
		  }
}
