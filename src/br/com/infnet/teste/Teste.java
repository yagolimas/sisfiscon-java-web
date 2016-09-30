package br.com.infnet.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.infnet.modelo.Endereco;
import br.com.infnet.modelo.Fornecedor;
import br.com.infnet.modelo.Processo;
import br.com.infnet.modelo.enums.UF;
import br.com.infnet.util.JPAUtil;

public class Teste {
	
	public static void main(String[] args){
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();	
		
		Endereco endereco1 = new Endereco();
		Endereco endereco2 = new Endereco();
		
		endereco1.setLogradouro("Rua Mend Sa");
		endereco1.setBairro("Lapa");
		endereco1.setMunicipio("Rio de Janeiro");
		endereco1.setComplemento("Nao possui");
		endereco1.setNumero("35");
		endereco1.setUf(UF.RJ);
		endereco1.setCep("25957-08");
		em.persist(endereco1);
		
		endereco2.setLogradouro("Rua Jardim Seco");
		endereco2.setBairro("Araras");
		endereco2.setMunicipio("Sao Paulo");
		endereco2.setComplemento("Nao possui");
		endereco2.setNumero("110");
		endereco2.setUf(UF.SP);
		endereco2.setCep("25912-008");		
		em.persist(endereco2);
		
		Fornecedor NerdWorld = new Fornecedor();
		Fornecedor InfoLink = new Fornecedor();
		
		NerdWorld.setCnpj("22.268.066/0001-92");
		NerdWorld.setInscricaoMunicipal("309428498438");
		NerdWorld.setRazaoSocial("NerdWorld LTDA");
		NerdWorld.setReceitaBruta(new BigDecimal("10000.00"));	
		NerdWorld.setEndereco(endereco1);
		
		em.persist(NerdWorld);
		
		InfoLink.setCnpj("21.324.078/0001-72");
		InfoLink.setInscricaoMunicipal("2979731732121");
		InfoLink.setRazaoSocial("InfoLink Tecno LTDA");
		InfoLink.setReceitaBruta(new BigDecimal("20000.00"));	
		InfoLink.setEndereco(endereco2);
		em.persist(InfoLink);
		
		Processo processo1 = new Processo();
		Processo processo2 = new Processo();
		Processo processo3 = new Processo();
		
		processo1.setFornecedor(InfoLink);
		processo1.setDataRelato(Calendar.getInstance());		
		processo1.setRelatoFiscalizacao("xpto");
		processo1.setFiscalResponsavel("Bolsonaro");
		
		processo2.setFornecedor(NerdWorld);
		processo2.setDataRelato(Calendar.getInstance());		
		processo2.setRelatoFiscalizacao("xpto");
		processo2.setFiscalResponsavel("Cunha");
		
		processo3.setFornecedor(NerdWorld);
		processo3.setDataRelato(Calendar.getInstance());		
		processo3.setRelatoFiscalizacao("xpto");
		processo3.setFiscalResponsavel("Zinabre");	
		
		em.persist(processo1);
		em.persist(processo2);
		em.persist(processo3);
		
		
		/*Query query = em.createQuery("from Fornecedor where cnpj = :cnpj");
		query.setParameter("cnpj", "23.267.067/0001-92");
		Fornecedor fornecedor = (Fornecedor) query.getSingleResult();
		System.out.print("Fornecedor => " + fornecedor.getRazaoSocial());*/
		
				
		em.getTransaction().commit();
		
		em.close();
	}
}
