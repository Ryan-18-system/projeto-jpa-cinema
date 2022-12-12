package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Filme;
import modelo.Pessoa;
import regra.negocio.Fachada;

public class TelaAtor {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button_1;
	private JLabel label_4;
	private JTextField textField_3;
	private JLabel label_6;
	private JTextField textField_4;
	private JLabel label_10;
	private JTextField textField_10;
	private JButton button_2;



	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaTelefone window = new TelaTelefone();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaAtor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Filme");
		frame.setBounds(100, 100, 1077, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 39, 1032, 174);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"nome", "cpf", "função", "cache", "Filmes"}
				));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 313, 711, 14);
		frame.getContentPane().add(label);

		label_3 = new JLabel("");
		label_3.setBounds(-138, 213, 538, 14);
		frame.getContentPane().add(label_3);
		

		label_1 = new JLabel("nome:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(21, 252, 71, 14);
		frame.getContentPane().add(label_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(64, 249, 134, 20);
		frame.getContentPane().add(textField_1);

		label_2 = new JLabel("Data de Nascimento:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(208, 252, 116, 14);
		frame.getContentPane().add(label_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(324, 249, 134, 20);
		frame.getContentPane().add(textField_2);

		


		label_4 = new JLabel("CPF:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(468, 252, 71, 14);
		frame.getContentPane().add(label_4);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(497, 249, 134, 20);
		frame.getContentPane().add(textField_3);

		
		label_6 = new JLabel("Função:");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(644, 252, 71, 14);
		frame.getContentPane().add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(693, 249, 116, 20);
		frame.getContentPane().add(textField_4);
		
		label_10 = new JLabel("Cache:");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_10.setBounds(820, 242, 82, 34);
		frame.getContentPane().add(label_10);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_10.setColumns(10);
		textField_10.setBounds(866, 249, 116, 20);
		frame.getContentPane().add(textField_10);
		
	
		button_1 = new JButton("Adicionar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
							|| textField_3.getText().isEmpty()|| textField_10.getText().isEmpty()|| textField_4.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = textField_1.getText();
					String data = textField_2.getText();
					String cpf = textField_3.getText();
					String funcao = textField_4.getText();
					Double cache = Double.parseDouble(textField_10.getText());
					Fachada.criarPessoa(nome, LocalDate.parse(data), cpf, funcao, cache);
					label.setText("Atriz/Ator cadastrada");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(617, 313, 152, 23);
		frame.getContentPane().add(button_1);
		

		button_2 = new JButton("Excluir ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 1);

						Fachada.apagarPessoa(cpf);
						label.setText("Ator/atriz excluído com sucesso");
						listagem();
					}
					else {
						label.setText("selecione um ator/atriz");
					}
				} catch(Exception ex)  {
					
					label.setText(ex.getMessage());
					
				}
				
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(783, 313, 177, 23);
		frame.getContentPane().add(button_2);
		frame.setVisible(true);

	}

	public void listagem() {
		try{
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("nome");
			model.addColumn("CPF");
			model.addColumn("Função");
			model.addColumn("Cache");
			model.addColumn("Filmes");

			List<Pessoa> lista = Fachada.listarAtores();
			for(Pessoa pessoa : lista) {
				model.addRow(new Object[]{ pessoa.getNome(), pessoa.getCpf(), pessoa.getFuncao(), pessoa.getCache(), pessoa.filmesFormatados()});
			}
			
			table.setModel(model);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
			table.getColumnModel().getColumn(2).setMinWidth(100);
			table.getColumnModel().getColumn(3).setMinWidth(100);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 

			//atualizar horario da listagem no titulo da janela
			frame.setTitle("Atores  -- "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));

		}
		catch(Exception erro){
			JOptionPane.showMessageDialog(frame,erro.getMessage());
		}

	}
}
