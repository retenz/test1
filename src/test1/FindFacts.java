/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import test1.exception.FaultInFileException;
import test1.exception.FileIsEmptyException;

/**
 *
 * @author Robert.Tenadze
 */
public class FindFacts {
    
    private final ArrayList<String> allFile= new ArrayList<>();
    private final ArrayList<String> leftSide= new ArrayList<>();
    private final ArrayList<String> rightSide= new ArrayList<>();
    private final HashSet<String> exFacts= new HashSet<>();
    private String filename;
    
    public FindFacts(String filename) throws IOException, FileNotFoundException, FileIsEmptyException, FaultInFileException {
        this.filename = filename;
        checkAllFile(filename);
    }
    
    
    public HashSet<String> giveAllFacts(){
        int sizeOfEx=0;
        
        createTables();
        while(sizeOfEx!=exFacts.size()){
            sizeOfEx=exFacts.size();
            for (int i=0;i<leftSide.size();i++){
                if(isSolve(leftSide.get(i)))
                exFacts.add(rightSide.get(i).trim());
            }
        }
        return exFacts;
    }
    
    
    private boolean isSolve(String text){ //решает левую часть уравнений
        
        boolean result;
        Pattern patWord= Pattern.compile("(_*[A-Za-z]+(_*\\w+)*)");
        Pattern patSymbol= Pattern.compile("(&&|\\|\\|)+");
        
        Matcher matWord= patWord.matcher(text);
        Matcher matSymbol= patSymbol.matcher(text);
        
        LinkedList<String> word= new LinkedList<>();
        LinkedList<String> symbol= new LinkedList<>();
        
        while (matSymbol.find())
            symbol.add(matSymbol.group());
        while (matWord.find())
            word.add(matWord.group());
        
        result= exFacts.contains(word.get(0)); 
        for (int i=1; i<word.size(); i++) {
            if (symbol.get(i-1).equals("&&"))
                result= result&&exFacts.contains(word.get(i));
            else
                result= result||exFacts.contains(word.get(i));
        }
        return result;
    } 
    
    
    private void checkAllFile(String fileName) throws FileNotFoundException, IOException, FileIsEmptyException, FaultInFileException { // проверяет весь файл на верность заполнения
        
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            
            if(!reader.ready())
                throw new FileIsEmptyException("Файл с исходными даннными пуст!");
            else{
                while ((line=reader.readLine())!=null){
                    allFile.add(line);            
                }
            }
        }
        if (!Pattern.matches("-{64}",allFile.get(allFile.size()-2))) {// проверка строки разделитель
            throw new FaultInFileException("В исходном файле отсутсвует строка-разделитель или она не соответствует её параметрам!\n" +
                    "Строка-разделитель это предпоследняя строка в файле, и она должна стоять перед строкой с исходными данными\n" +
                    "и состоять из 64 символов '-'.\n");
        }
        if(!Pattern.matches("^\\s*(_*[A-Za-z]+(_*\\w+)*)+?((\\s*,\\s*)_*[A-Za-z]+(_*\\w+)*)*\\s*$", allFile.get(allFile.size()-1))) { //проверка строки с исходгыми фактами
            throw new FaultInFileException( "В исходном файле отсутствует строка с известными фактами или не соответствует её параметрам!\n"+
                    "Строка с известными фактами должна стоять сразу после строки-разделитель и\n" +
                    "быть последней строкой в файле.\n");
        }
        
        for (int i=0; i<allFile.size()-2;i++){
            if(!Pattern.matches("^\\s*((_*[A-Za-z]+(_*\\w+)*)+?(\\s*(&&|\\|\\||->)\\s*))++(_*[A-Za-z]+(_*\\w+)*)*\\s*$", allFile.get(i))) //проверка строки с выражениями
                throw new FaultInFileException("Строка №"+(i+1)+" не соответствует параметрам строки с выражением!\n");
        }
    }
     
    
    
    private void createTables(){ // заполняет arraylis'ы leftSide, rightSide и сет exFacts
    
        String [] lines;
        Pattern pat= Pattern.compile("(_*[A-Za-z]+(_*\\w+)*)");
        Matcher mat= pat.matcher(allFile.get(allFile.size()-1));
        while (mat.find()){
            exFacts.add(mat.group());
        }
        
        for (int i=1; i<allFile.size()-2;i++){
            
                lines= allFile.get(i).split("->");
                leftSide.add(lines[0]);
                rightSide.add(lines[1]);
        }
    }
}