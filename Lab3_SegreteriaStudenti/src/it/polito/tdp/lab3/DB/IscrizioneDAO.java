package it.polito.tdp.lab3.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.model.Studente;

public class IscrizioneDAO {
	
	
	List <Studente> studentiIscritti = new LinkedList <Studente>();
	private String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
	
	public List<Studente> iscrittiCorso (String corso){
		studentiIscritti.clear();
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			Statement st = conn.createStatement();
			
			String sql = "select studente.matricola,studente.nome,studente.cognome,studente.CDS  from studente,iscrizione,corso where corso.nome=\""+ corso + "\" AND corso.codins=iscrizione.codins AND iscrizione.matricola=studente.matricola;";

			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				Studente stemp = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"),rs.getString("CDS"));
				studentiIscritti.add(stemp);
			}
				rs.close();
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		if (studentiIscritti.isEmpty()) {return null;}
		else {return studentiIscritti;}
	}
	
	public boolean studenteIscritto (String corso, int matricola){
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			Statement st = conn.createStatement();
			
			String sql = "select* from corso, iscrizione, studente where corso.nome =\"" + corso + "\" AND iscrizione.matricola=\"" + matricola + "\" AND corso.codins = iscrizione.codins AND iscrizione.matricola=studente.matricola;";

			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()){
				rs.close();
				conn.close();
				return true;
			}
			else{
				rs.close();
			    conn.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return false;
	}
	
	
	
}
