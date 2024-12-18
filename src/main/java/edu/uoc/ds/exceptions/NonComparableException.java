package edu.uoc.ds.exceptions;

import edu.uoc.ds.util.Utils;

/**
 * Class that implements the exception that is generated when you want to compare
 * two objects that are not comparable or are not comparable to each other.
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
public class NonComparableException extends ADTException {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Constructor without parameters.
     */
    public NonComparableException() {
        super();
    }

    /**
     * Constructor with a parameter.
     *
     * @param message message with the error that generated the exception
     */
    public NonComparableException(String message) {
        super(message);
    }
}
