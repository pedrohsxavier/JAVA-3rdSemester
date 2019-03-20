package aplicacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Pessoa;

public class Atualizacao {
	protected static EntityManager manager;
	public Atualizacao(){
		Pessoa p1;
	
		// INSTANCIA��O DO MANAGER--------------------------------
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
		manager = factory.createEntityManager();

		
//		ALTERA��O DOS OBJETOS------------------------------------
		manager.getTransaction().begin();
		p1 = manager.getReference(Pessoa.class, 1);   //obs: getReference() nao le todos os atributos
		p1.setNome("joana");
		manager.merge(p1);
		manager.getTransaction().commit();
				
		
		
		manager.close();
		factory.close();
		System.out.println("fim da aplica��o");
	}
	
	
	//=================================================
	public static void main(String[] args) {
		new Atualizacao();
	}
	

}
