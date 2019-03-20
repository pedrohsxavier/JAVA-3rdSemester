package aplicacao;
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Compromisso;
import modelo.Logradouro;
import modelo.Pessoa;
import modelo.Sexo;
import modelo.Telefone;

public class Cadastro {
	protected static EntityManager manager;
	public Cadastro(){
		Query query;
		Pessoa p1;

		// INSTANCIAÇÃO DO MANAGER--------------------------------
		//		EntityManagerFactory factory = 
		//		Persistence.createEntityManagerFactory("agenda-hibernate");
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("agenda-eclipselink");
		
		
		manager = factory.createEntityManager();


		//	PERSISTÊNCIA DOS OBJETOS NO BANCO-----------------------
		manager.getTransaction().begin();	
		p1 = new Pessoa("joao");
		p1.adicionarTelefone(new Telefone("8888 1111"));
		p1.adicionarTelefone(new Telefone("8888 2222"));
		p1.setSexo(Sexo.MASCULINO);
		p1.adicionarLogradouro(new Logradouro("rua1", "58031"));
		p1.adicionarLogradouro(new Logradouro("rua2", "58031"));
		p1.adicionarApelido("joaozinho");
		p1.adicionarApelido("jota");
		p1.adicionarApelido("jojo");
		p1.adicionarApelido("zinho");
		p1.adicionarCompromisso( new Compromisso("07/12/2018","prova de pob"));
		p1.adicionarCompromisso( new Compromisso("05/12/2018","entrevista"));
		manager.persist(p1);
		manager.getTransaction().commit();

		
		manager.getTransaction().begin();	
		p1 = new Pessoa("maria");
		p1.adicionarTelefone(new Telefone("8888 3333"));
		p1.adicionarTelefone(new Telefone("8888 4444"));
		p1.setSexo(Sexo.FEMININO);
		p1.adicionarLogradouro(new Logradouro("rua3", "58031"));
		p1.adicionarLogradouro(new Logradouro("rua4", "58031"));
		p1.adicionarApelido("mariazinha");
		p1.adicionarApelido("ma");
		p1.adicionarCompromisso( new Compromisso("20/12/2018","praia"));
		p1.adicionarCompromisso( new Compromisso("22/12/2018","cinema"));
		p1.adicionarCompromisso( new Compromisso("21/12/2018","chopptime"));
		manager.persist(p1);
		manager.getTransaction().commit();

		
		manager.getTransaction().begin();	
		p1 = new Pessoa("jose");
		p1.setSexo(Sexo.MASCULINO);
		manager.persist(p1);				
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
