package testes;

import classes.inheritance.joined.Cliente;
import dao.ClienteDAO;

public class Teste {
	public static void main(String[] args) {

		//INSERINDO CLIENTE
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

		//PESQUISANDO POR CLIENTE USANDO CHAVE
		Cliente c2  = new Cliente();
		c2 = objDAO.pesquisarKey(4L);
		System.out.println("Imprimindo Cliente pesqusiado: " + c2);
		c2 = objDAO.pesquisarKey(1L);
		System.out.println("Imprimindo Cliente pesqusiado: " + c2);
		c2 = objDAO.pesquisarKey(16L);
		System.out.println("Imprimindo Cliente pesqusiado: " + c2);

		//ATUALIZANDO CLIENTE PESQUISADO ^
		c2.setNome("Alexandre");
		if(objDAO.atualizar(c2)) {
			System.out.println("Cliente atualizado com sucesso!");		
		}else {
			System.out.println("Erro ao atualizar o cliente!");
		}
		
		c2 = objDAO.pesquisarCPF("11111111111");
		System.out.println("Imprimindo c2: " + c2);
		if(c2 == null) {
			System.out.println("Cliente não encontrado");
		} else {
			System.out.println("Imprimindo Cliente pesqusiado por CPF: " + c2);
			}
		//LISTANDO TODOS
		System.out.println("\nLISTAR TODOS");
		for (Cliente c : objDAO.listarTodos()) {
			System.out.println("FOR");
			System.out.printf(c.toString());
		}
	}

}
