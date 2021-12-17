package fretes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import classes.inheritance.joined.Cliente;
import util.Situacao;

@Entity
public class Frete implements Serializable, Comparable<Frete>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFrete;

	private Double valor;
	private String cidadeOrigem;
	private String cidadeDestino;
	//
	//Relacao OneToMany pois o Frete possui Itens que serao transportados e 
	//
	@OneToMany (cascade= CascadeType.PERSIST)
	@JoinColumn(name="idDepartamento")
	private List<ItemFrete> itens;
	//
	//Escolhi a Ordinal pois a enum possui um indice que pode ser utilizado para a escolha das opcoes.
	//Sendo mais importante que se mantenha a ordem
	//
	@Enumerated(EnumType.ORDINAL)
	private Situacao situacao;
	//
	//Relacao OneToOne Unidiriecional pois o Frete possui um Cliente. e nao o contrario \o/
	//Apesar do diagrama do enunciado mostar que e' bidirecional. nao consigo ver sentido na bidirecionalidade da relacao
	//
	@OneToOne (optional=false, cascade= CascadeType.PERSIST)
	@JoinColumn (name="idCliente")
	private Cliente cliente;

	//
	//TIMESTAMP para mostrar tempo e data e manter a legibilidade no banco de dados.
	//
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "data", nullable = false)
	 private Date data;

	public Frete() {}



	public long getIdFrete() {
		return idFrete;
	}

	public void setIdFrete(long idFrete) {
		this.idFrete = idFrete;
	}

	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getCidadeOrigem() {
		return cidadeOrigem;
	}
	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}
	public String getCidadeDestino() {
		return cidadeDestino;
	}
	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public List<ItemFrete> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ItemFrete> itens) {
		this.itens = itens;
	}


	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		String aux = "";
		if(itens != null) {
			for (ItemFrete item : itens) {
				if(item != null)
					aux += item.toString()+"\n"; 
			}
		}

		return "Data do Frete: [" + data + "]\n" + "[" + "id: " + idFrete +  ", Preço: R$" + valor + ", Cidade de Origem: " + cidadeOrigem + ", Cidade de Destino: " + cidadeDestino +  "]\n" + cliente.toString() + aux + 
				"Situação: " + situacao.getDescricao() + "\n";
	}
	@Override
	public int compareTo(Frete o) {
		// TODO Auto-generated method stub
		if(getValor() == o.getValor())
			return 0;
		else if(getValor() < o.getValor())
			return -1;
		return 1;
	}



	@Override
	public int hashCode() {
		return Objects.hash(cidadeDestino, cidadeOrigem, cliente, idFrete, itens, situacao, valor);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frete other = (Frete) obj;
		return Objects.equals(cidadeDestino, other.cidadeDestino) && Objects.equals(cidadeOrigem, other.cidadeOrigem)
				&& Objects.equals(cliente, other.cliente) && idFrete == other.idFrete
				&& Objects.equals(itens, other.itens) && situacao == other.situacao
				&& Objects.equals(valor, other.valor);
	}




}
