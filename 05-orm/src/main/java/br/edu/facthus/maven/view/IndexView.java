package br.edu.facthus.maven.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.facthus.jpa.bean.ContatosBean;
import br.edu.facthus.jpa.entity.Contato;

@Named
@ViewScoped
public class IndexView implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = 
			Logger.getLogger(IndexView.class.getName());
	
	@Inject
	private ContatosBean contatosBean;
	
	private Contato contato = new Contato();
	
	private List<Contato> listaContatos = new ArrayList<Contato>();
	
	public void salva() {
		if (contato.getId() == null) {
			contatosBean.cadastra(contato);
			FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Contato cadastrado com sucesso!"));
		} else {
			contatosBean.atualiza(contato);
			FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Contato atualizado com sucesso!"));
		}
	}
	
	public void lista() {
		listaContatos = contatosBean.lista();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(String.format("%d contato(s) encontrado(s).", 
						listaContatos.size())));
	}
	
	public void atualiza(Contato contato) {
		this.contato = contato;
	}

	public ContatosBean getContatosBean() {
		return contatosBean;
	}

	public void setContatosBean(ContatosBean contatosBean) {
		this.contatosBean = contatosBean;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}
		
}
