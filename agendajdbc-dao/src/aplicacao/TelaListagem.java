
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package aplicacao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import fachada.Fachada;

public class TelaListagem {

	private JFrame frmListagemDePessoas;
	private JTextArea text1;
	private JButton btnListar;
	private JScrollPane scrollPane;
	private JButton btnListartelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagem window = new TelaListagem();
					window.frmListagemDePessoas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmListagemDePessoas = new JFrame();
		this.frmListagemDePessoas.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
					Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
					Fachada.finalizar();
			}
		});
		this.frmListagemDePessoas.setTitle("Listagem");
		this.frmListagemDePessoas.setBounds(100, 100, 669, 300);
		this.frmListagemDePessoas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmListagemDePessoas.getContentPane().setLayout(null);
		this.text1 = new JTextArea();
		this.text1.setBounds(35, 23, 324, 190);
		this.frmListagemDePessoas.getContentPane().add(this.text1);
		this.btnListar = new JButton("ListarPessoas");
		this.btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					text1.setText(Fachada.listarPessoas());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		
		this.btnListar.setBounds(158, 222, 136, 23);
		this.frmListagemDePessoas.getContentPane().add(this.btnListar);
		this.scrollPane = new JScrollPane(text1);
		this.scrollPane.setBounds(25, 21, 606, 190);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.frmListagemDePessoas.getContentPane().add(this.scrollPane);
		this.btnListartelefone = new JButton("ListarTelefone");
		this.btnListartelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text1.setText(Fachada.listarTelefones());
			}
		});
		this.btnListartelefone.setBounds(371, 222, 136, 23);
		this.frmListagemDePessoas.getContentPane().add(this.btnListartelefone);

	}
}
