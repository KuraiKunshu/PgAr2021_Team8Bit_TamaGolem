package tamaGolem;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<Arco,Integer> mappa= new HashMap<>();
        Arco a1 = new Arco(new Elemento(0),new Elemento(1));
        Arco a2 = new Arco(new Elemento(1),new Elemento(0));
        mappa.put(a1,null);
        mappa.put(a2,-2);
        System.out.println(a1.equals(a2));

        System.out.println(mappa.get(a1));
        System.out.println(mappa.get(new Arco(new Elemento(1),new Elemento(0))));
        mappa.put(a1,2);
    }
}
