package Commands;

/**
 * Cодержит метод, вызывающий команду history <br>
 * history : вывести последние 6 команд (без их аргументов)
 */
public class History extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute(){
        commandManager.history();
    }
    public String toString(){
        return " > history : вывести последние 6 команд (без их аргументов)";
    }
}
