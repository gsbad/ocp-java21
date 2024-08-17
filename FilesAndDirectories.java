//import java.io.File;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.io.*;
import java.nio.file.*;

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

        //Testing the method printPathInformation
        FilesAndDirectories filesAndDirectories = new FilesAndDirectories();
        filesAndDirectories.printPathInformation(
                Path.of("/home/gsbad/desenvolvimento/ocp-java21/file/stripes.txt"));
        filesAndDirectories.printPathInformation(
                Path.of("./file/.././file/stripes.txt"));

        //Resolving Paths (concatenation)
        System.out.println("Resolve: "+
                parent.toPath().resolve(file3.toPath())); ///home/gsbad/desenvolvimento/ocp-java21/file/stripes.txt
        //Since the argument for the resolve method was a absolute path provided, that is the value returned:
        System.out.println("Resolve: "+
                parent.toPath().resolve("/absolute/prevails/gotit.txt")); ///absolute/prevails/gotit.txt

        //Relativizing a Path
        var caminho1 = Path.of("fish.txt");
        var caminho2 = Path.of("friendly/birds.txt");

        System.out.println(caminho1.relativize(caminho2));
        System.out.println(caminho2.relativize(caminho1));

    }

    public void printPathInformation(Path path){
        System.out.println("Filename is: " + path.getFileName());
        System.out.println(" Root is: " + path.getRoot());
        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null)
            System.out.println("   Current parent is: "+currentParent);
        System.out.println();
    }
}
