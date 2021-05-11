package tamaGolem;

public class Main {
    public static void main(String[] args) {
        int numeroElementiAttivi=10;
        Grafo g = new Grafo(numeroElementiAttivi);
        System.out.println(g.calcoloNumeroArchi(numeroElementiAttivi));
        for(int i=0;i<1000;i++){
            g.generaEquilibrio(500);
            System.out.println(g.isEquilibrato());
        }
        /*Map<Arco,Integer> map= g.getMappaDirezioni();
        for (Map.Entry<Arco,Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }*/

        /*
        System.out.println("---------------------------------------------------------------------------");
        Map<Arco,Integer> mappa= new HashMap<>();
        Arco a1 = new Arco(new Elemento(0),new Elemento(1));
        Arco a2 = new Arco(new Elemento(1),new Elemento(0));
        mappa.put(a1,2);
        mappa.put(a2,-2);
        System.out.println(a1.equals(a2));
        System.out.println(mappa.get(a1));
        System.out.println(mappa.get(new Arco(new Elemento(1),new Elemento(0))));
        mappa.put(a1,2);*/
    }
}
