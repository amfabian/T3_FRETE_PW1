package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fretes.Frete;
import util.JPAUtil;

public class FreteDAO {

	private EntityManager em;
	
	public boolean cadastrar(Frete entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao cadastrar novo Frete. Revertendo alteracoes");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	public boolean atualizar(Frete entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao atualizar o Frete\n" + entity + "\nRevertendo alteracoes");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	public boolean excluir(long id) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			Frete entity = em.find(Frete.class, id);
			em.remove(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao excluir o Frete com id " + id + "\nRevertendo alteracoes");
			if (em.getTransaction().isActive()) {				
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	public List<Frete> listarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Frete> query = em.createQuery("SELECT obj FROM Frete obj", Frete.class);
			List<Frete> fretes = query.getResultList();
			return fretes;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao listar todas os fretes. \nImprimindo Pilha:\n");
			e.printStackTrace();
			return null;
		} 
	}

	public Frete pesquisarKey(long id) {
		try {
			em = JPAUtil.getEntityManager();
			Frete entity = em.find(Frete.class, id);
			return entity;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao pesquisar pelo id do Frete");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public Frete pesquisarCidade(String cidade) { 
		try {
			FreteDAO objDAO = new FreteDAO();
			for (Frete frete : objDAO.listarTodos()) {
				if(frete.getCidadeOrigem().equals(cidade) | frete.getCidadeDestino().equals(cidade) ) {
				//	System.out.println("\nCIDADE MATCH");
					return frete;
				}
			}
		} catch (RuntimeException e) {
			System.out.println("\nErro ao listar todas os Fretes. \nImprimindo erro:\n");
			e.printStackTrace();
			return null;
		}
		return null;
	}
	

}
