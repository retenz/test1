/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import test1.exception.FileIsEmptyException;

/**
 *
 * @author Robert.Tenadze
 */
public interface Reader {
    
    public void reader(String fileName) throws FileNotFoundException, IOException, FileIsEmptyException;
    
}
