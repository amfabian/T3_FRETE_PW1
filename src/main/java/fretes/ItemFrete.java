package fretes;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemFrete implements util.Validador, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItemFrete;
	private String descricao;
	private Double peso;

	public ItemFrete() {}
	
	@Override
	public int hashCode() {
		return Objects.hash(descricao, idItemFrete, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemFrete other = (ItemFrete) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(idItemFrete, other.idItemFrete)
				&& Objects.equals(peso, other.peso);
	}

	public Long getIdItemFrete() {
		return idItemFrete;
	}

	public void setIdItemFrete(Long idItemFrete) {
		this.idItemFrete = idItemFrete;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "ItemFrete [id: " + idItemFrete + "descricao: " + descricao + ", peso: " + peso + "]";
	}

}
