package modelo;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.util.ArrayList;
import java.util.List;

import aplicacao.IDInterface;

public class Livro implements IDInterface{
	private int id;
	private String titulo;
	private int quantidade;
	private int ano;
	private List<Autor> autores = new ArrayList<Autor>();
	
	public Livro(String titulo, int quant, int ano) {
		this.titulo = titulo;
		this.quantidade = quant;
		this.ano = ano;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void adicionarAutor(Autor a){
		autores.add(a);
	}
	public void removerAutor(Autor a){
		autores.remove(a);
	}

	public Autor localizarAutor(String nome){
		for(Autor a : autores){
			if(a.getNome().equals(nome))
				return a;
		}
		return null;
	}

	public int getTotalAutores(){
		return autores.size();
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getQuant() {
		return quantidade;
	}

	public void setQuant(int quant) {
		this.quantidade = quant;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	@Override
	public String toString() {
		String texto = "Livro: "+id+ ", titulo:" + String.format("%8s", titulo) + ", quantidade: " + quantidade + ", ano: " + ano + ", autores:";
		if (autores.isEmpty())
			texto += "Sem autores";
		else 	
			for(Autor a: autores) 
				texto += a.getNome() + ", " ; 


		return texto ;
	}
}