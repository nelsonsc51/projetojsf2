package br.com.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

@Named
public class DaoGeneric<E> {

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private JPAUtil jpaUtil;
	
	public void salvar(E entidade) {
		 
		EntityTransaction entityTransaction  =  entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
				
		entityTransaction.commit();
		
		
	}
	
	public E merge(E entidade) {
		EntityTransaction entityTransaction  =  entityManager.getTransaction();
		entityTransaction.begin();
		
		E retorno = entityManager.merge(entidade);
				
		entityTransaction.commit();
		
		return retorno;
	}
	
	
	public void deletePorId(E entidade) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		//por causa dessa linha é criada uma variável do tipo JPAUtil chamada jpaUtil com inject
		//como era chamado estático, agora irá chamar um objeto
		Object id = jpaUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from "+entidade.getClass().getCanonicalName()+" where id = " +id).executeUpdate();
		
		entityTransaction.commit();

	}
	
	public List<E> getListEntity(Class<E> entidade) {
		EntityTransaction entityTransaction  =  entityManager.getTransaction();
		entityTransaction.begin();

		List<E> retorno = entityManager.createQuery("from "+entidade.getName()).getResultList();
		
		entityTransaction.commit();
		
		return retorno;
	}
	
	//  agora é preciso consultar o objeto todo para realizar o dowload da imagem
	// precisamos do nosso código
	public E consultar(Class<E> entidade, String codigo) {
		
		EntityTransaction  entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		// Precisamos do código para recuperar o objeto pessoa, colocando a classe direta entidade
		E objeto = entityManager.find(entidade, Long.parseLong(codigo));
		entityTransaction.commit();
		
		return objeto; 
	}
	
	
	
}
