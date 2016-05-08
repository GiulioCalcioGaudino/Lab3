package it.polito.tdp.lab3.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {

	private String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
	
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

	
	
}
