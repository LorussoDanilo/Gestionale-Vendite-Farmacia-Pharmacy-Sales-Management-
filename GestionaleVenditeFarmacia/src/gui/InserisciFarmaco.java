package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import eccezioniGui.ControlliGui;
import interfacciaUtente.InterfacciaUtente;
import methodsGui.ImportDataGui;
import methodsGui.InputDataGui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Questo JFrame permette di inserire un nuovo farmaco all'interno della tabella del JFrame GestioneFarmaci
 * @author CardinaleChristian,Barbieri Giuseppe
 *
 */
public class InserisciFarmaco extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static int DIM_FARMACI = 10;
	
	static InterfacciaUtente Eseguibile = new InterfacciaUtente();
	
	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String> ();
	JComboBox<String> comboBox = new JComboBox<String> (comboBoxModel);
	
	private JPanel contentPane;
	private JTextField textNomeFarmaco;
	private JTextField textConfezionamento;
	private JTextField textScadenza;
	private JTextField textPrezzo;
	
	/*
	 * label che avvisano l'utente di aver inserito un valore sbagliato nel rispettivo campo
	 */
	JLabel nomeFarmacoErratolbl = new JLabel("Nome Farmaco Errato!");
	JLabel dataConfezionamentoErratalbl = new JLabel("Data Confezionamento Errata!");
	JLabel dataScadenzaErratalbl = new JLabel("Data Scandenza Errata!");
	JLabel prezzoErratolbl = new JLabel("Prezzo Farmaco Errato!");
	
	/*
	 * Flag per l'inserimento corretto dei dati 
	 */
	boolean flagInserimentoCorretto = true;
	boolean flagNomeFarmacoCorretto = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserisciFarmaco frame = new InserisciFarmaco();
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
	public InserisciFarmaco() {
		InitUI();
	}
	/**
	 * Con questo metodo setto l'interfaccia con tutte le sue componenti
	 */
	private void InitUI() {
		createPane();
		createButton();
		createJLabel();
		createTextField();
	}
	
	/**
	 * Con questo metodo setto il pannello principale della finestra
	 */
	private void createPane() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("pillola.png"));
		setTitle("Inserisci Farmaco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}
	
	/**
	 * Con questo metodo creo ed imposto le funzionalità dei vari bottoni presenti nella finestra
	 * Per i controlli sono stati utilizzati delle espressioni regolari generate da un software terzo.
	 */
	@SuppressWarnings("unchecked")
	private void createButton() {
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox(); //creazione della comboBox contenente i tipi farmaci
		ArrayList<String> tipo_farmaci = new ArrayList <String>();
		ImportDataGui.importaTipoFarmaciGui(tipo_farmaci); //import dei farmaci
		String[] tipi = new String[tipo_farmaci.size()]; //conversione da arralist ad array
		tipi = tipo_farmaci.toArray(tipi);
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(tipi); //creazione del modello
		comboBox.setModel(model);
		comboBox.setBounds(216,262,147,31);
		contentPane.add(comboBox); //aggiunta della combobox alla gui
		
		getContentPane().setLayout(null);	
		
		/*
		 * Pulsante per inserire un nuovo farmaco
		 */
		JButton buttonInserisci = new JButton("Inserisci");
		buttonInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String nomeFarmaco = textNomeFarmaco.getText();
				String dataConfezionamento = textConfezionamento.getText();
				String dataScadenza = textScadenza.getText();
				String tipo = (String)comboBox.getSelectedItem();
				String prezzo = textPrezzo.getText();
				
				String dataOdierna = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				sdf.setLenient(false);
				Date data = new Date();
				dataOdierna = sdf.format(data);
				
				//controllo sull'inserimento corretto del nome farmaco , data confezionamento , data scadenza , tipo e prezzo
				if(nomeFarmaco.equals("") || dataConfezionamento.equals("") || dataScadenza.equals("") || prezzo.equals("")) {
					
					JOptionPane.showMessageDialog(contentPane,  "ATTENZIONE! \nE' presente almento un campo vuoto");
					flagInserimentoCorretto = false;
					
					}else {
							flagInserimentoCorretto = true;
						}
				
				//Controllo sull'inserimento corretto del nome farmaco
				if(!nomeFarmaco.matches("[a-zA-Z]+") || nomeFarmaco.isEmpty( ) ) {//se il farmaco non è composto da lettere(minuscole o maiuscole)
					//oppure è vuoto (si suppone che i nomi dei farmaci contengano soltanto lettere e non numeri)
					flagNomeFarmacoCorretto = false;
					nomeFarmacoErratolbl.setForeground(java.awt.Color.RED);
					nomeFarmacoErratolbl.setVisible(true);
					nomeFarmacoErratolbl.setFont(new Font("Tahoma", Font.BOLD, 12));
					nomeFarmacoErratolbl.setBounds(410,32,168,30);
					nomeFarmacoErratolbl.getColorModel();
					contentPane.add(nomeFarmacoErratolbl);
					
					JOptionPane.showMessageDialog(null, "Il nome del farmaco contiene dei numeri!");
					
					}else { //se il nome del farmaco risulta senza numeri
							flagNomeFarmacoCorretto = true;
							nomeFarmacoErratolbl.setVisible(false);
						}
				
				
				
				//Controllo sull'inserimento corretto della data di confezionamento
				if(!dataConfezionamento.matches("^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$") || dataConfezionamento.isEmpty( ) ) {
					
					flagInserimentoCorretto = false;
					dataConfezionamentoErratalbl.setForeground(java.awt.Color.RED);
					dataConfezionamentoErratalbl.setFont(new Font("Tahoma", Font.BOLD, 12));
					dataConfezionamentoErratalbl.setBounds(410, 77, 197, 71);
					contentPane.add(dataConfezionamentoErratalbl);
					
					
					}else {
							flagInserimentoCorretto = true;
							dataConfezionamentoErratalbl.setVisible(false);
						}
				
				
				//Controllo sull'inserimento corretto della data di scadenza
				if(!dataScadenza.matches("^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$") || dataScadenza.isEmpty( ) ) {
					
					flagInserimentoCorretto = false;
					dataScadenzaErratalbl.setForeground(java.awt.Color.RED);
					dataScadenzaErratalbl.setFont(new Font("Tahoma", Font.BOLD, 12));
					dataScadenzaErratalbl.setBounds(410, 162, 168, 71);
					contentPane.add(dataScadenzaErratalbl);;
					
					
					}else {
							flagInserimentoCorretto = true;
							dataScadenzaErratalbl.setVisible(false);
						}
				
				
				//Controllo sull'inserimento corretto del prezzo del farmaco
				if(!prezzo.matches("[0-9.]+") || prezzo.isEmpty( ) ) { //se il prezzo non contiene solo numeri oppure è vuoto
					
					flagInserimentoCorretto = false;
					prezzoErratolbl.setFont(new Font("Tahoma", Font.BOLD, 12));
					prezzoErratolbl.setForeground(java.awt.Color.RED);
					prezzoErratolbl.setSize(160, 41);
					prezzoErratolbl.setLocation(410, 326);
					contentPane.add(prezzoErratolbl);
					
					
					}else {
							flagInserimentoCorretto = true;
							prezzoErratolbl.setVisible(false);
					}
				
				/*
				 * Condizioni che se verificate permettono di inserire un nuovo farmaco
				 */
				if(flagInserimentoCorretto == true && flagNomeFarmacoCorretto == true && ControlliGui.checkData(dataConfezionamento) == true && ControlliGui.checkData(dataScadenza) == true ) { //se non ci sono errori
					BigDecimal prezzoDecimal = new BigDecimal(prezzo);
					GestioneFarmaci frameFarmaci = new GestioneFarmaci();
					try {
						if(ControlliGui.checkConfrontaData(dataConfezionamento, dataScadenza) == true && ControlliGui.checkConfrontaData( dataConfezionamento ,dataOdierna) == true && ControlliGui.checkConfrontaData(dataOdierna,dataScadenza) == true) {
							//Aggiungo le righe dei campi di testo inseriti dall'utente nella tabella
							frameFarmaci.AddRowToFarmaciTable(new Object[] {
										nomeFarmaco,
										dataConfezionamento,
										dataScadenza,
										tipo,
										prezzoDecimal,
							});
							
							InputDataGui inputDataGui = new InputDataGui();
							inputDataGui.addFarmaco(nomeFarmaco, dataConfezionamento, dataScadenza, tipo, prezzoDecimal);
							
							dataConfezionamentoErratalbl.setVisible(false);
							dataScadenzaErratalbl.setVisible(false);
							JOptionPane.showMessageDialog(contentPane, "Farmaco Aggiunto!");
							
						//else if per stampare messaggi personalizzati	
						}else if(ControlliGui.checkConfrontaData(dataConfezionamento, dataScadenza) == false){
								JOptionPane.showMessageDialog(null, "La data di confezionamento deve essere antecedente alla data di scadenza!");
								dataConfezionamentoErratalbl.setVisible(false);
								dataScadenzaErratalbl.setVisible(false);
						}else if(ControlliGui.checkConfrontaData(dataConfezionamento, dataOdierna) == false){
							JOptionPane.showMessageDialog(null, "La data di confezionamento deve essere antecedente alla data odierna!");
							dataConfezionamentoErratalbl.setVisible(false);
							dataScadenzaErratalbl.setVisible(false);
						}else if(ControlliGui.checkConfrontaData(dataOdierna,dataScadenza) == false){
							JOptionPane.showMessageDialog(null, "La data di scadenza deve essere successiva alla data odierna!");
							dataConfezionamentoErratalbl.setVisible(false);
							dataScadenzaErratalbl.setVisible(false);
						}
						
					
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (java.text.ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
					
				
			}
				
			});
				buttonInserisci.setBounds(28, 405, 123, 49);
				getContentPane().add(buttonInserisci);
				
				/*
				 * Pulsante per pulire le caselle
				 */
				JButton buttonPulisciCaselle = new JButton("Pulisci Caselle");
				buttonPulisciCaselle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					textNomeFarmaco.setText("");                                   
					textConfezionamento.setText("");								 
					textScadenza.setText("");										
					textPrezzo.setText("");										
				}
			});
				buttonPulisciCaselle.setBounds(190, 405, 134, 49);
				contentPane.add(buttonPulisciCaselle);
		
				/*
				 * Pulsante per tornare alla schermata precedente
				 */
				JButton buttonChiudi = new JButton("Chiudi");
				buttonChiudi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GestioneFarmaci frameFarmaci = new GestioneFarmaci();
					setVisible(false);
					frameFarmaci.setVisible(true);
				}
			});
		
				buttonChiudi.setBounds(360, 406, 134, 47);
				contentPane.add(buttonChiudi);
			}
		
			
	/**
	 * Con questo metodo setto le JLabel presenti all'interno della finestra
	 */
	private void createJLabel() {
		JLabel lblNomeFarmaco = new JLabel("Nome Farmaco");
		lblNomeFarmaco.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeFarmaco.setBounds(12, 13, 139, 56);
		contentPane.add(lblNomeFarmaco);
		
		JLabel lblConfezionamento = new JLabel("Data Confezionamento");
		lblConfezionamento.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfezionamento.setBounds(12, 82, 194, 56);
		contentPane.add(lblConfezionamento);
		
		JLabel lblScadenza = new JLabel("Data Scadenza");
		lblScadenza.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblScadenza.setBounds(12, 172, 161, 56);
		contentPane.add(lblScadenza);
		
		JLabel lblTipoFarmaco = new JLabel("Tipo Farmaco");
		lblTipoFarmaco.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoFarmaco.setBounds(12, 247, 112, 56);
		contentPane.add(lblTipoFarmaco);
		
		JLabel lblPrezzo = new JLabel("Prezzo\r");
		lblPrezzo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrezzo.setBounds(12, 317, 112, 56);
		contentPane.add(lblPrezzo);
		
		JLabel lblFormatData = new JLabel("[DD/MM/AAAA]");
		lblFormatData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFormatData.setBounds(14, 113, 137, 34);
		contentPane.add(lblFormatData);
		
		JLabel lblFormatData2 = new JLabel("DD/MM/AAAA");
		lblFormatData2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFormatData2.setBounds(12, 200, 110, 34);
		contentPane.add(lblFormatData2);
	}
	
	/**
	 * Con questo metodo setto i campi di testo presenti all'interno della finestra
	 */
	private void createTextField() {
		textNomeFarmaco = new JTextField();
		textNomeFarmaco.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textNomeFarmaco.setBounds(216, 15, 182, 56);
		getContentPane().add(textNomeFarmaco);
		textNomeFarmaco.setColumns(10);
		
	    textConfezionamento = new JTextField();
	    textConfezionamento.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textConfezionamento.setBounds(216, 84, 182, 56);
		getContentPane().add(textConfezionamento);
		textConfezionamento.setColumns(10);
		
		textScadenza = new JTextField();
		textScadenza.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textScadenza.setBounds(216, 174, 182, 56);
		getContentPane().add(textScadenza);
		textScadenza.setColumns(10);
		
		textPrezzo = new JTextField();
		textPrezzo.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textPrezzo.setBounds(216, 319, 182, 56);
		getContentPane().add(textPrezzo);
		textPrezzo.setColumns(10);
		
		JLabel lblIntervalloPrezzo = new JLabel("[0.00]");
		lblIntervalloPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIntervalloPrezzo.setBounds(12, 358, 83, 17);
		contentPane.add(lblIntervalloPrezzo);
	}
}

