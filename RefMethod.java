public class RefMethod {
    public static void main(String[] args) {
        //Converter - Static Methods
        Converter methodRef = Math::round;
        Converter lambda = x -> Math.round(x);
        System.out.println("\n\n" + methodRef.round(100.1));
        System.out.println(lambda.round(100.1));

        //StringStart - Methods on particular objects
        var str = "Amor";
        StringStart methodRef2 = str::startsWith;
        StringStart lambda2 = s -> str.startsWith(s);
        System.out.println("\n\n" + methodRef2.beginningCheck("A"));
        System.out.println(lambda2.beginningCheck("A"));

        //StringChecker - Methods on particular objects
        var str2 = "cheio";
        StringChecker methodRef3 = str2::isEmpty;
        StringChecker lambda3 = () -> str2.isEmpty();
        System.out.println("\n\n" + methodRef3.check());
        System.out.println(lambda3.check());

        //StringParameterCheck - Instance methods on parameters
        StringParameterCheck methodRef4 = String::isEmpty;
        StringParameterCheck lambda4 = s -> s.isEmpty();
        System.out.println("\n\n" + methodRef4.check("Zoo"));
        System.out.println(lambda4.check("Zoo"));

        //StringTwoParameterCheck - Instance methods on two parameters
        StringTwoParameterCheck methodRef5 = String::startsWith;
        StringTwoParameterCheck lambda5 = (t , p) -> t.startsWith(p);
        System.out.println("\n\n" + methodRef5.check("Zoo", "Z"));
        System.out.println(lambda5.check("Zoo", "Z"));

        //EmptyStringCreator - Construtor
        EmptyStringCreator methodRef6 = String::new;
        EmptyStringCreator lambda6 = () -> new String();
        var myObject = methodRef6.create();
        var myObject2 = lambda6.create();
        System.out.println("\n\n" + myObject.equals(""));
        System.out.println(myObject2.equals(""));

        //StringCopier - Construtor
        StringCopier methodRef7 = String::new;
        StringCopier lambda7 = s -> new String();
        var myString = methodRef7.copy("Zebra");
        var myString2 = lambda7.copy("Zebra");
        System.out.println("\n\n" + myString.equals("Zebra"));
        System.out.println(myString2.equals("Zebra"));
    }
}

// TANTO LAMBDAS QUANTO METHOD REFERENCES DEVEM BATER COM A ASSINATURA DOS METODOS DAS FUNCTIONAL INTERFACES
//Como as abaixo:

interface Converter{
    long round(double num);
}
interface StringStart{
    boolean beginningCheck(String prefix);
}
interface StringChecker{
    boolean check();
}
interface StringParameterCheck{
    boolean check(String text);
}
interface StringTwoParameterCheck{
    boolean check(String text, String prefix);
}
interface EmptyStringCreator{
    String create();
}
interface StringCopier{
    String copy(String value);
}