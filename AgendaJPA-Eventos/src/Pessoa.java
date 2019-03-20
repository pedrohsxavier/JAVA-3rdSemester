

/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */



import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity 

@EntityListeners( TrataEventos.class )  // CLASSE QUE IMPLEMENTA OS EVENTOS

public class Pessoa {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private LocalDate dtcadastro = LocalDate.of(2000,1,1);
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	private List<Telefone> telefones = new ArrayList<Telefone>();

	//construtor vazio
	public Pessoa (){}

	public Pessoa(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
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


	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", " + (nome != null ? "nome=" + nome + ", " : "")
				+ (telefones != null ? "telefones=" + telefones : "") + "]";
	}

	public LocalDate getDtcadastro() {
		return dtcadastro;
	}


	
}
