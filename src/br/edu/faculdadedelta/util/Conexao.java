package br.edu.faculdadedelta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection conectarBancoDeDados() throws ClassNotFoundException, SQLException {

		Connection conn = null;

		String url = "jdbc:postgresql://localhost:5432/CampeonatoBrasileiro";

		String usuario = "postgres";
		String senha = "";

		Class.forName("org.postgresql.Driver"); // Class forName do driver do PostgreSQL

		conn = DriverManager.getConnection(url, usuario, senha);

		return conn;

	}

	public static void fecharConexao(PreparedStatement ps, Connection conn, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}

	/**
	 * Method main to test the connection.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			conectarBancoDeDados();
			System.out.println("Conectou no banco de dados!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
