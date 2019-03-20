package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import aplicacao.IDInterface;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
public class Pessoa implements IDInterface{
	protected int id;
	protected String nome;
	private Date dtcadastro = new Date();
	protected ArrayList<Telefone> telefones = new ArrayList<Telefone>();
	//protected int idade=18;

	public Pessoa(String nome) {
		super();
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
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String classe = getClass().getSimpleName() + ":";
		String texto =  String.format("%10s", classe) + " id="+id + 
				", nome=" + String.format("%10s", nome) + 
				", nascimento=" + f.format(dtcadastro) ;

		texto += ", telefones:";
		for(Telefone t : telefones)
			texto+= t.getNumero() + ", ";

		return texto;
	}







}
