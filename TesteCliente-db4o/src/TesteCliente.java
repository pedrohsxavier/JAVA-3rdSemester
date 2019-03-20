


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

public class TesteCliente {
	ObjectContainer manager ;

	public TesteCliente(){		
		try {				
			ClientConfiguration config = Db4oClientServer. newClientConfiguration( ) ;
			config.common().messageLevel(0);   //0,1,2,3,4
			manager = Db4oClientServer.openClient(config, "10.0.4.239",34000,"usuario1","senha1");	
			//manager = Db4oClientServer.openClient(config,"localhost",34000,"usuario1","senha1");	

			//-------------------------------
			//------ gravar dados no servidor
			//-------------------------------
			Calendar hinicial, hfinal;
			hinicial = new GregorianCalendar();
			System.out.println("\n--inicio da gravação no servidor " + new GregorianCalendar().getTime());
			Pessoa p;
			for (int i = 1; i<=1000; i++){
				p = new Pessoa(i,"Pedro");
				p.adicionarTelefone(new Telefone("98801-"+i));
				p.adicionarTelefone(new Telefone("99902-"+i));
				manager.store(p);
				manager.commit();
			}	
			System.out.println("--fim da gravação no servidor  " + new GregorianCalendar().getTime());			


			//-------------------------------
			//----ler dados do servidor
			//-------------------------
			System.out.println("\n--inicio da consulta no servidor =    " + new GregorianCalendar().getTime());	
			Query q= manager.query();
			q.constrain(Pessoa.class);
//			q.descend("telefones").descend("numero").constrain("91000-299");
			List<Pessoa> pessoas = q.execute();		
			
			for (Pessoa aux: pessoas){
//				System.out.println(aux.toString());
			}
			System.out.println("--fim da consulta no servidor = " + new GregorianCalendar().getTime());	

			

			//-------------------------
			//----Tempo Total----------
			//-------------------------
			hfinal = new GregorianCalendar();
			System.out.println("\nFim da aplicacao cliente:");
			System.out.println("Hora inicial= " +hinicial.getTime());
			System.out.println("Hora final=   "+ hfinal.getTime());
			System.out.println("tempo (seg)=   "+ (hfinal.getTimeInMillis()-hinicial.getTimeInMillis())/1000.0);
			manager.close();	
		}
		catch (Exception e) {
			System.out.println("erro:"+e);
		} 
	}

	public static void main(String[] args) {
		new TesteCliente();

	}

}
