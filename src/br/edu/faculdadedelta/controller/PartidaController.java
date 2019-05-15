package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.PartidaDAO;
import br.edu.faculdadedelta.model.Partida;
import br.edu.faculdadedelta.model.Time;
import br.edu.faculdadedelta.util.FacesUtil;

@ManagedBean
@SessionScoped
public class PartidaController {
	
	private Partida partida = new Partida();
	private PartidaDAO dao = new PartidaDAO();
	
	private Time timeCasaSelecionado = new Time();
	private Time timeVisitanteSelecionado = new Time();
	
	private static final String PAGINA_CADASTRO_PARTIDA = "cadastroPartida.xhtml";
    private static final String PAGINA_LISTA_PARTIDA = "listaPartida.xhtml";
    
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	public PartidaDAO getDao() {
		return dao;
	}
	public void setDao(PartidaDAO dao) {
		this.dao = dao;
	}
	public Time getTimeCasaSelecionado() {
		return timeCasaSelecionado;
	}
	public void setTimeCasaSelecionado(Time timeCasaSelecionado) {
		this.timeCasaSelecionado = timeCasaSelecionado;
	}
	public Time getTimeVisitanteSelecionado() {
		return timeVisitanteSelecionado;
	}
	public void setTimeVisitanteSelecionado(Time timeVisitanteSelecionado) {
		this.timeVisitanteSelecionado = timeVisitanteSelecionado;
	}
    
	public void limparCampos(){
        partida = new Partida();
        timeCasaSelecionado = new Time();
        timeVisitanteSelecionado = new Time();
    }
    
	public String salvar(){
        try {
            if (partida.getId () == null){
                partida.setTimeCasa(timeCasaSelecionado);
                partida.setTimeVisitante(timeVisitanteSelecionado);
                dao.incluir (partida);
                limparCampos ();
                FacesUtil.exibirMensagem("Inclusão realizada com sucesso. ");
            } else {
                partida.setTimeCasa(timeCasaSelecionado);
                partida.setTimeVisitante(timeVisitanteSelecionado);
                dao.alterar (partida);
                limparCampos ();
                FacesUtil.exibirMensagem("Alteração realizada com sucesso. ");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
        }
        return PAGINA_CADASTRO_PARTIDA;
    }
	
	public String editar(){
        timeCasaSelecionado = partida.getTimeCasa();
        timeVisitanteSelecionado = partida.getTimeVisitante();
        return PAGINA_CADASTRO_PARTIDA;
    }

    public String excluir(){
        try {
            dao.excluir(partida);
            limparCampos();
            FacesUtil.exibirMensagem("Partida excluída com sucesso. ");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            FacesUtil.exibirMensagem("Erro ao excluir partida: " + e.getMessage ());
        }

        return PAGINA_LISTA_PARTIDA;
    }

    public List<Partida> getLista(){

        List<Partida> listaRetorno = new ArrayList<> ();
        try {
            listaRetorno = dao.listar ();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            FacesUtil.exibirMensagem("Erro ao listar partidas: " + e.getMessage ());
        }

        return listaRetorno;
    }
	
}
