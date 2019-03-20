package aplicacao;


/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Departamento;
import modelo.Empregado;


public class Teste {
	protected static ObjectContainer manager;

	public Teste(){	
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...
		config.common().objectClass(Departamento.class).cascadeOnDelete(true);;
		config.common().objectClass(Departamento.class).cascadeOnUpdate(true);;
		config.common().objectClass(Departamento.class).cascadeOnActivate(true);  
		config.common().objectClass(Empregado.class).cascadeOnDelete(true);;
		config.common().objectClass(Empregado.class).cascadeOnUpdate(true);;
		config.common().objectClass(Empregado.class).cascadeOnActivate(true);
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");		


		//cadastrar(); 			
		//alterar();			
		//apagar();				
		//listar();				
		consultar();			

		System.out.println("fim da aplicação");
		manager.close();
	}


	public void cadastrar(){
		Empregado e1,e2,e3,e4,e5;
		Departamento d1,d2,d3,d4;	
		d1 = new Departamento(1,"informatica");
		d2 = new Departamento(2,"eletronica");	
		d3 = new Departamento(3,"matematica");   // sem empregados		
		d4 = new Departamento(4,"fisica");   // sem empregados		
		e1 = new Empregado("joao",1000);
		e2 = new Empregado("maria",2000);
		e3 = new Empregado("jose",3000);
		e4 = new Empregado("ana",4000);
		e5 = new Empregado("paulo",4000);		
		d1.adicionarEmpregado(e1);
		d1.adicionarEmpregado(e2);
		d1.setChefe(e1);
		d2.adicionarEmpregado(e3);
		d2.adicionarEmpregado(e4);
		d2.setChefe(e3);
		d3.adicionarEmpregado(e5);
		d3.setChefe(null);
		manager.store(d1);
		manager.store(d2);
		manager.store(d3);
		manager.store(d4);
		manager.commit();
	}

	public void consultar(){
		List<Departamento> departamentos ;
		Query q;


		System.out.println("Qual o departamento do jose?");
		q = manager.query();
		q.constrain(Departamento.class);
		q.descend("empregados").descend("nome").constrain("jose");
		departamentos = q.execute();
		System.out.println(departamentos.get(0).getNome());


		//Qual o departamento que joao chefia ?"
		q = manager.query();
		q.constrain(Departamento.class);
		q.descend("chefe").descend("nome").constrain("joao");
		departamentos= q.execute();	
		System.out.println("Departamento que joao chefia: " + departamentos.get(0).getNome() + "\n");

		//Quais os departamentos que não tem chefe ?"
		q = manager.query();
		q.constrain(Departamento.class);
		q.descend("chefe").constrain(null);
		departamentos= q.execute();	
		System.out.println("Departamento que não tem chefe: " + departamentos.get(0).getNome() + "\n");

		//Quais os departamentos que não tem empregados ?"
		q = manager.query();
		q.constrain(Departamento.class);
		q.constrain(new Filtro1());
		departamentos= q.execute();	
		System.out.println("Departamento que não tem empregados: " + departamentos + "\n");

		//Quais os empregados do departamento de informatica?"
		q = manager.query();
		q.constrain(Empregado.class);
		q.descend("dep").descend("nome").constrain("informatica");
		List<Empregado> empregados = q.execute();
		System.out.print("empregados do departamento de informatica: ");
		for(Empregado e : empregados) {
			System.out.print(e.getNome() + ", ");
		}
		System.out.print("\n");

		//Quais os empregados subordinados ao joao?"
		q = manager.query();
		q.constrain(Empregado.class);
		q.descend("dep").descend("chefe").descend("nome").constrain("joao");
		//aqui joao é retirado pois ele é chefe mas nao é subordinado dele mesmo
		q.descend("nome").constrain("joao").not();
		empregados = q.execute();
		System.out.print("empregados subordinados ao joao: ");
		for(Empregado e : empregados) {
			System.out.print(e.getNome() + ", ");
		}
		System.out.print("\n");

		//Quais os empregados que ganham mais que o seu chefe?
		q = manager.query();
		q.constrain(Empregado.class);
		q.constrain(new Filtro2());
		empregados = q.execute();
		System.out.print("empregados que ganham mais que o seu chefe: ");
		for(Empregado e : empregados) {
			System.out.print(e.getNome() + ", ");
		}
		System.out.print("\n");
	}

	public void listar(){
		Query q;
		q = manager.query();
		q.constrain(Departamento.class);  				
		List<Departamento> resultado1= q.execute();		
		System.out.println("\nListagem de departamentos:");
		for(Departamento t: resultado1) {
			System.out.println(t); 
		}

		q = manager.query();
		q.constrain(Empregado.class);  				
		List<Empregado> resultado2= q.execute();		
		System.out.println("\nListagem de empregados:");
		for(Empregado a: resultado2) {
			System.out.println(a); 
		}
	}


	//==================
	public static void main(String[] args) throws Exception {
		new Teste();
	}




}

class Filtro1 implements Evaluation {
	public void evaluate(Candidate candidate) {
		Departamento d = (Departamento) candidate.getObject();
		if(d.getEmpregados().size() == 0)
			candidate.include(true);
		else
			candidate.include(false);
	}
}

class Filtro2 implements Evaluation {
	public void evaluate(Candidate candidate) {
		Empregado e = (Empregado) candidate.getObject();
		if(e.getSalario() > e.getDepartamento().getChefe().getSalario())
			candidate.include(true);
		else
			candidate.include(false);
	}
}

















