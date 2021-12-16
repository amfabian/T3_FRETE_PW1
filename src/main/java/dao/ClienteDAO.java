package dao;

import java.util.List;

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
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao cadastrar novo Cliente. Revertendo alteracoes");
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
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao atualizar o Cliente\n" + entity + "\nRevertendo alteracoes");
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
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao excluir o cliente com id " + id + "\nRevertendo alteracoes");
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
			if (em.getTransaction().isActive()) {
				System.out.println("\nErro ao pesquisar pelo id do cliente");
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public Cliente pesquisarCPF(String cpf) { 
		try {
			ClienteDAO objDAO = new ClienteDAO();
			for (Cliente c : objDAO.listarTodos()) {
				if(c.getCpf().equals(cpf)) {
					System.out.println("\nCPF MATCH");
					return c;
				}
			}
		} catch (RuntimeException e) {
			System.out.println("\nErro ao listar todas os clientes. \nImprimindo erro:\n");
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
