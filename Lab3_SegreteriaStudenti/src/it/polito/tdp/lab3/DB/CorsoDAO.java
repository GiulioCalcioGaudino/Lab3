package it.polito.tdp.lab3.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class CorsoDAO {
	Map <String,Corso> tuttiCorsi = new TreeMap <String,Corso>();
	private String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
	
	public Map<String,Corso> listaCorsi(){
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			Statement st = conn.createStatement();
			
			String sql = "select* from corso";

			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				Corso ctemp = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),rs.getInt("pd"));
				tuttiCorsi.put(rs.getString("nome"),ctemp);
			}
				rs.close();
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return tuttiCorsi;
	}
	
}
