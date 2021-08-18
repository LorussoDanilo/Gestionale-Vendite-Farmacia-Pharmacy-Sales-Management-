/**
 * Questo package contiene tutte le schermate interattive della GUI. Esse vengono gestite dal package dei metodi
 */
package gui;


import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Questa è la finestra di start, che permette di scegliere che dati gestire
 * 
 * @author LorussoDanilo
 *
 */
public class GestionaleFarmacia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static GestionaleFarmacia frameGestionaleFarmacia = new GestionaleFarmacia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionaleFarmacia frame = new GestionaleFarmacia();
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
	public GestionaleFarmacia() {
		initUI();
		
	}
	/**
	 * Con questo metodo setto l'interfaccia con le sue componenti
	 */
	private void initUI() {
		setResizable(false);
		setTitle("Gestionale Farmacia");
		setIconImage(Toolkit.getDefaultToolkit().getImage("CroceFarmacia.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 502);
		createPane();
		createButton();
		Icons();
	}
	
	
	/**
	 * Con questo metodo setto il pannello all'interno alla finestra
	 */
	private void createPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	
	/**
	 * Con questo metodo setto i pulsanti presenti nella finestra
	 */
	private void createButton() {
		/*
		 * Pulsante gestione vendite
		 */
		JButton gestioneVenditeButton = new JButton("Gestione Vendite");
		gestioneVenditeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestioneVendite frameVendite = new GestioneVendite();
				setVisible(false);//Cosi rendo la finestra corrente non visibile
				frameVendite.setVisible(true);
			}
		});
		gestioneVenditeButton.setBounds(143, 28, 194, 66);
		contentPane.add(gestioneVenditeButton);
		
		/*
		 * Pulsante gestione farmaci
		 */
		JButton gestioneFarmaciButton = new JButton("Gestione Farmaci");
		gestioneFarmaciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestioneFarmaci frameFarmaci = new GestioneFarmaci();
				setVisible(false);//Cosi rendo la finestra corrente non visibile
				frameFarmaci.setVisible(true);
			}
		});
		gestioneFarmaciButton.setBounds(143, 135, 194, 66);
		contentPane.add(gestioneFarmaciButton);
		
		/*
		 * Pulsante gestione impiegati
		 */
		JButton gestioneImpiegatiButton = new JButton("Gestione Impiegati");
		gestioneImpiegatiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestioneImpiegati frameImpiegati = new GestioneImpiegati();
				setVisible(false);//Cosi rendo la finestra corrente non visibile
				frameImpiegati.setVisible(true);
			}
		});
		gestioneImpiegatiButton.setBounds(143, 247, 194, 66);
		contentPane.add(gestioneImpiegatiButton);
		
		/*
		 * Pulsante di uscita dal programma
		 */
		JButton exitButton = new JButton("Esci");
		exitButton.addActionListener((event) -> System.exit(0));
		exitButton.setBounds(143, 356, 194, 66);
		contentPane.add(exitButton);
	}
	/**
	 * Questo metodo permette di definire tutte le icone all'interno del JFrame
	 */
	private void Icons() {
		/*
		 * Icone dei pulsanti
		 */
		JLabel iconaVendite = new JLabel("");
		iconaVendite.setBounds(49, 28, 66, 66);
		iconaVendite.setIcon(new ImageIcon("Vendite.jpg"));
		contentPane.add(iconaVendite);
		
		JLabel iconaFarmaci = new JLabel("");
		iconaFarmaci.setBounds(49, 135, 66, 66);
		iconaFarmaci.setIcon(new ImageIcon("pillola.png"));
		contentPane.add(iconaFarmaci);
		
		JLabel iconaImpiegati = new JLabel("");
		iconaImpiegati.setBounds(49, 247, 66, 66);
		iconaImpiegati.setIcon(new ImageIcon("impiegati.png"));
		contentPane.add(iconaImpiegati);
		
		JLabel iconaEsci = new JLabel("");
		iconaEsci.setBounds(49, 356, 66, 66);
		iconaEsci.setIcon(new ImageIcon("ExitIcon.png"));
		contentPane.add(iconaEsci);
	}
	
	
}
