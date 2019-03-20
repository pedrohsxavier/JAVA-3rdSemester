package aplicacaoTeste;
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
		try {
			Fachada.inicializar();
			Produto p;
			p=Fachada.cadastrarProduto("arroz", 3.0, 0, 5);
			p=Fachada.cadastrarProduto("feijao", 5.0, 100, 1);
			p=Fachada.cadastrarProduto("leite", 2.0, 100, 1);
			Prateleira pt;
			pt=Fachada.cadastrarPrateleira(200);
			pt=Fachada.cadastrarPrateleira(300);
			System.out.println("Cadastro ok");

			//atualização
			Fachada.inserirProdutoPrateleira(1, "arroz");
			Fachada.inserirProdutoPrateleira(1, "feijao");
			Fachada.inserirProdutoPrateleira(1, "leite");
			Fachada.retirarProdutoPrateleira("feijao");
			System.out.println("Atualizacao ok");

			//			//exclusao
			//			Sistema.apagarProduto("feijao");
			//			Sistema.apagarPrateleira(1);		


			//listagem
			System.out.println(Fachada.listarPrateleiras());
			System.out.println(Fachada.listarProdutos());


			//consultas
			System.out.println("\n\n----Consultas-----");
			System.out.println(Fachada.consultarPrateleirasVazias());
			System.out.println(Fachada.consultarProdutosSemPrateleira());
			System.out.println(Fachada.consultarTotalProdutos());
			System.out.println(Fachada.consultarPrateleiraComDoisProdutos());			
			System.out.println(Fachada.consultarProdutosVizinhos("arroz"));
			System.out.println(Fachada.consultarProdutosDaPrateleira(1));
			System.out.println(Fachada.consultarPrateleiraDoProduto("arroz"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}


	//  ***********************************************
	public static void main (String[] args)
	//  ***********************************************
	{
		new Teste();
	}

}
