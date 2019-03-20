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


public class Deletar {
	protected static ObjectContainer manager;

	public Deletar(){
		abrirBancoLocal();

		deletar();

		manager.close();	
		System.out.println("\n\n aviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(3);  // 0,1,2,3...
		config.common().objectClass(Autor.class).cascadeOnDelete(true);;
		config.common().objectClass(Autor.class).cascadeOnUpdate(true);;
		config.common().objectClass(Autor.class).cascadeOnActivate(true);
		config.common().objectClass(Livro.class).cascadeOnDelete(false);;
		config.common().objectClass(Livro.class).cascadeOnUpdate(true);;
		config.common().objectClass(Livro.class).cascadeOnActivate(true);	
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
	}

	public void deletar(){
		System.out.println("Excluindo...");
		
		//localizar livro titulo java
		Livro l = localizarLivro("java");	
		if(l != null) {
			
			for(Autor a: l.getAutores()) {
				a.removerLivro(l);
				
				manager.store(a);
				manager.commit();
			}
			
			manager.delete(l);
			manager.commit();
		}else
			System.out.println("inexistente");
	}
		
	//remover os autores do livro
	public Livro localizarLivro (String nome){
		Query q = manager.query();
		q.constrain(Livro.class);		
		q.descend("titulo").constrain(nome);
		List<Livro> resultados = q.execute();

		if(resultados.size()>0) {
			Livro l = resultados.get(0);
			return l;
		}else
			return null;
	} 


	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

