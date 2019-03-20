package aplicacao;
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Pessoa;

public class Exclusao {
	protected static EntityManager manager;

	public Exclusao() throws Exception{
		// INSTANCIAÇÃO DO MANAGER--------------------------------
		//		EntityManagerFactory factory = 
		//				Persistence.createEntityManagerFactory("agenda-hibernate");

		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("agenda-eclipselink");
		manager = factory.createEntityManager();

		
		//EXCLUSÃO 
		Query query;
		Pessoa p1;
		manager.getTransaction().begin();		
		query = manager.createQuery("select p from Pessoa p where p.nome='maria'");
		p1 = (Pessoa) query.getSingleResult();	
		manager.remove(p1);
		manager.getTransaction().commit();	

		manager.close();
		factory.close();
		System.out.println("fim da aplicação");

	}


	//=================================================
	public static void main(String[] args) {
		try {
			new Exclusao() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
