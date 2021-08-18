package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import eccezioniGui.ControlliGui;
import impiegato.Impiegato;
import methodsGui.PrintDataGui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JLabel;
import java.awt.Font;
import methodsGui.*;

/**
 * 
 * @author LorussoDanilo
 *Questa è la finestra che permette di gestire i dati relativi agli impiegati
 */
public class GestioneImpiegati extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Costante che determina la dimensione dell'arraylist
	 */
	private static final int DIM_IMPIEGATI = 20;
	/*
	 * Definizione delle variabili utilizzate per la modifica dei dati degli impiegati
	 */
	String nuovoCognome = "";
	String nuovoNome = "";
	String nuovoLimite = "";
	
	/*
	 * Definizione delle componenti
	 */
	private JPanel contentPane;
	private JTable impiegatiTable;
	
	/*
	 * Istanziazione dell'arrayList di tipo Impiegato
	 */
	private ArrayList <Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI);


	private static GestioneImpiegati frameImpiegati = new GestioneImpiegati();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frameImpiegati.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestioneImpiegati() {
		initUI();
	}
	
	
	/**
	 * Questo metodo permette di inizializzare l'interfaccia
	 */
	private void initUI() {	
		createPane();
		createMenuBar();
		createButton();
		createJLabel();
	}
	
	/**
	 * Con questo metodo setto i bottoni all'interno della finestra 
	 */
	private void createButton() {
		/*
		 * Pulsante che consente di accedere alla finestra di inserimento di nuovi dati
		 */
		JButton buttonInserisci = new JButton("Inserisci");
		buttonInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciImpiegato frameText = new InserisciImpiegato();
				
				frameText.setVisible(true);
				setVisible(false);
				frameText.setBounds(100,100,676,495);
				frameText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
			
		});
		
		buttonInserisci.setBounds(677, 109, 112, 46);
		contentPane.add(buttonInserisci);
		/*
		 * Pulsante per modifica dei dati
		 */
		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try {
				//Assegno alla variabile model il modello della tabella degli impiegati(Colonne,Righe)
				DefaultTableModel model = (DefaultTableModel) impiegatiTable.getModel();
				//Assegno alla variabile indice, l'indice della riga selezionata
				int indice = impiegatiTable.getSelectedRow();
				//Assegno i valori dei campi della tabella alle seguenti variabili
				String cognome = model.getValueAt(indice, 1).toString();
				String nome = model.getValueAt(indice, 2).toString();
				String limite = model.getValueAt(indice, 3).toString();
				
				/*
				 * Inserimento nuovo cognome 
				 */
				do {
					nuovoCognome = JOptionPane.showInputDialog(null, "Inserisci il Cognome",cognome);
					if(!nuovoCognome.matches("[a-zA-Z]+")){
						JOptionPane.showMessageDialog( frameImpiegati,"Inserire solo caratteri alfabetici!",
					               "Valore Inserito Errato!", JOptionPane.WARNING_MESSAGE);

					}
					
				}while(nuovoCognome.isEmpty() || !nuovoCognome.matches("[a-zA-Z]+"));
				
				/*
				 * Inserimento nuovo nome 
				 */
				do {
					nuovoNome = JOptionPane.showInputDialog(null, "Inserisci il nome",nome);
					if(!nuovoNome.matches("[a-zA-Z]+")){
						JOptionPane.showMessageDialog( frameImpiegati,"Inserire solo caratteri alfabetici!!",
					               "Valore Inserito Errato!", JOptionPane.WARNING_MESSAGE);

					}
				}while(nuovoNome.isEmpty() || !nuovoNome.matches("[a-zA-Z]+"));
				
				/*
				 * Inserimento nuovo limite
				 */
				do {
					nuovoLimite = JOptionPane.showInputDialog(null, "Inserisci il nuovo limite:",limite);
					int limiteInt = Integer.parseInt(nuovoLimite);
					
					
					/*
					 * Se la condizione non è soddisfatta i valori rimangono invariati
					 */
					if(ControlliGui.checkNumeroVenditeAnnuali(limiteInt) == false ) {
							
							model.setValueAt(nuovoCognome, indice, 1);
							model.setValueAt(nuovoNome, indice, 2);
							model.setValueAt(nuovoLimite, indice, 3);
							
							ChangeDataGui changeDataGui = new ChangeDataGui();
							changeDataGui.modificaImpiegatoGui(impiegati, indice, nuovoCognome, nuovoNome, limiteInt);
						}else {
								JOptionPane.showMessageDialog(null, "Fuori dal Range");
						}
					
				}while(nuovoLimite.isEmpty() || !nuovoLimite.matches("100[0-9]|10[1-9][0-9]|1[1-9][0-9]{2}|[2-9][0-9]{3}|1[0-3][0-9]{3}|14000"));//finchè l'input non è vuoto o non è compreso tra 100-14000(limite min - limite max)
			
			}catch(Exception e1) {
					
					String errore = e1.getMessage() + "Errore";
					System.out.println(errore);
					if (nuovoLimite.matches(("[a-zA-Z//s]+")) ){
						
						JOptionPane.showMessageDialog( frameImpiegati,"Il limite inserito non è un numero!",
					               "MODIFICA NON EFFETTUATA!", JOptionPane.WARNING_MESSAGE);
						}
			}
				
		}
		});
		
		buttonModifica.setBounds(677, 187, 112, 46);
		contentPane.add(buttonModifica);
		
		/*
		 * Pulsante per rimozione dati
		 */
		JButton buttonRimuovi = new JButton("Rimuovi");
		buttonRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel model = (DefaultTableModel) impiegatiTable.getModel();
					int indice = impiegatiTable.getSelectedRow();
					
					model.removeRow(indice);
					
					DeleteDataGui deleteDataGui = new DeleteDataGui(); 
					deleteDataGui.removeImpiegato(impiegati, indice);
					
				}catch(Exception e) {
					String errore = e.getMessage() + "Errore";
					System.out.println(errore);
				}
			}
		});
		buttonRimuovi.setBounds(677, 261, 112, 46);
		contentPane.add(buttonRimuovi);
		
		/*
		 * Pulsante per tornare alla schermata principale
		 */
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //La finestra corrente viene resa non visibile
				GestionaleFarmacia.frameGestionaleFarmacia.setVisible(true);
				
			}
		});
		buttonIndietro.setBounds(677, 340, 112, 46);
		contentPane.add(buttonIndietro);
		
		
	}
	
	/**
	 * Questo metodo contiene tutte le instanziazioni di JLabel presenti nel JFrame
	 */
	private void createJLabel() {
		JLabel lbIInfo = new JLabel("*Per effettuare una rimozione o una modifica dei dati , selezionare la riga desiderata");
		lbIInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbIInfo.setBounds(10, 490, 635, 22);
		contentPane.add(lbIInfo);
		
		JLabel label = new JLabel("[1000-14000]");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(487, 43, 138, 21);
		contentPane.add(label);
		
		JLabel lblperAggiornareLid = new JLabel("*Per aggiornare l'ID dopo la rimozione premere \"Indietro\" e tornare in questa finestra");
		lblperAggiornareLid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblperAggiornareLid.setBounds(10, 513, 536, 14);
		contentPane.add(lblperAggiornareLid);
	}
	
	/**
	 * Con questo metodo setto il pannello della finestra
	 */
	private void createPane() {
		setTitle("Gestione Impiegati");
		setIconImage(Toolkit.getDefaultToolkit().getImage("impiegati.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Creazione pannello che permette di scorrere i dati all'interno della tabella
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 615, 402);
		contentPane.add(scrollPane);
		
		//Visualizzazione della tabella degli impiegati all'interno del pannello
		visualizzaTabellaImpiegati();
		
		scrollPane.setViewportView(impiegatiTable);
	}
	
	/**
	 * Con questo metodo setto il la barra del menù della finestra
	 */
	@SuppressWarnings("deprecation")
	private void createMenuBar() {
		/*
		 * Creazione barra menù
		 */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 636, 32);
		contentPane.add(menuBar);
		
		JMenuItem buttonGestioneVendite = new JMenuItem("Gestione Vendite");
		buttonGestioneVendite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		buttonGestioneVendite.setIcon(new ImageIcon("Vendite.jpg"));
		buttonGestioneVendite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestioneVendite frameVendite = new GestioneVendite();
				setVisible(false);
				frameVendite.setVisible(true);
			}
		});
		menuBar.add(buttonGestioneVendite);
		
		JMenuItem buttonGestioneFarmaci = new JMenuItem("Gestione Farmaci");
		buttonGestioneFarmaci.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		buttonGestioneFarmaci.setIcon(new ImageIcon("pillola.png"));
		buttonGestioneFarmaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestioneFarmaci frameFarmaci = new GestioneFarmaci();
				setVisible(false);
				frameFarmaci.setVisible(true);
			}
		});
		menuBar.add(buttonGestioneFarmaci);
		
		JMenuItem buttonEsci = new JMenuItem("Esci");
		buttonEsci.setToolTipText("Premere CTRL+E per uscire");
		buttonEsci.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		buttonEsci.addActionListener( (event) -> System.exit(0) );
		buttonEsci.setIcon(new ImageIcon("ExitIcon.png"));
		
		menuBar.add(buttonEsci);
	}
	
	/**
	 * Questo metodo permette la visualizzazione dei dati degli impiegati all'interno della tabella
	 */
	public void visualizzaTabellaImpiegati() {
		
		//Inizializzazione colonne
		String colImpiegati[] = {"ID", "Cognome","Nome", "Limite Vendite Annuali"};
		//Definisco il modello della tabella
		DefaultTableModel tabellaImpiegatiModel = new DefaultTableModel(colImpiegati, 0);
		
		PrintDataGui printDataGui = new PrintDataGui();
		//Assegno al modello della tabella, quello definito nel metodo
		tabellaImpiegatiModel = printDataGui.visualizzaDatiImpiegati(impiegati, tabellaImpiegatiModel);
		
		if(impiegati.isEmpty()) {
			JLabel lblNonCiSonoImpiegati = new JLabel("Non ci sono impiegati memorizzati!");
			lblNonCiSonoImpiegati.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNonCiSonoImpiegati.setBounds(10, 37, 272, 33);
			contentPane.add(lblNonCiSonoImpiegati);
			
			
		}
		
		
		//Assegno alla tabella il modello definito in precedenza
		impiegatiTable = new JTable(tabellaImpiegatiModel) {
		
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			/**
			 *
			 * Con questo metodo i campi della tabella non sono editabili
			 */
			public boolean isCellEditable(int i,int i1) {
		    	return false;
			}
		};
		impiegatiTable.getTableHeader().setReorderingAllowed(false);
		impiegatiTable.getTableHeader().setResizingAllowed(false);
		
		
	}
	
	/**
	 * Questo metodo permette di aggiungere una riga alla tabella contenente gli impiegati 
	 */
	public void addRowToImpiegatiTable(Object[] dataRow) {
		
		//Inizializzazione colonne
		String colImpiegati[] = {"ID", "Cognome","Nome", "Limite Vendite Annuali"};
		//Definisco il modello della tabella
		DefaultTableModel tabellaImpiegatiModel = new DefaultTableModel(colImpiegati, 0);
		
		PrintDataGui printDataGui = new PrintDataGui();
		//Assegno al modello della tabella, quello definito nel metodo
		tabellaImpiegatiModel = printDataGui.visualizzaDatiImpiegati(impiegati, tabellaImpiegatiModel);
		
		if(impiegati.isEmpty()) {
			JLabel lblNonCiSonoImpiegati = new JLabel("Non ci sono impiegati memorizzati!");
			lblNonCiSonoImpiegati.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNonCiSonoImpiegati.setBounds(10, 37, 272, 33);
			contentPane.add(lblNonCiSonoImpiegati);
			
		}
		
		
		//Assegno alla tabella il modello definito in precedenza
		impiegatiTable = new JTable(tabellaImpiegatiModel);
		
		impiegatiTable.getTableHeader().setReorderingAllowed(false);
		impiegatiTable.getTableHeader().setResizingAllowed(false);
		impiegatiTable.setCellSelectionEnabled(true);
		
		tabellaImpiegatiModel.addRow(dataRow);
		
		
	}
}
