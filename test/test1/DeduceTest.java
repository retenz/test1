/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.ByteArrayOutputStream;
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
public class DeduceTest {

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public DeduceTest() {
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
    public void testMainIdealInput() { //нет ошибок в вводе текста исходного файла (идеальный ввод)

        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainIdealOption.txt"};
        System.setOut(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("aa, ss, a, s, d, u, v, ggg, M", outContent.toString());
    }

    @Test
    public void testMainFirstStrErr() { //ошибка в исходном файле в первой строке

        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainFirstStrErr.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Строка №1 не соответствует формату строки с выражением!\n",
                outContent.toString());
    }

    @Test
    public void testMainNoStrSep() { //нет строки разделитель

        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainNoStrSep.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Строка №6 не соответствует формату строки с выражением!\n",
                outContent.toString());
    }

    @Test
    public void testMainNoStrExFacts() { //нет строки с известными фактами

        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainNoStrExFacts.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Ошибка в формате входных данных.\n"
                + "Файл состоит из логических выражений.\n"
                + "Далее идёт строка-разделитель, которая состоит из 64 '-'.\n"
                + "Последняя строка - строка с известными фактами.\n",
                outContent.toString());
    }

    @Test
    public void testMainStrSepErr() { //ошибка в строке разделитель

        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainStrSepErr.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Строка №6 не соответствует формату строки с выражением!\n",
                outContent.toString());
    }

    @Test
    public void testMainExFactsErr() { //ошибка в строке с известными данными

        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainExFactsErr.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("В исходном файле отсутствует строка с известными фактами или не соответствует её формату!\n"
                + "Строка с известными фактами должна стоять сразу после строки-разделитель и\n"
                + "быть последней строкой в файле.\n",
                outContent.toString());
    }

    @Test
    public void testMainFileNotFound() { //файла с таким именем не существует

        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\NoFile"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Файла с таким именем не найдено!\n"
                + "Проверьте верность введённого пути.\n"
                + "(C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\NoFile (The system cannot find the file specified))\n"
                + "Пример верного ввода: java -jar test1.jar C:\\..\\fileName.txt\n"
                + "Где C:\\..\\fileName.txt - путь до файла с исходными данными.\n",
                outContent.toString());
    }

    @Test
    public void testMainNoArgs() { //массив параметров пуст

        String[] args = {};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Вы не указали путь до файла с исходными данными.\n"
                + "Пример верного ввода: java -jar test1.jar C:\\..\\fileName.txt\n"
                + "Где C:\\..\\fileName.txt - путь до файла с исходными данными.\n",
                outContent.toString());
    }

    @Test
    public void testMainWithNoErr() { //нет ошибок в вводе текста исходного файла (проверка " ", "_"  и цифр)

        System.out.println("testMainWithNoErr");
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainWithNoErr.txt"};
        System.setOut(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("aa, a, s, _d, u, v, ggg, AA_54, M, _ss", outContent.toString());
    }

    @Test
    public void testMainNoRuleResult() {  //нет результата выражения

        System.out.println("testMainWithNoErr");
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainNoRuleResult.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Строка №1 не соответствует формату строки с выражением!\n", outContent.toString());
    }

    @Test
    public void testMainNoExpression() { //нет самого выражения

        System.out.println("testMainWithNoErr");
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainNoRuleResult.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Строка №1 не соответствует формату строки с выражением!\n", outContent.toString());
    }

    @Test
    public void testMainLogicOperationErr() { //Две логические операции идут подряд

        System.out.println("testMainWithNoErr");
        String[] args = {"C:\\Users\\Robert.Tenadze\\Desktop\\example\\test1\\test\\src\\testMainLogicOperationErr.txt"};
        System.setErr(new PrintStream(outContent));
        Deduce.main(args);
        assertEquals("Строка №1 не соответствует формату строки с выражением!\n", outContent.toString());
    }
}
