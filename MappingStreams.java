import java.util.*;
import java.util.stream.*;

public class MappingStreams {
    public static void main(String[] args) {


        Stream<String> objStream = Stream.of("Hello", "there", "my", "dear", "lovely", "unique", "friends");
        //Function Parameters when mapping between types of Streams
        //                 ToCreateStream    ToCreateDoubleStream    ToCreateIntStream   ToCreateLongStream
        //
        //Stream<T>        Function<T,R>     ToDoubleFunction<T>     ToIntFunction<T>    ToLongFunction<T>
        //DoubleStream<T>  DoubleFunction<R> DoubleUnaryOperator     DoubleToIntFunction DoubleToLongFunction
        //IntStream        IntFunction<R>    IntToDoubleFunction     IntUnaryOperator    IntToLongFunction
        //LongStream       LongFunction<R>   LongToDoubleFunction    LongToIntFunction   LongUnaryOperator

        //Mapping methods between type of Streams
        IntStream intStream = objStream.mapToInt(String::length);//Parameter: ToIntFunction
        LongStream longStream = intStream.mapToLong(i->i); //Paramether: IntToLongFunction
        Stream<Long> backToLongStream = longStream.mapToObj(l->l); //Parameter: LongFunction<R>
        DoubleStream doubleStream = backToLongStream.mapToDouble(d->d); //Parameter: ToDoubleFunction<T>
        //etc, etc etc...

        var integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        IntStream ints = integerList.stream().flatMapToInt(IntStream::of); //Method reference
        DoubleStream doubles = integerList.stream().flatMapToDouble(x->DoubleStream.of(x));
        LongStream longs = integerList.stream().flatMapToLong(LongStream::of); //Method Reference

        //Using Optional with primitive stream
        var stream = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream.average(); //average() always returns OptionalDouble

        optional.ifPresent(System.out::println); //5.5
        optional.ifPresentOrElse(
                System.out::println, //5.5
                ()->System.out.println("No average found!")
        );

        long sum = longs.sum();
        System.out.println(sum); //6

        DoubleStream doubles2 = DoubleStream.generate(()->Math.PI).limit(10);
        OptionalDouble min = doubles2.min();
        min.ifPresent(System.out::println);

        Integer returnRange = range(IntStream.of(1, 2, 3, 4, 5));
        // Summarizing Statistics
        System.out.println("Summary Statistics: " + returnRange); // Should print 4

    }
    //Create a Stream from a primitive stream
    private static Stream<Integer> mapping(IntStream stream){
        return stream.mapToObj(x->x);
    }
    private static Stream<Integer> boxing(IntStream stream){
        return stream.boxed();
    }
    private static int max(IntStream intStream){
        OptionalInt optional = intStream.max();
        return optional.orElseThrow(RuntimeException::new);
    }
    private static int range(IntStream intStream){
        IntSummaryStatistics stats = intStream.summaryStatistics();
        if (stats.getCount() == 0) throw new RuntimeException();
        return stats.getMax()-stats.getMin();
    }
}
