package Commands;

/**
 * Cодержит в себе метод, вызывающий команду clear <br>
 * clear : очистить коллекцию
 */
public class Clear extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute(){
        commandManager.clear();
    }
    public String toString(){
        return " > clear : очистить коллекцию";
    }
}