package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.TimeDAO;
import br.edu.faculdadedelta.model.Time;
import br.edu.faculdadedelta.util.FacesUtil;

@ManagedBean
@SessionScoped
public class TimeController {
	
	private Time time = new Time();
    private TimeDAO dao = new TimeDAO();

    private static final String PAGINA_CADASTRO_TIME = "cadastroTime.xhtml";
    private static final String PAGINA_LISTA_TIME = "listaTime.xhtml";
    
    public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public TimeDAO getDao() {
		return dao;
	}

	public void setDao(TimeDAO dao) {
		this.dao = dao;
	}

	public void limparCampos(){
        time = new Time ();
    }

    public String salvar(){

        try {
            if (time.getId() == null){
                dao.incluir(time);
                limparCampos ();
                FacesUtil.exibirMensagem("Inclusão realizada com sucesso.");
            } else {
                dao.alterar (time);
                limparCampos ();
                FacesUtil.exibirMensagem("Alteração realizada com sucesso.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage ());
        }
        return PAGINA_CADASTRO_TIME;
    }

    public String editar(){
        return PAGINA_CADASTRO_TIME;
    }

    public String excluir(){
        try {
            dao.excluir(time);
            limparCampos();
            FacesUtil.exibirMensagem("Time excluído com sucesso.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            FacesUtil.exibirMensagem("Erro ao excluir time: " + e.getMessage ());
        }
        return PAGINA_LISTA_TIME;
    }

    public List<Time> getLista(){

        List<Time> listaRetorno = new ArrayList<>();
        try {
            listaRetorno = dao.listar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            FacesUtil.exibirMensagem("Erro ao listar times: " + e.getMessage());
        }
        return listaRetorno;
    }
    
}
