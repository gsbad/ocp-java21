import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dogs implements Comparable<Dogs> {

    public static void main(String[] args) {
        //Comparator e Comparable
        /*Comparator<Dogs> byWeight = new Comparator<Dogs>() {
            public int compare(Dogs d1, Dogs d2){
                return d2.getWeight()-d1.getWeight();
            }
        };*/
        //Implementaçao de Comparator reescrito com lambda expression
        //Pois trata-se de uma FunctionalInterface
        //Comparator<Dogs> byWeight = (d1,d2) -> d2.getWeight()-d1.getWeight();
        //Podemos implementar usando um helper method(static) da interface tb
        Comparator<Dogs> byWeight = Comparator.comparing(Dogs::getWeight);

        var dogs = new ArrayList<Dogs>();
        
        dogs.add(new Dogs("astolfo", 100));
        dogs.add(new Dogs("batista", 30));
        dogs.add(new Dogs("sua mae", 330));

        Collections.sort(dogs);

        System.out.println(dogs);

        Collections.sort(dogs,byWeight);

        System.out.println(dogs);

        //Comparing Multiple fields (comparing & thenComparing)
        //Helper Static Methods from Comparator FunctionalInterface:
        //comparing(function)
        //comparingDouble(function)
        //comparingInt(function)
        //comparingLong(function)
        //naturalOrder()
        //reverseOrder()
        Comparator<Squirrel> c = Comparator.comparing(Squirrel::getSpecies)
                            .thenComparingInt(Squirrel::getWeight);//apenas se o primeiro retorna 0
        //Helper Default Methods for building a Comparator chained:
        //reversed()
        //thenComparing()
        //thenComparingDouble()
        //thenComparingInt()
        //thenComparingLong()
        var squirrels = new ArrayList<Squirrel>();

        squirrels.add(new Squirrel(15, "tico"));
        squirrels.add(new Squirrel(33, "tico"));
        squirrels.add(new Squirrel(66, "teco"));
        squirrels.add(new Squirrel(35, "tico"));
        squirrels.add(new Squirrel(67, "teco"));
        squirrels.add(new Squirrel(39, "tico"));
        squirrels.add(new Squirrel(60, "teco"));
        squirrels.add(new Squirrel(21, "tico"));
        squirrels.add(new Squirrel(11, "teco"));

        //Collections.sort(squirrels); //Nao compila pois Squirrel n esta implementando Comparable
        Collections.sort(squirrels,c);
        System.out.println(squirrels);

        //System.out.println(squirrels);

        //Utilizando a classe propria criada nesse arquivo para fazer o sort c Comparator
        Comparator<Squirrel> d = new MultiFieldComparator();
        Collections.sort(squirrels, d);
        System.out.println(squirrels);
                    
    }

    private String name;
    private int weight;
    
    public Dogs(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public int compareTo(Dogs o) {
        return name.compareTo(o.name);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class Squirrel{
    private int weight;
    private String species;
    public Squirrel(int weight, String species) {
        this.weight = weight;
        this.species = species;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    @Override
    public String toString() {
        return "[weight=" + weight + ", species=" + species + "]\n";
    }
    
}

class MultiFieldComparator implements Comparator<Squirrel>{

    @Override
    public int compare(Squirrel s1, Squirrel s2) {
        int result = s1.getSpecies().compareTo(s2.getSpecies());
        if (result != 0) return result; //especies diferentes - retorna e encerra o metodo
        return s1.getWeight() - s2.getWeight(); //especie igual, usa criterio do peso e retorna
    }
    
}
