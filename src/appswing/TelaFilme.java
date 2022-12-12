/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import regra.negocio.Fachada;
import modelo.Filme;
import modelo.Pessoa;


public class TelaFilme {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button_3;
	private JLabel label_4;
	private JTextField textField_3;
	private JLabel label_6;
	private JTextField textField_4;
	private JButton button_2;
	private JButton button_4;
	private JLabel label_10;
	private JTextField textField_10;
	private JLabel label_11;




	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public TelaFilme() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Filme");
		frame.setBounds(100, 100, 900, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
			
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 39, 835, 174);
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
				new String[] {"nome", "data", "estudio", "atores"}
				));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 313, 711, 14);
		frame.getContentPane().add(label);
		

		label_1 = new JLabel("Cpf do ator:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(21, 298, 71, 14);
		frame.getContentPane().add(label_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(99, 293, 200, 24);
		frame.getContentPane().add(textField_1);

		label_2 = new JLabel("Cnpj Estúdio");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(21, 223, 132, 14);
		frame.getContentPane().add(label_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(21, 247, 152, 24);
		frame.getContentPane().add(textField_2);
		
		button_3 = new JButton("Adicionar Atriz/Ator");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nomeFilme = (String) table.getValueAt( table.getSelectedRow(), 0);
						if(textField_1.getText().isEmpty()) {
							label.setText("campo vazio, adicione um cpf");
							return;
						}
						String cpf = textField_1.getText();
						Fachada.addAtorAoFilme(cpf, nomeFilme);
						label.setText("Ator adicionado ao Filme");
						listagem();
					}
					else {
						label.setText("selecione um filme");
					}
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(304, 294, 152, 23);
		frame.getContentPane().add(button_3);
		
		button_2 = new JButton("Excluir ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);

						Fachada.apagarFilme(nome);
						label.setText("Filme excluido");
						listagem();
					}
					else {
						label.setText("selecione um filme");
					}
				} catch(Exception ex)  {
					
					label.setText(ex.getMessage());
					
				}
				
			}
		});
		
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(383, 327, 168, 26);
		frame.getContentPane().add(button_2);
		
		button_4 = new JButton("Adicionar Filme");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_10.getText().isEmpty()||textField_2.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}else {
						String nomeFilme = textField_10.getText();
						String cnpj =textField_2.getText();
						Fachada.criarFilme(nomeFilme);
						Fachada.addEstudioAoFilme(cnpj, nomeFilme);
						listagem();
						label.setText("Filme adicionado com Sucesso");
					}
					
				} catch(Exception ex)  {
					
					label.setText(ex.getMessage());
					
				}
				
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(539, 247, 152, 24);
		frame.getContentPane().add(button_4);
		
		label_10 = new JLabel("Nome do Filme");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_10.setBounds(203, 224, 86, 13);
		frame.getContentPane().add(label_10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(199, 247, 163, 24);
		frame.getContentPane().add(textField_10);
		textField_10.setColumns(10);
		
	

		frame.setVisible(true);

	}

	public void listagem() {
		try{
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("nome");
			model.addColumn("data");
			model.addColumn("estudio");
			model.addColumn("atores");

			List<Filme> lista = Fachada.listarFilmes();
			for(Filme filme : lista) {
				model.addRow(new Object[]{ filme.getNome(),filme.getDtLancamento(), filme.getEstudio().getNome(), filme.funcionariosFormatados()});
				
			}
			
			table.setModel(model);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
			table.getColumnModel().getColumn(2).setMinWidth(100);
			table.getColumnModel().getColumn(3).setMinWidth(100);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		
			//atualizar horario da listagem no titulo da janela
			frame.setTitle("Filme  -- "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));

		}
		catch(Exception erro){
			JOptionPane.showMessageDialog(frame,erro.getMessage());
		}

	}
}
