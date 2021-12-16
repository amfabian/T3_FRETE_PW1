package dao;

import fretes.Frete;


public interface GenericDAO <T> {
	public boolean cadastrar(T objeto);
	public boolean atualizar(T objeto);
	public boolean excluir(T objeto);
	public boolean listarTodos();
	public T pesquisarKey(T objeto);
	public boolean pesquisarCPF(T objeto);
	

}
