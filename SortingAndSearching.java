import java.util.*;

public class SortingAndSearching {
    public static void main(String[] args) {
        //Passando funçao lambda no construtor
        Set<Rabbit> rabbits = new TreeSet<>((r1,r2) -> r1.id()-r2.id());
        
        rabbits.add(new Rabbit(3));
        rabbits.add(new Rabbit(1));

        System.out.println(rabbits); //Sem ordenar

        //Collections.sort(rabbits); //- NAO COMPILA (Record n implementa Comparable)
        //é necessario passar um Comparator
        //Comparator<Rabbit> comparator = (r1,r2) -> r1.id()-r2.id();
        //Comparator<Rabbit> comparator = Comparator.comparing(Rabbit::id);


        //Collections.sort(rabbits,comparator);

        System.out.println(rabbits); //Apos o sort
    }

    record Rabbit(int id){}
}
