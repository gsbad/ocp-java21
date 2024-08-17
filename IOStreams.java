import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class IOStreams {
    public static void main(String[] args) {
        //Specifying enconding
        Charset utf8Charset = Charset.forName("UTF-8");

        //BufferedReader -> high-level IO Stream
        try (var br = new BufferedReader(new FileReader("zoo-data.txt"))) {
            System.out.println(br.readLine()); //FileReader -> high-level IO Stream
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Stream Base Classes - Abstracts
        /* (all abstract classes - cannot be instantiated)
        -InputStream - Abstract class for all input byte streams
        -OutputStream - Abstract class for all output byte streams
        -Reader - Abstract class for all input characters streams
        -Writer - Abstract class for all output characters streams
         */

        //Stream Base Classes - Concretes
        /* (all concrete classes)
        -FileInputStream - Reads file data as bytes
        -FileOutputStream - Writes file data as bytes
        -FileReader - Reads file data as characters
        -FileWriter - Writes file data as characters

        -BufferedInputStream - Reads byte data from existing InputStream as buffered
        -BufferedOutputStream - Writes byte data from existing OutputStream as buffered
        -BufferedReader - Reads character data from existing InputStream as buffered
        -BufferedWriter - Writes character data from existing OutputStream as buffered

        -ObjectInputStream - Deserialize primitive java data types from InputStream
        -ObjectOutputStream - Serialize primitive java data types from OutputStream
        -PrintReader - Writes formatted representations of java objects to binary stream
        -PrintWriter - Writes formatted representations of java objects to character stream
         */


    }
}
