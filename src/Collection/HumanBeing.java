package Collection;

import java.time.ZonedDateTime;
/**
 * Класс всех создаваемых объектов коллекции.
 */
public class HumanBeing {
    /** Уникальное поле, имеющееся у каждого объекта. Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля генерируется автоматически*/
    private int id;
    /**Поле не может быть null, Строка не может быть пустой*/
    private String name;
    /**Поле не может быть null*/
    private Coordinates coordinates;
    /**Поле не может быть null, Значение этого поля должно генерироваться автоматически*/
    private java.time.ZonedDateTime creationDate;
    private boolean realHero;
    /**Поле не может быть null*/
    private Boolean hasToothpick;
    /**Максимальное значение поля: 995*/
    private double impactSpeed;
    /**Поле не может быть null*/
    private String soundtrackName;
    private long minutesOfWaiting;
    /**Поле может быть null*/
    private Mood mood;
    /**Поле может быть null*/
    private Car car;

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + "(" + coordinates.getX() + ";" + coordinates.getY() + ")" +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", soundtrackName='" + soundtrackName + '\'' +
                ", minutesOfWaiting=" + minutesOfWaiting +
                ", mood=" + mood +
                ", car=(" + car.getName() + ", " + car.getCool() + ")" +
                '}';
    }

    public void setId(Integer id) throws NumberFormatException {
        if (id < 0) throw new NumberFormatException();
        this.id = id;
    }

    public void setName(String name) throws NullPointerException{
        if (name.equals("null") || name.trim().equals("") || name.trim().equals(" ")) throw new NullPointerException();
        this.name = name;
    }


    public void setCoordinates(Coordinates coordinates) throws NullPointerException{
        if (coordinates == null) throw new NullPointerException();
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) throws NullPointerException{
        if (creationDate == null) throw new NullPointerException();
        this.creationDate = creationDate;
    }

    public void setRealHero(Boolean realHero) {
        //if (realHero == null) throw new NullPointerException();
        this.realHero = realHero;
    }

    public void setHasToothpick(Boolean hasToothpick) throws NullPointerException{
        if (hasToothpick == null) throw new NullPointerException();
        this.hasToothpick = hasToothpick;
    }


    public void setImpactSpeed(Double impactSpeed) throws NumberFormatException{
        if (impactSpeed > 995) throw new NumberFormatException();
        this.impactSpeed = impactSpeed;
    }

    public void setSoundtrackName(String soundtrackName) throws NullPointerException{
        if (soundtrackName == null || soundtrackName.trim().equals(" ") || soundtrackName.equals("")){
            throw new NullPointerException();
        }
        this.soundtrackName = soundtrackName;
    }


    public void setMinutesOfWaiting(Long minutesOfWaiting) {
        //if (minutesOfWaiting == null) throw new NullPointerException();
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public void setMood(Mood mood) throws NullPointerException{
        this.mood = mood;
    }


    public void setCar(Car car) throws NullPointerException{
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public boolean isRealHero() {
        return realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }
    public double getImpactSpeed() {
        return impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }
    public long getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public Mood getMood() {
        return mood;
    }
    public Car getCar() {
        return car;
    }
}