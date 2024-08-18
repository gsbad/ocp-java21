import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var gorillas = new ArrayList<Gorilla>();
        gorillas.add(new Gorilla("Gug",15,false));
        gorillas.add(new Gorilla("Ug",11,false,"banana"));
        gorillas.add(new Gorilla("Bug",35,true));

        File dataFile = new File("gorilla.data");

        saveToFile(gorillas,dataFile); //Serialization
        var gorillasFromDisk = readFromFile(dataFile); //Deserialization

        System.out.println(gorillasFromDisk);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Name: ");
        String userInput = reader.readLine();
        System.out.println("You entedered: "+userInput);

    }
    static void saveToFile(List<Gorilla> gorillaList, File dataFile)
            throws IOException {
        try (var out = new ObjectOutputStream( //hi-lvl IOStream
                new BufferedOutputStream( //hi-lvl IOStream
                    new FileOutputStream(dataFile)))) { //low-lvl IOStream
            for (Gorilla gorilla : gorillaList)
                out.writeObject(gorilla); //serializing the data
        }
    }
    static List<Gorilla> readFromFile(File dataFile)
            throws IOException, ClassNotFoundException{
        var gorillas = new ArrayList<Gorilla>();
        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            while (true){
                var object = in.readObject();
                if (object instanceof Gorilla g)
                    gorillas.add(g); //deserializing the data
            }
        }catch (EOFException e){
            //e.printStackTrace();
        }
        return gorillas;
    }
}
/*
    To make a class serializable:
    - The class must be marked Serializable
    -Every instance member must...
        -implements serializable
        -marked transient
        -or have a null value at the time of serialization
 */
class Gorilla implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Boolean friendly;
    private transient String favoriteFood; //will not be saved when class is serialized

    public Gorilla(String name, int age, Boolean friendly) {
        this.name = name;
        this.age = age;
        this.friendly = friendly;
    }
    public Gorilla(String name, int age, Boolean friendly, String favoriteFood) {
        this.name = name;
        this.age = age;
        this.friendly = friendly;
        this.favoriteFood = favoriteFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getFriendly() {
        return friendly;
    }

    public void setFriendly(Boolean friendly) {
        this.friendly = friendly;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public String toString() {
        return "\nGorilla{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendly=" + friendly +
                ", favoriteFood='" + favoriteFood + '\'' +
                '}';
    }

}
