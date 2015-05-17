package CommandPattern;

public class Invoker 
{
	private Command command;
	
	public void setCommand(Command command)
	{
		this.command = command;
	}
	
	public void trigger()
	{
		command.execute();
	}
}
