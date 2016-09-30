package br.com.infnet.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.infnet.dao.DAO;
import br.com.infnet.modelo.Fornecedor;
import br.com.infnet.modelo.Processo;

@ManagedBean
@SessionScoped
public class ProcessoBean {
	
	private Processo processo;
	private boolean isEditable = false;

	public ProcessoBean() {
		this.processo = new Processo();
	}
	
	public boolean isEditable(){
		return isEditable;
	}

	public Processo getProcesso() {
		if (this.processo == null)
			return new Processo();
		return processo;
	}

	public List<Processo> getProcessos() {
		return new DAO<Processo>(Processo.class).listaTodos();
	}

	public void buscarFornecedor(String cnpj) {
		Fornecedor fornecedor = new DAO<Fornecedor>(Fornecedor.class).buscarPorCnpj(cnpj);

		if (fornecedor == null) {
			this.processo.setFornecedor(new Fornecedor());
		} else {
			this.processo.setFornecedor(fornecedor);
		}
	}

	public void gravar() {

		if (this.processo.getIdProcesso() == null) {
			new DAO<Processo>(Processo.class).adicionar(this.processo);
		} else {
			new DAO<Processo>(Processo.class).atualiza(this.processo);
		}
		this.processo = new Processo();
	}

	public String editar(Processo processo) {		
		
		if (processo == null) {
			this.processo = new Processo();
		} else {
			this.processo = processo;
			this.isEditable = true;
		}
		return "processo?faces-redirect=true";
	}

	public void remover(Processo processo) {
		new DAO<Processo>(Processo.class).remove(processo);
	}

	public String formProcesso() {
		return "processo?faces-redirect=true";
	}
}
