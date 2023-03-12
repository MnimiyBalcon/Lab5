package Commands;

/**
 * Cодержит метод с реализацией команды print_unique_soundtrack_name <br>
 * print_unique_soundtrack_name : вывести уникальные значения поля soundtrackName всех элементов в коллекции
 */
public class Print_unique_soundtrack_name extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute(){
        commandManager.print_unique_soundtrack_name();
    }
    public String toString(){
        return " > print_unique_soundtrack_name : вывести уникальные значения поля soundtrackName всех элементов в коллекции";
    }
}

