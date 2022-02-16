package classes.inheritance.joined;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import classes.onetomany.Frete;

@Entity
@PrimaryKeyJoinColumn(name = "idCliente", referencedColumnName = "idPessoa") 
public class Cliente extends Pessoa implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String endereco;
	private String telefone;
	private String cpf;
	
	@OneToOne (mappedBy = "cliente")
	private Frete frete;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public String toString() {
		return "Cliente [" + super.toString() + ", Endereco: " + endereco + ", Telefone: " + telefone + ", CPF: " + cpf + "]\n";
	} //arrumar para entregar o nome também.

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cpf, endereco, telefone);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(telefone, other.telefone);
	}





}
