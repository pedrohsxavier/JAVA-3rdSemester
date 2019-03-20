package modelo;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
public class Aluno extends Pessoa {
	double nota;
	
	public Aluno(String nome, Sexo sexo, double nota){
		super(nome, sexo);
		this.nota = nota;
	}

	@Override
	public String toString() {
		return super.toString()+ "  nota=" + nota ;
	}










}
