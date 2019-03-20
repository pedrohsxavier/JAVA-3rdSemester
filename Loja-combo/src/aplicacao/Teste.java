package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/


import fachada.Fachada;
import modelo.Prateleira;
import modelo.Produto;

public class Teste {

	public Teste() {
		Fachada.inicializar();
		cadastrar();
		//atualizar();
		//apagar();
		listar();
		consultar();
		Fachada.finalizar();
		
		System.out.println("\nfim do teste");
	}

	public void cadastrar() {
		try {
			Produto p;
			p=Fachada.cadastrarProduto("arroz", 3.0, 0, 5);
			p=Fachada.cadastrarProduto("feijao", 5.0, 100, 1);
			p=Fachada.cadastrarProduto("leite", 2.0, 100, 1);
			p=Fachada.cadastrarProduto("oleo", 5.0, 100, 1);
			p=Fachada.cadastrarProduto("carne", 10.0, 100, 1);
			p=Fachada.cadastrarProduto("farinha", 10.0, 100, 1);
			Prateleira pt;
			pt=Fachada.cadastrarPrateleira(200);
			pt=Fachada.cadastrarPrateleira(300);
			System.out.println("cadastro concluido!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizar() {
		try {
			Fachada.inserirProdutoPrateleira(1, "arroz");
			Fachada.inserirProdutoPrateleira(1, "feijao");
			Fachada.inserirProdutoPrateleira(1, "leite");
			Fachada.retirarProdutoPrateleira("feijao");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void apagar() {
		try {
			Fachada.apagarProduto("feijao");
			Fachada.apagarPrateleira(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void listar() {
		System.out.println(Fachada.listarPrateleiras());
		System.out.println(Fachada.listarProdutos());
	}

	public void consultar() {
		System.out.println("\n\n----Consultar-----");
		System.out.println(Fachada.consultarPrateleirasVazias());
		System.out.println(Fachada.consultarPrateleiraComDoisProdutos());
		System.out.println(Fachada.consultarPrateleiraDoProduto("arroz"));
		System.out.println(Fachada.consultarTotalProdutos());
		System.out.println(Fachada.consultarProdutosSemPrateleira());
		System.out.println(Fachada.consultarProdutosVizinhos("arroz"));
		System.out.println(Fachada.consultarProdutosDaPrateleira(1));
	}


	//  ***********************************************
	public static void main (String[] args)
	//  ***********************************************
	{
		Teste ap1 = new Teste();
	}

}
