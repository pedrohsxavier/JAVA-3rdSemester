
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
import modelo.Telefone;

public class TelaCadastroTelefone {

	private JFrame frmCadastroDePessoas;
	private JLabel lblNome;
	private JTextField textField_1;
	private JButton btnCadastrar;
	private JTextField textField_2;
	private DefaultListModel<String> model = new DefaultListModel<String>();	
	private JLabel lblTelefones;
	private JLabel lblMensagemDoUsuario;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroTelefone window = new TelaCadastroTelefone();
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
	public TelaCadastroTelefone() {
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
		this.frmCadastroDePessoas.setTitle("Cadastro de Telefone");
		this.frmCadastroDePessoas.setBounds(100, 100, 373, 269);
		this.frmCadastroDePessoas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmCadastroDePessoas.getContentPane().setLayout(null);
		this.lblNome = new JLabel("nome da pessoa");
		this.lblNome.setBounds(10, 41, 110, 14);
		this.frmCadastroDePessoas.getContentPane().add(this.lblNome);
		this.textField_1 = new JTextField();
		this.textField_1.setBounds(130, 38, 86, 20);
		this.frmCadastroDePessoas.getContentPane().add(this.textField_1);
		this.textField_1.setColumns(10);
		this.btnCadastrar = new JButton("Cadastrar");
		this.btnCadastrar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		this.btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome =textField_1.getText();
				String numero = textField_2.getText();
				try{		
					Telefone t = Fachada.cadastrarTelefone(nome, numero);
					lblMensagemDoUsuario.setText("cadastro feito: "+t.getNumero());

				} catch (Exception e) {
					lblMensagemDoUsuario.setText(e.getMessage());
				}
			}
		});
		this.btnCadastrar.setBounds(31, 141, 136, 23);
		this.frmCadastroDePessoas.getContentPane().add(this.btnCadastrar);
		this.textField_2 = new JTextField();
		this.textField_2.setBounds(130, 74, 86, 20);
		this.frmCadastroDePessoas.getContentPane().add(this.textField_2);
		this.textField_2.setColumns(10);
		this.lblTelefones = new JLabel("Telefone");
		this.lblTelefones.setBounds(10, 77, 110, 14);
		this.frmCadastroDePessoas.getContentPane().add(this.lblTelefones);
		this.lblMensagemDoUsuario = new JLabel("Mensagem do usuario");
		this.lblMensagemDoUsuario.setBounds(23, 210, 193, 14);
		this.frmCadastroDePessoas.getContentPane().add(this.lblMensagemDoUsuario);
		this.btnLimpar = new JButton("Limpar");
		this.btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("");
				textField_2.setText("");
				model.clear();
			}
		});
		this.btnLimpar.setBounds(193, 141, 141, 23);
		this.frmCadastroDePessoas.getContentPane().add(this.btnLimpar);

	}
}
