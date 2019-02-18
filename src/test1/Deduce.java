/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Robert.Tenadze
 */
public class Deduce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.print("Вы не указали путь до файла с исходными данными.\n");
            printUsage();
            return;
        }
        Parser parser = new Parser();
        try {
            Model model = parser.parse(args[0]);
            model.calculate();
            model.deduceFacts();
        } catch (FileNotFoundException ex) {
            System.err.print("Файла с таким именем не найдено!\n"
                    + "Проверьте верность введённого пути.\n"
                    + "(" + ex.getMessage() + ")\n");
            printUsage();
        } catch (IOException ex) {
            System.err.print("Проблема с чтением файла!\n"
                    + "(" + ex.getMessage() + ")\n");
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void printUsage() {
        System.err.print("Пример верного ввода: java -jar test1.jar C:\\..\\fileName.txt\n"
                + "Где C:\\..\\fileName.txt - путь до файла с исходными данными.\n");
    }

}
