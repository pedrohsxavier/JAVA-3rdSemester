package modelo;


/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pessoa  {
	private int id;
	private String nome;	
	private Date dtcadastro = new Date();
	private List<Telefone> telefones = new ArrayList<Telefone>(); //bidirecional

	public Pessoa(String nome) {
		this.nome = nome;
	}
	public Pessoa(int id, String nome, Date data) {
		this.id = id;
		this.nome = nome;
		this.dtcadastro = data;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void adicionarTelefone(Telefone t){
		telefones.add(t);
		t.setPessoa(this);
	}
	public void removerTelefone(Telefone t){
		telefones.remove(t);
		t.setPessoa(null);
	}
	public Telefone localizarTelefone(String numero){
		for(Telefone t : telefones)
			if(t.getNumero().equals(numero)) 
				return t;
		
		return null;			
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String texto ;
		texto = "  nome=" + nome + ", "	+ "nascimento=" + f.format(dtcadastro) ; 
		
		texto += ", telefones:";
		for(Telefone t : telefones)
			texto+= t.getNumero() + ", ";
	
		return texto;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public Date getDtcadastro() {
		return dtcadastro;
	}

	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}
	
	
}
