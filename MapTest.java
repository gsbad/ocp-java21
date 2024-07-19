import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class MapTest {
    public static void main(String[] args) {
        var mapa = Map.ofEntries(
            Map.entry("gustavo",1111),
            Map.entry("dayana",2222),
            Map.entry( "miguel",3333),
            Map.entry("adoken",4444),
            Map.entry("lisas",5555),
            Map.entry( "bob",6666)
            );
        Map<String,Integer> treeMap = new TreeMap<>(mapa);

        treeMap.entrySet().forEach((e) -> System.out.println(e.getKey() + ": " + e.getValue()));

        treeMap.put("assuero", 7777);
        System.out.println(treeMap);

        System.out.println();
        for (String keyString : treeMap.keySet()) {
            System.out.print(keyString + ", ");
        }
        System.out.println();
        System.out.println(treeMap.containsKey("dayana"));
        System.out.println(treeMap.containsValue(7777));
        System.out.println(treeMap.size());

        treeMap.clear();

        System.out.println();
        System.out.println(treeMap.containsKey("dayana"));
        System.out.println(treeMap.containsValue(7777));
        System.out.println(treeMap.size());
        System.out.println(treeMap.isEmpty());

        BinaryOperator<String> mapper = (v1,v2) //-> null;
                                -> v1.length() > v2.length() ? v1 : v2;
        Map<String,String> favorites = new HashMap<>();
        favorites.put("Jenny", "Souza");
        favorites.put("Tom", "Bus Tour");

        favorites.merge("Jenny", "Skyride", mapper);
        favorites.merge("Sam", "Skyride", mapper);

        System.out.println(favorites);
    }
}
