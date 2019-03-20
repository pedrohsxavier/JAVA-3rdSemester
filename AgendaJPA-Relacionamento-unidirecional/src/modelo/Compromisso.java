package modelo;
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compromisso {
	@Id	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data; 
	private String descricao;
	
	public Compromisso() {}
	
	public Compromisso(String datastr, String descricao) {
		this.descricao = descricao;
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			this.data = f.parse(datastr);
		} 
		catch (ParseException e) 
		{ throw new RuntimeException("data invalida");}
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return f.format(data) + " ---> " + descricao ;		
	}


	
}
