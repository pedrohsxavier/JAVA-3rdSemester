

import java.util.ArrayList;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
public class Pessoa {
	protected int id;
	protected String nome;
	protected ArrayList<Telefone> telefones = new ArrayList<Telefone>();
	//protected int idade=18;
	
	public Pessoa(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	public void adicionarTelefone(Telefone t){
		t.setPessoa(this);
		this.telefones.add(t);
	}
	
	public void removerTelefone(Telefone t){
		t.setPessoa(null);
		this.telefones.remove(t);
	}
	
	public Telefone localizarTelefone(String num){
		for(Telefone t : telefones)
			if (t.getNumero().equals(num))
				return t;
		return null;
	}
	

	
	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + "\n   telefones=" + telefones +
					//", idade=" + idade +
				"]";
	}






	
}
