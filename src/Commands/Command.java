package Commands;
/**
 * Является родительским классом для всех команд
 */
public abstract class Command {
     int key;
     public void setKey(int key) {
          this.key = key;
     }
     int id;
     public void setId(int id) {
          this.id = id;
     }
     String name;
     public void setName(String name) {
          this.name = name;
     }
     public abstract void execute();
}
