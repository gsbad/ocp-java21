import java.text.NumberFormat;
import static java.text.NumberFormat.Style;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.stream.Stream;

public class FormatingValues {
    public static void main(String[] args) {
        System.out.println("\nFormatting values:");
        double d = 1706.1987;
        NumberFormat f1 = new DecimalFormat("Your balance: ###,###,###.00");
        System.out.println("\n" + f1.format(d));

        
        System.out.println("\nFormatting dates:\n");
        var date = LocalDate.of(2017,Month.AUGUST,03);
        var time = LocalTime.of(2,30,40);
        var dt = LocalDateTime.of(date,time);
        //System.out.println(dt.getDayOfYear());
        //System.out.println(dt.getDayOfWeek());

        var diaSemana = switch (dt.getDayOfWeek()) {
            case SUNDAY -> "domingo";
            case MONDAY -> "segunda-feira";
            case TUESDAY -> "terça-feira";
            case WEDNESDAY -> "quarta-feira";
            case THURSDAY -> "quinta-feira";
            case FRIDAY -> "sexta-feira";
            case SATURDAY -> "sábado";
        };

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); //2017-08-03
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME)); //02:30:40

        var f = DateTimeFormatter.ofPattern("'Dia' dd 'de' MMMM 'de' yyyy', "+ diaSemana +", ás' hh:mm:ss");
        System.out.println(dt.format(f)); //Dia 03 de agosto de 2017, quinta-feira, ás 02:30:40
        // or (both are acceptable)
        System.out.println(f.format(dt)); //Dia 03 de agosto de 2017, quinta-feira, ás 02:30:40
        

        //Set Locale
        System.out.println(Locale.getDefault());
        //Locale Constructor is deprecated since java 19! Attention for exam ocp21
        var locale = Locale.of("en_US");  //Because that i use object factory method (of)
        Locale.setDefault(locale);
        System.out.println(Locale.getDefault());

        var l1 = new Locale.Builder() //Builder Design Pattern for set Locale
            .setLanguage("fr")
            .setRegion("FR")
            .build();
        Locale.setDefault(l1);    
        System.out.println(Locale.getDefault());


        //Localizing numbers and currency
        
        var visitantes = 3200500; //int
        var visitantesPorMes = visitantes / 12; //double
        var preco = BigDecimal.valueOf(45); //BigDecimal for values

        var us = NumberFormat.getInstance(Locale.US);
        var usMoeda = NumberFormat.getCurrencyInstance(Locale.US);

        var uk = NumberFormat.getInstance(Locale.UK);
        var ukMoeda = NumberFormat.getCurrencyInstance(Locale.UK);

        var ca_fr = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        var caFrMoeda = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);

        System.out.println("\nOutput US");

        System.out.println("visitantes: " + us.format(visitantes)
                        +"\nvisitantesPorMes: "+us.format(visitantesPorMes)
                        +"\nPreço: "+usMoeda.format(preco)+"\n");

        System.out.println("Output UK");

        System.out.println("visitantes: " + uk.format(visitantes)
                        +"\nvisitantesPorMes: "+uk.format(visitantesPorMes)
                        +"\nPreço: "+ukMoeda.format(preco)+"\n");

        System.out.println("Output CA_FR");                

        System.out.println("visitantes: " + ca_fr.format(visitantes)
                        +"\nvisitantesPorMes: "+ca_fr.format(visitantesPorMes)
                        +"\nPreço: "+caFrMoeda.format(preco)+"\n");


        //Format with CompactNumberFormats (New feature)
        var formatter = Stream.of(
            NumberFormat.getCompactNumberInstance(), //SHORT is used by default if you dont define!
            NumberFormat.getCompactNumberInstance(Locale.US, Style.SHORT), //static import Style
            NumberFormat.getCompactNumberInstance(Locale.US, Style.LONG),
            NumberFormat.getCompactNumberInstance(Locale.ITALY, Style.SHORT),
            NumberFormat.getCompactNumberInstance(Locale.ITALY, Style.LONG),
            NumberFormat.getCompactNumberInstance(Locale.GERMANY, Style.SHORT),
            NumberFormat.getCompactNumberInstance(Locale.GERMANY, Style.LONG),
            NumberFormat.getNumberInstance(Locale.US)
        );

        formatter.map(s->s.format(989_999_999)).forEach(System.out::println);
    }
}
