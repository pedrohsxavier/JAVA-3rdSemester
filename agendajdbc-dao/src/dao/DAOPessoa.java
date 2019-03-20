package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelo.Pessoa;
import modelo.Telefone;

/**IFPB - Curso SI - Disciplina de Persistencia de objetos
 * @author Prof Fausto Ayres
 */

public class DAOPessoa extends DAO<Pessoa> {

	public  void create(Pessoa p) {
		try{
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  //postgres
			//SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");	//mysql

			PreparedStatement st=con.prepareStatement("insert into Pessoa (nome,dtcadastro) values (\'" + p.getNome() + "\', '" + f.format(p.getDtcadastro()) +  "')");
			int i = st.executeUpdate();
			st.close();
		}catch(Exception e){ }
	}

	public  Pessoa read(Object chave) 	{
		Pessoa p=null;
		int idtel;
		int id;
		Date dt;
		String numero ;
		try{
			String nome = (String) chave ;
			PreparedStatement st = con.prepareStatement("select * from Pessoa where nome = \'"+ nome + "\'");
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
				nome = rs.getString("nome");
				dt = rs.getDate("dtcadastro");
				p = new Pessoa(id,nome,dt);

				//ler seus telefones
				st = con.prepareStatement("select * from telefone where idpessoa = "+ id);
				rs = st.executeQuery();
				while(rs.next()) {
					idtel = rs.getInt("id");
					numero = rs.getString("numero");
					p.adicionarTelefone(new Telefone(idtel,numero,p) );
				}
				st.close();
			}
			else
				p = null;
		}catch(Exception e){ }
		return p;

	}


	public Pessoa update(Pessoa p) 	{
		try{
			PreparedStatement st =con.prepareStatement("update Pessoa set nome = '"+ p.getNome() + "'  where id = "+p.getId());
			int i = st.executeUpdate();
			st.close();
		}catch(Exception e){ }
		return p;
	}

	public  void delete(Pessoa p) {
		try{
			PreparedStatement st =	con.prepareStatement("delete from Telefone where idpessoa = "+p.getId());
			st.executeUpdate();
			st =con.prepareStatement("delete from Pessoa where id = "+p.getId());
			int i = st.executeUpdate();
		}catch(Exception e){ }

	}

	public  ArrayList<Pessoa> readAll() {
		int id, idtel;
		Date dt;
		String nome,numero ;
		ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
		try{
			//ler todas as pessoas
			PreparedStatement st =	con.prepareStatement("select id, nome, dtcadastro from pessoa order by id");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				nome = rs.getString("nome");
				dt = rs.getDate("dtcadastro");
				Pessoa p = new Pessoa(id,nome,dt);
				resultados.add(p);
			}
			//ler os telefones de cada pessoa
			for(Pessoa p:resultados){
				st = con.prepareStatement("select * from telefone where idpessoa = "+ p.getId());
				rs = st.executeQuery();
				while(rs.next()) {
					idtel = rs.getInt("id");
					numero = rs.getString("numero");
					p.adicionarTelefone(new Telefone(idtel,numero,p) );
				}
			}
		}catch(Exception e){ }
		return resultados;

	}

	public  ArrayList<Pessoa> consultarPessoaSemTelefone(){
		int id;
		String nome ;
		Date dt;
		ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
		String sql="";
		try{
			sql = "select * from pessoa p where not exists (select * from telefone t where p.id=t.idpessoa)";
			PreparedStatement st =	con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");		
				nome = rs.getString("nome");
				dt = rs.getDate("dtcadastro");
				Pessoa p = new Pessoa(id,nome,dt);
				resultados.add(p);
			}
		}catch(Exception e){ }
		return resultados;
	}

}
