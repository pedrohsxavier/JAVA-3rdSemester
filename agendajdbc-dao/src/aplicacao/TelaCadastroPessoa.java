
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
package aplicacao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fachada.Fachada;
import modelo.Pessoa;

public class TelaCadastroPessoa {

	private JFrame frmCadastroDePessoas;
	private JLabel lblNome;
	private JTextField textField_1;
	private JButton btnCadastrar;
	private DefaultListModel<String> model = new DefaultListModel<String>();	
	private JLabel lblMensagemDoUsuario;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa window = new TelaCadastroPessoa();
					window.frmCadastroDePessoas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroPessoa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmCadastroDePessoas = new JFrame();
		this.frmCadastroDePessoas.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
					Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
					Fachada.finalizar();
			}
		});
		this.frmCadastroDePessoas.setTitle("Cadastro de Pessoa");
		this.frmCadastroDePessoas.setBounds(100, 100, 373, 269);
		this.frmCadastroDePessoas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmCadastroDePessoas.getContentPane().setLayout(null);
		this.lblNome = new JLabel("Nome");
		this.lblNome.setBounds(23, 41, 46, 14);
		this.frmCadastroDePessoas.getContentPane().add(this.lblNome);
		this.textField_1 = new JTextField();
		this.textField_1.setBounds(93, 38, 86, 20);
		this.frmCadastroDePessoas.getContentPane().add(this.textField_1);
		this.textField_1.setColumns(10);
		this.btnCadastrar = new JButton("Cadastrar");
		this.btnCadastrar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		this.btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = textField_1.getText();

				try {
					Pessoa p = Fachada.cadastrarPessoa(nome);
					lblMensagemDoUsuario.setText("cadastro feito "+p.getNome());

				} catch (Exception e) {
					lblMensagemDoUsuario.setText(e.getMessage());
				}
			}
		});
		this.btnCadastrar.setBounds(31, 141, 136, 23);
		this.frmCadastroDePessoas.getContentPane().add(this.btnCadastrar);
		this.lblMensagemDoUsuario = new JLabel("Mensagem do usuario");
		this.lblMensagemDoUsuario.setBounds(23, 210, 122, 14);
		this.frmCadastroDePessoas.getContentPane().add(this.lblMensagemDoUsuario);
		this.btnLimpar = new JButton("Limpar");
		this.btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("");
				model.clear();
			}
		});
		this.btnLimpar.setBounds(193, 141, 141, 23);
		this.frmCadastroDePessoas.getContentPane().add(this.btnLimpar);

	}
}
