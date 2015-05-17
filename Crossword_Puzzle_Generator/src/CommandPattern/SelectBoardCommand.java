package CommandPattern;

import UserInterface.BoardOption;


public class SelectBoardCommand implements Command {
	
	BoardOption boardOption;
	
	public SelectBoardCommand(BoardOption boardOption)
	{
		this.boardOption = boardOption;
	}
	
	@Override
	public void execute() 
	{
		boardOption.select();
	}

}
