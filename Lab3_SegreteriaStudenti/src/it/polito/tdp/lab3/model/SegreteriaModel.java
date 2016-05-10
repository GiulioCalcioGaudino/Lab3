package it.polito.tdp.lab3.model;

import java.util.*;
import java.util.Map;

import it.polito.tdp.lab3.DB.IscrizioneDAO;
import it.polito.tdp.lab3.DB.StudenteDAO;

public class SegreteriaModel {

	StudenteDAO studentedao = new StudenteDAO();
	IscrizioneDAO iscrizionedao = new IscrizioneDAO();
	
	public Studente completaCredenziali (int matricola){
		return studentedao.completaCredenziali(matricola);
	}
	
	public List<Studente> iscrittiCorso (String corso){
		return iscrizionedao.iscrittiCorso(corso);
	}
	
	public List<Corso> corsiStudente (int matricola){
		return studentedao.corsiStudente(matricola);
	}
	
	public boolean studenteIscritto (String corso, int matricola){
		return iscrizionedao.studenteIscritto(corso, matricola);
	}
	
	public Map<Integer,Studente> listaStudenti(){
		return studentedao.listaStudenti();
	}
	
	public void iscrivi (int matricola, String nome){
		iscrizionedao.iscrivi(matricola, nome);
	}
}
