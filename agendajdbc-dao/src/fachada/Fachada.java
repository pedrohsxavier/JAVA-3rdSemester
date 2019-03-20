package fachada;
import java.util.List;

import dao.DAO;
import dao.DAOPessoa;
import dao.DAOTelefone;
import modelo.Pessoa;
import modelo.Telefone;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

public class Fachada {
	private static DAOPessoa daopessoa= new DAOPessoa();
	private static DAOTelefone daotelefone= new DAOTelefone();

	public static void inicializar() {
		DAO.open();  
	}
	public static void finalizar() {
		DAO.close();
	}
	
	public static Pessoa cadastrarPessoa(String nome) throws Exception {
		DAO.begin();	
		Pessoa p;
		p = daopessoa.read(nome);
		if(p!=null)	
			throw new Exception("pessoa ja cadastrada:" + nome);
		
		p = new Pessoa(nome);
		daopessoa.create(p);
		DAO.commit();	
		return p;
	}

	public static Telefone cadastrarTelefone(String nome, String numero) throws Exception{
		DAO.begin();	
		Telefone t = daotelefone.read(numero);
		if(t!=null)	
			throw new Exception("numero ja cadastrado:" + numero);

		Pessoa p = daopessoa.read(nome);
		if(p==null)
			throw new Exception("pessoa inexistente:" + numero);

		t = new Telefone(p,numero);
		daotelefone.create(t);
		DAO.commit();	

		return t;
	}
	public static void alterarPessoa(String nome, String novo) throws Exception {	
		DAO.begin();	
		Pessoa p = daopessoa.read(nome);
		if(p==null)	
			throw new Exception("pessoa nao cadastrada:" + nome);
		
		p.setNome(novo);
		daopessoa.update(p);
		DAO.commit();	
	}
	

	public static void incluirTelefone(String nome, String numero)throws Exception{	
		DAO.begin();	
		Pessoa p = daopessoa.read(nome);
		if(p==null)	
			throw new Exception("pessoa nao cadastrada:" + nome);
		
		Telefone t = new Telefone(numero);
		p.adicionarTelefone(t);
		t.setPessoa(p);
		daotelefone.create(t);   
		DAO.commit();		
	}
	public static void excluirPessoa(String nome) throws Exception {	
		DAO.begin();	
		Pessoa p = daopessoa.read(nome);
		if(p==null)	
			throw new Exception("pessoa nao cadastrada:" + nome);

		daopessoa.delete(p);
		DAO.commit();		
	}
	public static String listarPessoas()  {	
		List<Pessoa> aux = daopessoa.readAll();	
		String texto = "\nListagem de pessoas:";
		if (aux.isEmpty())
			texto += "não tem pessoa cadastrada";
		else {	
			for(Pessoa p: aux) {
				texto += "\n" + p; 
			}
		}
		return texto;	
	}
	public static String listarTelefones()  {	
		List<Telefone> aux = daotelefone.readAll();		
		String texto = "\nListagem de telefones:";
		if (aux.isEmpty())
			texto += "não tem telefone cadastrado";
		else {	
			for(Telefone t: aux) {
				texto += "\n" + t; 
			}
		}
		return texto;	
	}
	public static String consultarPessoaSemTelefone() {
		List<Pessoa> aux = daopessoa.consultarPessoaSemTelefone();		
		String texto = "\nListagem de pessoas sem telefone:";
		if (aux.isEmpty())
			texto += "não encontrou";
		else {	
			for(Pessoa p: aux) {
				texto += "\n" + p; 
			}
		}
		return texto;	
	}
}

