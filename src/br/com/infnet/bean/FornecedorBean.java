package br.com.infnet.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.infnet.dao.DAO;
import br.com.infnet.modelo.Endereco;
import br.com.infnet.modelo.Fornecedor;
import br.com.infnet.modelo.enums.UF;

@ManagedBean
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedor;

	public FornecedorBean() {
		this.fornecedor = new Fornecedor();
	}

	public Fornecedor getFornecedor() {
		if (this.fornecedor == null)
			return new Fornecedor();
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public UF[] getUf() {
		return UF.values();
	}

	public List<Fornecedor> getFornecedores() {
		return new DAO<Fornecedor>(Fornecedor.class).listaTodos();
	}

	public void salvar() {

		if (this.fornecedor.getIdFornecedor() == null) {			
			new DAO<Endereco>(Endereco.class).adicionar(fornecedor.getEndereco());
			new DAO<Fornecedor>(Fornecedor.class).adicionar(this.fornecedor);
		} else {
			new DAO<Fornecedor>(Fornecedor.class).atualiza(this.fornecedor);
		}
		this.fornecedor = new Fornecedor();
	}

	public String formFornecedor() {
		return "fornecedor?faces-redirect=true";
	}
}
