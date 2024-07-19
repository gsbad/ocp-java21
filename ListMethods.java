import java.util.*;

public class ListMethods {
    public static void main(String[] args) {
        var numbers = Arrays.asList(1,2,3);

        System.out.println(numbers);

        //replaceAll(UnaryOperator)
        numbers.replaceAll(x -> x*2);

        System.out.println(numbers);
    }
}
