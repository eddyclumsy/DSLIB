package edu.uoc.ds.exceptions;

import edu.uoc.ds.util.Utils;

/**
 * Class that implements the exception that is generated when you want to insert one
 * item in a (sharpened) container that is full.
 * <p>
 * This is an extension of the runtime exceptions handled by
 * own compiler.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class FullContainerException extends ADTException {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Constructor without parameters
     */
    public FullContainerException() {
        super();
    }

    /**
     * Constructor with a parameter
     *
     * @param message message with the error that generated the exception
     */
    public FullContainerException(String message) {
        super(message);
    }
}
