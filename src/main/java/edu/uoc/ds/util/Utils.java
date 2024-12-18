
package edu.uoc.ds.util;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.Container;
import edu.uoc.ds.traversal.Traversal;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class provides a set of static methods
 * which are used in the implementation of ADTs.
 * It also provides static public methods for the purpose
 * that can be used by users of the
 * ADTs library.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class Utils {

    private Utils() {
    }

    /**
     * Current version of the TADs library. Used for
     * provide an identifier for each of the classes of the
     * bookstore.
     */
    private static final String VERSION = "2.1.0";


    /**
     * Getter of the attribute {@code version}
     * @return  the library version.
     */
    public static String getVersion() {
        return VERSION;
    }


    /**
     * Returns the ADT library version as a long.
     *
     * @return the number corresponding to the library version.
     * The method used for conversion is: version 1.00.00 -> 10000.
     */
    public static long getLongVersion() {
        String str = VERSION.replaceAll("[.]", "");
        return Long.parseLong(str);
    }

    /**
     * Returns an identifier that can be used for classes
     * from the library to determine object compatibility
     * serialized of the same class. Currently this identifier
     * corresponds to the library version.
     *
     * @return Identifier for serialization.
     */
    public static long getSerialVersionUID() {
        return getLongVersion();
    }



    /**
     * Returns a string textual representation of the container.
     *
     * @param theClass Name of the class corresponding to the container.
     * @param traversal Route with all the elements of the container.
     * @return a String with the textual representation of the container.
     */

    public static String containerToString(String theClass, Traversal traversal) {
        StringBuilder buffer = new StringBuilder("{" + theClass + ": ");
        while (traversal.hasNext()) {
            Position kv = traversal.next();
            buffer.append(kv.getElem());
            if (traversal.hasNext()) buffer.append(", ");
        }
        buffer.append("}");
        return buffer.toString();
    }


    /**
     * Returns in a string a textual representation of a delegating container
     *
     * @param theClass Name of the class corresponding to the container it makes
     * serve delegation.
     * @param container Container Implementation used.
     * @return a String with the textual representation of the container.
     */

    public static String delegatedContainerToString(String theClass, Container container) {
        return "[" + theClass + ": " + container.toString() + "]";
    }

    /**
     * Read a line from the given input stream.
     * @param str String of information to output to standard output
     * (especially useful if the input is the standard input to remove a
     * message of what is requested. If not, it can be left blank.
     * @param in Input Stream from which to read the line.
     * @return A String with the read data.
     * @throws IOException In case a problem occurred
     * when reading the data or outputting the message to the standard output.
     */

    public static String readString(String str, InputStream in) throws IOException {
        if (str!=null) System.out.println(str);
        StringBuffer buffer=new StringBuffer();
        int c=in.read();
        while (c=='\n' || c=='\r') c=in.read();
        while (c!='\n' && c!='\r') {
            buffer.append((char)c);
            c=in.read();
        }
        return buffer.toString();
    }

}
