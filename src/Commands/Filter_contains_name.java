package Commands;

/**
 * Cодержит метод, вызывающий команду filter_contains_name <br>
 * filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку
 */
public class Filter_contains_name extends Command {
    String name;
    CommandManager commandManager = new CommandManager();
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(){
        commandManager.filter_contains_name(name);
    }
    public String toString(){
        return " > filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}

