package aplicacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Produto;
import modelo.Professor;
import modelo.Sexo;

public class Cadastro {
	protected static EntityManager manager;
	public Cadastro(){
		Pessoa p1;
		Produto pr1;
		
		// INSTANCIAÇÃO DO MANAGER--------------------------------
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
		manager = factory.createEntityManager();

		
//		PERSISTÊNCIA DOS OBJETOS NO BANCO-----------------------
		manager.getTransaction().begin();
		p1 = new Pessoa("joao", Sexo.MASCULINO);
		manager.persist(p1);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		p1 = new Pessoa("maria", Sexo.FEMININO);
		manager.persist(p1);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		p1 = new Pessoa("jose", Sexo.MASCULINO);
		manager.persist(p1);	
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		p1 = new Aluno("paulo",Sexo.MASCULINO,9);
		manager.persist(p1);	
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		p1 = new Professor ("fausto", Sexo.MASCULINO, 1000.00);
		manager.persist(p1);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		pr1 = new Produto ("Arroz", 3.50, 50);
		manager.persist(pr1);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		pr1 = new Produto ("Feijao", 6.50, 50);
		manager.persist(pr1);
		manager.getTransaction().commit();
		
		
		
		manager.close();
		factory.close();
		System.out.println("fim da aplicação");
	}
	
	
	//=================================================
	public static void main(String[] args) {
		new Cadastro();
	}
	

}
