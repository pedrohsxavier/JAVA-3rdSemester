package aplicacao;
import fachada.Fachada;
import modelo.Pessoa;

/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

public class Teste {

	public Teste(){	
		Fachada.inicializar();		

		cadastrar(); 		// grava as pessoas no banco
//		atualizar();		// le, altera e regrava uma pessoa no banco
//		apagar();			// apaga uma pessoa no banco
		listar();			// imprime a listagem final das pessoas do banco
//		consultar();  		// consulta ao banco	

		Fachada.finalizar();


	}

	/************************/
	public void cadastrar(){
		/************************/
		try {
			Pessoa p1=Fachada.cadastrarPessoa("joao");
			Fachada.cadastrarTelefone("joao","8888 1111");
			Fachada.cadastrarTelefone("joao","8888 2222");
			System.out.println("cadastrado com sucesso:"+p1.getNome());

			Pessoa p2=Fachada.cadastrarPessoa("maria");
			Fachada.cadastrarTelefone("maria","8888 3333");
			Fachada.cadastrarTelefone("maria","8888 4444");
			System.out.println("cadastrado com sucesso:"+p2.getNome());

			Pessoa p3=Fachada.cadastrarPessoa("jose");
			System.out.println("cadastrado com sucesso:"+p3.getNome());
		} catch (Exception e) {
			System.out.println("-->"+e.getMessage());
		}
	}

	/************************/
	public void atualizar(){
		/************************/
		try {
			Fachada.alterarPessoa("joao","joana");
			Fachada.incluirTelefone("joana","9999 9999");
		} catch (Exception e) {
			System.out.println("-->"+e.getMessage());
		}
	}

	/************************/
	public void apagar(){
		/************************/
		try {
			Fachada.excluirPessoa("maria");			

		} catch (Exception e) {
			System.out.println("-->"+e.getMessage());
		}
	}

	/************************/
	public void listar(){
		/************************/
		try {
			System.out.println(Fachada.listarPessoas());
			System.out.println(Fachada.listarTelefones());

		} catch (Exception e) {
			System.out.println("-->"+e.getMessage());
		}
	}

	/************************/
	public void consultar(){
		/************************/
		try {
			System.out.println(Fachada.consultarPessoaSemTelefone() );

		} catch (Exception e) {
			System.out.println("-->"+e.getMessage());
		}
	}



	//=================================================
	public static void main(String[] args) {
		new Teste();
	}
	//=================================================

}
