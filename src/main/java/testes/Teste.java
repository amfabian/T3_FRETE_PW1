package testes;

import classes.inheritance.joined.Cliente;
import dao.ClienteDAO;

public class Teste {
	public static void main(String[] args) {
		Cliente c1  = new Cliente();
		c1.setCpf("01235488001");
		c1.setEndereco("Rua Vasco Alves");
		c1.setNome("Alexandre2");
		c1.setTelefone("51999403450");
		ClienteDAO objDAO = new ClienteDAO();
		if(objDAO.cadastrar(c1)) {
			System.out.println("Cliente inserido com sucesso!");
		} else {
			System.out.println("Erro ao inserir o cliente!");
		}
	
		System.out.println("\nLISTAR TODOS");
		for (Cliente c : objDAO.listarTodos())
			System.out.printf(c.toString());
	}

}
