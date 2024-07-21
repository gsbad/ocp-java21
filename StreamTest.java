import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
     
        //Terminal Stream Operation
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
        
    }
}
