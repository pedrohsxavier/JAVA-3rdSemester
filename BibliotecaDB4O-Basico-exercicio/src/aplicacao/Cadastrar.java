package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Autor;
import modelo.Livro;


public class Cadastrar {
	protected static ObjectContainer manager;

	public Cadastrar(){
		abrirBancoLocal();
		
		cadastrar();
		
		manager.close();	
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...
		config.common().objectClass(Autor.class).cascadeOnDelete(true);;
		config.common().objectClass(Autor.class).cascadeOnUpdate(true);;
		config.common().objectClass(Autor.class).cascadeOnActivate(true);
		config.common().objectClass(Livro.class).cascadeOnDelete(true);;
		config.common().objectClass(Livro.class).cascadeOnUpdate(true);;
		config.common().objectClass(Livro.class).cascadeOnActivate(true);		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
		IDControl.registrarManager(manager); 
	}

	public void cadastrar(){
		System.out.println("Cadastrando...");
		Autor a1, a2, a3, a4;
		a1 = new Autor("joao");
		a2 = new Autor("maria");
		a3 = new Autor("jose");
		
		Livro l1 = new Livro("java", 10, 2016);
		l1.adicionarAutor(a1);
		l1.adicionarAutor(a2);
		a1.adicionarLivro(l1);
		a2.adicionarLivro(l1);
		manager.store(l1);
		manager.commit();
		
		Livro l2 = new Livro("c", 13, 2015);
		l2.adicionarAutor(a1);
		l2.adicionarAutor(a3);
		a1.adicionarLivro(l2);
		a3.adicionarLivro(l2);
		manager.store(l2);	
		manager.commit();
		
		Livro l3 = new Livro("php", 8, 2015);
		l3.adicionarAutor(a3);
		a3.adicionarLivro(l3);
		manager.store(l3);			
		manager.commit();
	}

	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


