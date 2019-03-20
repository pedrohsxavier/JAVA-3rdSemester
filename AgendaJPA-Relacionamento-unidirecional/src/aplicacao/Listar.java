package aplicacao;
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Pessoa;
import modelo.Telefone;

public class Listar {
	protected static EntityManager manager;
	public Listar(){
		Query query;
		

		// INSTANCIAÇÃO DO MANAGER--------------------------------
		//		EntityManagerFactory factory = 
		//		Persistence.createEntityManagerFactory("agenda-hibernate");
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("agenda-eclipselink");
		manager = factory.createEntityManager();



		//  LISTAGENS----------------------------------------
		System.out.println("Listagem das pessoas");
		query = manager.createQuery("select p from Pessoa p");
		List<Pessoa> resultados = (List<Pessoa>) query.getResultList();
		for(Pessoa p: resultados)
			System.out.println(p+"\n");

		System.out.println("\nListagem dos telefones");
		query = manager.createQuery("select t from Telefone t");
		List<Telefone> resultados2 = (List<Telefone>) query.getResultList();
		for(Telefone tel: resultados2)
			System.out.println(tel);
		
		

		manager.close();
		factory.close();
	}


	//=================================================
	public static void main(String[] args) {
		new Listar();
	}


}
