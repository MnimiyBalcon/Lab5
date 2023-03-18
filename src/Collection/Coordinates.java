package Collection;

/**
 * Класс необходим, чтобы задавать поле coordinates объектов collection.HumanBeing.
 * Представляет собой декартовые координаты объекта.
 * @see HumanBeing
 */
public class Coordinates {
    /** Координата x объекта. Максимальное значение поля: 546*/
    private float x;
    /** Координата y объекта. Максимальное значение поля: 287, Поле не может быть null*/
    private Float y;

    public float getX() {
        return x;
    }

    public void setX(float x) throws NullPointerException{
        if (x<=546){
            this.x = x;
        }
        else{
            throw new  NullPointerException();
        }
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) throws NullPointerException{
        if (y<=287){
            this.y = y;
        }
        else {
            throw new  NullPointerException();
        }
    }
}