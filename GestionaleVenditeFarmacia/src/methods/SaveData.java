package methods;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Questa classe consente di scrivere sui file farmaci.txt,tipo_farmaci.txt, impiegati.txt,vendite.txt
 * 
 * @author LorussoDanilo
 *
 */
public class SaveData {
	
	/*
	 * Definizione delle costanti utilizzate per definire
	 * la dimensione degli arraylist
	 */
	final int DIM_VENDITE = 20;
	final int DIM_IMPIEGATI = 10;
	final int DIM_FARMACI = 20;
	final int DIM_TIPO_FARMACI = 20;
	
	
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE); //arraylist delle vendite
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI); //arraylist degli impiegati
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI); //arraylist dei farmaci
	private ArrayList<String> tipo_farmaci = new ArrayList <String>(DIM_TIPO_FARMACI); //arraylist dei tipo farmaci
	/**
	 * Costruttore con 4 parametri
	 * 
	 * @param vendite rappresenta la vendita da salvare su file
	 * @param impiegati rappresenta l'impiegato da salvare su file
	 * @param farmaci rappresenta il farmaco da salvare su file
	 * @param tipo_farmaci rappresenta il tipo del farmaco da salvare su file
	 */
	public SaveData(ArrayList<Vendita> vendite, ArrayList <Impiegato> impiegati, ArrayList <Farmaco> farmaci, ArrayList <String> tipo_farmaci) {
		this.vendite = vendite;
		this.impiegati = impiegati;
		this.farmaci = farmaci;
		this.tipo_farmaci = tipo_farmaci;
	}
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati relativi alle vendite su file.
	 * 
	 * @throws Exception se la scrittura su file non viene eseguita correttamente
	 */
	public void salvaVendite() {
		File nomeFile = new File("vendite.txt");
		DecimalFormat Euro_df = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALY);
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = vendite.size();
			
				//Nel file i vari campi sono separati da uno spazio
				for(int i = 0; i<dim; i++) {
					output.write(vendite.get(i).getImpiegato().getCognomeImpiegato() + " ");
					output.write(vendite.get(i).getImpiegato().getNomeImpiegato() + " ");
					output.write(vendite.get(i).getFarmacoVenduto().getNomeFarmaco() + " ");
					output.write(vendite.get(i).getDataVendita() + " ");
					output.write(vendite.get(i).getQuantitaVenduta() + " ");

				
					if(i == dim - 1) {
						//non metto spazio/carattere perchè nell'input non accetterebbe un BigDecimal con accanto un carattere
						output.write(Euro_df.format(vendite.get(i).getPrezzoTotale()) + ""); 
					}else {
						output.write(Euro_df.format(vendite.get(i).getPrezzoTotale()) + "\n");	
					}
				
			}
			
			output.close();
			
		}catch(Exception e) {
			System.out.println("Errore");
		}
	}
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati relativi ai farmaci su file.
	 * 
	 * @throws Exception se la scrittura su file non viene eseguita correttamente
	 */
	public void salvaFarmaci() {
		
		File nomeFile = new File("farmaci.txt");
		DecimalFormat Euro_df = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALY);
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = farmaci.size();
			
				//Nel file i vari campi sono separati da uno spazio
				for(int i = 0; i<dim; i++) {
					output.write(farmaci.get(i).getNomeFarmaco() + " ");
					output.write(farmaci.get(i).getDataConfezionamento() + " ");
					output.write(farmaci.get(i).getDataScadenza() + " ");
					output.write(farmaci.get(i).getTipo() + " ");
				
					if(i == dim - 1) {
						//non metto spazio/carattere perchè nell'input non accetterebbe un BigDecimal con accanto un carattere
						output.write(Euro_df.format(farmaci.get(i).getPrezzo()) + "" );
					}else {
						output.write(Euro_df.format(farmaci.get(i).getPrezzo()) + "\n" );
					}
				
			}
			
			output.close();
		}catch(Exception e) {
			System.out.println("Errore");
		}
	}
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati relativi ai tipo farmaci su file.
	 * 
	 * @throws Exception se la scrittura su file non viene eseguita correttamente
	 */
	public void salvaTipoFarmaci() {
		
		File nomeFile = new File("tipo_farmaci.txt");
		
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = tipo_farmaci.size();
			
				//Nel file i vari campi sono separati da uno spazio
				for(int i = 0; i<dim; i++) {
					if(i == dim - 1) {
						//non metto spazio/carattere vuoto perchè l'input non accetterebbe  un intero con accanto un carattere
						output.write(tipo_farmaci.get(i) + "");	
					}else {
						output.write(tipo_farmaci.get(i) + "\n");	
					}
				}
			
				output.close();
				
		}catch(Exception e) {
			System.out.println("Errore");
		}
	}
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati relativi agli impiegati su file.
	 * 
	 * @throws Exception se la scrittura su file non viene eseguita correttamente
	 */
	public void salvaImpiegati() {
		
		File nomeFile = new File("impiegati.txt");
		
		
		try {
				
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = impiegati.size();
			
				//Nel file i vari campi sono separati da uno spazio
				for(int i = 0; i<dim; i++) {
					output.write(impiegati.get(i).getCognomeImpiegato() + " ");
					output.write(impiegati.get(i).getNomeImpiegato() + " ");	
					if(i == dim - 1) {
						//Non metto spazio/carattere vuoto perchè nell'input non accetterebbe un intero con accando un carattere
						output.write(impiegati.get(i).getMaxVenditeAnnuali() + ""); 
					}else {
						output.write(impiegati.get(i).getMaxVenditeAnnuali() + "\n");
					}
				}
			
				output.close();
				
		}catch(Exception e) {
			System.out.println("Errore");
		}
	}
	
			
}
