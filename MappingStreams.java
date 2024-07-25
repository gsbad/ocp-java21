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
        IntStream intStream = objStream.mapToInt(s->s.length());//Parameter: ToIntFunction
        LongStream longStream = intStream.mapToLong(i->i); //Paramether: IntToLongFunction
        Stream<Long> backToLongStream = longStream.mapToObj(l->l); //Parameter: LongFunction<R>
        DoubleStream doubleStream = backToLongStream.mapToDouble(d->d); //Parameter: ToDoubleFunction<T>
        //etc, etc etc...      

        var integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        
        IntStream ints = integerList.stream().flatMapToInt(x->IntStream.of(x));
        DoubleStream doubles = integerList.stream().flatMapToDouble(x->DoubleStream.of(x));
        LongStream longs = integerList.stream().flatMapToLong(x->LongStream.of(x));

        //Using Optional with primitive stream
        var stream = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream.average(); //average() always returns OptionalDouble

        optional.ifPresent(System.out::println); //5.5
        System.out.println(optional.getAsDouble()); //5.5
        
        long sum = longs.sum();
        System.out.println(sum); //6
        DoubleStream doubles2 = DoubleStream.generate(()->Math.PI);
        OptionalDouble min = doubles2.min();

    }
    //Create a Stream from a primitive stream
    private static Stream<Integer> mapping(IntStream stream){
        return stream.mapToObj(x->x);
    }
    private static Stream<Integer> boxing(IntStream stream){
        return stream.boxed();
    }
}
