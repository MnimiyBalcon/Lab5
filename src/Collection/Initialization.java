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

                if (list.get(i)[0].equals("") ||
                    list.get(i)[1].equals("") ||
                    list.get(i)[2].equals("") ||
                    list.get(i)[3].equals("") ||
                    list.get(i)[5].equals("") ||
                    list.get(i)[6].equals("") ||
                    list.get(i)[7].equals("") ||
                    list.get(i)[10].equals("") ||
                    list.get(i)[11].equals("")){
                    throw new NullPointerException();
                }

                humanBeing.setName(list.get(i)[0]);                 //name

                try {
                    coordinates.setX(Float.parseFloat(list.get(i)[1])); //x
                    coordinates.setY(Float.parseFloat(list.get(i)[2])); //y
                } catch (NullPointerException e) {
                    System.out.println("Ошибка! Максимальное значение поля: 546");
                }
                try {
                    coordinates.setY(Float.parseFloat(list.get(i)[2])); //y
                }
                catch (NullPointerException e) {
                    System.out.println("Ошибка! Максимальное значение поля: 287");
                }
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
                if (!list.get(i)[8].equals("") && !list.get(i)[8].trim().equals(" "))
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
                if (!idCounter.add(id)){
                    id = idCounter.last() + 1;
                    idCounter.add(id);
                }
                   /* while (!idCounter.add(id)) { //генерация уникального id
                        id++;
                    }*/
                if (!keyCounter.add(key)){
                    key = keyCounter.last() + 1;
                    keyCounter.add(key);
                }
                    /*while (!keyCounter.add(key)){ //генерация уникального key
                        key++;
                    }*/

                humanBeing.setId(id); //id

                collection.put(key, humanBeing); //добавляем проверенный объект в коллекцию
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Некорректно введены данные. Поля не могут быть не заданы.");
            }
            catch (DateTimeParseException e0) {
                System.out.println("Некорректно указана дата. Пример ввода даты: 2023-03-12T16:15:15.348471500+03:00[Europe/Moscow]");
            } catch (EnumConstantNotPresentException e1){
                System.out.println("Некорректно введены данные. Поле Collection.Mood может принимать только значения:\n SADNESS \n LONGING \n RAGE \nCALM");
            } catch (NumberFormatException e2) {
                System.out.println("Некорректно введены данные. Проверьте числовые данные.");
            } catch (IllegalArgumentException e3) {
                System.out.println("Некорректно введены данные. Проверьте корректность логических переменных.");
            } catch (NullPointerException e4) {
                System.out.println("Некорректно введены данные. Поля не могут быть не заданы.");
            }

        }
        return collection;
    }
}
