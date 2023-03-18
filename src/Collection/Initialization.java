package Collection;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;

/**
 * Класс-инициализатор содержимого XML-файла.
 */

public class Initialization {//здесь происходит парсинг элементов массивов из list в поля HumanBeing


    static Hashtable<Integer, HumanBeing> collection = new Hashtable<>();
    /** Хранилище уникальных значений ID объектов.*/
    public static TreeSet<Integer> idCounter = new TreeSet<>(); //чтобы обеспечить уникальность id
    /** Хранилище уникальных значений key объектов.*/
    public static TreeSet<Integer> keyCounter = new TreeSet<>();
    static int id = 1;
    static int key = 1;
    /*static {
        idCounter.add(0);
    }*/


    static void printList(List<String[]> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println("List № " + (i+1) + ": ");
            for (int j = 0; j < 12; j++) {
                System.out.println("Element № " + j + " = " + list.get(i)[j]);
            }
        }
    }
    /**
     *
     * @param list Массив строковых данных, которые будут проверены на корректность и приведены к необходимому типу.
     * @return Возвращает коллекцию, элементы которой прошли проверку полностью.
     */
    public static Hashtable<Integer,HumanBeing> initi(List<String[]> list){
        for (int i = 0; i < list.size(); i++) {
            HumanBeing humanBeing = new HumanBeing();
            Coordinates coordinates = new Coordinates();
            Car car = new Car();
            try {
                    System.out.println("PARSING OBJECT № " + (i+1) + "... ");

                if (list.get(i)[0].equals("null") || list.get(i)[0].trim().equals("") || //быстрая проверка на валидность полей
                    list.get(i)[1].equals("null") || list.get(i)[1].trim().equals("") ||
                    list.get(i)[2].equals("null") || list.get(i)[2].trim().equals("") ||
                    list.get(i)[3].equals("null") || list.get(i)[3].trim().equals("") ||
                    list.get(i)[5].equals("null") || list.get(i)[5].trim().equals("") ||
                    list.get(i)[6].equals("null") || list.get(i)[6].trim().equals("") ||
                    list.get(i)[7].equals("null") || list.get(i)[7].trim().equals("") ||
                    list.get(i)[10].equals("null") || list.get(i)[10].trim().equals("") ||
                    list.get(i)[11].equals("null") || list.get(i)[11].trim().equals("")){
                    throw new NullPointerException();
                }

                humanBeing.setName(list.get(i)[0]);                 //name

                coordinates.setX(Float.parseFloat(list.get(i)[1])); //x

                coordinates.setY(Float.parseFloat(list.get(i)[2])); //y

                humanBeing.setCoordinates(coordinates);             //coordinates

                humanBeing.setCreationDate(ZonedDateTime.parse(list.get(i)[3])); //creationDate


                if (list.get(i)[4].equals("1") || list.get(i)[4].toLowerCase().equals("true")) {   //realHero
                    humanBeing.setRealHero(true);
                } else if (list.get(i)[4].equals("0") || list.get(i)[4].toLowerCase().equals("false")) {
                    humanBeing.setRealHero(false);
                }
                else if (list.get(i)[4].equals("") || list.get(i)[4].trim().equals(" ")){
                } else {
                    throw new IllegalArgumentException();
                }

                if (list.get(i)[5].equals("1") || list.get(i)[5].toLowerCase().equals("true")) {   //hasToothpick
                    humanBeing.setHasToothpick(true);
                } else if (list.get(i)[5].equals("0") || list.get(i)[5].toLowerCase().equals("false")) {
                    humanBeing.setHasToothpick(false);
                } else {
                    throw new IllegalArgumentException();
                }

                humanBeing.setImpactSpeed(Double.parseDouble(list.get(i)[6])); //impactSpeed

                humanBeing.setSoundtrackName(list.get(i)[7]);                 //soundtrackName
                if (!list.get(i)[8].trim().equals("") && !list.get(i)[8].trim().equals("null"))
                humanBeing.setMinutesOfWaiting(Long.parseLong(list.get(i)[8])); //minutesOfWaiting

                Mood mood1 = null;                                              //mood
                switch (list.get(i)[9].toUpperCase()) {
                    case "LONGING":
                        mood1 = Mood.LONGING;
                    case "SORROW":
                        mood1 = Mood.SORROW;
                        break;
                    case "CALM":
                        mood1 = Mood.CALM;
                        break;
                    case "RAGE":
                        mood1 = Mood.RAGE;
                        break;
                    default:
                        if (list.get(i)[9].equals("") || list.get(i)[9].trim().equals(" ")){
                    }
                        else if (!(list.get(i)[9].equals("null"))) {
                            throw new EnumConstantNotPresentException(null, null);
                        }
                }
                humanBeing.setMood(mood1); //mood

                car.setName(list.get(i)[10]); //car_name


                if (list.get(i)[11].equals("1") || list.get(i)[11].toLowerCase().equals("true")) {   //car_cool
                    car.setCool(true);
                } else if (list.get(i)[11].equals("0") || list.get(i)[11].toLowerCase().equals("false")) {
                    car.setCool(false);
                } else {
                    System.out.println(list.get(i)[11]);
                    throw new IllegalArgumentException();
                }

                humanBeing.setCar(car);     //car

                    while (!idCounter.add(id)) { //генерация уникального id
                        id++;
                    }
                    while (!keyCounter.add(key)){ //генерация уникального key
                        key++;
                    }

                humanBeing.setId(id); //id

                collection.put(key, humanBeing); //добавляем проверенный объект в коллекцию
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Некорректно введены данные. Поля не могут быть не заданы или быть null.");
            }
            catch (DateTimeParseException e0) {
                System.out.println("Некорректно указана дата. Пример ввода даты: 2023-03-12T16:15:15.348471500+03:00[Europe/Moscow]");
            } catch (EnumConstantNotPresentException e1){
                System.out.println("Некорректно введены данные. Поле Collection.Mood может принимать только значения:\n -SADNESS \n LONGING \nRAGE \n-null");
            } catch (NumberFormatException e2) {
                System.out.println("Некорректно введены данные. Проверьте числовые данные.");
            } catch (IllegalArgumentException e3) {
                System.out.println("Некорректно введены данные. Проверьте корректность логических переменных.");
            } catch (NullPointerException e4) {
                System.out.println("Некорректно введены данные. Поля не могут быть null.\nЧтобы узнать подробную информацию" +
                        " про ограничения на значения полей введите команду: help -a.");
            }

        }
        return collection;
    }
}

/*
0    private String name; //Поле не может быть null, Строка не может быть пустой
1,2    private Collection.Coordinates coordinates; //Поле не может быть null
3    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
4    private Boolean realHero;
5    private Boolean hasToothpick; //Поле не может быть null
6    private Double impactSpeed; //Максимальное значение поля: 995
7    private String soundtrackName; //Поле не может быть null
8   private Long minutesOfWaiting;
9    private Collection.Mood mood; //Поле может быть null
10,11   private Collection.Car car; //Поле может быть null (car) // Поле не может быть null (11,12)
*/
