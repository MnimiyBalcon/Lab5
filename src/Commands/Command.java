package Commands;
/**
 * Является абстрактным классом, от которого наследуются все команды.
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
