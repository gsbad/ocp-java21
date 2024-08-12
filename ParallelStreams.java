import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void twoWaysForCreateAParallelStream(String[] args) {
        // Two ways of creating a parallel stream
        Collection<Integer> collection = List.of(1, 2, 3, 4, 5);

        Stream<Integer> p1 = collection.stream().parallel(); //from a stream
        Stream<Integer> p2 = collection.parallelStream(); //method from Collection class
    }
    private static int doWork(int input){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){}
        return input;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        List.of(1,2,3,4,5)
//                .stream()
//                .map(w -> doWork(w))
//                .forEach(s -> System.out.println(s+" ")); //1,2,3,4,5 (25 seconds) with stream()
        List.of(1,2,3,4,5)
            .parallelStream()
            .map(w -> doWork(w))
            .forEach(s -> System.out.println(s+" ")); //unpredictable (5 seconds) with parallelStream()
            //.forEachOrdered(s -> System.out.println(s+" ")); //U can use forEachOrdered() for guarantee ordering

        System.out.println();
        var timeTaken = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Time: "+timeTaken+" seconds");

        //Processing parallel reductions
        System.out.println(
                List.of(1,2,3,4,5,6)
                    .parallelStream()
                        .findAny()
                        .get()
        );
        //List.of(1,2,3,4,5).stream().unordered(); //Creating unordered Streams

        //Combining results with reduce()
        System.out.println(
                List.of('w','o','l','f')
                    .parallelStream()
                    .reduce("",
                            (s1,c)->s1+c,
                            (s2,s3)->s2+s3)
        );
        System.out.println(
                List.of("w","o","l","f")
                        .parallelStream()
                        .reduce("X",String::concat) //XwXoXlXf - bad combining | Should be this: Xwolf
        );

        Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
        SortedSet<String> set = stream.collect( //Supplier, Acumulator, Combiner
                ConcurrentSkipListSet::new,
                Set::add,
                Set::addAll
        );
        System.out.print(set);
    }
}


