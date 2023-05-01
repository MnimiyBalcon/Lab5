package Collection;

/**
 * Объекты данного класса задают поле car объектов collection.HumanBeing.
 * @see HumanBeing
 */
public class Car {
    private String name; //Поле не может быть null
    private Boolean cool; //Поле может быть null

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
        if (!name.equals("")){
            this.name = name;
        }
        else throw new IllegalArgumentException();
    }

    public Boolean getCool() {
        return cool;
    }

    public void setCool(Boolean cool) {
        if (cool != null){
            this.cool = cool;
        }
        else System.out.println("Ошибка! Поле не может быть null");
    }
}