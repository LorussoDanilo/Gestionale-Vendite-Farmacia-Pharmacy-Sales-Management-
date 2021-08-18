package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import methodsGui.DeleteDataGui;
import methodsGui.InputDataGui;
import methodsGui.PrintDataGui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
/**
 * Questa è la finestra che permette di gestire i dati relativi ai tipi farmaci
 * @author CardinaleChristian,BarbieriGiuseppe,LorussoDanilo
 *
 */
public class GestioneTipoFarmaci extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tipoFarmaciTable;
	private JPanel contentPane;
	private JTextField txtNonCiSono;
	
	final static int DIM_TIPO_FARMACI = 10;
	
	/*
	 * Instanziazione di un'istanza dell'arrayList di tipo tipo_farmaco
	 */
	private ArrayList<String> tipo_farmaci = new ArrayList<String>(DIM_TIPO_FARMACI);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneTipoFarmaci frame = new GestioneTipoFarmaci();
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
	public GestioneTipoFarmaci() {
		initUI();
	}
	
	
	/**
	 * Questo metodo permette di inizializzare l'interfaccia caricando ciascun elemento della gui in ordine
	 * cambiando l'ordine dei create la finestra potrebbe non visualizzarsi più
	 */
	private void initUI() {	
		createPane();
		createButton();
		createJLabel();
		
	}
	/**
	 * Metodo che crea la finestra tipo farmaco
	 */
	public void createPane() {
		setTitle("Gestione Tipo Farmaci");
		setIconImage(Toolkit.getDefaultToolkit().getImage("tipo_farmaci.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 28, 229, 285);
		contentPane.add(scrollPane);
		
		visualizzaTabellaTipoFarmaci();
		scrollPane.setColumnHeaderView(tipoFarmaciTable);
		scrollPane.setViewportView(tipoFarmaciTable);

	}
	
	/**
	 * Metodo contenente tutti i bottoni presenti nella finestra
	 */
	public void createButton() {
		/*
		 * Pulsante di inserimento nuovo tipo farmaco
		 */
		JButton buttonInserisci = new JButton("Inserisci");
		buttonInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
						String tipoFarmaco;
						
						do {
							tipoFarmaco =JOptionPane.showInputDialog(null,"Inserisci il tipo del farmaco:", "");
							
						}while(tipoFarmaco.isEmpty() || !tipoFarmaco.matches("[a-zA-Z]+"));
						
						AddRowToTipoFarmaciTable(new Object[] {
							tipoFarmaco	
						});
						
						InputDataGui inputDataGui = new InputDataGui();
						inputDataGui.addTipoFarmaco(tipoFarmaco);
						JOptionPane.showMessageDialog(contentPane, "Tipo Aggiunto!");
						
				}catch(Exception e1) {
					e1.getMessage();
					
				}
				
			}
			
		});
		buttonInserisci.setBounds(298, 52, 134, 52);
		contentPane.add(buttonInserisci);
		
		/*
		 * Pulsante di rimozione tipo farmaco
		 */
		JButton buttonRimuovi = new JButton("Rimuovi");
		buttonRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						DefaultTableModel model = (DefaultTableModel)tipoFarmaciTable.getModel();
						int indice = tipoFarmaciTable.getSelectedRow();
						
						String indiceInserito = model.getValueAt(indice, 0).toString();
						int indiceInt = Integer.parseInt(indiceInserito);
						System.out.println(indiceInt);
						model.removeRow(indiceInt);
						
						DeleteDataGui deleteDataGui = new DeleteDataGui();
						deleteDataGui.removeTipoFarmaco(tipo_farmaci,indiceInt);
						
				}catch(Exception e1) {
						String errore = e1.getMessage() + "Errore";
						System.out.println(errore);
				}
			}
			
		});
		buttonRimuovi.setBounds(298, 140, 134, 52);
		contentPane.add(buttonRimuovi);
		
		/*
		 * Pulsante per tornare alla schermata precedente 
		 */
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioneFarmaci frameFarmaci = new GestioneFarmaci();
				frameFarmaci.setVisible(true);
			}
		});
		buttonIndietro.setBounds(298, 232, 134, 52);
		contentPane.add(buttonIndietro);
	}
	
	
	/**
	 * Metodo contenente le caselle di testo 
	 */
	public void createJLabel() {
		JLabel lbIInfo = new JLabel("*Per effettuare una rimozione , selezionare la riga desiderata");
		lbIInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbIInfo.setBounds(12, 326, 635, 22);
		contentPane.add(lbIInfo);
		
		JLabel lbIRimozione = new JLabel("*Per aggiornare l'ID dopo la rimozione premere \"Indietro\" e tornare in questa finestra");
		lbIRimozione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbIRimozione.setBounds(12, 349, 495, 28);
		contentPane.add(lbIRimozione);
	}

	/**
	 * Questo metodo permette la visualizzazione della tabella dei tipo farmaci
	 */
	public void visualizzaTabellaTipoFarmaci() {
		
		String colTipoFarmaci[] = {"ID","Tipo"};
		DefaultTableModel tabellaTipoFarmaciModel = new DefaultTableModel(colTipoFarmaci,0);
		PrintDataGui printDataGui = new PrintDataGui();
		tabellaTipoFarmaciModel = printDataGui.visualizzaDatiTipoFarmaci(tipo_farmaci, tabellaTipoFarmaciModel);
		
		if(tipo_farmaci.isEmpty()) {
			txtNonCiSono = new JTextField();
			txtNonCiSono.setText("Non ci sono tipi di farmaci memorizzati");
			txtNonCiSono.setBounds(238,387,190,30);
			contentPane.add(txtNonCiSono);
			txtNonCiSono.setColumns(10);
			txtNonCiSono.setEditable(false);
			txtNonCiSono.setEnabled(false);
		}
		
		/**
		 * Metodo che non permette la modifica delle righe della tabella
		 */
		tipoFarmaciTable = new JTable(tabellaTipoFarmaciModel) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int i,int i1) {
				return false;
			}
		};
		
		tipoFarmaciTable.setBounds(10,23,23,23);
		tipoFarmaciTable.getTableHeader().setReorderingAllowed(false);
		tipoFarmaciTable.getTableHeader().setResizingAllowed(false);
		tipoFarmaciTable.setCellSelectionEnabled(true);
		
		
		
	}

	/**
	 * Questo metodo permette l'aggiunta di un nuovo tipo farmaco alla relativa tabella
	 * @param dataRow rappresenta i dati da aggiungere alla tabella
	 */
	public void AddRowToTipoFarmaciTable(Object[] dataRow) {
		
		String colTipoFarmaci[] = {"ID","Tipo"};
		DefaultTableModel tabellaTipoFarmaciModel = new DefaultTableModel(colTipoFarmaci,0);
		PrintDataGui printDataGui = new PrintDataGui();
		tabellaTipoFarmaciModel = printDataGui.visualizzaDatiTipoFarmaci(tipo_farmaci, tabellaTipoFarmaciModel);
		
		if(tipo_farmaci.isEmpty()) {
			txtNonCiSono = new JTextField();
			txtNonCiSono.setText("Non ci sono tipi di farmaci memorizzati");
			txtNonCiSono.setBounds(238,387,190,30);
			contentPane.add(txtNonCiSono);
			txtNonCiSono.setColumns(10);
			txtNonCiSono.setEditable(false);
			txtNonCiSono.setEnabled(false);
		}
		
		tipoFarmaciTable = new JTable(tabellaTipoFarmaciModel);
		tipoFarmaciTable.setBounds(10,23,23,23);
		tipoFarmaciTable.getTableHeader().setReorderingAllowed(false);
		tipoFarmaciTable.getTableHeader().setResizingAllowed(false);
		tipoFarmaciTable.setCellSelectionEnabled(true);
		
		tipoFarmaciTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				boolean a = tipoFarmaciTable.isEditing();
				
				if(a == false ) {
					
					JOptionPane.showMessageDialog(null,"La tabella non è editabile");
					
				}
			}
		});
		
		tabellaTipoFarmaciModel.addRow(dataRow);
		
	}
}
