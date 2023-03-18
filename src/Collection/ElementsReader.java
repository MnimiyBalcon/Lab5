package Collection;
import Programm.Main;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Содержит метод, осуществляющий проверку структуры файла (например порядок или количество тегов)
 */

public class ElementsReader {

    final int Const = Main.CONST;

    private int e=Const;                                //для счёта элементов массива stringList
    private List<String[]> list = new ArrayList<>(); //список массивов, который в итоге отправляется на проверку
    private String[] tagList = new String[Const+3];       //список тегов для проверки на очерёдность
    private int tagCounter = 0;                      //для счёта элементов массива tagList
    private String[] stringList = new String[Const];    //массив значений (будущих полей коллекций)
    private boolean nobrain = true;                          //самокритика
    private boolean objectIsClosed = false;
    private int HB;                        //для локализации ошибок в файле

    /** Метод осуществляет проверку структуры файла и записывает полученные поля в список.
     * @param nodeList получается в процессе парсинга xml файла при помощи DOM, содержит в себе всю информацию об элементах коллекции.
     */
    public void printElements(NodeList nodeList){

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                //System.out.println(((Element) nodeList.item(i)).getTagName());

                tagList[tagCounter%(Const+3)] = ((Element) nodeList.item(i)).getTagName();
                tagCounter++;

                if (((Element) nodeList.item(i)).getTagName().equals("cool")&&e==(Const-1)){
                    objectIsClosed = true;
                }

                if (((Element) nodeList.item(i)).getTagName().equals("HumanBeing") && e==Const){
                    if(nobrain){
                        e=0;
                        nobrain = false;
                        HB++;
                        objectIsClosed = false;
                    }
                    else {
                        if (tagInspector(tagList)){ //проверка последовательности тегов
                            list.add(stringList);
                        }
                        else System.out.println("Ошибка в последовательности элементов в объекте (№ " + HB + ") \n Объект не был добавлен в коллекцию");
                        e=0;
                        stringList = new String[Const]; //очистка массива
                        HB++;
                        objectIsClosed=false;
                    }

                }
                else{
                    //здесь обрабатываются ошибки, связанные с количеством тегов в каждом HumanBeing
                    if (!((Element) nodeList.item(i)).getTagName().equals("HumanBeing") && e==Const || !(e==Const) && ((Element) nodeList.item(i)).getTagName().equals("HumanBeing")){
                        if (!(e==Const) && ((Element) nodeList.item(i)).getTagName().equals("HumanBeing")){
                            i--;
                        }
                        if (objectIsClosed){//исключение для ситуаций с лишними тегами уровня HumanBeing
                            tagList[(tagCounter-1)%(Const+3)] = "HumanBeing";
                            if(tagInspector(tagList)){
                                list.add(stringList);
                            }
                            System.out.println("Ошибка в количестве элементов в объекте (№ null) \n Объект не был добавлен в коллекцию");
                        }
                        else System.out.println("Ошибка в количестве элементов в объекте (№ " + HB + ") \n Объект не был добавлен в коллекцию");
                        e=Const;
                        nobrain = true;
                        tagCounter = 0;
                        stringList = new String[Const];
                        objectIsClosed=false;
                        continue;
                    }
                }

                if(nodeList.item(i).getTextContent().trim().equals("") || !(nodeList.item(i).getFirstChild().getTextContent().trim().equals(""))){
                    if (!((Element) nodeList.item(i)).getTagName().equals("coordinates") && !((Element) nodeList.item(i)).getTagName().equals("car")){
                        stringList[e] = nodeList.item(i).getTextContent().trim();
                        //System.out.println("value = " + stringList[e] + " (e = " + e + ")");
                        e++;
                    }
                }

                if (nodeList.item(i).hasChildNodes()){
                    printElements(nodeList.item(i).getChildNodes());
                }
            }
        }
    }

    /**
     * Вспомогательный метод, проверяющий последовательность тегов в документе.
     * @param absList - массив, содержащий информацию о тегах конкретного элемента коллекции.
     * @return возвращает true, если порядок совпадает с эталонным, иначе - false.
     */

    private boolean tagInspector(String[] absList){ //проверка последовательности тегов (метод)
        for (int i = 0; i < Const+3; i++) {
            if (absList[i] != Structure.order[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * @return Возвращает прошедшие первый этап проверки массивы полей элементов коллекции
     */
    public List<String[]> getList() {
    if (objectIsClosed){
    if (tagInspector(tagList)){ //проверка последовательности тегов
        list.add(stringList);
    }
    else System.out.println("Ошибка в последовательности элементов в объекте (№ " + HB + ") \n Объект не был добавлен в коллекцию");
}
        return list;
    }
}
