package dao;

import fretes.Frete;
import pessoas.Cliente;

public interface GenericDAO <T> {
	public boolean cadastrar(T objeto);
	public boolean atualizar(T objeto);
	public boolean excluir(T objeto);
	public boolean listarTodos();
	public T pesquisarKey(T objeto);
	public boolean pesquisarCPF(T objeto);
	

}
