/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */


package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public abstract class DAO<T> implements DAOInterface<T>  {
	protected static Connection con;

	public static void open()  {
		criarTabelasPostgres();
		//criarTabelasMysql();
	}

	public static void close() {
		if(con!=null){
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public abstract void create(T t) ;
	public abstract T read(Object chave) ;
	public abstract T update(T t) ;
	public abstract void delete(T t) ;
	public abstract ArrayList<T> readAll();

	public static void begin() throws Exception{
		con.setAutoCommit(false);
	}

	public static void commit() throws Exception{
		con.commit();
	}

	public static void rollback() throws Exception{
		con.rollback();
	}


	public static void criarTabelasPostgres()  
	{
		try {
			/*Porta do Postgres*/
			String url= "jdbc:postgresql://localhost:5432/agenda";
			/*Porta do segundo servidor do Postgres*/
			//String url= "jdbc:postgresql://localhost:5433/agenda";
			/*IP do Servidor na porta do Postgres*/
			//String url= "jdbc:postgresql://10.0.4.159:5432/agenda";
			con = DriverManager.getConnection(url,"postgres","ifpb");						

			con.setAutoCommit(false);
			PreparedStatement st =con.prepareStatement("Select * from pg_tables where tableowner = 'postgres' and tablename= 'pessoa'");
			ResultSet rs = st.executeQuery();
			if (rs.next()) return;//tabela existe

			st = con.prepareStatement("create table Pessoa(id SERIAL not null, nome varchar(30), "+
					" dtcadastro timestamp, primary key (id) ); ");

			st.executeUpdate();

			// obs: tipo serial so funciona no POSTGRES
			st = con.prepareStatement("create table Telefone(id SERIAL, idpessoa integer, numero varchar(30),primary key (id), foreign key (idpessoa) references pessoa );");
			st.executeUpdate();
			con.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void criarTabelasMysql()  
	{
		try {
			String url= "jdbc:mysql://localhost:3306/agenda";
			con = DriverManager.getConnection(url,"root",""); 	
			con.setAutoCommit(false);

			PreparedStatement st = con.prepareStatement("create table IF NOT EXISTS Pessoa(id integer not null AUTO_INCREMENT, nome varchar(30), "+
					" dtcadastro timestamp, primary key (id) ); ");
			st.executeUpdate();

			st = con.prepareStatement("create table IF NOT EXISTS Telefone(id integer not null AUTO_INCREMENT, idpessoa integer, numero varchar(30),primary key (id), foreign key (idpessoa) references pessoa(id) );");
			st.executeUpdate();
			con.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}

