import java.util.ArrayList;
import java.util.Collections;

public class ComparableTest {
    public static void main(String[] args) {
        var ducks = new ArrayList<Duck>();

        ducks.add(new Duck("Quack"));
        ducks.add(new Duck("Puddles"));
        ducks.add(new Duck("Patolho"));

        Collections.sort(ducks);
        System.out.println(ducks);

        var a1 = new Animal2();
        var a2 = new Animal2();

        a1.setId(5);
        a2.setId(7);

        System.out.println(a1.compareTo(a2)); //-2
        System.out.println(a1.compareTo(a1)); //0
        System.out.println(a2.compareTo(a1)); //2

        var d1 = new MissingDuck("patolino");
        var d2 = new MissingDuck("patolino");

        System.out.println(d1.compareTo(d2));
    }
}

class Duck implements Comparable<Duck> {
    private String name;

    //construtor
    public Duck(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
    @Override
    public int compareTo(Duck duck){
        return name.compareTo(duck.name);
    }
}


class Animal2 implements Comparable<Animal2> {
    private int id;

    public int compareTo(Animal2 a){
        return id - a.id;
    }

    public void setId(int id){
        this.id = id;
    }
}

class LegacyDuck implements Comparable{ //N especificou o tipo
    private String name;

    @Override
    public int compareTo(Object obj){
        LegacyDuck d = (LegacyDuck) obj; //Cast necessario
        return name.compareTo(d.name);
    }
}

class MissingDuck implements Comparable<MissingDuck>{
    private String name;

    public MissingDuck(String name){
        this.name = name;
    }

    public int compareTo(MissingDuck quack){
        if(quack == null)
            throw new IllegalArgumentException("Poorly formed duck!");
        if(this.name == null && quack.name == null)
            return 0;
        else if(this.name == null) return -1;
        else if(quack.name == null) return 1;
        else
            return name.compareTo(quack.name);    
    }
}