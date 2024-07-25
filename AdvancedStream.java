import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Stream;

public class AdvancedStream {
    public static void main(String[] args) {
        var cats = new ArrayList<String>();
        cats.add("Annie");
        cats.add("Ripley");
        var stream = cats.stream();
        cats.add("KC");
        //A stream so é processada apos a chamada do metodo terminal count()
        System.out.println(stream.count()); //Por isso a saida é 3

        threeDigit(Optional.of(123));

        var animals = List.of("bird-", "horse-", "bunny-", "cat-", "dog-", "fish-", "lamb-", "mouse-");
        Spliterator<String> originalBagOfFood = animals.spliterator();
        Spliterator<String> emmasBag = originalBagOfFood.trySplit();
        emmasBag.forEachRemaining(System.out::print); //bird-horse-bunny-cat-

        System.out.println("\n");

        Spliterator<String> jillsBag = originalBagOfFood.trySplit();
        jillsBag.tryAdvance(System.out::println); //dog-
        jillsBag.forEachRemaining(System.out::println); //fish-

        originalBagOfFood.forEachRemaining(System.out::print); //lamb-mouse-

        var originalBag = Stream.iterate(1, n->n<=3, n->++n).spliterator();

        Spliterator<Integer> newBag = originalBag.trySplit();

        System.out.println("\n");

        //newBag.forEachRemaining(System.out::print);
        newBag.tryAdvance(System.out::println);
        newBag.tryAdvance(System.out::println);
        newBag.tryAdvance(System.out::println);
        newBag.tryAdvance(System.out::println); //Nao imprime

    }

    private static void threeDigit(Optional<Integer> optional){
//        if (optional.isPresent()) {
//            var num = optional.get();
//            var string = "" + num;
//            if (string.length() == 3)
//                System.out.println(string);
//        }
        optional.map(n-> "" + n)
                .filter(s->s.length() == 3)
                .ifPresent(System.out::println);
    }
}
