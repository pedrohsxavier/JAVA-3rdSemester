package modelo;


import javax.persistence.Entity;

@Entity
public  class Aluno extends Pessoa {
	private double nota;

	public Aluno() {}
	

	public Aluno(String nome, Sexo sexo, double nota) {
		super(nome, sexo);
		this.nota=nota;
	}



	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return super.toString() + "    "+ nota;
	}

	

	

	
}
