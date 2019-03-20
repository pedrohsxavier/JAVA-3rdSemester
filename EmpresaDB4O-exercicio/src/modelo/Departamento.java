package modelo;
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

import java.util.ArrayList;
import java.util.List;


public class Departamento  {
	private int id;		//gerar automaticamente
	private String nome;
	private List<Empregado> empregados = new ArrayList<Empregado>();   
	private Empregado chefe;

	public Departamento(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int cod) {
		this.id = cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionarEmpregado(Empregado emp) {
		empregados.add(emp);
		emp.setDepartamento(this);
	}
	public void removerEmpregado(Empregado emp) {
		empregados.remove(emp);
		emp.setDepartamento(null);
	}
	public List<Empregado> getEmpregados() {
		return empregados;
	}

	public Empregado getChefe() {
		return chefe;
	}
	public void setChefe(Empregado chefe) {
		this.chefe = chefe;
	}


	public String toString(){
		return 
				"Codigo:" + getId() + " , Nome:" + getNome()  +  
				"\n Chefe:" + ((chefe!=null) ? chefe :"") + 
				"\n Empregados:" + ((empregados.size()>0) ? empregados : "");
	}
}