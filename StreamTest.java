import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
 * STREAM STEPS
 * 1- SOURCE
 * 2- [INTERMEDIATE OPERATIONS]
 * 3- TERMINAL OPERATIONS
 */
public class StreamTest {
    public static void main(String[] args) {
        //CREATING STREAM SOURCES
        System.out.println("\n");

        Stream<String> sourceStreamString = Stream.of("a","b","c","d","e"); //factoryMethod passando varargs 

        List<Double> listDouble = new ArrayList<>(); //convertendo uma coleção a um Stream
        listDouble.add(Math.random());
        listDouble.add(Math.random());
        listDouble.add(Math.random());
        listDouble.add(Math.random());

        Stream<Double> sourceStreamDouble = listDouble.stream(); //metodo stream()
        Stream<Double> sourceParallelStreamDouble = listDouble.parallelStream(); //metodo parallelStream() p/ multithreads

        //Stream infinita
        Stream<Double> infiniteStream = Stream.generate(Math::random);
        Stream<Integer> infiniteStream2 = Stream.iterate(1 , x->x+2);  //seed & UnaryOperator

        //Stream infinita com limitador
        Stream<Integer> sourceStreamInt = Stream.iterate(1,
                                                    x->x<100,
                                                    x->x+2); //seed & Predicate & UnaryOperator
     
        sourceStreamString.findAny().ifPresent(System.out::println); //findAny()

        //TERMINAL STREAM OPERATIONS
        //count()
        //min()
        //max()
        //findAny()
        //findFirst()
        //allMatch()
        //anyMatch()
        //noneMatch()
        //forEach()
        //reduce()
        //collect()
        //max é um terminal stream operation, portanto so roda uma vez e encerra a stream
        //se sourceStreamInt tentar ser usado novamente lançará uma Exception em tempo de execução
        Optional<Integer> maximo = sourceStreamInt.max((i1,i2) -> i1 - i2); 
        maximo.ifPresent(System.out::println); //Consumer //99

        //infiniteStream.forEach(x->System.out.println(x)); //roda infinitamente
        //exemplo de codigo chain com stream
        System.out.println(List.of(1,2,3,4,5,6).stream().reduce(1, (i1,i2) -> i1*i2)); //720

        var list = List.of("monkey", "2", "chimp");
        Predicate<String> pred = x-> Character.isLetter(x.charAt(0));

        System.out.println(list.stream().anyMatch(pred)); //anyMatch()
        System.out.println(list.stream().allMatch(pred)); //allMatch()
        System.out.println(list.stream().noneMatch(pred)); //noneMatch()

        //Iterating
        //infiniteStream2.forEach(System.out::println);

        String result = String.format("%.2f%n", sourceStreamDouble.reduce(1.0, (x1,x2)->x1+x2));
        System.out.println(result);

        Stream<String> big = Stream.of("b","i","g");
        Stream<String> wolf = Stream.of("w","o","l","f");
        String palavra1 = big.reduce("",(s,c)->s+c);
        String palavra2 = wolf.reduce("",String::concat);
        System.out.println(palavra1 + " " + palavra2);

        BinaryOperator<Integer> op = (a,b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(1);
        Stream<Integer> fiveElements = Stream.of(1,2,3,4,5);

        empty.reduce(op).ifPresent(System.out::println);
        oneElement.reduce(op).ifPresent(System.out::println);
        fiveElements.reduce(op).ifPresent(System.out::println);

        //Collecting
        Stream<String> stream = Stream.of("m", "i", "g", "u", "e", "l");
        StringBuilder palavra3 = stream.collect(StringBuilder::new,
                                                StringBuilder::append, 
                                                StringBuilder::append);
        System.out.println(palavra3); //miguel

        Stream<String> stream2 = Stream.of("w","o","l","f");
        TreeSet<String> palavra4 = stream2.collect(TreeSet::new,
                                           TreeSet::add, 
                                           TreeSet::addAll);
        System.out.println(palavra4); //flow

        Stream<String> stream3 = Stream.of("w","o","l","f");
        TreeSet<String> palavra5 = stream3.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(palavra5); //flow

        Stream<String> stream4 = Stream.of("w","o","l","f");
        Set<String> palavra6 = stream4.collect(Collectors.toSet());
        System.out.println(palavra6); //fwlo


        //COMMON INTERMEDIATE OPERATIONS (metodos que retornam uma Stream)
        //Usando a infiniteStream e infiniteStream2 criadas nas linhas 33, 34
        Stream<Double> finiteStream = infiniteStream.limit(33).map((a)->a+1); //map
        finiteStream.forEach(x->System.out.printf("%.3f%n", x));
        
        Stream<Integer> finiteStream2 = infiniteStream2.skip(11).limit(55); //skip, limit
        finiteStream2.forEach(System.out::println);

        List<String> zero = List.of();
        var one = List.of("Bonobo");
        var two = List.of("Mama Gorilla","Baby Gorilla");

        Stream<List<String>> animals = Stream.of(zero,one,two);
        animals.flatMap(m -> m.stream()).forEach(System.out::println); //flatMap

        System.out.println("\nSorted: ");
        var three = Stream.of("Bonobo");
        var four = Stream.of("Mama Gorilla","Baby Gorilla");
        Comparator<String> reverse = Comparator.reverseOrder(); //comparator p ser passado em sorted
        
        Stream.concat(three, four).sorted(reverse).forEach(System.out::println); //sorted, concat

        var numbers = new ArrayList<>();
        var letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');

        Stream<List<?>> badPeeking = Stream.of(numbers,letters); //peek
        badPeeking.peek(x->x.remove(0)).map(List::size).forEach(System.out::print); //00
    }
}
