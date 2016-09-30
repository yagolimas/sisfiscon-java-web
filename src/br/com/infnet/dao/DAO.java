package br.com.infnet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.infnet.util.JPAUtil;

@SuppressWarnings("unchecked")
public class DAO<T>{
	
	private final Class<T> classe;
	
	public DAO(Class<T> classe){		
		this.classe = classe; 
	}	
	
	public void adicionar(T t){
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			em.close();
		}	
	}
	
	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();	
		
		try{
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			em.close();
		}		
	}
	
	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> lista = null;
		
		try{
			CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
			query.select(query.from(classe));
			lista = em.createQuery(query).getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			em.close();
		}	
		return lista;
	}
	
	public void remove(T t){		
		EntityManager em = new JPAUtil().getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.remove(em.merge(t));
			em.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			em.close();
		}
	}
	
	public T buscarPorCnpj(String cnpj){
		EntityManager em = new JPAUtil().getEntityManager();		
		T instancia = null;
		
		try{
			Query query = em.createQuery("select f from Fornecedor as f where f.cnpj = :cnpj");
			query.setParameter("cnpj", cnpj);
			List<T> list =  query.getResultList();
			
			if (!list.isEmpty()){				  
				instancia = (T) list.get(0);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			em.close();
		}		
		return instancia;	   
	}
}
