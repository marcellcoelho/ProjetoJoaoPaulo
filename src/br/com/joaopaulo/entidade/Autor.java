package br.com.joaopaulo.entidade;

import java.util.List;

public class Autor implements Comparable<Autor> {

	private String nome;

	private List<Propositura> proposituraList;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Propositura> getProposituraList() {
		return proposituraList;
	}

	public void setProposituraList(List<Propositura> proposituraList) {
		this.proposituraList = proposituraList;
	}
	
	@Override
	public int compareTo(Autor o) {
		return this.getNome().compareToIgnoreCase(o.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
