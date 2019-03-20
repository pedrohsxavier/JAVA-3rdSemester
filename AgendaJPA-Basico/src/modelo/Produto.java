package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	private String nome;
	private double preco;
	private int estoque;
	
	public Produto() {}
	
	public Produto(String n, double p, int e) {
		this.nome = n;
		this.preco = p;
		this.estoque = e;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + "]";
	}

	
	
}
