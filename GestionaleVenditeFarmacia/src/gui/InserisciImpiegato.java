package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import methodsGui.InputDataGui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

/**
 * Questo JFrame permette di inserire un nuovo impiegato all'interno della tabella del JFrame GestioneImpiegati
 * @author CardinaleChristian 
 *
 */
public class InserisciImpiegato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/*
	 * Dichiarazione delle componenti visibili solo a questo JFrame
	 */
	private JTextField textCognome = new JTextField();
	private JTextField textNome = new JTextField();
	private JTextField textLimite = new JTextField();
	JLabel lblCognomeErrato = new JLabel("Cognome errato!");
	JLabel lblNomeErrato = new JLabel("Nome errato!");
	JLabel lblLimiteErrato = new JLabel("Limite errato!");
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserisciImpiegato frame = new InserisciImpiegato();
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
	public InserisciImpiegato() {
		InitUI();
	}
	
	/**
	 * Con questo metodo setto l'interfaccia con tutte le sue componenti
	 */
	private void InitUI() {
		createPane();
		createButton();
		createLabel();
		createTextField();
		
	}
	
	/**
	 * Con questo metodo setto il pannello all'interno della finestra
	 */
	private void createPane() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("impiegati.png"));
		setTitle("Inserisci Impiegato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	/**
	 * Con questo metodo setto i pulsanti all'interno della finestra
	 */
	private void createButton() {
		/*
		 * Pulsante per inserire un nuovo impiegato
		 */
		JButton buttonInserisci = new JButton("Inserisci");
		buttonInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nomeImp = textNome.getText();
				String cognomeImp = textCognome.getText();
				String limite = textLimite.getText();
				
				boolean flagInserimentoCorretto = false;
				
				try {
					//Controllo sull'inserimento del nome,cognome o limite
					if(nomeImp.equals("") || cognomeImp.equals("") || limite.equals("") ) {
						JOptionPane.showMessageDialog(contentPane, "ATTENZIONE!\n E' presente almeno un campo vuoto");
						flagInserimentoCorretto = false;
					}else {
						flagInserimentoCorretto = true;
					}
					//Controllo sull'inserimento corretto del nome
					if(!nomeImp.matches("[a-zA-Z//s]+") ) {
						flagInserimentoCorretto = false;
						lblNomeErrato.setForeground(Color.RED);
						lblNomeErrato.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNomeErrato.setBounds(430, 114, 149, 38);
						contentPane.add(lblNomeErrato);
						lblNomeErrato.setVisible(true);
						
					}else {
						flagInserimentoCorretto = true;
						lblNomeErrato.setVisible(false);
					}
					//Controllo sull'inserimento corretto del limite
					if(!limite.matches("[0-9]+") ) {
						flagInserimentoCorretto = false;
						lblLimiteErrato.setForeground(Color.RED);
						lblLimiteErrato.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblLimiteErrato.setBounds(430, 205, 149, 28);
						contentPane.add(lblLimiteErrato);
						lblLimiteErrato.setVisible(true);
					
					}else {
						flagInserimentoCorretto = true;
						lblLimiteErrato.setVisible(false);
					}
					//Controllo sull'inserimento corretto del cognome
					if(!cognomeImp.matches("[a-zA-Z//s]+") ) {
						flagInserimentoCorretto = false;
						lblCognomeErrato.setForeground(Color.RED);
						lblCognomeErrato.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblCognomeErrato.setBounds(430, 40, 176, 23);
						contentPane.add(lblCognomeErrato);
						lblCognomeErrato.setVisible(true);
					
					}else {
						flagInserimentoCorretto = true;
						lblCognomeErrato.setVisible(false);
					}
					
					
					int limiteInt = Integer.parseInt(limite);
					GestioneImpiegati frameImpiegati = new GestioneImpiegati();
					//Aggiungiamo le righe dei campi di testo inseriti dall'utente nella tabella
					if(flagInserimentoCorretto == true && eccezioniGui.ControlliGui.checkNumeroVenditeAnnuali(limiteInt) == false) {
						frameImpiegati.addRowToImpiegatiTable(new Object[] {
																nomeImp,
																cognomeImp,
																limiteInt
															 });
						InputDataGui inputDataGui = new InputDataGui();
						inputDataGui.addImpiegato(cognomeImp, nomeImp, limiteInt);
						
						
						JOptionPane.showMessageDialog(contentPane, "Impiegato Aggiunto!");
					}else {
						JOptionPane.showMessageDialog(contentPane, "Fuori dal Range!");
					}
					
				}catch(Exception e1) {
					String errore = e1.getMessage() + "Errore";
					System.out.println(errore);
				}
			}
		});
		buttonInserisci.setBounds(38, 311, 134, 52);
		contentPane.add(buttonInserisci);
		
		/*
		 * Pulsante per pulire le caselle
		 */
		JButton buttonPulisciCaselle = new JButton("Pulisci Caselle");
		buttonPulisciCaselle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textNome.setText("");
				textCognome.setText("");
				textLimite.setText("");
			}
		});	
		buttonPulisciCaselle.setBounds(210, 311, 134, 52);
		contentPane.add(buttonPulisciCaselle);
		
		/*
		 * Pulsante per tornare alla schermata precedente
		 */
		JButton buttonChiudi = new JButton("Chiudi");
		buttonChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestioneImpiegati frameImpiegati = new GestioneImpiegati();
				setVisible(false);
				frameImpiegati.setVisible(true);
			}
		});		
		buttonChiudi.setBounds(390, 311, 134, 52);
		contentPane.add(buttonChiudi);
	}
	
	/**
	 * Con questo metodo setto le JLabel presenti all'interno della finestra
	 */
	private void createLabel() {
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCognome.setBounds(83, 23, 89, 56);
		contentPane.add(lblCognome);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNome.setBounds(83, 105, 89, 56);
		contentPane.add(lblNome);
		
		JLabel lblLimiteVenditeAnnuali = new JLabel("Limite Vendite Annuali");
		lblLimiteVenditeAnnuali.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLimiteVenditeAnnuali.setBounds(12, 195, 194, 38);
		contentPane.add(lblLimiteVenditeAnnuali);
		
		JLabel lblIntervalloVenditeAnnuali = new JLabel("[1000-14000]");
		lblIntervalloVenditeAnnuali.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIntervalloVenditeAnnuali.setBounds(38, 225, 112, 32);
		contentPane.add(lblIntervalloVenditeAnnuali);
	}
	
	/**
	 * Con questo metodo setto i campi di testo presenti all'interno della finestra
	 */
	private void createTextField() {
		textCognome.setFont(new Font("Tahoma", Font.ITALIC, 16));
		
		textCognome.setBackground(Color.WHITE);
		textCognome.setBounds(222, 24, 194, 56);
		contentPane.add(textCognome);
		textNome.setFont(new Font("Tahoma", Font.ITALIC, 16));
		
		
		textNome.setBounds(222, 106, 194, 56);
		contentPane.add(textNome);
		textLimite.setFont(new Font("Tahoma", Font.ITALIC, 16));
		
		
		textLimite.setBounds(222, 187, 194, 56);
		contentPane.add(textLimite);
		
		
		
		
		
		
		
	}
}
