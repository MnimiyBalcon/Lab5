package Commands;

/**
 * Cодержит метод с реализацией команды exit <br>
 * exit : завершить программу (без сохранения в файл)
 */
public class Exit extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute(){
        commandManager.exit();
    }
    public String toString(){
        return "exit : завершить программу (без сохранения в файл)";
    }
}
