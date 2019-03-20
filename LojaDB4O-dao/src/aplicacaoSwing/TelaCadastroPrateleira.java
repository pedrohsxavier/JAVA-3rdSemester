package aplicacaoSwing;
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
	private JTextField textField;
	private JLabel lblNome;
	private JButton btnCriar;

	
	/**
	 * Create the frame.
	 */
	public TelaCadastroPrateleira() {
		setTitle("Cadastrar Prateleira");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(98, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Peso");
		lblNome.setBounds(10, 14, 78, 14);
		contentPane.add(lblNome);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int tamanho = Integer.parseInt(textField.getText());
					Prateleira pt = Fachada.cadastrarPrateleira(tamanho);
					JOptionPane.showMessageDialog(null,"cadastrado id= "+pt.getId());
					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(72, 53, 115, 23);
		contentPane.add(btnCriar);
	}
}
