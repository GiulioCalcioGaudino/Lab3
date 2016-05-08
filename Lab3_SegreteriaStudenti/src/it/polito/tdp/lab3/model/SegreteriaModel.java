package it.polito.tdp.lab3.model;

import it.polito.tdp.lab3.DB.StudenteDAO;

public class SegreteriaModel {

	StudenteDAO studentedao = new StudenteDAO();
	
	public Studente completaCredenziali (int matricola){
		return studentedao.completaCredenziali(matricola);
	}
	
}
