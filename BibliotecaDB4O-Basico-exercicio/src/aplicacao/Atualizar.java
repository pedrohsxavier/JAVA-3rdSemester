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


public class Atualizar {
	protected static ObjectContainer manager;

	public Atualizar(){
		abrirBancoLocal();

		alterar();
		
		manager.close();	
		System.out.println("\n\n aviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void abrirBancoLocal(){
		}
	
	public void alterar(){
		System.out.println("Alterando...");
		
		//localizar livro titulo java
	
		
		//localizar autor nome jose
		
		
		//adicionar autor no livro
		
		
	}
	

	//=================================================
	public static void main(String[] args) {
		new Atualizar();
	}
}

