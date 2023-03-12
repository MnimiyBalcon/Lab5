package Commands;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Cодержит в себе метод, реализующий команду save.
 * save : сохранить коллекцию в файл
 */
public class Save extends Command {
    CommandManager commandManager = new CommandManager();
    String name;
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void execute() {
        try{
            commandManager.save(name);
        } catch (IOException | ParserConfigurationException e) {
            System.out.println("Файл не найден. Попробуйте ещё раз.");
        }
    }
    public String toString(){
        return "> save : сохранить коллекцию в файл";
    }
}