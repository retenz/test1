/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.interfaces.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import test1.exception.FileIsEmptyException;
import test1.interfaces.Reader;

/**
 *
 * @author Robert.Tenadze
 */
public class FileReader implements Reader{

    public ArrayList<String> allFile= new ArrayList<>();
    
    @Override
    public void reader(String fileName) throws FileNotFoundException, IOException, FileIsEmptyException {
          
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
    }
    
}
