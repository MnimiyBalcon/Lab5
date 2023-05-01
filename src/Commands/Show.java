package Commands;

import Collection.*;
import Programm.*;
import java.util.Hashtable;

/**
 * Cодержит в себе метод, вызывающий команду show <br>
 * show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends Command {
    Hashtable<Integer, HumanBeing> collection = Main.collection; //здесь хранится коллекция
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute() {
        commandManager.show();
    }
    public String toString(){
        return " > show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении.\n " +
                "В качестве результата выводит поле name и id, соответствующие объекту";
    }
}