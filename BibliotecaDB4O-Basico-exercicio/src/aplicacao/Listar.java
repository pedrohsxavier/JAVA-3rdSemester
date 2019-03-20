package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import modelo.Autor;
import modelo.Livro;


public class Listar {
	protected static ObjectContainer manager;

	public Listar(){
		abrirBancoLocal();
		
		listarLivros();
		listarAutores();

		manager.close();	
		System.out.println("\n\n aviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration();
		config.common().messageLevel(0);
		config.common().objectClass(Autor.class).cascadeOnDelete(false);
		config.common().objectClass(Autor.class).cascadeOnUpdate(true);
		config.common().objectClass(Autor.class).cascadeOnActivate(true);
		config.common().objectClass(Livro.class).cascadeOnDelete(false);
		config.common().objectClass(Livro.class).cascadeOnUpdate(true);
		config.common().objectClass(Livro.class).cascadeOnActivate(true);
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
		IDControl.registrarManager(manager);	
	}

	public void listarLivros(){
		System.out.println("\nListagem dos Livros:");
		Query q = manager.query();
		q.constrain(Livro.class);
		//q.descend("titulo").orderAscending();
		
		List<Livro> resultados = q.execute();
		
		for(Livro l: resultados)
			System.out.println(l);
	}
	
	public void listarAutores(){
		System.out.println("\nListagem de Autores:");
		Query q = manager.query();
		q.constrain(Autor.class);
		q.descend("nome").orderAscending();
		
		List<Autor> resultados = q.execute();
		
		for(Autor a: resultados)
			System.out.println(a);
	}
	

	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

