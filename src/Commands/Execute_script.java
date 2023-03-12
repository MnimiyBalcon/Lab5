package Commands;

/**
 * Cодержит в себе метод, реализующий команду execute_script.
 * execute_script file_name : считать и исполнить скрипт из указанного файла
 */
public class Execute_script extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void setName(String name) {
        this.name = name;
    }
    public void execute(){
        commandManager.execute_script(name);
    }
    public String toString(){
        return "> execute_script file_name : считать и исполнить скрипт из указанного файла.\n" +
                " В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }
}