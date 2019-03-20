package performance;

import java.util.Calendar;
import java.util.GregorianCalendar;

import fachada.Fachada;
import modelo.Prateleira;

public class MedirTempo {

	public static void main(String[] args) {

		Calendar hinicial, hfinal;
		Prateleira pt;
		hinicial = new GregorianCalendar();

		Fachada.inicializar();

		//------ gravar dados no banco
		System.out.println("\ninicio da gravação no servidor " + hinicial.getTime());
		try{
			pt = Fachada.cadastrarPrateleira(100);
			for (int i = 1; i<=200; i++){
				String nome = "produto"+i;
				Fachada.cadastrarProduto(nome, 9.0, 9, 9);
				Fachada.inserirProdutoPrateleira(pt.getId(), nome);
			}
		}
		catch(Exception e){System.out.println("erro:"+ e.getMessage());}
		System.out.println("\nfim da gravação no servidor    " + new GregorianCalendar().getTime());			



		//----ler dados do banco
		System.out.println("\n\ninicio da consulta no servidor =  " + new GregorianCalendar().getTime());	
		String s = Fachada.listarProdutos();
		System.out.println("\nfim da consulta no servidor =     " + new GregorianCalendar().getTime());
		hfinal = new GregorianCalendar();

		System.out.println(s);



		//---- Calculo do Tempo Total----------
		System.out.println( 
				"\n\n Hora inicial= " +hinicial.getTime() +
				"\n Hora final=   "+ hfinal.getTime() +
				"\n Total (seg)=  "+(hfinal.getTimeInMillis()-hinicial.getTimeInMillis())/1000.0
				);

		Fachada.finalizar();

	}

}


