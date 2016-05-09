package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.SegreteriaModel;
import it.polito.tdp.lab3.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	SegreteriaModel segreteria = new SegreteriaModel();
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCompleta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCerca(ActionEvent event) {
    	if (cbCorsi.getValue()==null){
    		txtResult.setText("seleziona corso o campo vuoto");
    		return;
    	}
    	txtResult.clear();
    	String nomeCorso;
    	int matricola;
    	List <Studente> studentiTemp= new LinkedList <Studente>();
    	List <Corso> corsiTemp= new LinkedList <Corso>();
    	
    	//caso1
    	if (cbCorsi.getValue().compareTo("")!=0 && txtMatricola.getText().compareTo("")==0){
    	nomeCorso = cbCorsi.getValue();
    	if(segreteria.iscrittiCorso(nomeCorso)==null){
    		txtResult.setText("corso senza iscritti");
    		return;
    	}
    	studentiTemp.addAll(segreteria.iscrittiCorso(nomeCorso));
    	for(int i=0; i<studentiTemp.size();i++){
    		Studente stemp = studentiTemp.get(i);
    		txtResult.appendText(stemp.getMatricola() + "  " + stemp.getCognome() + "  " + stemp.getNome() + "  " + stemp.getCDS() + "\n" );
    	}
    	}
    	
    	//caso2
    	if(cbCorsi.getValue().compareTo("")==0 && txtMatricola.getText().compareTo("")!=0){
    		if(!txtMatricola.getText().matches("[0-9]*")){
        		txtResult.setText("inserisci matricola valida");
        		return;}
    		matricola=Integer.parseInt(txtMatricola.getText());
    		if(segreteria.corsiStudente(matricola)==null){
    			txtResult.setText("Studente inesistente o non iscritto ad alcun corso");
    			return;
    		}
    		corsiTemp.addAll(segreteria.corsiStudente(matricola));
    		for(int i =0; i<corsiTemp.size();i++){
    			Corso ctemp = corsiTemp.get(i);
    			txtResult.appendText(ctemp.getCodins() + "  " + ctemp.getCrediti() + "  " + ctemp.getNome() + "  pd:" + ctemp.getPd() + "\n");
    		}
    		}
    	
    	//caso3
    	
    	
    	
    	
    	
    }

    @FXML
    void doCompleta(ActionEvent event) {
    	if(txtMatricola.getText().matches("")){
    		txtResult.setText("inserisci la matricola");
    		return;
    	}
    	
    	if(!txtMatricola.getText().matches("[0-9]*")){
    		txtResult.setText("inserisci matricola valida");
    		return;
    	}
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Studente studente = segreteria.completaCredenziali(matricola);
    	if(studente!=null){
    		txtNome.setText(studente.getNome());
    		txtCognome.setText(studente.getCognome());
    		txtResult.clear();
    	}
    	else {
    		txtResult.setText("Nessuno studente iscritto corrisponde alla matricola immessa");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    }

    @FXML
    void initialize() {
        assert cbCorsi != null : "fx:id=\"cbCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtnome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

        cbCorsi.getItems().addAll("","Gestione dell'innovazione e sviluppo prodotto", "Gestione dell'innovazione e sviluppo prodotto ICT","Ingegneria della qualità","Strategia, tecnologia e marketing","Economia e finanza d'impresa","Analisi e gestione dei sistemi produttivi","Analisi finanziaria e creditizia per l'impresa","Diritto commerciale","Economia dei sistemi industriali","Sistemi informativi aziendali","Analisi dei sistemi economici","Economia aziendale","Economia aziendale2","Gestione dei progetti");
        txtResult.setEditable(false);
        txtNome.setEditable(false);
        txtCognome.setEditable(false);
    }
}
