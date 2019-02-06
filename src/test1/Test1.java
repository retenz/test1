/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import test1.exception.FaultInFileException;
import test1.exception.FileIsEmptyException;

/**
 *
 * @author Robert.Tenadze
 */
public class Test1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        HashSet<String> allFacts;
        
        if (args.length == 0) {
            System.out.print("Вы не указали путь до файла с исходными данными.\n"
                    + "Пример верного ввода: java -jar test1.jar C:\\..\\fileName.txt\n"
                    + "Где C:\\..\\fileName.txt - путь до файла с исходными данными\n");
        } else {
            
            try {
                FindFacts fact = new FindFacts(args[0]);
                allFacts=fact.giveAllFacts();
                System.out.print(allFacts.toString().replace("[", "").replace("]", ""));
            } catch (FileNotFoundException ex) {
                System.out.print("Файла с таким именем не найдено!\n"
                    + "Проверьте верность введённого пути.\n"
                    + "Пример верного ввода: java -jar test1.jar C:\\..\\fileName.txt\n"
                    + "Где C:\\..\\fileName.txt - путь до файла с исходными данными\n");
           
            } catch (FileIsEmptyException | FaultInFileException ex) {
                System.out.print(ex.getMessage());
            } catch (IOException ex) {
                System.out.print("Проблема с чтением файла!");
            } 
        } 

//        FindFacts fact=new FindFacts("C:\\Users\\Robert.Tenadze\\Desktop\\txt.txt");
//        fact.giveAllFacts();
//        System.out.println("Ошибки в следующих строках:\n" +
//                "Строка №1\n" +
//                "Ошибка:Данная строка не соответствует параметрам строки с выражением!");
//    }
    }
}
