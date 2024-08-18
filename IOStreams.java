import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

        //Using IO Streams
        Path src = Path.of("sharks.log");
        Path dest = Path.of("sharkscopy.log");
        try {
            readLazily(src);
            //lines - lazily processes - Stream<String>
            Files.lines(src).forEach(System.out::println);
            //readAllLines - read entire file into memory - List<String>
            Files.readAllLines(src).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Combining with newBufferedReader and newBufferedWriter


    }

    static void copyStream(InputStream in, OutputStream out) throws IOException{
        int b;
        while ((b = in.read()) != -1) out.write(b);
    }
    static void copyStream(Reader in, Writer out) throws IOException{
        int b;
        while ((b = in.read()) != -1) out.write(b);
    }
    static void copyStream2(InputStream in, OutputStream out) throws IOException{
        int batchSize = 1024;
        var buffer = new byte[batchSize];
        int lengthRead;
        while ((lengthRead = in.read(buffer,0,batchSize)) > 0){
            out.write(buffer,0,lengthRead);
            out.flush();
        }
    }
    static void copyTextFile(File src, File dest) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(src));
             var writer = new PrintWriter(new FileWriter(dest))) {

            String line = null;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.println();
            }
        }
    }

    //Enhancing with Files class (NIO2 API) - Much better!
    static void copyPathAsString(Path input, Path output) throws IOException{
        String string = Files.readString(input);
        Files.writeString(output, string);
    }
    static void copyPathAsBytes(Path input, Path output) throws IOException{
        byte[] bytes = Files.readAllBytes(input);
        Files.write(output, bytes);
    }
    static void copyPathAsLines(Path input, Path output) throws IOException{
        List<String> lines = Files.readAllLines(input);
        Files.write(output,lines);
    }
    static void readLazily(Path path) throws IOException{
        try (var s = Files.lines(path)) {
            s.filter(f->f.startsWith("WARN:"))
                .map(f->f.substring(5))
                .forEach(System.out::println);
        }
    }
    //newBufferedReader & newBufferedWriter
    static void copyPath(Path input, Path output) throws IOException{
        try (var reader = Files.newBufferedReader(input);
             var writer = Files.newBufferedWriter(output)) {
            String line = null;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
        }
    }

}
