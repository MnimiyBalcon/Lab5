package Commands;

public class Abc extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute(){
        commandManager.abc();
    }
}
