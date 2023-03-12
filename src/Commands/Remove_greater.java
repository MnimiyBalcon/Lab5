package Commands;
/**
 * Cодержит метод с реализацией команды remove_greater <br>
 * remove_greater {id} : удалить из коллекции все элементы, превышающие заданный
 */
public class Remove_greater extends Command {
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
        commandManager.remove_greater(id);
    }
    public String toString(){
        return "remove_greater {id} : удалить из коллекции все элементы, превышающие заданный";
    }
}
