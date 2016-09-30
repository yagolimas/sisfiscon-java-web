package br.com.infnet.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Processo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idProcesso;	
	private String relatoFiscalizacao;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataRelato = Calendar.getInstance();
	private String fiscalResponsavel;
	
	@ManyToOne
	private Fornecedor fornecedor;

	public Processo(){
		this.fornecedor = new Fornecedor();
	}
	
	public Long getIdProcesso() {
		return idProcesso;
	}
	
	public void setIdProcesso(Long idProcesso) {
		this.idProcesso = idProcesso;
	}
	
	public String getRelatoFiscalizacao() {
		return relatoFiscalizacao;
	}
	
	public void setRelatoFiscalizacao(String relatoFiscalizacao) {
		this.relatoFiscalizacao = relatoFiscalizacao;
	}
	
	public Calendar getDataRelato() {
		return dataRelato;
	}
	
	public void setDataRelato(Calendar dataRelato) {
		this.dataRelato = dataRelato;
	}
	
	public String getFiscalResponsavel() {
		return fiscalResponsavel;
	}
	
	public void setFiscalResponsavel(String fiscalResponsavel) {
		this.fiscalResponsavel = fiscalResponsavel;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}	

	public String getNumero() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		return cal.get(Calendar.YEAR) + "" + 
			   cal.get(Calendar.MONTH + 1) + "" + 
			   cal.get(Calendar.DATE) + "" + 
			   cal.get(Calendar.HOUR_OF_DAY) + "" +
			   cal.get(Calendar.MINUTE) + "" +
			   cal.get(Calendar.SECOND) + "" +
			   fornecedor.getCnpj();
	}
}
