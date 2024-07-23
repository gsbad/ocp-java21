import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStream {
    public static void main(String[] args) {
        IntStream inteiros = IntStream.of(1,2,3);
        Stream<Integer> inteiros2 = Stream.of(1,2,3,4,5,6,7);

        System.out.println("\n");
        
        System.out.println(inteiros2.mapToInt(x->x).sum());//mapToInt, sum //28

        System.out.println("\n");

        OptionalDouble avg = inteiros.average(); //average
        avg.ifPresent(System.out::println); //2

        System.out.println("\n");
        
        DoubleStream empty = DoubleStream.empty();
        DoubleStream oneValue = DoubleStream.of(3.14);
        oneValue.forEach(System.out::println);

        System.out.println("\n");

        DoubleStream varargs = DoubleStream.of(1.45, 3.22, 3.46, 4.01, 4.05, 6.66);
        varargs.forEach(System.out::println);

        System.out.println("\n");

        DoubleStream random = DoubleStream.generate(()->(Math.round(Math.random()*100)));
        DoubleStream fractions = DoubleStream.iterate(.9, d->d/2);

        fractions.limit(9).forEach(System.out::println);
        System.out.println("\n");
        random.limit(9).forEach(System.out::println);

        System.out.println("\n");

        IntStream count = IntStream.iterate(1, n->n+1).limit(5); //12345
        count.forEach(System.out::print);

        System.out.println("\n");

        //IntStream range = IntStream.range(1, 6); //12345
        IntStream range = IntStream.rangeClosed(1, 5); //12345
        range.forEach(System.out::print);

        System.out.println("\n");
    }
}
