package aplicacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Pessoa;

public class Atualizacao {
	protected static EntityManager manager;
	public Atualizacao(){
		Pessoa p1;
	
		// INSTANCIAÇÃO DO MANAGER--------------------------------
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
		manager = factory.createEntityManager();

		
//		ALTERAÇÃO DOS OBJETOS------------------------------------
		manager.getTransaction().begin();
		p1 = manager.getReference(Pessoa.class, 1);   //obs: getReference() nao le todos os atributos
		p1.setNome("joana");
		manager.merge(p1);
		manager.getTransaction().commit();
				
		
		
		manager.close();
		factory.close();
		System.out.println("fim da aplicação");
	}
	
	
	//=================================================
	public static void main(String[] args) {
		new Atualizacao();
	}
	

}
