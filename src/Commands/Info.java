package Commands;

/**
 * Cодержит метод с реализацией команды info <br>
 * info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class Info extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute(){
        commandManager.info();
    }
    public String toString(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
