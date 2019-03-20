package modelo;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import daodb4o.IDInterface;

@Entity
public class Prateleira implements IDInterface{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double pesomaximo;
	@OneToMany(mappedBy="prateleira", cascade=CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<Produto>();

	public Prateleira() {}
	
	public Prateleira(double peso) {
		super();
		this.pesomaximo = peso;
	}

	public void adicionarProduto(Produto p){
		produtos.add(p);
		p.setPrateleira(this);
	}
	public void removerProduto(Produto p){
		produtos.remove(p);
		p.setPrateleira(null);
	}

	public Produto localizarProduto(String nome){
		for(Produto p : produtos){
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}

	public int getTotalProdutos(){
		return produtos.size();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPesoMaximo() {
		return pesomaximo;
	}
	public void setPesoMaximo(double peso) {
		this.pesomaximo = peso;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	@Override
	public String toString() {
		String texto = "Prateleira [id=" + id + ", peso=" + pesomaximo + "kg" ;
		texto += "\n  lista de produtos: ";
		if (produtos.isEmpty())
			texto += "sem produto";
		else 	
			for(Produto p: produtos) 
				texto += "\n  --> " + p.getNome() + " " + p.getPeso() + "kg "; 


		return texto + "\n]";
	}


}



