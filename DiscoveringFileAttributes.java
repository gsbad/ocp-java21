import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class DiscoveringFileAttributes {
    public static void main(String[] args) throws IOException {
        var path = Paths.get(".");
        
        //Checking File Accessibility
        System.out.println(Files.isHidden(path));
        System.out.println(Files.isReadable(path));
        System.out.println(Files.isWritable(path));
        System.out.println(Files.isExecutable(path));

        //Retrieving Attributes
        BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
        System.out.println("Is a directory? " + data.isDirectory());
        System.out.println("Is a regular file? " + data.isRegularFile());
        System.out.println("Is a symbolic link? " + data.isSymbolicLink());
        System.out.println("Size (in bytes): ? " + data.size());
        System.out.println("Last modified: " + data.lastModifiedTime());

        //Modifying Attributes
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class); //View
        BasicFileAttributes attributes = view.readAttributes();//Attributes

        FileTime lastModifiedTime = FileTime.fromMillis(
                attributes.lastModifiedTime().toMillis() + 60_000); //+1 min
        view.setTimes(lastModifiedTime,null,null);

        //Traversing a Directory Tree
        var dfa = new DiscoveringFileAttributes();
        long pathSize = dfa.getPathSize(path);
        System.out.printf("Total size: %.2f megabytes" , (pathSize/1_000_000.0));

        //Searching Directory
        long minSize = 1_000;
        try (var s = Files.find(path, 10,
                (p, a) -> a.isRegularFile()
                        && p.toString().endsWith(".java")
                        && a.size() > minSize)) {
            System.out.println("\n\nFiles:");
            s.forEach(System.out::println);
        }
    }
    long getSize(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    long getPathSize(Path source) throws IOException{
        try(var s = Files.walk(source)) {
            return s.parallel()
                    .filter(p->!Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }
}
