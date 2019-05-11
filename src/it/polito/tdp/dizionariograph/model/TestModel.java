package it.polito.tdp.dizionariograph.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.createGraph(3);
		System.out.println(String.format("**Grafo creato**\n"));
		System.out.println(model.getGrafo());
				
		List<String> vicini = model.displayNeighbours("ali");
		System.out.println("Neighbours di ala: " + vicini + "\n");
		
		System.out.println("Cerco il vertice con grado massimo...");
		System.out.println(model.findMaxDegree());
	}

}
