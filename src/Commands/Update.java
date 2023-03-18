package Commands;

/**
 * Cодержит в себе метод, вызывающий команду update <br>
 * update id {element} : обновить значение элемента коллекции, id которого равен заданному
 */
public class Update extends Command {
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
    public void execute() {
        if (id != 0){
            commandManager.update(id);
        }
        else System.out.println("Ошибка! Проверьте правильность ввода.");
    }
    public String toString(){
        return " > update id {element} : обновить значение элемента коллекции, id которого равен заданному\n " +
                "После вызова команды все новые поля объекта вводятся с новой строки в соответствии с получаемой инструкцией";
    }
}
