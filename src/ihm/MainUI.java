package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import gcm.model.Patient;
import gcm.model.PatientDaoJDBC;

import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldNumeroSecuSoc;
	private JTextArea textFieldAdresse;
	private JTextField textFieldAge;
	private JTable table;

	String[] colonnes = { "Nom", "Prénom", "Adresse", "N° SS", "Age", "Code Postal", "Ville" };
	String[][] donnees = { { "LGS", "Farid", "rue de joie", "1010120304054", "21", "59000", "LILLE" } };

	JPanel panelAjoutPatient;
	JPanel panelConsultation;
	JScrollPane scrollPane;
	int nss;
	
	String ville;
	String adresse;
	JDialog jdialogUpdate;
	
	static JDialog jDialog;
	/**
	 * @wbp.nonvisual location=15,514
	 */
	private final JToolBar toolBar = new JToolBar();
	/**
	 * @wbp.nonvisual location=39,524
	 */
	private final JButton buttonAdd = new JButton("Ajouter");

	/**
	 * Launch the application.
	 */

	JPanel panelAuthentification = new JPanel();
	JLabel lblUsername = new JLabel("Nom d'utilisateur");
	static JTextField txtUsername = new JTextField(10);
	JLabel lblPassword = new JLabel("Mot de passe");
	static JPasswordField txtpwd = new JPasswordField(10);

	static JButton btnLogin = new JButton("Valider");
	static JButton btnReset = new JButton("Annuler");
	JTextArea textAreaAdrEdit = new JTextArea();
	JTextArea textAreaVilleEdit = new JTextArea();
	public void createDialogAuthentification() {

		panelAuthentification.add(lblUsername);
		panelAuthentification.add(txtUsername);
		panelAuthentification.add(lblPassword);
		panelAuthentification.add(txtpwd);
		panelAuthentification.add(btnLogin);
		panelAuthentification.add(btnReset);
		jDialog.getContentPane().add(panelAuthentification);
		jDialog.setBounds(300, 300, 250, 200);

	}

	public static void main(String[] args) {

		MainUI frame = new MainUI();
		jDialog = new JDialog(frame, "Authentifiaction");
		frame.createDialogAuthentification();
		jDialog.setVisible(true);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user = txtUsername.getText();
				String pwd = txtpwd.getText();
				if ("admin".equals(user) && "admin".equals(pwd)) {
					frame.setVisible(true);
					jDialog.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(frame, "Veuillez réessayer une autre fois ", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { MainUI
		 * frame = new MainUI(); frame.setVisible(true); } catch (Exception e) {
		 * e.printStackTrace(); } } });
		 */
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setTitle("Gestion Cabinet M\u00E9dical");
		setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 662);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Application");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Quitter");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Patient");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consulter la liste des patients");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("consultation");
				PatientDaoJDBC patientDao = new PatientDaoJDBC();
				List<Patient> listePatient = patientDao.findAll();
				donnees = new String[listePatient.size()][7];

				for (int i = 0; i < listePatient.size(); i++) {
					Patient p = listePatient.get(i);// récupération de patient numéro i

					donnees[i][0] = "" + p.getNom();
					donnees[i][1] = "" + p.getPrenom();
					donnees[i][2] = "" + p.getAdresse();
					donnees[i][3] = "" + p.getNss();
					donnees[i][4] = "" + p.getDatedeNaissance();
					donnees[i][5] = "" + p.getCodePostal();
					donnees[i][6] = "" + p.getVille();

				}
				table = new JTable(donnees, colonnes);
				scrollPane.setViewportView(table);
				panelConsultation.updateUI();

				System.out.print("consultation");
				contentPane.remove(panelAjoutPatient);// supprimer panelAjoutPatient de panel principal contenPane
				contentPane.add(panelConsultation, BorderLayout.CENTER);// ajouter panelConsultation dans le centre de contentPane
				contentPane.updateUI();// mettre à jour le contentPane aprés la modification

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ajouter un nouveau patient");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.print("ajout");
				contentPane.remove(panelConsultation);// supprimer panelConsultation de panel principal contenPane
				contentPane.add(panelAjoutPatient, BorderLayout.CENTER);// ajouter panelAjoutPatient dans le centre de
																		// contentPane
				contentPane.updateUI();// mettre à jour le contentPane aprés la modification
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		panelAjoutPatient = new JPanel();
		panelAjoutPatient.setBackground(Color.PINK);
		panelAjoutPatient.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ajout de patient", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panelAjoutPatient.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(191, 113, 45, 20);
		panelAjoutPatient.add(lblNewLabel);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(335, 113, 146, 26);

		panelAjoutPatient.add(textFieldNom);
		textFieldNom.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(167, 166, 69, 20);

		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelAjoutPatient.add(lblNewLabel_1);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(335, 163, 146, 26);
		panelAjoutPatient.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Num\u00E9ro S\u00E9curit\u00E9 Sociale");
		lblNewLabel_2.setBounds(95, 52, 192, 20);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelAjoutPatient.add(lblNewLabel_2);

		textFieldNumeroSecuSoc = new JTextField();
		textFieldNumeroSecuSoc.setBounds(319, 51, 310, 26);
		panelAjoutPatient.add(textFieldNumeroSecuSoc);
		textFieldNumeroSecuSoc.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Adresse");
		lblNewLabel_3.setBounds(167, 245, 69, 20);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelAjoutPatient.add(lblNewLabel_3);

		textFieldAdresse = new JTextArea();
		textFieldAdresse.setBounds(332, 211, 310, 110);
		panelAjoutPatient.add(textFieldAdresse);
		textFieldAdresse.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Age");
		lblNewLabel_4.setBounds(191, 474, 146, 26);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelAjoutPatient.add(lblNewLabel_4);

		textFieldAge = new JTextField();
		textFieldAge.setBounds(381, 480, 222, 20);
		panelAjoutPatient.add(textFieldAge);
		textFieldAge.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Ville");
		lblNewLabel_5.setBounds(191, 412, 45, 20);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelAjoutPatient.add(lblNewLabel_5);

		JComboBox comboBoxVille = new JComboBox();
		comboBoxVille.setBounds(337, 383, 156, 26);
		comboBoxVille.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxVille.addItem("Lille");
		comboBoxVille.addItem("Valenciennes");
		comboBoxVille.addItem("Orchies");
		comboBoxVille.addItem("Denain");
		comboBoxVille.addItem("Tourcoing");
		comboBoxVille.addItem("Lambersart");
		comboBoxVille.addItem("Lomme");
		comboBoxVille.addItem("Marcq-en-Baroeul");
		comboBoxVille.addItem("La Bassée");
		comboBoxVille.addItem("Dunkerque");
		comboBoxVille.addItem("Cassel");
		panelAjoutPatient.add(comboBoxVille);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient patient = new Patient();

				String val1 = textFieldNumeroSecuSoc.getText();

				int nss = Integer.parseInt(val1);// Convertir String en entier
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				String adresse = textFieldAdresse.getText();

				String val2 = textFieldAge.getText();
				int age = Integer.parseInt(val2);

				String ville = comboBoxVille.getSelectedItem().toString();

				patient.setNss(nss);
				patient.setNom(nom);
				patient.setPrenom(prenom);
				patient.setAdresse(adresse);
				// patient.setDateDeNaissance(new Date());
				patient.setVille(ville);

				System.out.println(patient);
				PatientDaoJDBC patientDao = new PatientDaoJDBC();
				patientDao.add(patient);

				JOptionPane.showConfirmDialog(MainUI.this, "Patient est bien ajouté", "Succès",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnNewButton.setBounds(269, 541, 115, 29);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelAjoutPatient.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.setBounds(470, 541, 115, 29);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelAjoutPatient.add(btnNewButton_1);

		panelConsultation = new JPanel();

		JButton ButtonShow = new JButton("Afficher");
		ButtonShow.setIcon(new ImageIcon(
				"C:\\Users\\afpa.POR7302374\\eclipse-workspace\\GCM\\images\\iconfinder_view-show_3671905.png"));
		ButtonShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientDaoJDBC patientDao = new PatientDaoJDBC();
				List<Patient> listePatient = patientDao.findAll();
				donnees = new String[listePatient.size()][7];

				for (int i = 0; i < listePatient.size(); i++) {
					Patient p = listePatient.get(i);// récupération de patient numéro i

					donnees[i][0] = "" + p.getNom();
					donnees[i][1] = "" + p.getPrenom();
					donnees[i][2] = "" + p.getAdresse();
					donnees[i][3] = "" + p.getNss();
					donnees[i][4] = "" + p.getDatedeNaissance();
					donnees[i][5] = "" + p.getCodePostal();
					donnees[i][6] = "" + p.getVille();

				}
				table = new JTable(donnees, colonnes);
				scrollPane.setViewportView(table);
				panelConsultation.updateUI();

				System.out.print("consultation");
				contentPane.remove(panelAjoutPatient);// supprimer panelAjoutPatient de panel principal contenPane
				contentPane.add(panelConsultation, BorderLayout.CENTER);// ajouter panelConsultation dans le centre de
																		// contentPane
				contentPane.updateUI();// mettre à jour le contentPane aprés la modification

			}
		});
		panelConsultation.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();

		table = new JTable(donnees, colonnes);
		scrollPane.setViewportView(table);

		panelConsultation.add(scrollPane);
		contentPane.add(panelConsultation, BorderLayout.CENTER);
		buttonAdd.setIcon(
				new ImageIcon("C:\\Users\\afpa.POR7302374\\eclipse-workspace\\GCM\\images\\iconfinder_add_134224.png"));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("ajout");
				contentPane.remove(panelConsultation);// supprimer panelConsultation de panel principal contenPane
				contentPane.add(panelAjoutPatient, BorderLayout.CENTER);// ajouter panelAjoutPatient dans le centre de
																		// contentPane
				contentPane.updateUI();// mettre à jour le contentPane aprés la modification

			}
		});
		toolBar.add(buttonAdd);
		toolBar.add(ButtonShow);
		contentPane.add(toolBar, BorderLayout.NORTH);
		getContentPane().add(contentPane);

		JPanel panelAction = new JPanel();
		contentPane.add(panelAction, BorderLayout.SOUTH);

		JButton btnDelete = new JButton("Supprimer");

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				System.out.println("row=" + row);
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Attention", "Veuillez sélectionner un patient",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String value = table.getValueAt(row, 3).toString();
					System.out.println("value=" + value);
					PatientDaoJDBC patientdao = new PatientDaoJDBC();
					int nss = Integer.parseInt(value);
					patientdao.delete(nss);
				}

			}
		});
		panelAction.add(btnDelete);
		JPanel panelModification = new JPanel();

		JButton btnUpdate = new JButton("Modifier");
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row=table.getSelectedRow();
				System.out.println("row=" + row);
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Attention", "Veuillez sélectionner un patient",
							JOptionPane.WARNING_MESSAGE);
				} else {
				
				adresse=table.getValueAt(row, 2).toString();
				ville=table.getValueAt(row, 6).toString();
				String nsstext=table.getValueAt(row, 3).toString();
				
				nss=Integer.parseInt(nsstext);
				textAreaAdrEdit.setText(adresse);
                textAreaVilleEdit.setText(ville);
				
				
				 jdialogUpdate = new JDialog(MainUI.this, "Modification du patient");
				jdialogUpdate.getContentPane().add(panelModification);
				jdialogUpdate.setBounds(300, 300, 800, 600);
				jdialogUpdate.setVisible(true);
				}

			}
		});
		panelAction.add(btnUpdate);

		//contentPane.add(panelModification, BorderLayout.CENTER);
		panelModification.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Adresse");
		lblNewLabel_6.setBounds(182, 85, 77, 19);
		panelModification.add(lblNewLabel_6);

		
		textAreaAdrEdit.setBounds(292, 85, 284, 102);
		panelModification.add(textAreaAdrEdit);

		JLabel lblNewLabel_7 = new JLabel("Ville");
		lblNewLabel_7.setBounds(182, 253, 66, 13);
		panelModification.add(lblNewLabel_7);

		
		textAreaVilleEdit.setBounds(292, 253, 284, 13);
		panelModification.add(textAreaVilleEdit);

		JButton btnValiderModification = new JButton("Valider");
		btnValiderModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("modifier patient"+nss);
				PatientDaoJDBC patientDaoJDBC=new PatientDaoJDBC();
				patientDaoJDBC.update(nss,textAreaVilleEdit.getText(),textAreaAdrEdit.getText());
				jdialogUpdate.setVisible(false);

			}
		});
		btnValiderModification.setBounds(292, 297, 97, 21);
		panelModification.add(btnValiderModification);

		JButton btnreset = new JButton("Annuler");
		btnreset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnreset.setBounds(491, 300, 85, 19);
		panelModification.add(btnreset);

	}
	

	
	public void resetFields() {
		textAreaVilleEdit.setText("");
		textAreaAdrEdit.setText("");
    }
}
