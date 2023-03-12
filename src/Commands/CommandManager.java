package Commands;

import Collection.*;
import Programm.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Менеджер команд. Содержит информацию о работе всех команд и используется для их вызова.
 */

public class CommandManager {
     Hashtable<Integer, HumanBeing> collection = Main.collection; //здесь хранится коллекция
     private final int commandConst = 6;
     private HumanBeing buffer = new HumanBeing();
     private boolean updateFlag = false;
     public void help(){
        System.out.println("Справка по доступным командам:\n" +
                " > help : вывести справку по доступным командам\n" +
                " > info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                " > show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                " > insert null {element} : добавить новый элемент с заданным ключом\n" +
                "После вызова команды все новые поля объекта вводятся с новой строки в соответствии с получаемой инструкцией\n " +
                " > update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "После вызова команды все новые поля объекта вводятся с новой строки в соответствии с получаемой инструкцией\n " +
                " > remove_key null : удалить элемент из коллекции по его ключу\n" +
                " > clear : очистить коллекцию\n" +
                " > save {name}: сохранить коллекцию в файл\n" +
                " > execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                " > exit : завершить программу (без сохранения в файл)\n" +
                " > remove_greater {id} : удалить из коллекции все элементы, превышающие заданный\n" +
                " > history : вывести последние 6 команд (без их аргументов)\n" +
                " > remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n" +
                " > filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку\n" +
                " > print_unique_soundtrack_name : вывести уникальные значения поля soundtrackName всех элементов в коллекции\n" +
                " > print_field_descending_minutes_of_waiting : вывести значения поля minutesOfWaiting всех элементов в порядке убывания\n");

         Structure.commandHistory[Structure.commandCounter%commandConst] = "help";
         Structure.commandCounter++;
    }

    public void info(){
        collection = Main.collection;
                System.out.println("В коллекции типа HumanBeing имеется " + collection.size() + " объектов.");
                System.out.println("Дата создания коллекции: " + Main.timeStamp);
                Structure.commandHistory[Structure.commandCounter%commandConst] = "info";
                Structure.commandCounter++;
        }


        public void show() {
            collection = Main.collection;
            System.out.println("В коллекции содержатся следующие объекты:");
            for (Integer keys : Initialization.keyCounter){
                System.out.println(collection.get(keys).toString());
            }
            Structure.commandHistory[Structure.commandCounter%commandConst] = "show";
            Structure.commandCounter++;
        }
    void insert(int key) {
        collection = Main.collection;
        if (Initialization.keyCounter.add(key)){
            HumanBeing humanBeing = new HumanBeing();
            Coordinates coordinates = new Coordinates();
            Car car = new Car();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Для выхода из команды введите \"exit\"");
            String line="";
            for (int i = 0; i < Main.CONST-1; i++) {
                    if (line.equals("exit")) break;
                    switch (i) {
                        case 0: //name
                            System.out.println("Введите поле name");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                humanBeing.setName(line.trim());
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            }
                            break;
                        case 1: //Coordinates_x
                            System.out.println("Введите поле Coordinates_x");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                coordinates.setX(Float.parseFloat(line.trim()));
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            }
                                break;

                        case 2: //Coordinates_y
                            System.out.println("Введите поле Coordinates_y");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                coordinates.setY(Float.parseFloat(line));
                                humanBeing.setCoordinates(coordinates);
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            }
                            break;
                        case 3: //realHero
                            System.out.println("Введите поле realHero");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                if (line.equals("1") || line.toLowerCase().equals("true")) {   //realHero
                                    humanBeing.setRealHero(true);
                                } else if (line.equals("0") || line.toLowerCase().equals("false")) {
                                    humanBeing.setRealHero(false);
                                }
                                else if (line.trim().equals("") || line.trim().equals(" ")){
                                }
                                else throw new IllegalArgumentException();
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            } break;
                        case 4: //hasToothpick
                            System.out.println("Введите поле hasToothpick");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                if (line.equals("1") || line.toLowerCase().equals("true")) {   //realHero
                                    humanBeing.setHasToothpick(true);
                                } else if (line.equals("0") || line.toLowerCase().equals("false")) {
                                    humanBeing.setHasToothpick(false);
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            } break;
                        case 5: //impactSpeed
                            System.out.println("Введите поле impactSpeed");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                humanBeing.setImpactSpeed(Double.parseDouble(line.trim()));
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            } break;
                        case 6: //soundtrackName
                            System.out.println("Введите поле soundtrackName");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                humanBeing.setSoundtrackName(line.trim());
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            } break;
                        case 7: //minutesOfWaiting
                            System.out.println("Введите поле minutesOfWaiting");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                if (!(line.trim().equals("") || line.trim().equals(" ") || line.trim().equals("null")))
                                humanBeing.setMinutesOfWaiting(Long.parseLong(line.trim()));
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            }
                            break;
                        case 8: //mood
                            System.out.println("Введите поле mood. Для ввода доступны:\\n -SORROW\\n -CALM\\n -LONGING\\n -RAGE\\n");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                Mood mood1 = null;                                              //mood
                                boolean flag=false;
                                switch (line.toUpperCase()) {
                                    case "LONGING":
                                        mood1 = Mood.LONGING;
                                        flag=true;
                                    case "SORROW":
                                        mood1 = Mood.SORROW;
                                        flag=true;
                                        break;
                                    case "CALM":
                                        mood1 = Mood.CALM;
                                        flag=true;
                                        break;
                                    case "RAGE":
                                        mood1 = Mood.RAGE;
                                        flag=true;
                                        break;
                                }
                                if (flag){
                                    humanBeing.setMood(mood1);
                                }
                                else if (line.toUpperCase().trim().equals(" ") || line.toUpperCase().equals("")){}
                                else {
                                    throw new IllegalArgumentException();
                                }

                            } catch (IllegalArgumentException e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            } break;
                        case 9: //car_name
                            System.out.println("Введите поле car_name");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                car.setName(line.trim());
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            }break;
                        case 10: //car_cool
                            System.out.println("Введите поле car_cool");
                            line = scanner.nextLine();
                            if (line.equals("exit")) break;
                            try {
                                if (line.equals("1") || line.toLowerCase().equals("true")) {   //realHero
                                    car.setCool(true);
                                } else if (line.equals("0") || line.toLowerCase().equals("false")) {
                                    car.setCool(false);
                                } else {
                                    throw new IllegalArgumentException();
                                }
                                humanBeing.setCar(car);
                            } catch (Exception e) {
                                System.out.println("Некорректно введены данные.");
                                i--;
                            } break;
                    }
                }

            try {
                humanBeing.setCreationDate(ZonedDateTime.now()); //дата создания коллекции
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

            if (line.equals("exit")) {
                System.out.println("Выполнение команды было прервано");
                if (updateFlag){
                    updateFlag = false;
                    collection.put(key, buffer);
                    Initialization.idCounter.add(collection.get(key).getId());
                }
                else Initialization.keyCounter.remove(key);
            }
            else {
                int id=1;
                while (!Initialization.idCounter.add(id)) { //генерация уникального id
                    id++;
                }
                humanBeing.setId(id);
                collection.put(key, humanBeing);
            }
        }
        else System.out.println("Этот ключ уже занят другим элементом");
        Structure.commandHistory[Structure.commandCounter%commandConst] = "insert";
        Structure.commandCounter++;
    }

    public void update(int id){
        collection = Main.collection;
        int key = 0;
        for (Integer keys: Initialization.keyCounter){
            if (collection.get(keys).getId() == id){
                key = keys;
            }
        }
        if (key!=0){
            updateFlag = true;
            buffer = collection.get(key);
            Initialization.idCounter.remove(collection.get(key).getId());
            collection.remove(key);
            Initialization.keyCounter.remove(key);
            insert(key);
        }
        else System.out.println("В коллекции нет элемента с указанным id");
        Structure.commandHistory[Structure.commandCounter%commandConst] = "update";
        Structure.commandCounter++;
    }

    public void remove_key(int key){
        collection = Main.collection;
        try{
            Initialization.idCounter.remove(collection.get(key).getId());
            collection.remove(key);
            Initialization.keyCounter.remove(key);
            Structure.commandHistory[Structure.commandCounter%commandConst] = "remove_key";
            Structure.commandCounter++;
        }
        catch (NullPointerException e) {
            System.out.println("В коллекции нет элемента с таким ключом");
        }
    }
    public void clear(){
        collection = Main.collection;
         List<Integer> keys = new ArrayList<>();
        for (Integer key : Initialization.keyCounter){
            Initialization.idCounter.remove(collection.get(key).getId());
            collection.remove(key);
            keys.add(key);
        }
        for (Integer key:
             keys) {
            Initialization.keyCounter.remove(key);
        }
        Structure.commandHistory[Structure.commandCounter%commandConst] = "clear";
        Structure.commandCounter++;
    }
    public void exit(){
        collection = Main.collection;
        Structure.commandHistory[Structure.commandCounter%commandConst] = "exit";
        Structure.commandCounter++;
        System.exit(0);
    }
    public void remove_greater(int ID){
        List<Integer> keys = new ArrayList<>();
        collection = Main.collection;
        for (Integer currentKey: Initialization.keyCounter){
            if (Main.collection.get(currentKey).getId() > ID){
                Initialization.idCounter.remove(collection.get(currentKey).getId());
                Main.collection.remove(currentKey);
                keys.add(currentKey);
            }
        }
        for (Integer key:
                keys) {
            Initialization.keyCounter.remove(key);
        }
        Structure.commandHistory[Structure.commandCounter%commandConst] = "remove_greater";
        Structure.commandCounter++;
    }
    public void history(){
        System.out.println("Список последних 6 команд: ");
        for (int i=0; i<6; i++){
            System.out.println(Structure.commandHistory[i]);
        }
        Structure.commandHistory[Structure.commandCounter%commandConst] = "history";
        Structure.commandCounter++;
    }
    void remove_lower_key(int key){
        collection = Main.collection;
        List<Integer> keys = new ArrayList<>();
        for (Integer currentKey: Initialization.keyCounter){
            if (currentKey<key){
                Initialization.idCounter.remove(collection.get(currentKey).getId());
                collection.remove(currentKey);
                keys.add(currentKey);
            }
        }
        for (Integer key1:
                keys) {
            Initialization.keyCounter.remove(key1);
        }
        Structure.commandHistory[Structure.commandCounter%commandConst] = "remove_lower_key";
        Structure.commandCounter++;
    }
    void filter_contains_name(String name){
        collection = Main.collection;
        for (Integer keys: Initialization.keyCounter){
            if (collection.get(keys).getName().equals(name)){
                System.out.println(collection.get(keys));
            }
        }
        Structure.commandHistory[Structure.commandCounter%commandConst] = "filter_contains_name";
        Structure.commandCounter++;
    }
    void print_unique_soundtrack_name(){
        collection = Main.collection;
        TreeSet<String> uniqueSoundtrackNames = new TreeSet<>();
        System.out.println("uniqueSoundtrackNames: ");
        for (Integer keys: Initialization.keyCounter){
            if (uniqueSoundtrackNames.add(collection.get(keys).getSoundtrackName())){
                System.out.println(collection.get(keys).getSoundtrackName());
            }
        }
        Structure.commandHistory[Structure.commandCounter%commandConst] = "print_unique_soundtrack_name";
        Structure.commandCounter++;
    }
    public void print_field_descending_minutes_of_waiting(){
        collection = Main.collection;
        System.out.println("Descending minutes of waiting: ");
        SortedSet<Long> waitings = new TreeSet<>();
        for (Integer keys: Initialization.keyCounter){
            try{
                waitings.add(collection.get(keys).getMinutesOfWaiting());
            }
            catch (NullPointerException e){}
        }
            System.out.println(waitings);
        Structure.commandHistory[Structure.commandCounter%commandConst] = "print_field_descending_minutes_of_waiting";
        Structure.commandCounter++;
    }
    public void save(String filePath) throws IOException, ParserConfigurationException {
        collection = Main.collection;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element[] tags = new Element[Main.CONST+3];
            Text[] text_content = new Text[Main.CONST];
            Element HumanBeingCatalog = document.createElement("HumanBeingCatalog");

            document.appendChild(HumanBeingCatalog);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            for (Integer key : Initialization.keyCounter) {
                for (int i = 0; i < Main.CONST+3; i++) {
                    tags[i] = document.createElement(Structure.order[i]);
                }


                tags[2].appendChild(tags[3]);
                tags[2].appendChild(tags[4]);
                tags[12].appendChild(tags[13]);
                tags[12].appendChild(tags[14]);

                for (int i = 0; i < Main.CONST+3; i++) {
                    if (!(i==0 || i==3 || i==4 || i==13 || i==14))
                    tags[0].appendChild(tags[i]);
                }

                text_content[0] = document.createTextNode(collection.get(key).getName());
                tags[1].appendChild(text_content[0]);
                text_content[1] = document.createTextNode(String.valueOf(collection.get(key).getCoordinates().getX()));
                tags[3].appendChild(text_content[1]);
                text_content[2] = document.createTextNode(String.valueOf(collection.get(key).getCoordinates().getY()));
                tags[4].appendChild(text_content[2]);
                text_content[3] = document.createTextNode(String.valueOf(collection.get(key).getCreationDate()));
                tags[5].appendChild(text_content[3]);
                try{
                    text_content[4] = document.createTextNode(String.valueOf(collection.get(key).isRealHero()));
                }
                catch (NullPointerException e){
                    text_content[4] = document.createTextNode(" ");
                }
                tags[6].appendChild(text_content[4]);
                text_content[5] = document.createTextNode(String.valueOf(collection.get(key).getHasToothpick()));
                tags[7].appendChild(text_content[5]);
                text_content[6] = document.createTextNode(String.valueOf(collection.get(key).getImpactSpeed()));
                tags[8].appendChild(text_content[6]);
                text_content[7] = document.createTextNode(collection.get(key).getSoundtrackName());
                tags[9].appendChild(text_content[7]);
                try{
                    text_content[8] = document.createTextNode(String.valueOf(collection.get(key).getMinutesOfWaiting()));
                }
                catch (NullPointerException e){
                    text_content[8] = document.createTextNode(" ");
                }
                tags[10].appendChild(text_content[8]);
                try{
                    text_content[9] = document.createTextNode(String.valueOf(collection.get(key).getMood()));
                    if (String.valueOf(collection.get(key).getMood()).equals ("null")){
                        text_content[9] = document.createTextNode(" ");
                    }
                }
                catch (NullPointerException e){
                    text_content[9] = document.createTextNode(" ");
                }
                tags[11].appendChild(text_content[9]);
                text_content[10] = document.createTextNode(collection.get(key).getCar().getName());
                tags[13].appendChild(text_content[10]);
                text_content[11] = document.createTextNode(String.valueOf(collection.get(key).getCar().getCool()));
                tags[14].appendChild(text_content[11]);

                HumanBeingCatalog.appendChild(tags[0]);

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(document), new StreamResult(new BufferedWriter(new FileWriter(filePath))));
            }

        } catch (IOException | ParserConfigurationException | TransformerException e){
            e.printStackTrace();
        }
        Structure.commandHistory[Structure.commandCounter%commandConst] = "save";
        Structure.commandCounter++;
    }

    public void execute_script(String filePath) {
        collection = Main.collection;
            try {
                File file = new File(filePath);
                Scanner sc = new Scanner(file);
                Path path = Paths.get(file.toString());
                if (!(Files.isReadable(path))) {
                    throw new SecurityException();
                }
                while (sc.hasNext()) {
                    String[] str = sc.nextLine().trim().split(" ");
                    try {
                        if (str.length == 1){
                            Structure.commandList.get(str[0]).execute();
                        }
                        else if(str.length>1){
                            try{
                                Structure.commandList.get(str[0]).setId(Integer.parseInt(str[1]));
                                Structure.commandList.get(str[0]).setKey(Integer.parseInt(str[1]));
                            }
                            catch (NumberFormatException e0){
                                Structure.commandList.get(str[0]).setName(str[1]);
                            }
                            Structure.commandList.get(str[0]).execute();
                        }
                        else System.out.println("Команда введена неверно или такой команды не существует, попробуйте еще раз.");

                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Команда введена неверно или такой команды не существует, попробуйте еще раз.");
                    } catch (NumberFormatException e1) {
                        System.out.println("Некорректно введены числовые данные. Попробуйте еще раз.");
                    }
                }
                Structure.commandHistory[Structure.commandCounter%commandConst] = "execute_script";
                Structure.commandCounter++;
            } catch (FileNotFoundException | NullPointerException e) {
                System.out.println("Файл не найден!");
            }
            catch (SecurityException e1){
                System.out.println("Ошибка прав доступа!");
            }

    }
}