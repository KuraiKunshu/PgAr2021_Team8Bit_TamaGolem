package tamaGolem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Interfaccia {
	
	

	
	public static void stampaPietreDisponibili(LinkedList<Elemento> pietre) {
		System.out.println("Scegli tra le pietre disponibili digitando l'indice numerico:");
		for(int i=0; i < Elemento.getNumeroElementi(); i++) {
			int numPietreUguali=0;
			for(int j=0; j<pietre.size(); j++) {
				if(pietre.get(j).equals(new Elemento(i))) {
					numPietreUguali++;
				}
			}
			System.out.println(i + "-" +(new Elemento(i)).getNome() + "("+ numPietreUguali +")");
		}
	}
}
