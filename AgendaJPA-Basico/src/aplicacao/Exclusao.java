package aplicacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Pessoa;

public class Exclusao {
	protected static EntityManager manager;
	public Exclusao(){
		Pessoa p1;
	
		// INSTANCIA��O DO MANAGER--------------------------------
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
		manager = factory.createEntityManager();
		
//		EXCLUS�O DOS OBJETOS-----------------------------------------
		manager.getTransaction().begin();
		p1 = manager.getReference(Pessoa.class, 2);  //maria
		manager.remove(p1);
		manager.getTransaction().commit();	

		manager.close();
		factory.close();
		System.out.println("fim da aplica��o");
	}
	
	
	//=================================================
	public static void main(String[] args) {
		new Exclusao();
	}
	

}
