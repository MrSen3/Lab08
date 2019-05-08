package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.*;
import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumeroLettere;

    @FXML
    private TextField txtParolaDaCercare;

    @FXML
    private TextArea txtResult;

	private Model model;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	//Leggo il valore inserito nella prima casella di testo
    	int numeroLettere=Integer.parseInt(txtNumeroLettere.getText());
    	
    	//Ora devo chiamare il model affinche' generi il grafo non orientato
    	model.createGraph(numeroLettere);
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	List<String> figli= new ArrayList<String>();
    	//Il grafo deve essere gia' creato
    	
    	//Leggo la parola inserita
    	String parolaInserita = txtParolaDaCercare.getText();
    	
    	//Chiamo il model affinche' trovi i figli della parola inserita e me li salvi in una lista
    	txtResult.setText("Le parole che differiscono per una sola lettera da "+parolaInserita.toUpperCase()+ " sono:\n");
    	figli = model.displayNeighbours(parolaInserita);
    	for(String s: figli) {
    		txtResult.setText(s+ "\n");
    	}
    }
    
    @FXML
    void doTrovaGradoMax(ActionEvent event) {

    }



    @FXML
    void doReset(ActionEvent event) {
    	txtNumeroLettere.clear();
    	txtParolaDaCercare.clear();
    	txtResult.clear();
    }
    
    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParolaDaCercare != null : "fx:id=\"txtParolaDaCercare\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }


	public void setModel(Model model) {
		this.model=model;
		
	}
	
}
