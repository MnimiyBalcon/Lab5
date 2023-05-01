package Commands;


/**
 * Cодержит метод, вызывающий команду remove_lower_key <br>
 * remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный
 */
public class Remove_lower_key extends Command {
    CommandManager commandManager = new CommandManager();
    int key;
    @Override
    public void setKey(int key) {
        this.key = key;
    }
    int id;
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void execute(){
        if (key != 0){
            commandManager.remove_lower_key(key);
        }
        else System.out.println("Ошибка! Проверьте правильность ввода.");
    }
    public String toString(){
        return " > remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный";
    }
}
