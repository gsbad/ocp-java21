import java.util.Optional;

public class OptionalTest{
    public static void main(String[] args) {
        Optional<Double> opt = average(89,100,90,55);
        System.out.println("\n\nMédia: " + opt);
    }
    public static Optional<Double> average(int...scores){
        if(scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int i : scores) sum += i;
        return Optional.of((double)sum/scores.length);
    }
}