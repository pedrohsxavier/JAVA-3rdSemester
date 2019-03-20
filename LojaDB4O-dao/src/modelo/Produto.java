package modelo;

import dao.IDInterface;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

public class Produto implements IDInterface{
	private int id;
	private String nome;
	private double preco;
	private int estoque;
	private double peso;	
	private Prateleira prateleira;

	public Produto () {}
	
	public Produto(String nome,  double preco, int estoque, double peso) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.estoque=estoque;
		this.peso=peso;
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
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Prateleira getPrateleira() {
		return prateleira;
	}
	public void setPrateleira(Prateleira prateleira) {
		this.prateleira = prateleira;
	}
	
	@Override
	public String toString() {
		return "Produto [id=" +id+ (nome != null ? ",nome=" + nome + ", " : "")
				+ "preco=" + preco + ", estoque=" + estoque + ", peso="
				+ peso + ", " 
				+ (prateleira != null ? "prateleira=" + prateleira.getId() : "sem prateleira")
				+ "]";
	}


}
