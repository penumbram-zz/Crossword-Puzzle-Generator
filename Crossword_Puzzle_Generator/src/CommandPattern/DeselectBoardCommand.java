package CommandPattern;

import UserInterface.BoardOption;

public class DeselectBoardCommand implements Command
{
	
	BoardOption boardOption;
	
	public DeselectBoardCommand(BoardOption boardOption)
	{
		this.boardOption = boardOption;
	}

	@Override
	public void execute() 
	{
		boardOption.deselect();
	}

}
