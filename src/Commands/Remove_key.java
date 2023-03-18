package Commands;
/**
 * Cодержит метод, вызывающий команду remove_key <br>
 * remove_key null : удалить элемент из коллекции по его ключу
 */
public class Remove_key extends Command {
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
            commandManager.remove_key(key);
        }
        else System.out.println("Ошибка! Проверьте правильность ввода.");
    }
    public String toString(){
        return " > remove_key null : удалить элемент из коллекции по его ключу";
    }
}