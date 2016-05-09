package it.polito.tdp.lab3.model;

import java.util.List;

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
	
}
