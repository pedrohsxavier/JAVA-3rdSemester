package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import modelo.Produto;
import modelo.Venda;

public class atualizacaoPersimista {
	protected static EntityManager manager;
	Scanner teclado = new Scanner(System.in);

	public atualizacaoPersimista(){
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("loja-eclipselink");
		manager = factory.createEntityManager();

		Produto p=null;
		int id=1;

		try{
			manager.getTransaction().begin();	
			p = manager.find(Produto.class, id);
			System.out.println(p);	
			//=======================================================
			//lock pessimista (bloqueio total)
			manager.lock(p,LockModeType.PESSIMISTIC_WRITE); 
			//=======================================================
			//	 		usando timeout (alguns sgbd nao aceitam)
			//			Map<String,Object> props = 	new HashMap<String,Object>();
			//			props.put ("javax.persistence.lock.timeout", 1000);  //aguarda a primeira transacao terminar
			//		//	props.put ("javax.persistence.lock.timeout", 0);  //nao aguarda a primeira transacao terminar
			//			manager.lock(p,LockModeType.PESSIMISTIC_WRITE,props);


			System.out.println("digite o nome do vendedor para concluir a venda");
			String nome = teclado.nextLine();
			Venda v = new Venda(nome,p);	
			manager.persist(v);
			manager.getTransaction().commit();		
			System.out.println("commit realizado: " + p);	

		}
		catch(RollbackException e){
			System.out.println("transação cancelada");
			System.out.println("tentar outra vez ");
		}
		catch(Exception e){
			System.out.println("transação cancelada lock negado"+e.getMessage());
		}
		System.out.println("--FIM--");

		manager.close();
		factory.close();
	}




	//=================================================
	public static void main(String[] args) {
		new atualizacaoPersimista();
	}
	//=================================================

}
