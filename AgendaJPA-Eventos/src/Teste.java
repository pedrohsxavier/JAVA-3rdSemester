
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Teste {
	protected static EntityManager manager;
	public Teste(){
		Pessoa p1;


		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("agenda");
		manager = factory.createEntityManager();

		try{
			// GRAVAÇÃO
			manager.getTransaction().begin();	
			p1 = new Pessoa("joao");
			p1.adicionarTelefone(new Telefone("8888 1111"));
			p1.adicionarTelefone(new Telefone("8888 2222"));
			manager.persist(p1);
			manager.getTransaction().commit();

			manager.getTransaction().begin();	
			p1 = new Pessoa("maria");
			p1.adicionarTelefone(new Telefone("8888 3333"));
			p1.adicionarTelefone(new Telefone("8888 4444"));
			manager.persist(p1);
			manager.getTransaction().commit();

			manager.getTransaction().begin();	
			p1 = new Pessoa("jose");
			manager.persist(p1);				
			manager.getTransaction().commit();

			manager.getTransaction().begin();	
			p1 = new Pessoa("paulo");
			manager.persist(p1);				
			manager.getTransaction().commit();

			manager.getTransaction().begin();	
			Telefone t1 = new Telefone("0000000");
			manager.persist(t1);				
			manager.getTransaction().commit();
			
			manager.clear();


//			//		//ALTERAÇÃO
//			//		
			manager.getTransaction().begin();
			p1 = manager.find(Pessoa.class, 1);
			p1.adicionarTelefone( new Telefone("8888 6666"));

			Telefone t = p1.localizarTelefone("8888 2222");
			if(t!=null) 
				p1.removerTelefone(t);		//remove na memória

			manager.merge(p1);
			manager.getTransaction().commit();

			//					//EXCLUSÃO 
			manager.getTransaction().begin();
			p1 = manager.find(Pessoa.class, 2);
			manager.remove(p1);
			manager.getTransaction().commit();	


			//LISTAR
			System.out.println("\n-------LISTAGEM DE PESSOAS-----------");
			Query query = manager.createQuery("select p from Pessoa p");
			List<Pessoa> resultados = (List<Pessoa>) query.getResultList();
			for(Pessoa p: resultados)
				System.out.println(p);

			System.out.println("\n-------LISTAGEM DE TELEFONES-----------");
			query = manager.createQuery("select t from Telefone t");
			List<Telefone> resultados2 = (List<Telefone>) query.getResultList();
			for(Telefone p: resultados2)
				System.out.println(p);

			manager.close();
		}

		catch(Exception e){
			System.out.println("transação abortada" + e.getMessage());
		}
		System.out.println("fim da aplicação");
	}


	//=================================================
	public static void main(String[] args) {
		new Teste();
	}


}
