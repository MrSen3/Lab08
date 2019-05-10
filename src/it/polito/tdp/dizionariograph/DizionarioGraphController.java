package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.*;
import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

	private int numeroLettere=0;
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

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnGradoMax;

	private Model model;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	//Controllo se la prima casella è vuota
    	if(txtNumeroLettere.getText().isEmpty()) {
    		txtResult.appendText("Errore: inserire un numero intero nel primo campo di testo");
    		return;
    	}
    	
    	//Leggo il valore inserito nella prima casella di testo, ma devo controllare se si verifica un errore di conversione da stringa a intero
    try{
    	
    	if(numeroLettere!=Integer.parseInt(txtNumeroLettere.getText())) {
    		numeroLettere=Integer.parseInt(txtNumeroLettere.getText());
    	}
    	else {//Controllo utile solo per alleggerire i calcoli richiesti al programma, ma  inutile per il resto
    		txtResult.appendText("Grafo della dimensione richiesta già creato\n");
    		return;
    	}
    	
    	//Ora devo chiamare il model affinche' generi il grafo non orientato
    	model.createGraph(numeroLettere);
    	txtResult.appendText("Grafo creato\n");
    	
    	//Attivo il pulsante successivo
    	btnTrovaVicini.setDisable(false);
    	//E nel caso in cui sia acceso il terzo pulsante lo devo disattivare
    	btnGradoMax.setDisable(true);
    }
    	catch(NumberFormatException n) {
    		System.err.println("Errore nella conversione in intero del valore inserito nella prima casella di testo");
    	}
    	
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	//Errori
    	if(txtParolaDaCercare.getText().trim().isEmpty()) {
    		txtResult.appendText("Errore: inserire una parola nel secondo campo  di testo\n");
    		return;
    	}
    	
    	System.out.println(txtParolaDaCercare.getText().trim().length()+"   "+Integer.parseInt(txtNumeroLettere.getText()));
    	if(txtParolaDaCercare.getText().trim().length()!=Integer.parseInt(txtNumeroLettere.getText())) {
    		txtResult.appendText("Errore: inserire una parola della lunghezza corretta\n");
    		return;
    	}
    	
    	List<String> figli= new ArrayList<String>();
    	//Il grafo deve essere gia' creato
    	
    	//Leggo la parola inserita
    	String parolaInserita = txtParolaDaCercare.getText();
    	
    	//Chiamo il model affinche' trovi i figli della parola inserita e me li salvi in una lista
    	figli = model.displayNeighbours(parolaInserita);
    	if(figli.size()==0) {
    		//La parola cercata non e' presente nel database
    		txtResult.appendText(parolaInserita.toUpperCase()+ " non è presente nel database. RIPROVA\n");
    		return;
    	}
    	
    	txtResult.appendText("Le parole che differiscono per una sola lettera da "+parolaInserita.toUpperCase()+ " sono "+figli.size()+":\n");
    	for(String s: figli) {
    		txtResult.appendText(s+ "\n");
    	}
    	
    	//Attivo il pulsante successivo
    	btnGradoMax.setDisable(false);
    	
    }
    
    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	
    }



    @FXML
    void doReset(ActionEvent event) {
    	//Pulisco la vista
    	txtNumeroLettere.clear();
    	txtParolaDaCercare.clear();
    	txtResult.clear();
    	
    	//Oscuro i bottoni 2 e 3
    	btnTrovaVicini.setDisable(true);
    	btnGradoMax.setDisable(true);
    }
    
    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParolaDaCercare != null : "fx:id=\"txtParolaDaCercare\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
    }


	public void setModel(Model model) {
		this.model=model;
		
	}
	
}
