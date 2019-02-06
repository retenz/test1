/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.exception;

/**
 *
 * @author Robert.Tenadze
 */
public class FaultInFileException extends Exception {

    public FaultInFileException() {
    }

    public FaultInFileException(String msg) {
        super(msg);
    }
}
