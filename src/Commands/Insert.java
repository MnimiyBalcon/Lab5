package Commands;

/**
 * Cодержит метод с реализацией команды insert <br>
 * insert null {element} : добавить новый элемент с заданным ключом
 */
public class Insert extends Command {
    CommandManager commandManager = new CommandManager();
    int key;
    public void setKey(int key) {
        this.key = key;
    }
    int id;
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void execute(){
        commandManager.insert(key);
    }
    public String toString(){
        return " > insert null {element} : добавить новый элемент с заданным ключом\n" +
        "После вызова команды все поля объекта вводятся с новой строки в соответствии с получаемой инструкцией";
    }
}
