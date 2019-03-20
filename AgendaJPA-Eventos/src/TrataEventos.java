
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */


import java.time.LocalDate;
import java.time.Period;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;

// usada pelas classes Pessoa e Telefone

public class TrataEventos{

	@PrePersist
	public void exibirmsg1(Object obj) throws Exception {
		System.out.println("vai persistir... " + obj);

		//validar telefone
		//		if (obj instanceof Telefone && ((Telefone)obj).getNumero().length() > 9)  
		//			throw new PersistenceException("telefone formato invalido ="+
		//					((Telefone)obj).getNumero() );
	
		if (obj instanceof Pessoa)  {
			Pessoa p = (Pessoa)obj;
			LocalDate hoje = LocalDate.now();
			Period per = Period.between(p.getDtcadastro(), hoje);
			System.out.println("idade=" +per.getYears());
		}

	}

	@PostPersist
	public void exibirmsg2(Object obj) {
		System.out.println("ja persistiu... " + obj);
	}

	@PostLoad
	public void exibirmsg3(Object obj) {
		System.out.println("carregando... " + obj);
	}	

	@PostRemove
	public void exibirmsg4(Object obj) {
		System.out.println("removeu.... " + obj);
	}

}
