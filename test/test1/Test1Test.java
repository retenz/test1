/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robert.Tenadze
 */
public class Test1Test {
    
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public Test1Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Test1.
     */
    @Test
    public void testMainIdealOption()  { //нет ошибок в вводе текста исходного файла (идеальный ввод)
        
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainIdealOption.txt"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("aa, ss, a, s, d, u, ggg, M", outContent.toString());
    }
    
    @Test
    public void testMainFirstStrErr()  { //ошибка в исходном файле в первой строке
       
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainFirstStrErr.txt"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("Строка №1 не соответствует параметрам строки с выражением!\n", 
                outContent.toString());
    }
    
    @Test
    public void testMainNoStrSep() { //нет строки разделитель
       
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainNoStrSep.txt"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("В исходном файле отсутсвует строка-разделитель или она не соответствует её параметрам!\n" +
                "Строка-разделитель это предпоследняя строка в файле, и она должна стоять перед строкой с исходными данными\n" +
                "и состоять из 64 символов '-'.\n", 
                outContent.toString());
    }
    
    @Test
    public void testMainNoStrExFacts() { //нет строки с известными фактами
       
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainNoStrExFacts.txt"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("В исходном файле отсутсвует строка-разделитель или она не соответствует её параметрам!\n" +
                "Строка-разделитель это предпоследняя строка в файле, и она должна стоять перед строкой с исходными данными\n" +
                "и состоять из 64 символов '-'.\n", 
                outContent.toString());
    }
    
    @Test
    public void testMainStrSepErr() { //ошибка в строке разделитель
       
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainStrSepErr.txt"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("В исходном файле отсутсвует строка-разделитель или она не соответствует её параметрам!\n" +
                "Строка-разделитель это предпоследняя строка в файле, и она должна стоять перед строкой с исходными данными\n" +
                "и состоять из 64 символов '-'.\n", 
                outContent.toString());
    }
  
    @Test
    public void testMainExFactsErr() { //ошибка в строке с известными данными
       
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainExFactsErr.txt"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("В исходном файле отсутствует строка с известными фактами или не соответствует её параметрам!\n" +
                "Строка с известными фактами должна стоять сразу после строки-разделитель и\n" +
                "быть последней строкой в файле.\n", 
                outContent.toString());
    }
    
    @Test
    public void testMainFileNotFound() { //файла с таким именем не существует
       
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\NoFile"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("Файла с таким именем не найдено!\n" +
                "Проверьте верность введённого пути.\n" +
                "Пример верного ввода: java -jar test1.jar C:\\..\\fileName.txt\n" +
                "Где C:\\..\\fileName.txt - путь до файла с исходными данными\n", 
                outContent.toString());
    }
    
    @Test
    public void testMainNoArgs() { //массив параметров пуст
       
        String[] args = {};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("Вы не указали путь до файла с исходными данными.\n" +
                "Пример верного ввода: java -jar test1.jar C:\\..\\fileName.txt\n" +
                "Где C:\\..\\fileName.txt - путь до файла с исходными данными\n", 
                outContent.toString());
    }
    
    @Test
    public void testMainWithNoErr()  { //нет ошибок в вводе текста исходного файла (проверка " ", "_"  и цифр
        
        System.out.println("testMainWithNoErr");
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainWithNoErr.txt"};
        System.setOut(new PrintStream(outContent));
        Test1.main(args);
        assertEquals("aa, a, s, _d, u, ggg, AA_54, M, _ss", outContent.toString());
    }
}

