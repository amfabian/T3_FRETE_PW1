package classes.inheritance.joined;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// Relacionamento Herança
// Uma tabela para cada classe JOINED
// Escolhi essa opçao, pois uma unica tabela me parece que ficara com muitos campos, o que nao me pareceu adequado.
//A estrategia JOINED e' a que me parece mais proxima para abstracao extends do JAVA.
//
//idPessoa como Primary Key. 

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPessoa")
	private Long idPessoa;
	private String nome;

	public Pessoa() {}
	public Pessoa(Long idPessoa, String nome) {
		this.idPessoa = idPessoa;
		this.nome = nome;
	}

	//GETTERS e SETTERS 
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Pessoa [id: " + idPessoa + ", nome: " + nome + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idPessoa, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(idPessoa, other.idPessoa) && Objects.equals(nome, other.nome);
	}


}
