package modelo;

/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

public class Empregado {
	private String nome;
	private double salario; 
	private Departamento dep;


	public Empregado (){}
	public Empregado (String n, double s){
		nome=n;
		salario=s;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Departamento getDepartamento() {
		return dep;
	}
	public void setDepartamento(Departamento d) {
		this.dep = d;
	}

	public String toString() {
		return 	"Nome:" + getNome() +
				", Salario:" + getSalario() ;
	}

}
