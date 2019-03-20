package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Produto;

public class CargaInicial {
	protected static EntityManager manager;

	public CargaInicial(){

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja-eclipselink");
		manager = factory.createEntityManager();

		Produto p1,p2,p3;
		manager.getTransaction().begin();
		p1 = new Produto("arroz", 2.5, 5);
		manager.persist(p1);  
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		p2 = new Produto("feijao", 4.0, 5);
		manager.persist(p2);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		p3 = new Produto("carne", 20.0, 5);
		manager.persist(p3);  
		manager.getTransaction().commit();	
		
		System.out.println("cadastro concluido");
		
		manager.close();
		factory.close();
	}


	public void listar(){
		System.out.println("-----------listagem de Produtos-----------");
		Query query = manager.createQuery("select p from Produto p");
		@SuppressWarnings("unchecked")
		List<Produto> produtos = (List<Produto>) query.getResultList();
		for (Produto p : produtos) {
			System.out.println(p);
		}
	}


	//=================================================
	public static void main(String[] args) {
		new CargaInicial();
	}
	//=================================================

}
