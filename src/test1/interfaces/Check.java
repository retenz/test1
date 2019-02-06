/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.interfaces;

import java.util.ArrayList;
import test1.exception.FaultInFileException;
import test1.exception.FileIsEmptyException;

/**
 *
 * @author Robert.Tenadze
 */
public interface Check {
    
    public void checking(ArrayList<String> allFile) throws  FileIsEmptyException, FaultInFileException;
    
}
