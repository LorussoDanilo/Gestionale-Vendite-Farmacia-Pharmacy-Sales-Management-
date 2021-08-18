package methodsGui;

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
 * Questa classe contiene tutti i metodi che consentono di rendere persistenti all'interno di file i dati elaborati al programma
 * @author CardinaleChristian,BarbieriGiuseppe
 *
 */
public class SaveDataGui {
	
	
	final static int DIM_IMPIEGATI = 10;
	
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI);
	
	
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati 
	 * relativi alle vendite su file
	 * 
	 * @param vendite è l'arraylist contenente le vendite
	 * @exception Exception se la scrittura su file non viene eseguita correttamente
	 */
	public static void salvaVendite(ArrayList <Vendita> vendite) {
		
		File nomeFile = new File("vendite.txt");
		DecimalFormat Euro_df = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALY);
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = vendite.size();
				
				for(int i = 0; i < dim ; i++) {
					
					output.write(vendite.get(i).getImpiegato().getCognomeImpiegato() + " ");
					output.write(vendite.get(i).getImpiegato().getNomeImpiegato() + " ");
					output.write(vendite.get(i).getFarmacoVenduto().getNomeFarmaco() + " ");
					output.write(vendite.get(i).getDataVendita() + " ");
					output.write(vendite.get(i).getQuantitaVenduta() + " ");
					
					if(i == dim - 1) {
							output.write(Euro_df.format(vendite.get(i).getPrezzoTotale()) + ""); //non metto spazio ne carattere vuoto perchè nell'input non accetterebbe un BigDecimal con accanto un carattere
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
	 * Questo metodo permette di effettuare la scrittura dei dati 
	 * relativi agl'impiegati su file
	 * 
	 * @param impiegati è l'arraylist contenente gli impiegati
	 * @exception Exception se la scrittura su file non viene eseguita correttamente
	 */
	public static void salvaImpiegati(ArrayList <Impiegato> impiegati) {
		
		File nomeFile = new File("impiegati.txt");
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = impiegati.size();
				
				for(int i = 0; i < dim ; i++) {
					
					output.write(impiegati.get(i).getCognomeImpiegato() + " ");
					output.write(impiegati.get(i).getNomeImpiegato() + " ");
	
					if(i == dim - 1) {
							output.write(impiegati.get(i).getMaxVenditeAnnuali() + ""); //non metto spazio ne carattere vuoto perchè nell'input non accetterebbe un intero con accanto un carattere
					}else { 
							output.write(impiegati.get(i).getMaxVenditeAnnuali() + "\n");
					}
				}
				
				output.close();
				
		}catch(Exception e) {
			System.out.println("Errore");
			
		}
		
	}
	
	
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati 
	 * relativi agl'impiegati su file
	 * 
	 * @exception Exception se la scrittura su file non viene eseguita correttamente
	 */
	public void salvaImpiegati() {
		
		File nomeFile = new File("impiegati.txt");
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = impiegati.size();
				
				for(int i = 0; i < dim ; i++) {
					
					output.write(impiegati.get(i).getCognomeImpiegato() + " ");
					output.write(impiegati.get(i).getNomeImpiegato() + " ");
	
					if(i == dim - 1) {
							output.write(impiegati.get(i).getMaxVenditeAnnuali() + ""); //non metto spazio ne carattere vuoto perchè nell'input non accetterebbe un intero con accanto un carattere
					}else { 
							output.write(impiegati.get(i).getMaxVenditeAnnuali() + "\n");
					}
				}
				
				output.close();
				
		}catch(Exception e) {
			System.out.println("Errore");
			
		}
		
	}
	
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati 
	 * relativi agl'impiegati su file
	 * 
	 * @param cognome dell'impiegato
	 * @param nome dell'impiegato
	 * @param maxVenditeAnnuali dell'impiegato
	 * @exception Exception se la scrittura su file non viene eseguita correttamente
	 */
	public void salvaImpiegati(String cognome, String Nome, int maxVenditeAnnuali) {
		
		File nomeFile = new File("impiegati.txt");
		ImportDataGui.importaImpiegatiGui(impiegati);
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = impiegati.size();
				
				for(int i = 0; i < dim ; i++) {
					
					output.write(impiegati.get(i).getCognomeImpiegato() + " ");
					output.write(impiegati.get(i).getNomeImpiegato() + " ");
	
					if(i == dim - 1) {
							output.write(impiegati.get(i).getMaxVenditeAnnuali() + ""); //non metto spazio ne carattere vuoto perchè nell'input non accetterebbe un intero con accanto un carattere
					}else { 
							output.write(impiegati.get(i).getMaxVenditeAnnuali() + "\n");
					}
				}
				
				output.close();
				
		}catch(Exception e) {
			System.out.println("Errore");
			
		}
		
	}
	
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati 
	 * relativi ai farmaci su file
	 * 
	 * @param farmaci è l'arraylist contenente i farmaci
	 * @exception Exception se la scrittura su file non viene eseguita correttamente
	 */
	public static void salvaFarmaci(ArrayList <Farmaco> farmaci) {
		
		File nomeFile = new File("farmaci.txt");
		DecimalFormat Euro_df = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALY);
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = farmaci.size();
				
				for(int i = 0; i < dim ; i++) {
					
					output.write(farmaci.get(i).getNomeFarmaco() + " ");
					output.write(farmaci.get(i).getDataConfezionamento() + " ");
					output.write(farmaci.get(i).getDataScadenza() + " ");
					output.write(farmaci.get(i).getTipo() + " ");
	
					if(i == dim - 1) {
							output.write(Euro_df.format(farmaci.get(i).getPrezzo()) + ""); //non metto spazio ne carattere vuoto perchè nell'input non accetterebbe un BigDecimal con accanto un carattere
					}else { 
							output.write(Euro_df.format(farmaci.get(i).getPrezzo()) + "\n");
					}
				}
				
				output.close();
				
		}catch(Exception e) {
			System.out.println("Errore");
			
		}
		
	}
	
	
	/**
	 * Questo metodo permette di effettuare la scrittura dei dati 
	 * relativi ai tipi di farmaci su file
	 * 
	 * @param tipo_farmaci è l'arraylist contenente i tipi dei farmaci
	 * @exception Exception se la scrittura su file non viene eseguita correttamente
	 */
	public static void salvaTipoFarmaci(ArrayList <String> tipo_farmaci) {
		
		File nomeFile = new File("tipo_farmaci.txt");
		
		try {
				FileWriter fw = new FileWriter(nomeFile);
				Writer output = new BufferedWriter(fw);
				int dim = tipo_farmaci.size();
				
				for(int i = 0; i < dim ; i++) {
					
					if(i == dim - 1) {
							output.write(tipo_farmaci.get(i) + ""); //non metto spazio ne carattere vuoto perchè nell'input non accetterebbe un BigDecimal con accanto un carattere
					}else { 
							output.write(tipo_farmaci.get(i) + "\n");
					}
				}
				
				output.close();
				
		}catch(Exception e) {
			System.out.println("Errore");
			
		}
		
	}
	
	
	
}
