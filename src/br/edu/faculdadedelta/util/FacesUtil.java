package br.edu.faculdadedelta.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void exibirMensagem(String msg) {
		FacesMessage mensagem = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
}
