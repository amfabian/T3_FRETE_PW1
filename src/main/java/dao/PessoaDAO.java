package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classes.inheritance.joined.Pessoa;
import util.JPAUtil;


public class PessoaDAO {

	private EntityManager em;

	public PessoaDAO() {
	}
	
	
	
	public boolean cadastrar(Pessoa entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao cadastrar nova Pessoa. Revertendo alteracoes");
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	
	public boolean atualizar(Pessoa entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao atualizar a pessoa. Revertendo alteracoes");
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	
	public boolean excluir(long id) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			Pessoa entity = em.find(Pessoa.class, id);
			em.remove(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao excluir a pessoa. Revertendo alteracoes");
				em.getTransaction().rollback();
			}
			return false;
		} 
	}
	

	
	public List<Pessoa> listarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Pessoa> query = em.createQuery("SELECT obj FROM Pessoa obj", Pessoa.class);
			List<Pessoa> pessoas = query.getResultList();
			return pessoas;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao listar todas as pessoa. Imprimindo erro:");
			e.printStackTrace();
			return null;
		} 
	}

	
	public Pessoa pesquisarKey(long id) {
		try {
			em = JPAUtil.getEntityManager();
			Pessoa entity = em.find(Pessoa.class, id);
			return entity;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao pesquisar pelo id da pessoa");
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	
	public Pessoa pesquisarCPF(String cpf) {
		try {
			em = JPAUtil.getEntityManager();
			Pessoa entity = em.find(Pessoa.class, cpf);
			return entity;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao pesquisar pelo cpf da pessoa");
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

}
