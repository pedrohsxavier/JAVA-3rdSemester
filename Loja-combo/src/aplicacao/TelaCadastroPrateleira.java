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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Prateleira;

public class TelaCadastroPrateleira extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JLabel lblPreco;
	private JButton btnCriar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPrateleira frame = new TelaCadastroPrateleira();
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
	public TelaCadastroPrateleira() {
		setTitle("Cadastrar Produto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 235, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblPreco = new JLabel("Pesomax");
		lblPreco.setBounds(10, 11, 59, 14);
		contentPane.add(lblPreco);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 8, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					double pesomax = Double.parseDouble(textField_1.getText());
					Prateleira p = Fachada.cadastrarPrateleira(pesomax);
					JOptionPane.showMessageDialog(null,"cadastrado id= "+p.getId());					
					textField_1.setText("");
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(47, 69, 115, 23);
		contentPane.add(btnCriar);
	}
}
