package Commands;

/**
 * Cодержит в себе метод, реализующий команду print_field_descending_minutes_of_waiting.
 * print_field_descending_minutes_of_waiting : вывести значения поля minutesOfWaiting всех элементов в порядке убывания
 */
public class Print_field_descending_minutes_of_waiting extends Command {
    CommandManager commandManager = new CommandManager();
    @Override
    public void execute() {
        commandManager.print_field_descending_minutes_of_waiting();
    }
    public String toString(){
        return " > print_field_descending_minutes_of_waiting : вывести значения поля minutesOfWaiting всех элементов в порядке убывания";
    }
}