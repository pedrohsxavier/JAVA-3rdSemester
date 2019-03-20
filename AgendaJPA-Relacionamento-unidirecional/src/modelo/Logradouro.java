package modelo;

import javax.persistence.Column;

/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */

import javax.persistence.Embeddable;

@Embeddable 

public class Logradouro {
	@Column(length=100)
	private String rua;
	
	@Column(length=30)
	private String bairro;

	public Logradouro() {}
	public Logradouro(String rua, String bairro) {
		this.rua = rua;
		this.bairro=bairro;
	}
	@Override
	public String toString() {
		return  (rua != null ? "rua=" + rua + ", " : "") + (bairro != null ? "bairro=" + bairro : "");
	} 
	
}
