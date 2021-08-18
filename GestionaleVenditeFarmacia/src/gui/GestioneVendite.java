package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import eccezioniGui.ControlliGui;
import farmaco.Farmaco;
import impiegato.Impiegato;
import methodsGui.ImportDataGui;
import methodsGui.InputDataGui;
import methodsGui.PrintDataGui;
import methodsGui.ChangeDataGui;
import methodsGui.DeleteDataGui;
import vendita.Vendita;
import java.awt.Rectangle;

/**
 * 
 * @author BarbieriGiuseppe
 * Questa è la finestra che permette di gestire i dati relativi alle vendite
 */
public class GestioneVendite extends JFrame {
	final static int DIM_VENDITE = 20;
	/**
	 * Definizione della variabili da utilizzare
	 */
	
	static ChangeDataGui ChangeDataGui = new ChangeDataGui();
	static DeleteDataGui DeleteDataGui = new DeleteDataGui();
	static PrintDataGui PrintDataGui = new PrintDataGui();
	
	/*
	 * Instanziazione di un'istanza dell'arrayList di tipo Vendita
	 */
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE);
	
	private static final long serialVersionUID = 1L; //aggiunta in automatico
	private JPanel gestVenditePane; //pannello della finestra vendite
	private JTable venditeTable; //tabella vendite
	private JTextField txtNonCiSono;
	
	//variabili utilizzate per l'acquisizione dei valori inseriti dall'utente nella modifica di una riga
	Impiegato nuovoImpiegato;
	Farmaco nuovoNomeFarmaco;
	String nuovaDataVendita;
	String nuovaQuantita;
	Impiegato impiegatoSelezionato;
	Farmaco farmacoSelezionato;
	Vendita vendita;
	String inputDataVendita = "";
	String inputDataVenditaMese = "";
	String inputDataVenditaAnno = "";
	/**
	 * Variabili che definiscono la dimensione degli arrayList 
	 */
	final static int DIM_FARMACI = 10;
	final static int DIM_IMPIEGATO = 20;
	/*
	 * Definizione degli arrayList
	 */
	ArrayList<Impiegato> impiegati = new ArrayList <Impiegato>();
	ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>();
	
	//definizione delle combobox farmaco e impiegato per poterle utilizzare in altri metodi
	DefaultComboBoxModel<Farmaco> comboBoxFarmacoModel = new DefaultComboBoxModel<Farmaco>();
	JComboBox<Farmaco> comboBoxFarmaco = new JComboBox<Farmaco> (comboBoxFarmacoModel);
	DefaultComboBoxModel<Impiegato> comboBoxImpiegatoModel = new DefaultComboBoxModel<Impiegato>();
	JComboBox<Impiegato> comboBoxImpiegato = new JComboBox<Impiegato> (comboBoxImpiegatoModel);

	boolean flagInserimentoCorretto = true;
	boolean flagData = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneVendite frameVendite = new GestioneVendite();
					frameVendite.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestioneVendite() {
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
	 * Metodo che consente di creare il pannello principale, 
	 */
	public void createPane() {
		setTitle("Gestione Vendite");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Vendite.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 595);
		gestVenditePane = new JPanel();
		gestVenditePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(gestVenditePane);
		gestVenditePane.setLayout(null);
		//Creazione pannello che permette di scorrere i dati all'interno della tabella
		JScrollPane venditePane = new JScrollPane();
		venditePane.setBounds(10, 67, 615, 361);
		gestVenditePane.add(venditePane);
		venditePane.setColumnHeaderView(venditeTable);
		visualizzaVenditeTable();
		
		venditePane.setColumnHeaderView(venditeTable);
		venditePane.setViewportView(venditeTable);
	
	}
	
	/**
	 * Metodo che contiene delle label informative su alcune funzioni della finestra
	 */
	private void createJLabel() {
		JLabel lblInfo = new JLabel("*Per effettuare una rimozione o una modifica dei dati , selezionare la riga desiderata");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInfo.setBounds(new Rectangle(10, 439, 502, 31));
		gestVenditePane.add(lblInfo);
		
		JLabel lblAvviso = new JLabel("*Per aggiornare l'ID dopo la rimozione premere \"Indietro\" e tornare in questa finestra");
		lblAvviso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAvviso.setBounds(new Rectangle(10, 470, 692, 25));
		gestVenditePane.add(lblAvviso);
	}
	
	/**
	 * Metodo che crea la barra del menù per spostarsi nelle varie finestre o uscire
	 */
	@SuppressWarnings("deprecation")
	public void createMenuBar() {
		/*
		 * Creazione barra menù
		 */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 812, 37);
		gestVenditePane.add(menuBar);
		
		
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
		
		JMenuItem buttonGestioneImpiegati = new JMenuItem("Gestione Impiegati");
		buttonGestioneImpiegati.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
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
		buttonEsci.setIcon(new ImageIcon("ExitIcon.png"));
		buttonEsci.setToolTipText("Premere CTRL+E per uscire");
		buttonEsci.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		buttonEsci.addActionListener( (event) -> System.exit(0) );
		
		menuBar.add(buttonEsci);
		
		

	}
	/**
	 * Metodo contenente tutti i bottoni presenti nella finestra
	 */
	public void createButton() {
		/*
		 * Pulsante per l'inserimento di una vendita
		 */
		JButton buttonInserisci = new JButton("Inserisci");
		buttonInserisci.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				//definizione delle variabili
				String dataOdierna = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //format della data
				sdf.setLenient(false);
				Date data = new Date();
				dataOdierna = sdf.format(data);
				
				//Conversione da arraylist ad array
				ArrayList<Impiegato> impiegati = new ArrayList <Impiegato>();
				ImportDataGui.importaImpiegatiGui(impiegati); //import degli impiegati
				Impiegato[] imp = new Impiegato[impiegati.size()]; //conversione 
				imp = impiegati.toArray(imp);
				
				ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>();
				ImportDataGui.importaFarmaciGui(farmaci); //import dei farmaci
				Farmaco[] farmaciArray = new Farmaco[farmaci.size()];
				farmaciArray = farmaci.toArray(farmaciArray);
				
				int quantitaInt = 0;//Quantità precedenti 

				
				
				//input del nuovo impiegato e del nuovo farmaco. viene mostrata una finestra di dialogo dove l'utente può selezionare soltanto quelli esistenti
				nuovoImpiegato = (Impiegato) JOptionPane.showInputDialog(null,"Inserisci Impiegato:","Inserisci Impiegato:",JOptionPane.QUESTION_MESSAGE,null,imp,imp);
				
				nuovoNomeFarmaco = (Farmaco) JOptionPane.showInputDialog(null,"Inserisci Farmaco:","Inserisci Farmaco:",JOptionPane.QUESTION_MESSAGE,null,farmaciArray,farmaciArray);
				
				/*
				 * Inserimento nuova data vendita
				 */
				do {
					
					nuovaDataVendita = JOptionPane.showInputDialog(null,"Inserisci la Data[dd/MM/yyyy]:");
					if(ControlliGui.checkConfrontaData(nuovaDataVendita, dataOdierna)== false) { //confronta la data inserita con la data odierna
						JOptionPane.showMessageDialog(null, "La data di vendita inserita deve essere antecedente o uguale alla data odierna!");
					}
					
					if(ControlliGui.checkConfrontaData(nuovaDataVendita, nuovoNomeFarmaco.getDataScadenza()) == false) { //confronta la data di vendita con la data di scadenza
						JOptionPane.showMessageDialog(null, "La data di vendita inserita deve essere antecedente alla data di scadenza del farmaco!");
					}
					
					if(ControlliGui.checkConfrontaData(nuovoNomeFarmaco.getDataConfezionamento(), nuovaDataVendita) == false) { //confronta la data di confezionamento con la data di vendita
						JOptionPane.showMessageDialog(null, "La data di vendita deve essere successiva alla data di confezionamento del farmaco!");
					}
					
					//finchè la data inserita non è vuota oppure uno dei controlli non risulta true
				}while(nuovaDataVendita.isEmpty() || ControlliGui.checkData(nuovaDataVendita) == false || ControlliGui.checkConfrontaData(nuovaDataVendita, dataOdierna) == false 
						|| ControlliGui.checkConfrontaData(nuovaDataVendita, nuovoNomeFarmaco.getDataScadenza())== false
						|| ControlliGui.checkConfrontaData(nuovoNomeFarmaco.getDataConfezionamento(), nuovaDataVendita)== false);
				
				//controlli limitazioni sulle quantità
				//flag limiti, essi sono di tipo boolean in quanto i controlli ritornano un valore booleano 
				boolean limiteAnnuale;
				boolean limiteGiornaliero;
				
				/*
				 * Inserimento della quantità della nuova vendta 
				 */
				do {
					
					nuovaQuantita = JOptionPane.showInputDialog(null,"Inserisci la Quantita");
					if(nuovaQuantita.matches("[a-zA-Z]+")) {
						JOptionPane.showMessageDialog(null, "La quantità inserita non è corretta! Inserimento annullato");
					}
					
					int quantitaNuovaInt = Integer.parseInt(nuovaQuantita); //conversione da string a intero
					limiteAnnuale = ControlliGui.limiteVenditeAnnualiInserisci(vendite, impiegati, impiegati.indexOf(nuovoImpiegato), nuovaDataVendita, quantitaNuovaInt,quantitaInt);
					limiteGiornaliero = ControlliGui.limiteVenditeGiornaliereInserisci(vendite, impiegati, impiegati.indexOf(nuovoImpiegato), nuovaDataVendita, quantitaNuovaInt,quantitaInt);
					
					BigDecimal nuovoPrezzoTotale = new BigDecimal("0.00"); //creo un nuovo prezzo totale che servirà per calcolare il nuovo prezzo totale basato sulla nuova quantità inserita dall'utente
					nuovoPrezzoTotale = nuovoNomeFarmaco.getPrezzo().multiply(new BigDecimal(quantitaNuovaInt));
					
					InputDataGui inputDataGui = new InputDataGui();
					GestioneVendite frameVendite = new GestioneVendite();
					
					if(limiteAnnuale == true && limiteGiornaliero == true) { //se entrambi i controlli sono corretti aggiorna i valori con i nuovi
						inputDataGui.addVendita(nuovoImpiegato, nuovoNomeFarmaco, nuovaDataVendita, quantitaNuovaInt, nuovoPrezzoTotale);
						frameVendite.addRowToVenditeTable(new Object[] { //crea un array che contiene temporaneamente i valori da assegnare alle celle
								nuovoImpiegato,
								nuovoNomeFarmaco,
								nuovaDataVendita,
								quantitaNuovaInt,
								nuovoPrezzoTotale
							});
						
						JOptionPane.showMessageDialog(null, "Vendita Aggiunta!");
						
					}else {
							JOptionPane.showMessageDialog(null, "Vendita Non Aggiunta!");
					}
					
				}while(nuovaQuantita.isEmpty() || !nuovaQuantita.matches("[0-9/]+") ); //finchè la quantita inserita risulta vuota oppure non corrisponde ad un intero
				
				
				
		}catch(Exception e) {
			String errore = e.getMessage() + "Errore";
			System.out.println(errore);
		}
			}	
	});
		
		
		buttonInserisci.setBounds(677, 94, 112, 46);
		gestVenditePane.add(buttonInserisci);
		
		/*
		 * Pulsante per la modifica di una vendita 
		 */
		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				
				try {
						//definizione delle variabili
						String dataOdierna = "";
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //format della data
						sdf.setLenient(false);
						Date data = new Date();
						dataOdierna = sdf.format(data);
						
						//Conversione da arraylist ad array
						ArrayList<Impiegato> impiegati = new ArrayList <Impiegato>();
						ImportDataGui.importaImpiegatiGui(impiegati); //import degli impiegati
						Impiegato[] imp = new Impiegato[impiegati.size()]; //conversione 
						imp = impiegati.toArray(imp);
						
						ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>();
						ImportDataGui.importaFarmaciGui(farmaci); //import dei farmaci
						Farmaco[] farmaciArray = new Farmaco[farmaci.size()];
						farmaciArray = farmaci.toArray(farmaciArray);
						
						//ottenimento della riga selezionata dall'utente
						DefaultTableModel model = (DefaultTableModel)venditeTable.getModel();
						int indice = venditeTable.getSelectedRow();
						
						/*ottenimento dei singoli valori e relativa divisione 
						 * viene definita una variabile di tipo String il quale contiene il valore del dato posto nella riga selezionata dall'utente e della relativa colonna
						 * ad esempio cognome è il primo valore della riga selezionata x
						*/
						String dataVendita = model.getValueAt(indice, 4).toString();
						String quantita = model.getValueAt(indice, 5).toString();
						int quantitaInt = Integer.parseInt(quantita); //conversione da string a intero della vecchia quantita

						
						
						//input del nuovo impiegato e del nuovo farmaco. viene mostrata una finestra di dialogo dove l'utente può selezionare soltanto quelli esistenti
						nuovoImpiegato = (Impiegato) JOptionPane.showInputDialog(null,"Inserisci Impiegato:","Inserisci Impiegato:",JOptionPane.QUESTION_MESSAGE,null,imp,imp);
						
						nuovoNomeFarmaco = (Farmaco) JOptionPane.showInputDialog(null,"Inserisci Farmaco:","Inserisci Farmaco:",JOptionPane.QUESTION_MESSAGE,null,farmaciArray,farmaciArray);
						
						/*
						 * Inserimento di una nuova data vendita
						 */
						do {
							
							nuovaDataVendita = JOptionPane.showInputDialog(null,"Inserisci la Data[dd/MM/yyyy]:",dataVendita);
							if(ControlliGui.checkConfrontaData(nuovaDataVendita, dataOdierna)== false) { //confronta la data inserita con la data odierna
								JOptionPane.showMessageDialog(null, "La data di vendita inserita deve essere antecedente o uguale alla data odierna!");
							}
							
							if(ControlliGui.checkConfrontaData(nuovaDataVendita, nuovoNomeFarmaco.getDataScadenza()) == false) { //confronta la data di vendita con la data di scadenza
								JOptionPane.showMessageDialog(null, "La data di vendita inserita deve essere antecedente alla data di scadenza del farmaco!");
							}
							
							if(ControlliGui.checkConfrontaData(nuovoNomeFarmaco.getDataConfezionamento(), nuovaDataVendita) == false) { //confronta la data di confezionamento con la data di vendita
								JOptionPane.showMessageDialog(null, "La data di vendita deve essere successiva alla data di confezionamento del farmaco!");
							}
							
							//finchè la data inserita non è vuota oppure uno dei controlli non risulta true
						}while(nuovaDataVendita.isEmpty() || ControlliGui.checkData(nuovaDataVendita) == false || ControlliGui.checkConfrontaData(nuovaDataVendita, dataOdierna) == false 
								|| ControlliGui.checkConfrontaData(nuovaDataVendita, nuovoNomeFarmaco.getDataScadenza())== false
								|| ControlliGui.checkConfrontaData(nuovoNomeFarmaco.getDataConfezionamento(), nuovaDataVendita)== false);
						
						//controlli limitazioni sulle quantità
						//flag limiti, essi sono di tipo boolean in quanto i controlli ritornano un valore booleano 
						boolean limiteAnnuale;
						boolean limiteGiornaliero;
						
						/*
						 * Inserimento della nuova quantita
						 */
						do {
							
							nuovaQuantita = JOptionPane.showInputDialog(null,"Inserisci la Quantita",quantita);
							int quantitaNuovaInt = Integer.parseInt(nuovaQuantita); //conversione da string a intero
							limiteAnnuale = ControlliGui.limiteVenditeAnnualiModifica(vendite, impiegati, impiegati.indexOf(nuovoImpiegato), nuovaDataVendita, quantitaNuovaInt,quantitaInt);
							limiteGiornaliero = ControlliGui.limiteVenditeGiornaliereModifica(vendite, impiegati, impiegati.indexOf(nuovoImpiegato), nuovaDataVendita, quantitaNuovaInt,quantitaInt);
							
							BigDecimal nuovoPrezzoTotale = new BigDecimal("0.00"); //creo un nuovo prezzo totale che servirà per calcolare il nuovo prezzo totale basato sulla nuova quantità inserita dall'utente
							nuovoPrezzoTotale = nuovoNomeFarmaco.getPrezzo().multiply(new BigDecimal(quantitaNuovaInt));
							
							if(limiteAnnuale == true && limiteGiornaliero == true) { //se entrambi i controlli sono corretti aggiorna i valori con i nuovi
								
								model.setValueAt(nuovoImpiegato.getCognomeImpiegato(), indice, 1);
								model.setValueAt(nuovoImpiegato.getNomeImpiegato(), indice, 2);
								model.setValueAt(nuovoNomeFarmaco.getNomeFarmaco(), indice, 3);
								model.setValueAt(nuovaDataVendita, indice, 4);
								model.setValueAt(nuovaQuantita, indice, 5);
								model.setValueAt(nuovoPrezzoTotale, indice, 6);
								
								ChangeDataGui.modificaVenditaGui(vendite, indice, nuovoImpiegato, nuovoNomeFarmaco, nuovaDataVendita, quantitaNuovaInt, nuovoPrezzoTotale);
								JOptionPane.showMessageDialog(null, "Modifica Effettuata!");
								
							}else {
									JOptionPane.showMessageDialog(null, "Modifica Non Effettuata!");
							}
							
						}while(nuovaQuantita.isEmpty() || !nuovaQuantita.matches("[0-9/]+") ); //finchè la quantita inserita risulta vuota oppure non corrisponde ad un intero
						
						
						
				}catch(Exception e) {
					String errore = e.getMessage() + "Errore";
					System.out.println(errore);
				}
				
			}
		});
		buttonModifica.setBounds(677, 166, 112, 46);
		gestVenditePane.add(buttonModifica);
		
		/*
		 * Pulsante per effettuare una rimozione di una vendita
		 */
		JButton buttonRimuovi = new JButton("Rimuovi");
		buttonRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				try {
					//acquisizione della riga selezionata e rimozione di quest'ultima
					DefaultTableModel model = (DefaultTableModel) venditeTable.getModel();
					int indice = venditeTable.getSelectedRow();
					
					model.removeRow(indice);
					
					//esecuzione del metodo removeVendita per rimuovere la riga appena eliminato e rendere persistente la rimozione
					DeleteDataGui deleteDataGui = new DeleteDataGui();
					deleteDataGui.removeVendita(vendite, indice);
					
				}catch(Exception e) {
					String errore = e.getMessage() + "Errore ";
					System.out.println(errore);
				}
				
			}
		});

		buttonRimuovi.setBounds(677, 237, 112, 46);
		gestVenditePane.add(buttonRimuovi);
		/*
		 * Pulsante che permette di eliminare le vendite tramite un filtro(Impiegato,Farmaco,Data)
		 */
		JButton buttonRimuoviPer = new JButton("Rimuovi Per:");
		buttonRimuoviPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				try {
					
					int i = 0;
					String[] bottoni = {"Impiegato","Farmaco","Data"};
					int scelta = 0;
					scelta = JOptionPane.showOptionDialog(null, "Per cosa vuoi rimuovere?", "Input",
							JOptionPane.DEFAULT_OPTION, scelta, null, bottoni, bottoni[i]); //la scelta ritorna 0 se si clicca impiegato e rispettivamente 1 e 2 per farmaco e data
					
					if(scelta == 0) { //viene selezionato Impiegato
						ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>();
						ImportDataGui.importaImpiegatiGui(impiegati); //import degli impiegati
						Impiegato[] imp = new Impiegato[impiegati.size()]; //conversione da arraylist ad array
						imp = impiegati.toArray(imp);
						
						impiegatoSelezionato = (Impiegato) JOptionPane.showInputDialog(null,"Scegli l'impiegato:","Scegli l'impiegato:",
								JOptionPane.QUESTION_MESSAGE,null,imp,imp);
						DeleteDataGui.removeVenditeByImpiegato(vendite, impiegatoSelezionato);
					}
					
					if(scelta == 1) { //viene selezionato Farmaco
						ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>();
						ImportDataGui.importaFarmaciGui(farmaci);
						Farmaco[] farmaciArray = new Farmaco[farmaci.size()];
						farmaciArray = farmaci.toArray(farmaciArray);
						
						farmacoSelezionato = (Farmaco) JOptionPane.showInputDialog(null, "Scegli il farmaco:", "Scegli il farmaco",
								JOptionPane.QUESTION_MESSAGE, null , farmaciArray, farmaciArray);
						DeleteDataGui.removeVenditeByFarmaco(vendite, farmacoSelezionato);
						
					}
					
					if(scelta == 2) {//viene scelta la data- in questo caso ci sarà un'ulteriore divisione per giorno, mese e anno 
						int j = 0; //secondo indice
						int sceltaData = 0;
						DefaultTableModel model = (DefaultTableModel) venditeTable.getModel();
						String[] bottoniData = {"Giorno","Mese","Anno"};
						
						sceltaData = JOptionPane.showOptionDialog(null, "Per cosa vuoi rimuovere?", "Input",
								
								JOptionPane.DEFAULT_OPTION,sceltaData,null,bottoniData,bottoniData[j]); //la scelta ritorna 0 se si sceglie il giorno e risp. 1 e 2 per mese e anno 
						
						//la richiesta di inserimento sarà ripetuta ogni qualvolta il controllo risulterà == false
							if(sceltaData == 0) {
								do {
									inputDataVendita = JOptionPane.showInputDialog(null, "Inserisci la data [gg/MM/yyyy]:", inputDataVendita);
								}while(ControlliGui.checkData(inputDataVendita) == false);
								
								DeleteDataGui.removeVenditeByGiorno(vendite, inputDataVendita);
								PrintDataGui.visualizzaDatiVendite(vendite,model);
							
							}
							
							if(sceltaData == 1) {//viene scelto il mese
								do {
									inputDataVendita = JOptionPane.showInputDialog(null, "Inserisci la data [MM/yyyy]:", inputDataVenditaMese);
								}while(ControlliGui.checkDataMese(inputDataVenditaMese) == false);
								
								DeleteDataGui.removeVenditeByGiorno(vendite, inputDataVendita);
								PrintDataGui.visualizzaDatiVendite(vendite, model);
								
								
								}
							
							if(sceltaData == 2) { //viene scelto l'anno
								do {
									inputDataVenditaAnno = JOptionPane.showInputDialog(null,"Inserisci la data [yyyy]:",inputDataVenditaAnno);
									
								}while(ControlliGui.checkDataAnno(inputDataVenditaAnno) == false); 
								
								DeleteDataGui.removeVenditeByAnno(vendite, inputDataVenditaAnno);
							}
							
							
					}
					
				}catch(Exception e) {
					String errore = e.getMessage() + "Errore";
					System.out.println(errore);
				}
				
			}
		});
		buttonRimuoviPer.setBounds(677, 309, 112, 46);
		gestVenditePane.add(buttonRimuoviPer);
		
		/*
		 * Pulsante per tornare alla schermata precedente 
		 */
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GestionaleFarmacia frameGestionale = new GestionaleFarmacia();
				setVisible(false);
				frameGestionale.setVisible(true);
			}
		});
		buttonIndietro.setBounds(677, 382, 112, 46);
		gestVenditePane.add(buttonIndietro);
		
		JLabel lblNewLabel_1 = new JLabel("*Per aggiornare i dati dopo un inserimento premere \"Indietro\" e tornare in questa finestra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 492, 615, 37);
		gestVenditePane.add(lblNewLabel_1);
	}
	
	/**
	 * Metodo che consente la visualizzazione dei dati all'interno della tabella 
	 */
	public void visualizzaVenditeTable() {
		String colVendite[] = {"Codice","Cognome","Nome","Farmaco","Data","Quantita","Totale(€)"};
		DefaultTableModel tabellaVenditeModel = new DefaultTableModel(colVendite,0);
		PrintDataGui printDataGui = new PrintDataGui();
		
		tabellaVenditeModel = printDataGui.visualizzaDatiVendite(vendite, tabellaVenditeModel);
		
			if(vendite.isEmpty()) {
				txtNonCiSono = new JTextField();
				txtNonCiSono.setText("Non ci sono vendite memorizzate!");
				txtNonCiSono.setBounds(610, 470, 200, 50);
				gestVenditePane.add(txtNonCiSono);
				txtNonCiSono.setColumns(10);
				txtNonCiSono.setEditable(false);
				txtNonCiSono.setEnabled(false);
		}
		
		venditeTable = new JTable(tabellaVenditeModel) {
			private static final long serialVersionUID = 1L; //aggiunta in automatico
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
			
		};
			venditeTable.setFillsViewportHeight(false);
			venditeTable.setBounds(10,20,20,20);
			venditeTable.getTableHeader().setReorderingAllowed(false);
			venditeTable.getTableHeader().setResizingAllowed(false);
			venditeTable.setEnabled(true);
		
	}
	
	/**
	 * Metodo che consente l'aggiornamento e l'inserimento di una nuova riga di una vendita
	 * @param dataRow rappresenta i dati della riga che sarà aggiunta alla tabella
	 */
	public void addRowToVenditeTable(Object[] dataRow) {
		String colVendite[] = {"Codice","Cognome","Nome","Farmaco","Data","Quantita","Totale(€)"};
		DefaultTableModel tabellaVenditeModel = new DefaultTableModel(colVendite,0);
		PrintDataGui printDataGui = new PrintDataGui();
		
		tabellaVenditeModel = printDataGui.visualizzaDatiVendite(vendite, tabellaVenditeModel);
		
			if(vendite.isEmpty()) {
				txtNonCiSono = new JTextField();
				txtNonCiSono.setText("Non ci sono vendite memorizzate!");
				txtNonCiSono.setBounds(480, 490, 250, 50);
				gestVenditePane.add(txtNonCiSono);
				txtNonCiSono.setColumns(10);
				txtNonCiSono.setEditable(false);
				txtNonCiSono.setEnabled(false);
		}
		
		venditeTable = new JTable(tabellaVenditeModel) {
			private static final long serialVersionUID = 1L; //aggiunta in automatico
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};		
			venditeTable.setFillsViewportHeight(false);
			venditeTable.setBounds(10,20,20,20);
			venditeTable.getTableHeader().setReorderingAllowed(false);
			venditeTable.getTableHeader().setResizingAllowed(false);
			venditeTable.setEnabled(true);
			
			tabellaVenditeModel.addRow(dataRow);
	}
}