package it.polito.tdp.dizionariograph.model;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	private Graph <String, DefaultEdge> grafo;
	private List<String> parole;
//	private Map<Integer, String> wordIdMap;
	
	
	
	public void createGraph(int numeroLettere) {
		//Grafo non orientato non pesato---->SimpleGraph
		this.grafo = new SimpleGraph<>(DefaultEdge.class);

		//Adesso per aggiungere i vertici al grafo ho bisogno del db dizionario, quindi chiamo il DAO
		WordDAO dao = new WordDAO();
		this.parole=dao.getAllWordsFixedLength(numeroLettere);
				
		//Qui aggiungo al grafo creato in precedenza le parole della lunghezza specificata
		Graphs.addAllVertices(this.grafo, this.parole);
		
		//Adesso devo aggiungere gli archi
		//Un arco puo' collegare solo le parole che differiscono di una sola lettera
		for(String s1: this.parole) {
			//Prendo una parola e controllo con quali  parole differisce di una sola lettera
			//e per ognuna di queste parole creo un arco
			
			for(String s2: this.parole) {
				//Qui ho s1 fissa e la confronto con tutte le altre parole di lunghezza specificata
				if(controllaParole(s1, s2)) {
					this.grafo.addEdge(s1, s2);
				}
			}//uscito da questo for comincera' dalla seconda parola e la confronta con tutte quelle successive
			//Questo si puo' fare perche' il grafo e' non orientato e quindi se due parole differiscono di una sola lettera verra' creato l'edge e non ci sara' piu' bisogno di confrontarle
		}
		
		
		System.err.println("createGraph -- TODO");
	}

	
	//Per fare questo serve la lezione 26
	public List<String> displayNeighbours(String parolaInserita) {

		System.err.println("displayNeighbours -- TODO");
		return new ArrayList<String>();
	}

	public int findMaxDegree() {
		System.err.println("findMaxDegree -- TODO");
		return -1;
	}
	
	
	public boolean controllaParole(String s1, String s2) {
		int contatore = 0;
		
		//Fisso una lettera della prima parola e...
		for(int i=0; i<s1.length(); i++) {
			
			//scorro tutte le lettere della seconda
			for(int j=0; j<s1.length(); j++) {
				
				//ogni volta che trovo una differenza aumento il contatore
				if(s1.charAt(i)!=s2.charAt(j)) {
					contatore++;
					//se il contatore supera 1, allora posso ritornare false e non far lavorare piu' il programma
					if(contatore>1)
						return false;
				}
			}
		}
		
		if(contatore == 1)
			return true;
		else
			return false;
	}


	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}


	public List<String> getParole() {
		return parole;
	}
	
	
	
	
}
