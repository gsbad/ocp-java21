import java.util.Optional;

public class OptionalTest{
    public static void main(String[] args) {
        Optional<Double> opt = average(89,100,90,55,1,100,77,99,1);
        System.out.printf("%n%nMédia: %.2f", opt.get());

        if(opt.isPresent()) System.out.println("\nOptional present!");
        opt.ifPresent((x) -> System.out.println(x)); 
       
        Optional<Double> opt2 = average();
        System.out.println(opt2.orElse(Double.valueOf(11.11)));
    }
    public static Optional<Double> average(int...scores){
        if(scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int i : scores) sum += i;
        return Optional.of((double)sum/scores.length);
    }
}