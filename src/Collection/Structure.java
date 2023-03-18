package Collection;

import Commands.*;
import Programm.Main;
import java.util.Hashtable;

/**
 * Структура проекта <br>
 * Класс содержит информацию о порядке тегов и о командах.
 */
public class Structure {
    /**эталонный порядок тегов в файле*/
    public final static String[] order = new String[Main.CONST + 3];
    /**Отображение, содержащее объекты команд и их строковое представление в качестве ключа.*/
    public final static Hashtable<String, Command> commandList = new Hashtable<>();
    /**счётчик для истории команд*/
    public static int commandCounter;
    /**история команд*/
    public static String[] commandHistory = {null, null, null, null, null, null};
    static {
        order[0] = "HumanBeing";
        order[1] = "name";
        order[2] = "coordinates";
        order[3] = "x";
        order[4] = "y";
        order[5] = "creationDate";
        order[6] = "realHero";
        order[7] = "hasToothpick";
        order[8] = "impactSpeed";
        order[9] = "soundtrackName";
        order[10] = "minutesOfWaiting";
        order[11] = "mood";
        order[12] = "car";
        order[13] = "name";
        order[14] = "cool";
    }
    static {
        commandList.put("help",new Help());
        commandList.put("show",new Show());
        commandList.put("info", new Info());
        commandList.put("insert",new Insert());
        commandList.put("update",new Update());
        commandList.put("remove_key",new Remove_key());
        commandList.put("clear",new Clear());
        commandList.put("save",new Save());
        commandList.put("execute_script",new Execute_script());
        commandList.put("exit",new Exit());
        commandList.put("remove_greater",new Remove_greater());
        commandList.put("history",new History());
        commandList.put("remove_lower_key",new Remove_lower_key());
        commandList.put("filter_contains_name",new Filter_contains_name());
        commandList.put("print_unique_soundtrack_name",new Print_unique_soundtrack_name());
        commandList.put("print_field_descending_minutes_of_waiting",new Print_field_descending_minutes_of_waiting());
    }
}