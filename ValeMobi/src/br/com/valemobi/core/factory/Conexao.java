package br.com.valemobi.core.factory;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
	private static  Connection con =  null;
	
	private static final String url = "jdbc:mysql://127.0.0.1:3306/db_valemobi";
	private static final String usuario = "root";
	private static final String senha = "root";
	
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,usuario,senha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return con;
	}


}
