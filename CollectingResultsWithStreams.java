import java.util.TreeSet;
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


    }
}
