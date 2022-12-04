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

import modelo.Estudio;
import regra.negocio.Fachada;

public class TelaEstudio {
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

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// TelaTelefone window = new TelaTelefone();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public TelaEstudio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Estudio");
		frame.setBounds(100, 100, 941, 377);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 39, 867, 174);
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
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "nome", "cnpj", "Filmes" }));
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

		label_2 = new JLabel("CNPJ:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(208, 252, 71, 14);
		frame.getContentPane().add(label_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(277, 249, 89, 20);
		frame.getContentPane().add(textField_2);

		button_1 = new JButton("Adicionar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().isEmpty() || textField_2.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = textField_1.getText();
					String cnpj = textField_2.getText();
					Fachada.criarEstudio(nome, cnpj);
					label.setText("Estúdio Cadastrado");
					listagem();
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(439, 248, 152, 23);
		frame.getContentPane().add(button_1);

		frame.setVisible(true);

	}

	public void listagem() {
		try {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("nome");
			model.addColumn("CNPJ");
			model.addColumn("Filmes");
			List<Estudio> lista = Fachada.listarEstudios();
			for (Estudio estudio : lista) {
				model.addRow(new Object[] { estudio.getNome(), estudio.getCnpj(), estudio.filmesFormatados() });
			}

			table.setModel(model);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(2).setMinWidth(100);
			table.getColumnModel().getColumn(3).setMinWidth(100);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			// atualizar horario da listagem no titulo da janela
			frame.setTitle(
					"Estúdios  -- " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(frame, erro.getMessage());
		}

	}

}
