package testes;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import classes.inheritance.joined.Cliente;
import classes.onetomany.Frete;
import classes.onetomany.ItemFrete;
import dao.ClienteDAO;
import dao.FreteDAO;
import dao.ItemFreteDAO;
import util.Situacao;


public class NovoTeste {
	public static void main(String[] args) {
		System.out.println("\n***************************************************************");
		Cliente c1  = new Cliente();
		ClienteDAO objDAO = new ClienteDAO();
		Long key = (long) 1;
		c1 = objDAO.pesquisarKey(key);		
		System.out.println("\n\nINICIO DOS TESTES COM FRETE");
		//INSERINDO FRETE
		System.out.println("\nINSERINDO FRETE");
		Frete frete = new Frete();
		FreteDAO objFreteDAO = new FreteDAO();							
		frete.setValor((double) 100);
		frete.setCidadeOrigem("Porto Alegre");
		frete.setCidadeDestino("Canoas");
		frete.setSituacao(Situacao.EM_ANDAMENTO);
		frete.setCliente(c1);
		frete.setData(new Date());
		if(objFreteDAO.cadastrar(frete)) {
			System.out.println("\nFrete inserido com sucesso: " + frete);
		} else {
			System.out.println("\nErro ao inserir o frete!");
		}
		
		System.out.println("\n***************************************************************");
		key = (long) 2;
		c1 = objDAO.pesquisarKey(key);
		
		frete.setValor((double) 90);
		frete.setCidadeOrigem("Porto Alegre");
		frete.setCidadeDestino("Imbe");
		frete.setSituacao(Situacao.CANCELADO);
		frete.setCliente(c1);
		frete.setData(new Date());
		if(objFreteDAO.cadastrar(frete)) {
			System.out.println("\nFrete inserido com sucesso: " + frete);
		} else {
			System.out.println("\nErro ao inserir o frete!");
		}
		
		
		System.out.println("\n***************************************************************");
		key = (long) 3;
		c1 = objDAO.pesquisarKey(key);
		
		frete.setValor((double) 90);
		frete.setCidadeOrigem("Tramandai");
		frete.setCidadeDestino("Viamao");
		frete.setSituacao(Situacao.ENCERRADO);
		frete.setCliente(c1);
		frete.setData(new Date());
		if(objFreteDAO.cadastrar(frete)) {
			System.out.println("\nFrete inserido com sucesso: " + frete);
		} else {
			System.out.println("\nErro ao inserir o frete!");
		}
		
		
		System.out.println("\n***************************************************************");
		key = (long) 4;
		c1 = objDAO.pesquisarKey(key);
		
		frete.setValor((double) 70);
		frete.setCidadeOrigem("Porto Alegre");
		frete.setCidadeDestino("Viamao");
		frete.setSituacao(Situacao.ENCERRADO);
		frete.setCliente(c1);
		frete.setData(new Date());
		if(objFreteDAO.cadastrar(frete)) {
			System.out.println("\nFrete inserido com sucesso: " + frete);
		} else {
			System.out.println("\nErro ao inserir o frete!");
		}
		
		System.out.println("\n***************************************************************");
		key = (long) 5;
		c1 = objDAO.pesquisarKey(key);
		
		frete.setValor((double) 490);
		frete.setCidadeOrigem("Imbe");
		frete.setCidadeDestino("Rio de Janeiro");
		frete.setSituacao(Situacao.EM_ANDAMENTO);
		frete.setCliente(c1);
		frete.setData(new Date());
		if(objFreteDAO.cadastrar(frete)) {
			System.out.println("\nFrete inserido com sucesso: " + frete);
		} else {
			System.out.println("\nErro ao inserir o frete!");
		}
		


		//PESQUISANDO POR FRETE USANDO CHAVE/ID
		System.out.println("\nPESQUISANDO FRETE USANDO ID");
		Frete frete2  = new Frete();
		
		
		frete2 = objFreteDAO.pesquisarKey(1L);
		System.out.println("Imprimindo Frete com id: " + 1 + "\n"+ frete2);
		//LISTANDO TODOS fretes
				System.out.println("\nLISTAR FRETES");
						for (Frete f : objFreteDAO.listarTodos()) {
							System.out.printf(f.toString());
							System.out.printf("\n");
						}

	}

}
