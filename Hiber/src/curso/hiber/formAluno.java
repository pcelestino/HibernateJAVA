package curso.hiber;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class formAluno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfAluNome;
	private JLabel lblCurso;
	private JTextField tfAluCurso;
	private JLabel lblCidade;
	private JTextField tfAluCidade;
	private JLabel lblFone;
	private JTextField tfAluFone;
	private JLabel lblCadastroDeAlunos;
	private JScrollPane scrollPane;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnAtualizar;
	private JButton btnExcluir;
	private JTable table;
	private List<Aluno> listAluno;
	private int codAluno;
	
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formAluno frame = new formAluno();
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
	public formAluno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{1, 0, 0, 1, 1, 1, 0};
		gbl_contentPane.rowHeights = new int[]{70, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 10.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblCadastroDeAlunos = new JLabel("Cadastro de Alunos");
		lblCadastroDeAlunos.setForeground(new Color(105, 105, 105));
		lblCadastroDeAlunos.setFont(new Font("Arial", Font.BOLD, 21));
		GridBagConstraints gbc_lblCadastroDeAlunos = new GridBagConstraints();
		gbc_lblCadastroDeAlunos.gridwidth = 6;
		gbc_lblCadastroDeAlunos.insets = new Insets(0, 0, 5, 0);
		gbc_lblCadastroDeAlunos.gridx = 0;
		gbc_lblCadastroDeAlunos.gridy = 0;
		contentPane.add(lblCadastroDeAlunos, gbc_lblCadastroDeAlunos);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);
		
		tfAluNome = new JTextField();
		GridBagConstraints gbc_tfAluNome = new GridBagConstraints();
		gbc_tfAluNome.gridwidth = 3;
		gbc_tfAluNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAluNome.insets = new Insets(0, 0, 5, 5);
		gbc_tfAluNome.gridx = 1;
		gbc_tfAluNome.gridy = 1;
		contentPane.add(tfAluNome, gbc_tfAluNome);
		tfAluNome.setColumns(10);
		
		lblCurso = new JLabel("Curso:");
		GridBagConstraints gbc_lblCurso = new GridBagConstraints();
		gbc_lblCurso.anchor = GridBagConstraints.WEST;
		gbc_lblCurso.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurso.gridx = 4;
		gbc_lblCurso.gridy = 1;
		contentPane.add(lblCurso, gbc_lblCurso);
		
		tfAluCurso = new JTextField();
		GridBagConstraints gbc_tfAluCurso = new GridBagConstraints();
		gbc_tfAluCurso.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAluCurso.insets = new Insets(0, 0, 5, 0);
		gbc_tfAluCurso.gridx = 5;
		gbc_tfAluCurso.gridy = 1;
		contentPane.add(tfAluCurso, gbc_tfAluCurso);
		tfAluCurso.setColumns(10);
		
		lblCidade = new JLabel("Cidade:");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.WEST;
		gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade.gridx = 0;
		gbc_lblCidade.gridy = 2;
		contentPane.add(lblCidade, gbc_lblCidade);
		
		tfAluCidade = new JTextField();
		GridBagConstraints gbc_tfAluCidade = new GridBagConstraints();
		gbc_tfAluCidade.gridwidth = 3;
		gbc_tfAluCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAluCidade.insets = new Insets(0, 0, 5, 5);
		gbc_tfAluCidade.gridx = 1;
		gbc_tfAluCidade.gridy = 2;
		contentPane.add(tfAluCidade, gbc_tfAluCidade);
		tfAluCidade.setColumns(10);
		
		lblFone = new JLabel("Fone:");
		GridBagConstraints gbc_lblFone = new GridBagConstraints();
		gbc_lblFone.anchor = GridBagConstraints.WEST;
		gbc_lblFone.insets = new Insets(0, 0, 5, 5);
		gbc_lblFone.gridx = 4;
		gbc_lblFone.gridy = 2;
		contentPane.add(lblFone, gbc_lblFone);
		
		tfAluFone = new JTextField();
		GridBagConstraints gbc_tfAluFone = new GridBagConstraints();
		gbc_tfAluFone.insets = new Insets(0, 0, 5, 0);
		gbc_tfAluFone.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAluFone.gridx = 5;
		gbc_tfAluFone.gridy = 2;
		contentPane.add(tfAluFone, gbc_tfAluFone);
		tfAluFone.setColumns(10);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfAluNome.setText("");
				tfAluCurso.setText("");
				tfAluCidade.setText("");
				tfAluFone.setText("");
				codAluno = -1;
			}
		});
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.insets = new Insets(0, 0, 5, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 3;
		contentPane.add(btnNovo, gbc_btnNovo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalvar.gridx = 1;
		gbc_btnSalvar.gridy = 3;
		contentPane.add(btnSalvar, gbc_btnSalvar);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (codAluno == -1) {
					codAluno = Integer.valueOf(JOptionPane.showInputDialog("Digite o código do aluno"));
				}
				atualizar();
				fillTable();
				repaint();
			}
		});
		GridBagConstraints gbc_btnAtualizar = new GridBagConstraints();
		gbc_btnAtualizar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtualizar.gridx = 2;
		gbc_btnAtualizar.gridy = 3;
		contentPane.add(btnAtualizar, gbc_btnAtualizar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (codAluno == -1) {
					codAluno = Integer.valueOf(JOptionPane.showInputDialog("Digite o código do aluno"));
				}
				excluir();
				fillTable();
				repaint();
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.BOTH;
		gbc_btnExcluir.insets = new Insets(0, 0, 5, 5);
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 3;
		contentPane.add(btnExcluir, gbc_btnExcluir);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				codAluno = table.getSelectedRow();
				tfAluNome.setText(listAluno.get(codAluno).getAlu_nome());
				tfAluCurso.setText(listAluno.get(codAluno).getAlu_curso());
				tfAluCidade.setText(listAluno.get(codAluno).getAlu_cidade());
				tfAluFone.setText(listAluno.get(codAluno).getAlu_fone());
			}
		});
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Curso", "Cidade", "Fone"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(51);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(table);
		
		codAluno = -1;
		fillTable();
		setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unchecked")
	public void fillTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		
		Session session = getSessionFactory().openSession();
		listAluno = new ArrayList<Aluno>();
		listAluno = session.createQuery("from Aluno").list();
		
		
		for (int i = 0; i < listAluno.size(); i++) {
			Aluno aluno = listAluno.get(i);
			defaultTableModel.addRow(new Object[] {aluno.getAlu_codigo(), aluno.getAlu_nome(), aluno.getAlu_curso(), aluno.getAlu_cidade(), aluno.getAlu_fone()});
		}
		session.close();
	}
	
	public void salvar() {
		Session session = getSessionFactory().openSession();
		Aluno aluno = new Aluno();
		aluno.setAlu_nome(tfAluNome.getText());
		aluno.setAlu_curso(tfAluCurso.getText());
		aluno.setAlu_cidade(tfAluCidade.getText());
		aluno.setAlu_fone(tfAluFone.getText());
		Transaction transaction = session.beginTransaction();
		session.save(aluno);
		transaction.commit();
		session.close();
	}
	
	public void atualizar() {
		Session session = getSessionFactory().openSession();
		Aluno aluno = new Aluno();
		aluno.setAlu_codigo(codAluno);
		aluno.setAlu_nome(tfAluNome.getText());
		aluno.setAlu_curso(tfAluCurso.getText());
		aluno.setAlu_cidade(tfAluCidade.getText());
		aluno.setAlu_fone(tfAluFone.getText());
		Transaction transaction = session.beginTransaction();
		session.update(aluno);
		transaction.commit();
		session.close();
	}
	
	public void excluir() {
		Session session = getSessionFactory().openSession();
		Aluno aluno = new Aluno();
		aluno.setAlu_codigo(codAluno);
		Transaction transaction = session.beginTransaction();
		session.delete(aluno);
		transaction.commit();
		session.close();
	}

	public SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}
