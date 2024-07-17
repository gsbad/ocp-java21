import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

public class BuiltInFunctionalInterfaces {  
    public static void main(String[] args) {
        System.out.println("\n\n\n");
        
        //Implementando Built-In FunctionalInterfaces (Interfaces funcionais nativas do JAVA)

        // ======================================================================== Supplier<T>
        //metodo get()
        //Supplier<T> - T get();

        Supplier<LocalDate> s1 = LocalDate::now; //Method Reference
        Supplier<LocalDate> s2 = () -> LocalDate.now(); //Lambda
        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();

        System.out.println(d1 + " " + d2);
        System.out.println("\n");

        // + um exemplo com Supplier

        Supplier<StringBuilder> s3 = StringBuilder::new; //Method Reference
        Supplier<StringBuilder> s4 = () -> new StringBuilder(); //Lambda

        //StringBuilder d3 = s3.get();
        //StringBuilder d4 = s4.get();

        //System.out.println(d3 + " " + d4); //Empty String
        //System.out.println("\n");


        // ====================================================== Consumer<T> & BiConsumer<T, U>
        //metodo accept()
        //Consumer<t> - void accept(T t);
        //BiConsumer<t,u> - void accept(T t, U u);
        //Nao tem retorno

        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);
        c1.accept("c1- Printed with a Consumer<String> variable!");
        c2.accept("c2- Printed with a Consumer<String> variable!");

        // + um exemplo com Consumer
        HashMap<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> c4 = map::put; //Method Reference
        BiConsumer<String, Integer> c5 = (s,i) -> map.put(s, i); //Lambda

        c4.accept("charmander", 12);
        c4.accept("charizard", 92);

        c5.accept("pikachu", 18);
        c5.accept("raichu", 132);

        System.out.println(map);


        // ====================================================== Predicate<T> & BiPredicate<T, U>
        //metodo test()
        //Predicate<t> - boolean test(T t); - @return boolean (true|false)
        //BiPredicate<t,u> - boolean test(T t, U u); - @return boolean (true|false)
        String teste = "cheio";
        Predicate<String> pred = String::isEmpty;
        Predicate<String> pred2 = s -> s.isEmpty();

        System.out.println("Teste Predicate<t>: " + pred.test(teste)); //false
        System.out.println("Teste2 Predicate<t>: " + pred2.test("")); //true

        // + um exemplo com Predicate

        BiPredicate<String, String> pred3 = String::startsWith; //Method Reference
        BiPredicate<String, String> pred4 = (s,p) -> s.startsWith(p); //Lambda
        System.out.println("Teste BiPredicate<t,u>: " + pred3.test("zulu", "z")); //true
        System.out.println("Teste2 BiPredicate<t,u>: " + pred4.test("teste", "t")); //true


        // ====================================================== Function<T, R> & BiFunction<T, U, R>
        //metodo apply()
        //Function<T,R> - R apply(T t); @return the function result
        //BiFunction<T,U,R> - R apply(T t, U u); @return the function result

        Function<String, Integer> f1 = String::length; //Method Reference
        Function<String, Integer> f2 = s -> s.length(); //Lambda

        System.out.println(f1.apply("teste")); //5
        System.out.println(f2.apply("Ola!")); //4

        BiFunction<String, String, String> f3 = String::concat; //Method Reference
        BiFunction<String, String, String> f4 = (str1,str2) -> str1.concat(str2); //Lambda

        System.out.println(f3.apply("Hello ", "Mundo!")); //Hello Mundo!
        System.out.println(f4.apply("Olá ", "World!")); //Olá World!

        // UnaryOperator<T> extends Function<T,T> & BinaryOperator<T> extends BiFunction<T,T,T> - Mesmo tipo
        //metodo apply()
        //UnaryOperator<T> - T apply(T t1); @return the function result
        //BinaryOperator<T> - T apply(T t1, T t2); @return the function result

        UnaryOperator<String> u1 = String::toUpperCase; //Method Reference
        UnaryOperator<String> u2 = s -> s.toUpperCase(); //Lambda

        System.out.println(u1.apply("to upper case")); //TO UPPER CASE
        System.out.println(u2.apply("to upper case2!")); //TO UPPER CASE2!

        BinaryOperator<String> bo = String::concat; //Method Reference
        BinaryOperator<String> bo2 = (str3, str4) -> str3.concat(str4); //Lambda

        System.out.println(bo.apply("Hello ", "Mundo!")); //Hello Mundo!
        System.out.println(bo2.apply("Olá ", "World!")); //Olá World!

        // ========================================================== CONVENIENCE METHODS

        // ====== and() & negate() ======

        Predicate<String> egs = s -> s.contains("egs");
        Predicate<String> brown = s -> s.contains("brown");

        //Isso
        //Predicate<String> brownEgs = s -> s.contains("egs") & s.contains("brown");
        //Predicate<String> otherEgs = s -> s.contains("egs") && !s.contains("brown");
        //Vira isso:
        Predicate<String> brownEgs = egs.and(brown);
        Predicate<String> otherEgs = egs.and(brown.negate());

        String teste2 = "brown egs";
        String teste3 = "brown egs";

        System.out.println("Contem brown egs: " + brownEgs.test(teste2));
        System.out.println("Contem other egs: " + otherEgs.test(teste3));


        // ======= andThen() =========
        Consumer<String> x1 = x -> System.out.print(x);
        Consumer<String> x2 = x -> System.out.println(x);

        Consumer<String> combinado = x1.andThen(x2); //Java 4ever!Java 4ever!
        combinado.accept("Java 4ever!");
       

        // ======= compose() =========
        Function<Integer, Integer> before = x -> x + 1;
        Function<Integer, Integer> after = x -> x * 2;

        Function<Integer, Integer> chained = after.compose(before); //8
        Integer result = chained.apply(3);
        System.out.println("compose() - input = 3 passando por 2 funções: " + result);


        // ================================== Functional Interface for primitives

        // BooleanSuplier - method boolean getAsBoolean();

        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random() > .5; 

        System.out.println("BooleanSuplier b1: " + b1.getAsBoolean());
        System.out.println("BooleanSuplier b2: " + b2.getAsBoolean());

        //For double, int & long (repare q sao todos primitivos)


    }

}