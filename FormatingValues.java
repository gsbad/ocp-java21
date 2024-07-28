import java.text.NumberFormat;
import java.text.DecimalFormat;

public class FormatingValues {
    public static void main(String[] args) {
        double d = 20201706.1987;
        NumberFormat f1 = new DecimalFormat("###,###,###.00");
        System.out.println("\n" + f1.format(d));

    }
}
