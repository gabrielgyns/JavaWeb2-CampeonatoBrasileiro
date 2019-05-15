package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.model.Time;
import br.edu.faculdadedelta.util.Conexao;

public class TimeDAO {
	
public void incluir(Time time) throws ClassNotFoundException, SQLException {
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "INSERT INTO times (descricao) VALUES (?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, time.getDescricao().trim());
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);	
		
	}
	
	public void alterar(Time time) throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "UPDATE times SET descricao = ? WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, time.getDescricao().trim());
		ps.setLong(2, time.getId());
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
		
	}
	
	public void excluir(Time time) throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "DELETE FROM times WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, time.getId());
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
		
	}
	
	public List<Time> listar() throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "SELECT id, descricao FROM times";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<Time> listaRetorno = new ArrayList<>();
		
		while(rs.next()) {
			listaRetorno.add(this.popularTime(rs));
		}
		
		Conexao.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}

	
	public Time getTimeById(Long id) throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "SELECT id, descricao FROM times WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Time retornoTime = new Time();
		if(rs.next()) {
			retornoTime = this.popularTime(rs);
		}
		
		Conexao.fecharConexao(ps, conn, rs);
		
		return retornoTime;
	}
	
	private Time popularTime(ResultSet rs) throws SQLException {
		
		Time time = new Time();
		
		time.setId(rs.getLong ("id"));
		time.setDescricao(rs.getString ("descricao").trim ());
		
		return time;
	}
	
}
