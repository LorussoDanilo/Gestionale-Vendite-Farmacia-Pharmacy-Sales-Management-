package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import eccezioniGui.ControlliGui;
import methodsGui.InputDataGui;
import farmaco.Farmaco;
import methodsGui.ChangeDataGui;
import methodsGui.SaveDataGui;
import methodsGui.DeleteDataGui;
import methodsGui.ImportDataGui;
import methodsGui.PrintDataGui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JLabel;

/**
 * 
 * @author Cardinale Christian,Barbieri Giuseppe
 *Questa è la finestra che permette di gestire i dati relativi ai farmaci
 */
public class GestioneFarmaci extends JFrame {

	/*
	 * Definizione delle costanti relative alla dimensione degli ArrayList
	 */
	final static int DIM_VENDITE = 20;
	final static int DIM_IMPIEGATI = 10;
	final static int DIM_FARMACI = 20;
	/*
	 * Definizione delle variabili utilizzate per la modifica dei dati dei farmaci
	 */
	String nuovoFarmaco = "";
	String nuovoConfezionamento = "";
	String nuovaScadenza = "";
	String nuovoTipo = "";
	String nuovoPrezzo = "";
	
	static PrintDataGui PrintDataGui = new PrintDataGui();
	static SaveDataGui SaveDataGui = new SaveDataGui();
	static InputDataGui InputDataGui = new InputDataGui();
	static ChangeDataGui ChangeDataGui = new ChangeDataGui();
	static DeleteDataGui DeleteDataGui = new DeleteDataGui();
	
	/*
	 * Instanziazione di un'istanza dell'arrayList di tipo Farmaco
	 */
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI);
	/*
	 * Definizione della variabile relativa al testo che indica che non sono presenti farmaci sul file
	 */
	private JTextField txtNonCiSono;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable farmaciTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneFarmaci frameFarmaci = new GestioneFarmaci();
					frameFarmaci.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestioneFarmaci() {
		initUI();
	}
	
	
	/**
	 * Questo metodo permette di inizializzare l'interfaccia
	 */
	private void initUI() {	
		createPane();
		createMenuBar();
		createJLabel();
		createButton();
	}
	
	/**
	 * Con questo metodo imposto il pannello della finestra
	 */
	private void createPane() {
		setTitle("Gestione Farmaci");
		setIconImage(Toolkit.getDefaultToolkit().getImage("pillola.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Creazione pannello che permette di scorrere i dati all'interno della tabella
		JScrollPane farmaciPane = new JScrollPane();
		farmaciPane.setBounds(10, 67, 802, 402);
		contentPane.add(farmaciPane);
		
		visualizzaFarmaciTable();
		
		farmaciPane.setColumnHeaderView(farmaciTable);
		farmaciPane.setViewportView(farmaciTable);
	}
	
	/**
	 * Con questo metodo imposto la barra del menù 
	 */
	@SuppressWarnings("deprecation")
	private void createMenuBar() {
		/*
		 * Creazione barra menù
		 */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 970, 37);
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
		
		JMenuItem buttonGestioneImpiegati = new JMenuItem("Gestione Impiegati");
		buttonGestioneImpiegati.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		buttonGestioneImpiegati.setIcon(new ImageIcon("impiegati.png"));
		buttonGestioneImpiegati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestioneImpiegati frameImpiegati = new GestioneImpiegati();
				setVisible(false);
				frameImpiegati.setVisible(true);
			}
		});
		menuBar.add(buttonGestioneImpiegati);
		
		JMenuItem buttonEsci = new JMenuItem("Esci");
		buttonEsci.setToolTipText("Premere CTRL+E per uscire");
		buttonEsci.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		buttonEsci.addActionListener( (event) -> System.exit(0) );
		
		JMenuItem buttonTipoFarmaci = new JMenuItem("Gest. Tipo Farmaci");
		buttonTipoFarmaci.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		buttonTipoFarmaci.setIcon(new ImageIcon("tipo_farmaci.png"));
		buttonTipoFarmaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestioneTipoFarmaci frameTipoFarmaci = new GestioneTipoFarmaci();
				setVisible(false);
				frameTipoFarmaci.setVisible(true);
			}
		
		});
		menuBar.add(buttonTipoFarmaci);
		buttonEsci.setIcon(new ImageIcon("ExitIcon.png"));
		menuBar.add(buttonEsci);
	
	}
	
	/**
	 * Metodo che contiene delle label informative su alcune funzioni della finestra
	 */
		private void createJLabel() {
			JLabel lblInfo = new JLabel("*Per effettuare una rimozione o una modifica dei dati , selezionare la riga desiderata");
			lblInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblInfo.setBounds(new Rectangle(10, 471, 552, 37));
			contentPane.add(lblInfo);
			
			JLabel lblRimozione = new JLabel("*Per aggiornare l'ID dopo la rimozione premere \"Indietro\" e tornare in questa finestra");
			lblRimozione.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblRimozione.setBounds(new Rectangle(10, 498, 580, 37));
			contentPane.add(lblRimozione);
		}
	
	
	/**
	 * Con questo metodo imposto i bottoni presenti nella finestra
	 */
	private void createButton() {
		/*
		 * Pulsante che permette la visualizzazione del frame che permette l'inserimento di un nuovo farmaco
		 */
		JButton buttonInserisci = new JButton("Inserisci");
		buttonInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciFarmaco frameText = new InserisciFarmaco();
				
				frameText.setVisible(true);
				setVisible(false);
				frameText.setBounds(100,100,676,495);
				frameText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
			
		});
		
		buttonInserisci.setBounds(836, 107, 112, 46);
		contentPane.add(buttonInserisci);
		
		/*
		 * Pulsante di modifica
		 */
		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.setActionCommand("Modifica");
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
						ArrayList<String> tipo_farmaci = new ArrayList<String>();
						ImportDataGui.importaTipoFarmaciGui(tipo_farmaci);
						String[] tipoFarmaci = new String[tipo_farmaci.size()];
						tipoFarmaci = tipo_farmaci.toArray(tipoFarmaci);
						DefaultTableModel model = (DefaultTableModel)farmaciTable.getModel();
						int indice = farmaciTable.getSelectedRow();
						
						String dataOdierna = "";
						SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
						sdf.setLenient(false);
						Date data = new Date();
						dataOdierna = sdf.format(data);
						/*
						 * Ottenimento dei valori dalla riga selezionata
						 */
						String farmaco = model.getValueAt(indice, 1).toString();
						String confezionamento = model.getValueAt(indice, 2).toString();	
						String scadenza = model.getValueAt(indice, 3).toString();
						String prezzo = model.getValueAt(indice, 5).toString();
						
						/*
						 * Inserimento nuovo Nome Farmaco
						 */
						do {
							nuovoFarmaco = JOptionPane.showInputDialog(null,"Inserisci il Nome del Farmaco:",farmaco);
						}while(nuovoFarmaco.isEmpty() || !nuovoFarmaco.matches("[a-zA-Z]+"));
						
						/*
						 * Inserimento nuova data confezionamento
						 */
						do {
							nuovoConfezionamento= JOptionPane.showInputDialog(null,"Inserisci la data di Confezionamento[dd/MM/yyyy]:",confezionamento);
							if(ControlliGui.checkConfrontaData(nuovoConfezionamento, dataOdierna) == false) {
								JOptionPane.showMessageDialog(null, "La data di confezionamento deve essere antecedente alla data odierna");
							}
						}while(nuovoConfezionamento.isEmpty() || !ControlliGui.checkData(nuovoConfezionamento) || ControlliGui.checkConfrontaData(nuovoConfezionamento, dataOdierna) == false);
						
						/*
						 * Inserimento data di scadenza
						 */
						do {
							nuovaScadenza= JOptionPane.showInputDialog(null,"Inserisci la data di Scadenza[dd/MM/yyyy]:",scadenza);
							if(ControlliGui.checkConfrontaData(nuovoConfezionamento, nuovaScadenza) == false) {
								JOptionPane.showMessageDialog(null, "La data di scadenza deve essere successiva alla data di confezionamento");
							}
							
							if(ControlliGui.checkConfrontaData(dataOdierna, nuovaScadenza) == false) {
								JOptionPane.showMessageDialog(null, "La data di scadenza deve essere successiva alla data odierna");
							}
						}while(nuovaScadenza.isEmpty() || !ControlliGui.checkData(nuovaScadenza) || ControlliGui.checkConfrontaData(nuovoConfezionamento, nuovaScadenza) == false || ControlliGui.checkConfrontaData(dataOdierna, nuovaScadenza) == false);
						
						/*
						 * Inserimento nuovo tipo farmaco
						 */
						do {
							nuovoTipo = (String)JOptionPane.showInputDialog(null,"Inserisci il tipo Farmaco:","Inserisci il tipo farmaco:",JOptionPane.QUESTION_MESSAGE,null,tipoFarmaci,tipoFarmaci);
						}while(nuovoTipo.isEmpty() || !nuovoTipo.matches("[a-zA-Z]+"));
						
						/*
						 * Inserimento nuovo prezzo del farmaco
						 */
						do {
							nuovoPrezzo = JOptionPane.showInputDialog(null,"Inserisci il prezzo del farmaco[00.00]:",prezzo);
							if(!nuovoPrezzo.matches("[0-9.]+")) {
								JOptionPane.showMessageDialog(null, "Inserire il formato corretto del prezzo!");
							}
						}while(nuovoPrezzo.isEmpty() || !nuovoPrezzo.matches("[0-9.]+"));
						
						/*
						 * Settaggio nuovi valori per la riga selezionata
						 */
						model.setValueAt(nuovoFarmaco,indice, 1);
						model.setValueAt(nuovoConfezionamento,indice, 2);
						model.setValueAt(nuovaScadenza,indice, 3);
						model.setValueAt(nuovoTipo,indice, 4);
						model.setValueAt(nuovoPrezzo,indice, 5);
						
						BigDecimal prezzoBigDecimal = new BigDecimal(nuovoPrezzo);
						
						ChangeDataGui.modificaFarmacoGui(farmaci,indice,nuovoFarmaco,nuovoConfezionamento,nuovaScadenza,nuovoTipo,prezzoBigDecimal);
						
						
				}catch(Exception e1) {
							String errore = e1.getMessage() + "Errore";
							System.out.println(errore);
				}
				
				
			}
			
		});
		buttonModifica.setBounds(836, 179, 112, 46);
		contentPane.add(buttonModifica);
		
		/*
		 * Pulsante di rimozione
		 */
		JButton buttonRimuovi = new JButton("Rimuovi");
		buttonRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
						DefaultTableModel model = (DefaultTableModel)farmaciTable.getModel();
						int indice = farmaciTable.getSelectedRow();
						
						System.out.println(indice);
						model.removeRow(indice);
						
						DeleteDataGui deleteDataGui = new DeleteDataGui();
						deleteDataGui.removeFarmaco(farmaci, indice);
					
				}catch(Exception e1) {
						String errore = e1.getMessage() + "Errore";
						System.out.println(errore);
					
				}
				
			}
			
		});
		buttonRimuovi.setBounds(836, 261, 112, 46);
		contentPane.add(buttonRimuovi);
		
		/*
		 * Pulsante per tornare alla schermata precedente
		 */
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //La finestra corrente viene resa non visibile
				GestionaleFarmacia.frameGestionaleFarmacia.setVisible(true);
				
			}
		});
		buttonIndietro.setBounds(836, 340, 112, 46);
		contentPane.add(buttonIndietro);
		
	}
	
	/**
	 * Metodo che permette la visualizzazione della tabella dei farmaci
	 */
	public void visualizzaFarmaciTable() {
		
		String colFarmaci[] = {"ID","Farmaco","Confezionamento","Scadenza","Tipo","Prezzo (€) "};
		DefaultTableModel tabellaFarmaciModel = new DefaultTableModel(colFarmaci,0);
		
		PrintDataGui printDataGui = new PrintDataGui();
		tabellaFarmaciModel = printDataGui.visualizzaDatiFarmaci(farmaci, tabellaFarmaciModel);
		
		if(farmaci.isEmpty()) {
			txtNonCiSono = new JTextField();
			txtNonCiSono.setText("Non ci sono farmaci memorizzati!");
			txtNonCiSono.setBounds(530,470,190,30);
			contentPane.add(txtNonCiSono);
			txtNonCiSono.setColumns(10);
			txtNonCiSono.setEditable(false);
			txtNonCiSono.setEnabled(false);
		}
		
		farmaciTable = new JTable(tabellaFarmaciModel) {
		 //aggiunta in automatico
			private static final long serialVersionUID = 1L; 
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
			
		};
		
		farmaciTable.setFillsViewportHeight(false);
		farmaciTable.setBounds(10,23,23,23);
		farmaciTable.getTableHeader().setReorderingAllowed(false);
		farmaciTable.getTableHeader().setResizingAllowed(false);
		farmaciTable.setEnabled(true);
		
		
	}
	
	
	/**
	 * Metodo che permette l'aggiunta di una nuova riga, e quindi di un nuovo dato, relativo all'impiegato
	 * @param dataRow rappresenta i dati della riga che sarà aggiunta alla tabella
	 */
	public void AddRowToFarmaciTable(Object[] dataRow) {
		
		String colFarmaci[] = {"ID","Farmaco","Confezionamento","Scadenza","Tipo","Prezzo(€)"};
		DefaultTableModel tabellaFarmaciModel = new DefaultTableModel(colFarmaci,0);
		PrintDataGui printDataGui = new PrintDataGui();
		tabellaFarmaciModel = printDataGui.visualizzaDatiFarmaci(farmaci, tabellaFarmaciModel);
		
		if(farmaci.isEmpty()) {
			txtNonCiSono = new JTextField();
			txtNonCiSono.setText("Non ci sono farmaci memorizzati!");
			txtNonCiSono.setBounds(300,387,190,30);
			contentPane.add(txtNonCiSono);
			txtNonCiSono.setColumns(10);
			txtNonCiSono.setEditable(false);
			txtNonCiSono.setEnabled(false);
		}
		
		farmaciTable = new JTable(tabellaFarmaciModel);
		farmaciTable.setBounds(10,23,23,23);
		farmaciTable.getTableHeader().setReorderingAllowed(false);
		farmaciTable.getTableHeader().setResizingAllowed(false);
		farmaciTable.setCellSelectionEnabled(true);
		
		tabellaFarmaciModel.addRow(dataRow);
		
	}
	
	
}