import java.util.*;

public class CollectionMethods {
    public static void main(String[] args) {
        Collection<String> cats = new ArrayList<>( );
        System.out.println("\n" + cats);

        cats.add("lisas");
        cats.add("adoken");
        cats.add("bobaldo");
        cats.add("xaveco");

        System.out.println("\n" + cats);

        cats.removeIf(s -> s.startsWith("l"));

        System.out.println("\n" + cats);

        cats.forEach(System.out::println);

        for (String cat : cats) {
            System.out.println("Enhanced for: " + cat);
        }

        Iterator<String> iter = cats.iterator();
        while (iter.hasNext()) {
            System.out.println("Iterator() for: " + iter.next());
        }

        var list1 = List.of(1,2);
        var list2 = List.of(2,1);
        var set1 = Set.of(1,2);
        var set2 = Set.of(2,1);

        System.out.println(list1.equals(list2));
        System.out.println(set1.equals(set2));


        var heights = new ArrayList<Integer>();
        heights.add(1);

        System.out.println("\n" + heights);

        int teste = heights.get(0);

        Integer[] array = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        List<Integer> asList = Arrays.asList(array);
        List<Integer> of = List.of(array);
        List<Integer> copy = List.copyOf(asList);

        array[0] = 9;

        System.out.println(asList);
        System.out.println(of);
        System.out.println(copy);

        asList.set(0, 8);
        System.out.println(asList);


    }
}
