package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

public class TelaConsulta extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnCriar;
	private JButton btnConsulta;
	private JButton btnConsulta_1;
	private JButton btnConsulta_2;
	private JButton btnConsulta_3;
	private JButton btnConsulta_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta frame = new TelaConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConsulta() {
		setTitle("Consultar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCriar = new JButton("Prateleiras Vazias");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					textArea.setText(Fachada.consultarPrateleirasVazias());
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(414, 13, 271, 23);
		contentPane.add(btnCriar);

		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 11, 348, 228);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);

		JButton btnCaonsulta = new JButton("Prateleira com menos de 2 Produtos");
		btnCaonsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(Fachada.consultarPrateleiraComDoisProdutos());
			}
		});
		btnCaonsulta.setBounds(414, 47, 271, 23);
		contentPane.add(btnCaonsulta);

		btnConsulta = new JButton("Prateleira do Produto (nome)");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog("nome do produto");
				textArea.setText(Fachada.consultarPrateleiraDoProduto(nome));
			}
		});
		btnConsulta.setBounds(414, 81, 271, 23);
		contentPane.add(btnConsulta);

		btnConsulta_1 = new JButton("Total de Produtos");
		btnConsulta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(Fachada.consultarTotalProdutos());
			}
		});
		btnConsulta_1.setBounds(414, 115, 271, 23);
		contentPane.add(btnConsulta_1);

		btnConsulta_2 = new JButton("Produtos sem Prateleira");
		btnConsulta_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(Fachada.consultarProdutosSemPrateleira());
			}
		});
		btnConsulta_2.setBounds(414, 149, 271, 23);
		contentPane.add(btnConsulta_2);
		
		btnConsulta_3 = new JButton("Produtos Vizinhos do (nome)");
		btnConsulta_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog("nome do produto");
				textArea.setText(Fachada.consultarProdutosVizinhos(nome));
			}
		});
		btnConsulta_3.setBounds(414, 183, 271, 23);
		contentPane.add(btnConsulta_3);
		
		btnConsulta_4 = new JButton("Produtos da Prateleira(id)");
		btnConsulta_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("id da prateleira"));
				textArea.setText(Fachada.consultarProdutosDaPrateleira(id));
			}
		});
		btnConsulta_4.setBounds(414, 217, 271, 23);
		contentPane.add(btnConsulta_4);
	}
}
