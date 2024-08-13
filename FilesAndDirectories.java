import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesAndDirectories {
    public static void main(String[] args) {
        //Creating a File
        File file1 = new File("/home/gsbad/desenvolvimento/ocp-java21/file/stripes.txt");
        File file2 = new File("/home/gsbad/desenvolvimento/ocp-java21/file","stripes.txt");

        File parent = new File("/home/gsbad/desenvolvimento/ocp-java21/file");
        File file3 = new File(parent,"stripes.txt");

        //All the four files created point for the same location on disk

        System.out.println(file1.exists());


        //Creating a Path
        Path path1 = Path.of("/home/gsbad/desenvolvimento/ocp-java21/file/stripes.txt");
        Path path2 = Path.of("/home","gsbad","desenvolvimento","ocp-java21","file","stripes.txt");

        Path path3 = Paths.get("/home/gsbad/desenvolvimento/ocp-java21/file/stripes.txt");
        Path path4 = Paths.get("/home","gsbad","desenvolvimento","ocp-java21","file","stripes.txt");

        //All the four paths created point for the same reference on disk

        System.out.println(Files.exists(path1));

        //Switching between File and Path
        File rabbit = new File("rabbit");
        Path rabbitPath = rabbit.toPath();
        File rabbitPathFile = rabbitPath.toFile();

        //Obtaining a Path from the FileSystems Class
        Path path5 = FileSystems.getDefault()
                .getPath("/home/gsbad/desenvolvimento/ocp-java21/file/stripes.txt");
        Path path6 = FileSystems.getDefault()
                .getPath("/home","gsbad","desenvolvimento","ocp-java21","file","stripes.txt");

    }
}
