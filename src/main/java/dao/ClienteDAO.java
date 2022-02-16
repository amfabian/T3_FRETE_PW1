package dao;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classes.inheritance.joined.Cliente;
import util.JPAUtil;

public class ClienteDAO {

	private EntityManager em;

	public ClienteDAO() {};

	public boolean cadastrar(Cliente entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao cadastrar novo Cliente. Revertendo alteracoes");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	public boolean atualizar(Cliente entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao atualizar o Cliente\n" + entity + "\nRevertendo alteracoes");
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
			Cliente entity = em.find(Cliente.class, id);
			em.remove(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao excluir o cliente com id " + id + "\nRevertendo alteracoes");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	public List<Cliente> listarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Cliente> query = em.createQuery("SELECT obj FROM Cliente obj", Cliente.class);
			List<Cliente> clientes = query.getResultList();
			return clientes;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao listar todas os clientes. \nImprimindo erro:\n");
			e.printStackTrace();
			return null;
		} 
	}

	public Cliente pesquisarKey(long id) {
		try {
			em = JPAUtil.getEntityManager();
			Cliente entity = em.find(Cliente.class, id);
			return entity;
		} catch (RuntimeException e) {
			System.out.println("\nErro ao pesquisar pelo id do cliente");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Cliente> pesquisarCPF(String cpf) { 

		try {
			return(em.createQuery("SELECT c FROM Cliente c WHERE c.cpf LIKE :custName").setParameter("custName", cpf).getResultList());
			
		} catch (RuntimeException e) {
			System.out.println("\nErro ao pesquisar por CPF. \nImprimindo erro:\n");
			e.printStackTrace();
			return null;
		}
	}
}
