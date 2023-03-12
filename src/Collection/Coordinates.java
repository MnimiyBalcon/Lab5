package Collection;

/**
 * Класс необходим, чтобы задавать поле coordinates объектов collection.HumanBeing.
 * Представляет собой декартовые координаты объекта.
 * @see HumanBeing
 */
public class Coordinates {
    private float x; //Максимальное значение поля: 546
    private Float y; //Максимальное значение поля: 287, Поле не может быть null

    public float getX() {
        return x;
    }

    public void setX(float x) throws NumberFormatException{
        if (x<=546){
            this.x = x;
        }
        else{
            System.out.println("Ошибка! Значение поля не может быть больше 546!");
            throw new  NumberFormatException();
        }
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) throws NumberFormatException{
        if (y<=287){
            this.y = y;
        }
        else {
            System.out.println("Ошибка! Значение поля не может быть больше 287!");
            throw new  NumberFormatException();
        }
    }
}