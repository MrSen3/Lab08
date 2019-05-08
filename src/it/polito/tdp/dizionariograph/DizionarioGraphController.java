package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

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

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {

    }

    @FXML
    void doTrovaVicini(ActionEvent event) {

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
