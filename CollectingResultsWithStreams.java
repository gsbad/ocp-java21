import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResultsWithStreams {
    public static void main(String[] args) {
        var ohMy = Stream.of("lions", "tigers", "elephants", "bears");
        String result = ohMy.collect(Collectors.joining(", ")); //joining(CharSequence delimiter)

        System.out.println(result); //lions, tigers, elephants, bears

        var ohMy2 = Stream.of("lions", "tigers", "elephants", "bears");
        Double result2 = ohMy2.collect(Collectors.averagingInt(String::length)); //averagingInt() - Always a double

        System.out.println(result2); //6.25

        var ohMy3 = Stream.of("lions", "tigers", "elephants", "bears"); //stream pipeline: source (of)
        TreeSet<String> result3 = ohMy3
                .filter(s->s.contains("t")) //stream pipeline: intermediate operation (filter)
                //stream pipeline: terminal operation (collect)
                .collect(Collectors.toCollection(()->new TreeSet<>())); //usando lambda p variar
                //poderia ser assim:
                //.collect(Collectors.toCollection(TreeSet::new)); //method reference
        result3.forEach(System.out::println); //tigers, elephants

        var ohMy4 = Stream.of("lions", "tigers", "elephants", "bears");
        // Function.identity() == s->s
        //Map<String,Integer> map = ohMy4.collect(Collectors.toMap(Function.identity(), String::length));
        Map<String,Integer> map = ohMy4.collect(Collectors.toMap(s->s, String::length));
        System.out.println(map); //{lions=5, tigers=6, elephants=9, bears=5}

        var ohMy5 = Stream.of("lions", "tigers", "elephants", "bears", "dogs", "cats");
        // Function.identity() == s->s
        Map<Integer,String> map2 = ohMy5.collect(Collectors.toMap(
                String::length, //Function
                s->s, //Function
                (a,b) -> a + ", " + b, //BinaryOperator
                TreeMap::new)); //Supplier
        System.out.println(map2); //{4=dogs, cats, 5=lions, bears, 6=tigers, 9=elephants}

        var ohMy6 = Stream.of("lions", "tigers", "elephants", "bears", "dogs", "cats");
        Map<Integer, List<String>> map3 = ohMy6.collect(Collectors.groupingBy(String::length));
        System.out.println(map3); //{4=[dogs, cats], 5=[lions, bears], 6=[tigers], 9=[elephants]}

        var ohMy7 = Stream.of("lions", "tigers", "elephants", "bears", "dogs", "cats");
        Map<Integer, Set<String>> map4 = ohMy7.collect(Collectors.groupingBy(
                String::length,
                Collectors.toSet()
        ));
        System.out.println(map4); //{4=[cats, dogs], 5=[lions, bears], 6=[tigers], 9=[elephants]}

        var ohMy8 = Stream.of("lions", "tigers", "elephants", "bears", "dogs", "cats");
        TreeMap<Integer, Set<String>> map5 = ohMy8.collect(Collectors.groupingBy(
                String::length,
                TreeMap::new,
                Collectors.toSet()
        ));
        System.out.println(map5); //{4=[cats, dogs], 5=[lions, bears], 6=[tigers], 9=[elephants]}

        var ohMy9 = Stream.of("lions", "tigers", "elephants", "bears", "dogs", "cats");
        TreeMap<Integer, List<String>> map6 = ohMy9.collect(Collectors.groupingBy(
                String::length,
                TreeMap::new,
                Collectors.toList()
        ));
        System.out.println(map6); //{4=[cats, dogs], 5=[lions, bears], 6=[tigers], 9=[elephants]}


    }
}
