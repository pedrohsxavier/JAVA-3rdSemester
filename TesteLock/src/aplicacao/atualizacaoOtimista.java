package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import modelo.Produto;
import modelo.Venda;

public class atualizacaoOtimista {
	protected static EntityManager manager;
	Scanner teclado = new Scanner(System.in);

	public atualizacaoOtimista(){
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("loja-eclipselink");
		manager = factory.createEntityManager();

		Produto p=null;
		int id=1;
		boolean sair=false;
		
		while (sair==false)
		//for(int i=0; i<50; i++)
		{
			try{
				manager.getTransaction().begin();	
				p = manager.find(Produto.class, id);
				System.out.println(p);
				//=======================================================
				//obs: ocultar @version na classe Produto desabilita o controle de concorrencia de produto
				//obs: não é mais necessário o lock otimista abaixo, pois é default
				//manager.lock(p,LockModeType.OPTIMISTIC_FORCE_INCREMENT);  
				//=======================================================
				
				System.out.println("digite o nome do vendedor para concluir a venda");
				String nome = teclado.nextLine();
				Venda v = new Venda(nome,p);	
				manager.persist(v);
				manager.getTransaction().commit();		//gera exceção
				
				System.out.println("commit realizado: " + p);	
				sair=true;
			}
			catch(RollbackException e){
				System.out.println("transação cancelada devido a um outro commit  ");
				System.out.println("tentar outra vez ");
				sair=false;
			}
			catch(Exception e){
				System.out.println("problema no commit");
				sair=true;
			}
		}//while
		System.out.println("--FIM--");
		
		manager.close();
		factory.close();
	}


	//=================================================
	public static void main(String[] args) {
		new atualizacaoOtimista();
	}
	//=================================================

}
