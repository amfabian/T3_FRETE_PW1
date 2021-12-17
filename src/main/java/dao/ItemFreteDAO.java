package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fretes.Frete;
import fretes.ItemFrete;
import util.JPAUtil;

public class ItemFreteDAO {

	private EntityManager em;
	
	public boolean cadastrar(ItemFrete entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao cadastrar novo Item. Revertendo alteracoes");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}
	
	public boolean atualizar(ItemFrete entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao atualizar o Item\n" + entity + "\nRevertendo alteracoes");
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
			ItemFrete entity = em.find(ItemFrete.class, id);
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

	public List<ItemFrete> listarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<ItemFrete> query = em.createQuery("SELECT obj FROM ItemFrete obj", ItemFrete.class);
			List<ItemFrete> itens = query.getResultList();
			return itens;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao listar todas os itens. \nImprimindo Pilha:\n");
			e.printStackTrace();
			return null;
		} 
	}
	public ItemFrete pesquisarKey(long id) {
		try {
			em = JPAUtil.getEntityManager();
			ItemFrete entity = em.find(ItemFrete.class, id);
			return entity;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao pesquisar pelo id do ItemFrete");
			if (em.getTransaction().isActive()) {				
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public ItemFrete pesquisarDescricao(String descricao) { 
		try {
			ItemFreteDAO objDAO = new ItemFreteDAO();
			for (ItemFrete item : objDAO.listarTodos()) {
				if(item.getDescricao().equals(descricao)) {
				//	System.out.println("\nITEM MATCH");
					return item;
				}
			}
		} catch (RuntimeException e) {
			System.out.println("\nErro ao listar todas os Itens. \nImprimindo erro:\n");
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
