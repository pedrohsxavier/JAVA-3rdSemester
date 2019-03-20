/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
public class Aluno extends Pessoa{
	double nota;
	
	public Aluno(int id, String nome, double nota){
		super(id,nome);
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ",\n   telefones=" + telefones + 
						//	", idade=" + idade + 
				", nota=" + nota + "]";
	}










}
