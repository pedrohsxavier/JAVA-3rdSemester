package modelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

public class Autor  {   
	private String nome;
	private Date dataCadastro = new Date();
	private List<Livro> livros = new ArrayList<Livro>();

	public Autor(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public List<Livro> getLivros() {
		return livros;
	}
	
	public void adicionarLivro(Livro l){
		livros.add(l);
	}
	public void removerLivro(Livro l){
		livros.remove(l);
	}
	@Override
	public String toString() {
		String texto="Autor: nome:" + String.format("%8s", nome) + ", data cadastro: " + dataCadastro + ", livros:" ;
		if (livros.isEmpty())
			texto += "Sem livros";
		else 	
			for(Livro a: livros) 
				texto += a.getTitulo() + ", "; 
		return texto;

	}		
}