package it.polito.tdp.dizionariograph.model;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private class EdgeTraversedGraphListener implements TraversalListener<String, DefaultEdge> {

		@Override
		public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> ev) {
			// TODO Auto-generated method stub
			//Ogni volta che ha visitato un arco, deve capire se il nodo figlio visitato e' nuovo o no.
			//Se e' la prima volta che viene visitato allora viene salvato nella mappa back con chiva=nodo figlio e argomento nodo padre
			//back e' una mappa che punta dal basso vero l'alto (child->parent)
			//per un nuovo vertice 'child' scoperto devo avere che:
			// - child e' ancora conosciuto(non ancora trovato)
			// - parent gia' visitato
			
			String sourceVertex = grafo.getEdgeSource(ev.getEdge());
			String targetVertex = grafo.getEdgeTarget(ev.getEdge());

			/*
			 * se il grafo e' orientato allora source=parent, target==child
			 * se non e' orientato, potrebbe essere al contrario
			 */
			
			//se questo target non e' ancora una chiave della mappa e intanto il source e' gia' nella mappa(vuol dire che so da dove proviene)
			if(!backVisit.containsKey(targetVertex) && backVisit.containsValue(sourceVertex)) {
				backVisit.put(targetVertex, sourceVertex);
			} else if(!backVisit.containsKey(sourceVertex) && backVisit.containsValue(targetVertex)){//questo serve solo nel caso di grafi non orientati
				backVisit.put(sourceVertex, targetVertex);
			}
						
		}

		@Override
		public void vertexFinished(VertexTraversalEvent<String> arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void vertexTraversed(VertexTraversalEvent<String> arg0) {
			// TODO Auto-generated method stub
			
		}
	
		
	}
	private Graph <String, DefaultEdge> grafo;
	private List<String> parole;
//	private Map<Integer, String> wordIdMap;
	private Map<String, String> backVisit;//albero all'indietro relativo alla visita
	
	
	public void createGraph(int numeroLettere) {
		//Grafo non orientato non pesato---->SimpleGraph
		this.grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		//Adesso per aggiungere i vertici al grafo ho bisogno del db dizionario, quindi chiamo il DAO
		WordDAO dao = new WordDAO();
		parole=new ArrayList<String>(dao.getAllWordsFixedLength(numeroLettere));
		
		if (parole.size() == 0) {
			System.out.println("Nessuna parola trovata");
			return;
}
		//Qui aggiungo al grafo creato in precedenza le parole della lunghezza specificata
		Graphs.addAllVertices(this.grafo, this.parole);
		
		//Adesso devo aggiungere gli archi
		//Un arco puo' collegare solo le parole che differiscono di una sola lettera
		for(String s1: parole) {
			//Prendo una parola e controllo con quali  parole differisce di una sola lettera
			//e per ognuna di queste parole creo un arco
			
			for(String s2: parole) {
				//Qui ho s1 fissa e la confronto con tutte le altre parole di lunghezza specificata
				if(controllaParole(s1, s2) && s1.compareTo(s2)!=0) {
					this.grafo.addEdge(s1, s2);
				}
			}//uscito da questo for comincera' dalla seconda parola e la confronta con tutte quelle successive
			//Questo si puo' fare perche' il grafo e' non orientato e quindi se due parole differiscono di una sola lettera verra' creato l'edge e non ci sara' piu' bisogno di confrontarle
		}
		
		System.err.println("createGraph -- TODO");
		System.out.println("Vertici: "+this.grafo.vertexSet().size()+" Archi: "+this.grafo.edgeSet().size());
		
	}

	
	//Per fare questo serve la lezione 26
	public List<String> displayNeighbours(String parolaInserita) {
		List<String> result = new ArrayList<String>();
		if (!parole.contains(parolaInserita)) {
			return result;
		}
		result=Graphs.neighborListOf(this.grafo, parolaInserita);
		System.err.println("displayNeighbours -- TODO");
		return result;
		
//		backVisit=new HashMap<String, String>();
//		
//		GraphIterator<String, DefaultEdge> it = new BreadthFirstIterator<>(this.grafo, parolaInserita);
//		
//		it.addTraversalListener(new Model.EdgeTraversedGraphListener());
//		
//		backVisit.put(parolaInserita, null);
//		
//		while(it.hasNext()) {
//			result.add(it.next());
//		}
//		
		
//		return result;
	}

	public int findMaxDegree() {
		System.err.println("findMaxDegree -- TODO");
		return -1;
	}
	
	
	public boolean controllaParole(String s1, String s2) {
		int contatore = 0;
		
		//Fisso una lettera della prima parola e...
		for(int i=0; i<s1.length(); i++) {
			//ogni volta che trovo una differenza aumento il contatore
			if(s1.charAt(i)!=s2.charAt(i)) {
				contatore++;
				//se il contatore supera 1, allora posso ritornare false e non far lavorare piu' il programma
				if(contatore>1) {
					break;}
			}
		}
		
		if(contatore <= 1)
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
