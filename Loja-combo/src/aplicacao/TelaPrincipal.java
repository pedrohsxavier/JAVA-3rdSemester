package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal {

	private JFrame frmPrincipal;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmListar;
	private JMenu mnProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		try {
			frmPrincipal.setContentPane(new Fundo("imagem1.jpg"));
		} catch (IOException e1) {
		}	

		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
				JOptionPane.showMessageDialog(null, "sistema inicializado !");
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
				JOptionPane.showMessageDialog(null, "sistema finalizado !");

			}
		});
		frmPrincipal.setTitle("Loja Bem Muito");
		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		mnProduto = new JMenu("Produto");
		menuBar.add(mnProduto);

		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroProduto j = new TelaCadastroProduto();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmCadastrar);

		mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemProduto j = new TelaListagemProduto();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmListar);

		JMenu mnPrateleira = new JMenu("Prateleira");
		menuBar.add(mnPrateleira);

		JMenuItem mntmCriar = new JMenuItem("Criar");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroPrateleira j = new TelaCadastroPrateleira();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmCriar);

		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemPrateleira j = new TelaListagemPrateleira();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmListar_1);

		JMenuItem mntmInserirProduto = new JMenuItem("Inserir produto");
		mntmInserirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInserirProduto j = new TelaInserirProduto();
				j.setVisible(true);
			}
		});
		mnPrateleira.add(mntmInserirProduto);
		
		JMenu mnConsultas = new JMenu("Consultas");
		mnConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaConsulta j = new TelaConsulta();
				j.setVisible(true);
			}
		});
		menuBar.add(mnConsultas);
	}
}
