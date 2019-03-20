package modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private double preco;
	private int estoque;
	@Version
	private int versao;

	public Produto() {}
	public Produto(String nome,  double preco, int estoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.estoque=estoque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void baixarEstoque() {
		estoque = estoque - 1;
	}

	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", estoque=" + estoque + ", versao=" + versao + "]";
	}

}
