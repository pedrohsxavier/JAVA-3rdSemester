package modelo;


/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity 
public class Pessoa {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private Date dtcadastro = new Date();

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	//RELACIONAMENTO BIDIRECIONAL
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)       				
	private List<Telefone> telefones = new ArrayList<>();


	//RELACIONAMENTO UNIDIRECIONAL
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER) //nao tem mappedBy
	@JoinColumn(name="idpessoa") 		//cria chave estrangeira da tabela compromisso
	//	@OrderColumn(name="ordem") 	//ordem de insercao
	//	@OrderBy("descricao")   			//tipo de ordenacao dos compromissos
	private List<Compromisso> compromissos = new ArrayList<>();

	
	
	//RELACIONAMENTOS SIMPLES (classe sem chave primaria)
	@ElementCollection
	private List<String> apelidos = new ArrayList<>();

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="tablogradouros", joinColumns=@JoinColumn(name="idpessoa") )	
	private List<Logradouro> logradouros = new ArrayList<>();


	//construtor vazio
	public Pessoa (){}
	public Pessoa(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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

	//------------------------------------------
	public void adicionarCompromisso(Compromisso comp){
		compromissos.add(comp);
	}
	public List<Compromisso> getCompromissos() {
		return compromissos;
	}


	//------------------------------------------
	public void adicionarApelido(String apelido){
		apelidos.add(apelido);
	}
	public List<String> getApelidos() {
		return apelidos;
	}

	//------------------------------------------
	public void adicionarLogradouro(Logradouro e){
		logradouros.add(e);
	}
	public List<Logradouro> getLogradouros() {
		return logradouros;
	}
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String texto ;
		texto = id + ", nome=" + String.format("%10s", nome) + ", "	+ "nascimento=" + f.format(dtcadastro) ; 

		texto += ", telefones:";
		for(Telefone t : telefones)
			texto+= t.getNumero() + ", ";

		texto += "\n   logradouros:"+logradouros;
		texto += "\n   apelidos:"+apelidos;
		texto += "\n   compromissos:"+compromissos;

		return texto;
	}
}
