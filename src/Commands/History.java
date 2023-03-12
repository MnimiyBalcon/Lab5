package Commands;

/**
 * Cодержит метод с реализацией команды history <br>
 * history : вывести последние 6 команд (без их аргументов)
 */
public class History extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute(){
        commandManager.history();
    }
    public String toString(){
        return "history : вывести последние 6 команд (без их аргументов)";
    }
}
