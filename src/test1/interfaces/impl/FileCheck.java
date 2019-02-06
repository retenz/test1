/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.interfaces.impl;

import java.util.ArrayList;
import java.util.regex.Pattern;
import test1.exception.FaultInFileException;
import test1.exception.FileIsEmptyException;
import test1.interfaces.Check;

/**
 *
 * @author Robert.Tenadze
 */
public class FileCheck implements Check{

    @Override
    public void checking(ArrayList<String> allFile) throws FileIsEmptyException, FaultInFileException {
        
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
    
}
