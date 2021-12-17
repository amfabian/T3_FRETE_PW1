package testes;

import java.util.ArrayList;
import java.util.Date;

import classes.inheritance.joined.Cliente;
import dao.ClienteDAO;
import dao.FreteDAO;
import dao.ItemFreteDAO;
import fretes.Frete;
import fretes.ItemFrete;
import util.Situacao;

public class TestesMapeamentos {
	public static void main(String[] args) {
		Cliente c1  = new Cliente();
		ClienteDAO objDAO = new ClienteDAO();
		System.out.println("\n***************************************************************");
		System.out.println("\n***************************************************************");
		System.out.println("\n\nINICIO DOS TESTES COM CLIENTE");
		//INSERINDO CLIENTE
		System.out.println("\nINSERINDO CLIENTES\n");
				
				c1.setCpf("01235488001");
				c1.setEndereco("Rua Vasco Alves");
				c1.setNome("Alexandre2");
				c1.setTelefone("51999403450");
				if(objDAO.cadastrar(c1)) {
					System.out.println("Cliente inserido com sucesso!");
				} else {
					System.out.println("Erro ao inserir o cliente!");
					}
				Cliente c2  = new Cliente();
				
				c2.setCpf("11111111111");
				c2.setEndereco("Rua Cristovao Colombo");
				c2.setNome("Eduardo");
				c2.setTelefone("51999886677");
				if(objDAO.cadastrar(c2)) {
					System.out.println("Cliente inserido com sucesso!");
				} else {
					System.out.println("Erro ao inserir o cliente!");
					}
				

		System.out.println("\n---------------------------------------------------");
		
		//PESQUISANDO POR CLIENTE USANDO CHAVE
		System.out.println("\nPESQUISANDO CLIENTES UTILIZANDO ID");
				Cliente c3  = new Cliente();
				Long key = (long) 1;
				c3 = objDAO.pesquisarKey(key);
				System.out.println("Imprimindo Cliente com id: " + key + "\n"+ c3);
	//MAIS TESTES DE PESQUISA UTILIZANDO IDs
	//TRECHO COMENTADO POIS CAUSARA ERRO QUANDO BANCO ESTIVER VAZIO
	//DEPOIS DE RODAR ALGUMAS VEZES (3) PODE DESCOMENTAR O TRECHO ABAIXO		
//				key = 3L;
//				c3 = objDAO.pesquisarKey(key);
//				System.out.println("Imprimindo Cliente com id: " + key + "\n"+ c3);
//				key = 5L;
//				c3 = objDAO.pesquisarKey(key);
//				System.out.println("Imprimindo Cliente com id: " + key + "\n"+ c3);

		System.out.println("\n---------------------------------------------------");
		
		//ATUALIZANDO CLIENTE PESQUISADO ^
		System.out.println("\nATUALIZANDO ULTIMO CLIENTE PESQUISADO ^^");
		System.out.println("\nAtualizando o nome do cliente com a chave (" + key + ") de " + c3.getNome()+  " para Outro Nome");
		c3.setNome("Outro Nome");
				if(objDAO.atualizar(c3)) {			
					System.out.println("Cliente atualizado com sucesso: \n" + c3);		
				}else {
					System.out.println("Erro ao atualizar o cliente!");
				}

		System.out.println("\n---------------------------------------------------");
				String cpf = "11111111111";
		System.out.println("\nPESQUISANDO CLIENTE UTILIZANDO O CPF " + cpf);
				c3 = objDAO.pesquisarCPF(cpf);
				if(c3 == null) {
					System.out.println("Cliente não encontrado");
				} else {
					System.out.println("Imprimindo Cliente pesquisado por CPF: " + c3);
				}
		System.out.println("\n---------------------------------------------------");
		//LISTANDO TODOS
		System.out.println("\nLISTAR TODOS");
				for (Cliente c : objDAO.listarTodos()) {
		
					System.out.printf(c.toString());
				}
				
				System.out.println("\n***************************************************************");
				System.out.println("\n***************************************************************");
				System.out.println("\n\nINICIO DOS TESTES COM FRETE");
				//INSERINDO FRETE
				System.out.println("\nINSERINDO FRETE");
				Frete frete = new Frete();
				FreteDAO objFreteDAO = new FreteDAO();							
				frete.setValor((double) 100);
				frete.setCidadeOrigem("Porto Alegre");
				frete.setCidadeDestino("Canoas");
				frete.setSituacao(Situacao.EM_ANDAMENTO);
				frete.setCliente(objDAO.pesquisarCPF("11111111111"));
				frete.setData(new Date());
				ItemFrete item1 = new ItemFrete();
				item1.setDescricao("chocolate");
				item1.setPeso((double) 11);
			
				ItemFrete item2 = new ItemFrete();
				item2.setDescricao("suco");
				item2.setPeso((double) 20);
			
				ArrayList<ItemFrete> itens = new ArrayList<ItemFrete>();
				if(itens.add(item1)) {
					System.out.println("\nItem 1 add com suceso");}
				if(itens.add(item2)) {
					System.out.println("Item 2 add com suceso");}
				frete.setItens(itens);				
				if(objFreteDAO.cadastrar(frete)) {
					System.out.println("\nFrete inserido com sucesso!");
				} else {
					System.out.println("\nErro ao inserir o frete!");
				}

		
		//PESQUISANDO POR FRETE USANDO CHAVE/ID
		System.out.println("\nPESQUISANDO FRETE USANDO ID");
				Frete frete2  = new Frete();
				key = (long) 1;
				frete2 = objFreteDAO.pesquisarKey(key);
				System.out.println("Imprimindo Frete com id: " + key + "\n"+ frete2);
				
//MAIS TESTES DE PESQUISA UTILIZANDO IDs
//TRECHO COMENTADO POIS CAUSARA ERRO QUANDO BANCO ESTIVER VAZIO
//DEPOIS DE RODAR ALGUMAS VEZES (3) PODE DESCOMENTAR O TRECHO ABAIXO		
//				key = (long) 2;
//				frete2 = objFreteDAO.pesquisarKey(key);
//				System.out.println("Imprimindo Frete com id: " + key + "\n"+ frete2);
//				key = (long) 3;
//				frete2 = objFreteDAO.pesquisarKey(key);
//				System.out.println("Imprimindo Frete com id: " + key + "\n"+ frete2);
//				System.out.println("\n---------------------------------------------------");
		
		//Atualizando Frete
		System.out.println("\nATUALIZANDO FRETE COM A ULTIMA CHAVE PESQUISADA ^^ ");
		
				frete2.setCidadeOrigem("Imbe");
				System.out.println("\nAtualizando a cidade do Frete com a chave (" + key + ") de " + frete2.getCidadeOrigem() +  " para Imbe");
				if(objFreteDAO.atualizar(frete2)) {
					System.out.println("Frete atualizado com sucesso!");		
				}else {
					System.out.println("Erro ao atualizar o frete!");
				}
				frete2 = objFreteDAO.pesquisarKey(key);
				System.out.println("Imprimindo Frete ATUALIZADO com chave: " + key + "\n"+ frete2);
				
		System.out.println("\n---------------------------------------------------");
		//PESQUISANDO POR FRETE USANDO CHAVE/ID
		System.out.println("\nPESQUISANDO FRETE USANDO CIDADE");
				String cidade = "Imbe";
				frete2 = objFreteDAO.pesquisarCidade(cidade);
				if(frete2 == null) {
					System.out.println("Frete não encontrado");
				} else {
					System.out.println("Imprimindo Frete pesquisando pela cidade (" + cidade + "): \n" + frete2);
				}

				System.out.println("\n---------------------------------------------------");
		//LISTANDO TODOS
		System.out.println("\nLISTAR TODOS");
				for (Frete f : objFreteDAO.listarTodos()) {
					System.out.printf(f.toString());
					System.out.printf("\n");
				}

				System.out.println("\n***************************************************************");
				System.out.println("\n***************************************************************");
				System.out.println("\n\nINICIO DOS TESTES COM ITENS DE FRETE");
				
				System.out.println("\nINSERINDO ITEM FRETE");
				ItemFrete itemfrete1 = new ItemFrete();
				ItemFreteDAO objItemDAO = new ItemFreteDAO();
				itemfrete1.setDescricao("laranja");
				itemfrete1.setPeso((double) 10);
				System.out.println("\nImprimindo item1:  " + itemfrete1);
				if(objItemDAO.cadastrar(itemfrete1)) {
					System.out.println("Item Frete inserido com sucesso!");
				} else {
					System.out.println("Erro ao inserir o ItemFrete!");
					}		
				
				System.out.println("\n---------------------------------------------------");
				System.out.println("\nPESQUISANDO ITEM FRETE PELA ID");
				itemfrete1 = objItemDAO.pesquisarKey(1L);
				System.out.println("Imprimindo Item Frete com id 1: \n" + itemfrete1);
				System.out.println("\n---------------------------------------------------");
				System.out.println("\nATUALIZANDO ITEM FRETE PESQUISADO ACIMA ^^ ");
				itemfrete1.setPeso((double) 22);
				objItemDAO.atualizar(itemfrete1);
				System.out.println("Imprimindo Item Frete atualizado com novo peso: \n" + itemfrete1);
				System.out.println("\n---------------------------------------------------");
				System.out.println("\nPESQUISANDO ITEM FRETE PELA DESCRICAO");
				String pesq = "laranja";
				itemfrete1 = objItemDAO.pesquisarDescricao(pesq);
				if(itemfrete1 == null) {
					System.out.println("Item de Frete não encontrado");
				} else {
					System.out.println("Imprimindo Item Frete pesquisando pela descricao (" + pesq + "): \n" + itemfrete1);
				}
				System.out.println("\n---------------------------------------------------");
				
				System.out.println("\nLISTAR TODOS");
				for (ItemFrete i : objItemDAO.listarTodos()) {
					System.out.printf(i.toString());
					System.out.printf("\n");
				}

				
				
				
	}

}
