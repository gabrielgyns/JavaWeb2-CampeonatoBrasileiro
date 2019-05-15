package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.model.Partida;
import br.edu.faculdadedelta.model.Time;
import br.edu.faculdadedelta.model.type.StatusPartida;
import br.edu.faculdadedelta.util.Conexao;

public class PartidaDAO {
	
	public void incluir(Partida partida) throws ClassNotFoundException, SQLException {
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "INSERT INTO partidas (id_time_casa, id_time_visitante, chave_status_partida, horario_partida, local_partida, saldo_gol_casa, saldo_gol_visitante) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, partida.getTimeCasa().getId());
		ps.setLong(2, partida.getTimeVisitante().getId());
		ps.setString(3, partida.getStatusPartida().name().trim());
		ps.setString(4, partida.getHorarioPartida().trim());
		ps.setString(5, partida.getLocalPartida().trim());
		ps.setInt(6, partida.getSaldoGolCasa());
		ps.setInt(7, partida.getSaldoGolVisitante());
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
		
	}
	
	public void alterar(Partida partida) throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "UPDATE partidas SET id_time_casa = ?, id_time_visitante = ?, horario_partida = ?, local_partida = ?, saldo_gol_casa = ?, saldo_gol_visitante = ?, chave_status_partida = ? "
				+ " WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, partida.getTimeCasa().getId());
		ps.setLong(2, partida.getTimeVisitante().getId());
		ps.setString(3, partida.getHorarioPartida().trim());
		ps.setString(4, partida.getLocalPartida().trim());
		ps.setInt(5, partida.getSaldoGolCasa());
		ps.setInt(6, partida.getSaldoGolVisitante());
		ps.setString(7, partida.getStatusPartida().name().trim());
		ps.setLong(8, partida.getId());
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
		
	}

	public void excluir(Partida partida) throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = "DELETE FROM partidas WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, partida.getId());
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
		
	}
	
public List<Partida> listar() throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarBancoDeDados();
		
		String sql = 	"SELECT p.id AS idPartida, " +
                		"	p.horario_partida AS horarioPartida, " +
                		"	p.local_partida AS localPartida, " +
                		"	p.saldo_gol_casa AS saldoGolCasa, " +
                		"	p.saldo_gol_visitante AS saldoGolVisitante, " +
                		"	p.chave_status_partida AS chaveStatus, " +
                		"	t1.id AS idTimeCasa, " +
                		"	t1.descricao AS descricaoTimeCasa, " +
                		"	t2.id AS idTimeVisitante, " +
                		"	t2.descricao AS descricaoTimeVisitante " +
                		" FROM partidas p " +
                		" INNER JOIN times t1 ON p.id_time_casa = t1.id " +
                		" INNER JOIN times t2 ON p.id_time_visitante = t2.id ";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<Partida> listaRetorno = new ArrayList<>();
		
		while(rs.next()) {
			
			Partida partida = new Partida();
            partida.setId(rs.getLong("idPartida"));
            partida.setHorarioPartida(rs.getString("horarioPartida").trim());
            partida.setLocalPartida(rs.getString("localPartida").trim());
            partida.setSaldoGolCasa(rs.getInt("saldoGolCasa"));
            partida.setSaldoGolVisitante(rs.getInt("saldoGolVisitante"));
            partida.setStatusPartida(StatusPartida.valueOf(rs.getString("chaveStatus").trim()));
            
            Time timeCasa = new Time();
            timeCasa.setId(rs.getLong ("idTimeCasa"));
            timeCasa.setDescricao(rs.getString ("descricaoTimeCasa").trim ());

            Time timeVisitante = new Time();
            timeVisitante.setId(rs.getLong ("idTimeVisitante"));
            timeVisitante.setDescricao(rs.getString ("descricaoTimeVisitante").trim ());

            partida.setTimeCasa(timeCasa);
            partida.setTimeVisitante(timeVisitante);

            listaRetorno.add(partida);
		}
		
		Conexao.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
	
}
