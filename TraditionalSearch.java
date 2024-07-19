//TraditionalSearcb(psvm)
import java.util.*;

public class TraditionalSearch {
    public static void main(String[] args) {
        var animais = new ArrayList<Animal>();

        animais.add(new Animal("Peixe", false, true));
        animais.add(new Animal("Aguia", true, false));
        animais.add(new Animal("Baleia", false, true));
        animais.add(new Animal("Pombo", true, false));
        animais.add(new Animal("Macaco", false, false));

        System.out.println("\n");
        print(animais, new ChecaVoa());
        print(animais, new ChecaNada());
    }

    // Method print
    public static void print(List<Animal> animais, ChecaAnimal checador) {
        for (Animal animal : animais) {
            if (checador.test(animal)) {
                System.out.print(animal + " ");
            }
        }
        System.out.println();
    }
}

// record Animal
record Animal(String especie, boolean podeVoar, boolean podeNadar) {}

// interface ChecaAnimal
interface ChecaAnimal {
    boolean test(Animal a);
}

// class ChecaVoa
class ChecaVoa implements ChecaAnimal {
    public boolean test(Animal a) {
        return a.podeVoar(); // Acessando o método gerado pelo record
    }
}

// class ChecaNada
class ChecaNada implements ChecaAnimal {
    public boolean test(Animal a) {
        return a.podeNadar(); // Acessando o método gerado pelo record
    }
}
