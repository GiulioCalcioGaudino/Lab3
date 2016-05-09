package it.polito.tdp.lab3.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {

	List <Corso> corsiStudente = new LinkedList <Corso>();
	private String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
	Map <Integer,Studente> tuttiStudenti = new TreeMap <Integer,Studente>();
	
	public Studente completaCredenziali (int matricola){
		
	try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		
		Statement st = conn.createStatement();
		
		String sql = "SELECT * FROM studente WHERE matricola=\"" + matricola + "\"";

		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()){
			Studente stemp = new Studente(matricola, rs.getString("nome"), rs.getString("cognome"),rs.getString("CDS"));
			rs.close();
			conn.close();
			return stemp;
		}
		else {
			rs.close();
			conn.close();
			return null;
		}
		
		

		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}
	return null;
}

	public List<Corso> corsiStudente (int matricola){
		corsiStudente.clear();
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			Statement st = conn.createStatement();
			
			String sql = "select corso.codins, corso.crediti,corso.nome,corso.pd  from studente,iscrizione,corso where studente.matricola=\""+ matricola + "\" AND corso.codins=iscrizione.codins AND iscrizione.matricola=studente.matricola;";

			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				Corso ctemp = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),rs.getInt("pd"));
				corsiStudente.add(ctemp);
			}
				rs.close();
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		if (corsiStudente.isEmpty()) {return null;}
		else {return corsiStudente;}
		
	}
		public Map<Integer,Studente> listaStudenti(){
			try {
				Connection conn = DriverManager.getConnection(jdbcURL);
				
				Statement st = conn.createStatement();
				
				String sql = "select* from studente";

				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()){
					Studente stemp = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"),rs.getString("CDS"));
					tuttiStudenti.put(rs.getInt("matricola"),stemp);
				}
					rs.close();
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			return tuttiStudenti;
		}
	
	

}
