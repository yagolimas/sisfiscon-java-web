package br.com.infnet.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"cnpj"})})
public class Fornecedor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idFornecedor;
	private String cnpj;
	private String razaoSocial;
	private String inscricaoMunicipal;
	private BigDecimal receitaBruta;
	
	@OneToOne
	private Endereco endereco;
	
	public Fornecedor(){
		this.endereco = new Endereco();
	}
	
	public Long getIdFornecedor() {		
		return idFornecedor;
	}
	
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}	
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}
	
	public BigDecimal getReceitaBruta() {
		return receitaBruta;
	}
	
	public void setReceitaBruta(BigDecimal receitaBruta) {
		this.receitaBruta = receitaBruta;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
