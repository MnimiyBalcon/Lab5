package Programm;

import Collection.*;
import Commands.*;

import Commands.CommandManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Programm.Main-класс.
 * Осуществляет считывание комманд с клавиатуры пользователя и обрабатывает их.
 * @autor Смирнов Василий "va_smirnov@infochemistry.ru"
 * @version 1.0
 */

public class Main {
    /**
     * Точка входа в программу.
     * Класс занимается чтением из консоли и файла
     * @param args имя XML файла, в котором содержатся элементы коллекции.
     */
    public static String filePath;
    public static String timeStamp;
    public static List<String[]> list; //здесь находятся обработанные, но ещё не проверенные данные из файла
    public static Hashtable<Integer, HumanBeing> collection = new Hashtable<>(); //здесь хранится коллекция

    //константа для парсинга ( = кол-во полей, требующихся для заполнения одного объекта)
    public static final int CONST = 12;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Scanner fileReader = null;
        File file = null;
        String test = null;
        Scanner pathReader = new Scanner(System.in);
        String line;

        try {
            filePath = args[0];
            try {
                file = new File(filePath);
                fileReader = new Scanner(file);
                test = fileReader.nextLine();
                //System.out.println(test);
                Path path = Paths.get(file.toString());
                if (!(Files.isReadable(path))) {
                    System.out.println("Ошибка прав доступа!");
                    System.exit(0); //!!!
                }
            } catch (FileNotFoundException | NullPointerException e) {
                System.out.println("Файл не найден!");
                System.exit(0); //!!!
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введите путь к файлу с исходными данными или нажмите Enter, чтобы использовать файл" +
                    " по умолчанию.");

            while (pathReader.hasNextLine()) { //цикл по нахождению файла с коллекциями. Файл найден => break;
                filePath = pathReader.nextLine(); // user вручную вбивает путь к файлу
                
                try {
                    file = new File(filePath);
                    fileReader = new Scanner(file);
                    test = fileReader.nextLine();
                    //System.out.println(test);
                    Path path = Paths.get(file.toString()); //тк. метод Files.isReadable() принимает только Path (не ii <-> String)
                    if (!(Files.isReadable(path))) {
                        System.out.println("Ошибка прав доступа!\nВведите еще раз или нажмите Enter, чтобы использовать" +
                                "файл по умолчанию.");
                    } else {
                        break;
                    }
                } catch (FileNotFoundException | NullPointerException e2) {
                    System.out.println("Файл не найден.\nВведите еще раз или нажмите Enter" +
                            ", чтобы использовать файл по умолчанию.");
                } catch (NoSuchElementException e0) {
                    System.out.println("Файл пуст!");
                    break;
                }
            }

        }

        //здесь начинается чтение файла
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(filePath);

        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();
        //первая проверка
        ElementsReader elementsReader = new ElementsReader();
        elementsReader.printElements(nodeList);
        //конец первой проверки

        list = elementsReader.getList(); //получаем обработанные данные
        //проверка требований к полям объектов
        collection = Initialization.initi(list); //получение искомой коллекции из файла
        //конец проверки

        timeStamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime()); //дата создания коллекции
        CommandManager commandManager = new CommandManager();

/**интерактивный режим*/

        System.out.println("Интерактивный режим включён. Введите \"help\" для получения информации по командам");
        while (pathReader.hasNextLine()) {
            String[] str = pathReader.nextLine().trim().split(" ");
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

            }
            /*catch (Exception e){
                e.printStackTrace();
            }*/
            catch (NullPointerException e) {
                System.out.println("Ошибка! Проверьте правильность ввода.");
            } catch (NumberFormatException e1) {
                System.out.println("Некорректно введены числовые данные. Попробуйте еще раз.");
            }
            finally {
                System.out.print("Введите команду: ");
            }
        }
        pathReader.close();
    }
}
