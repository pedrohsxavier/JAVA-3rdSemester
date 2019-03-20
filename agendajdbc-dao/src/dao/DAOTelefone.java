package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Telefone;

/**IFPB - Curso SI - Disciplina de Persistencia de objetos
 * @author Prof Fausto Ayres
 */

public class DAOTelefone extends DAO<Telefone> {

	public  void create(Telefone t) 	{
		try{
			String sql="" ;
			sql= "insert into Telefone (idpessoa,numero) values ("+t.getPessoa().getId()+",\'" + t.getNumero() + "\')";
			PreparedStatement st  = con.prepareStatement(sql);
			st.executeUpdate();
			st.close();
		}catch(Exception e){ }
	}

	public  Telefone read(Object chave) {
		String sql="";
		String numero = (String) chave ;
		Telefone t=null;
		int idtel;
		try{
			sql = "select * from telefone where numero = \'"+ numero+"\'";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				idtel = rs.getInt("id");
				numero = rs.getString("numero");
				t = new Telefone(idtel,numero,null) ;
				// falta ler a pessoa relacionada !!!!
				st.close();
			}
			else
				t = null;
		}catch(Exception e){ }
		return t;
	}

	public  Telefone update(Telefone t) 	{
		try{
			String sql="" ;
			sql= "update Telefone set numero = '"+ t.getNumero() + "'  where id = "+t.getId() ;
			PreparedStatement st =	con.prepareStatement(sql);
			st.executeUpdate();
			st.close();
		}catch(Exception e){ }
		return t;
	}

	public  void delete(Telefone t)	{
		try{
			String sql="" ;
			sql= "delete from Telefone where numero = \'"+t.getNumero() +"\'" ;
			PreparedStatement st =	con.prepareStatement(sql);
			st.executeUpdate();
			st.close();
		}catch(Exception e){ }
	}

	@SuppressWarnings("unused")
	public  ArrayList<Telefone> readAll() 	{
		String numero ;
		int id,idpessoa;
		ArrayList<Telefone> resultado = new ArrayList<Telefone>();
		String sql="";
		try{
			sql = "select id,idpessoa, numero from telefone order by id";
			PreparedStatement st =	con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				idpessoa = rs.getInt("idpessoa");
				numero = rs.getString("numero");
				//falta ler a pessoa relacionada !!!!!!!!
				Telefone t = new Telefone(id,numero, null);
				resultado.add(t);
			}
			st.close();
		}catch(Exception e){ }
		return resultado;
	}
}
