package aplicacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Pessoa;

public class Exclusao {
	protected static EntityManager manager;
	public Exclusao(){
		Pessoa p1;
	
		// INSTANCIAÇÃO DO MANAGER--------------------------------
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
		manager = factory.createEntityManager();
		
//		EXCLUSÃO DOS OBJETOS-----------------------------------------
		manager.getTransaction().begin();
		p1 = manager.getReference(Pessoa.class, 2);  //maria
		manager.remove(p1);
		manager.getTransaction().commit();	

		manager.close();
		factory.close();
		System.out.println("fim da aplicação");
	}
	
	
	//=================================================
	public static void main(String[] args) {
		new Exclusao();
	}
	

}
