package methodsGui;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Questa classe contiene i metodi che permettono di importare gli impiegati da file e gestirli all'interno della Gui
 * @author LorussoDanilo
 *
 */
public class ImportDataGui {
	
	/**
	 *  Questo metodo permette di importare le vendite da file
	 * @param arraylist contenente le vendite
	 * @throws ParseException se il formato della data non è valida
	 * @throws FileNotFoundException se ci sono problemi relativi sulla lettura da file
	 */
	public static void importaVenditeGui(ArrayList <Vendita> vendite) {
		
		int indexVendita = 0;
		String cognomeImpiegato = " ";
		String nomeImpiegato = " ";
		String nomeFarmaco = " ";
		String dataVendita = " ";
		int quantita = 0;
		BigDecimal prezzoTotale = new BigDecimal("0.00");
		BigDecimal prezzoFarmaco = new BigDecimal("0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		
		try {
				Scanner txtIn = new Scanner(new File("vendite.txt"));
				
				while(txtIn.hasNextLine()) {
					
					cognomeImpiegato = txtIn.next();
					
					nomeImpiegato = txtIn.next();
					
					nomeFarmaco = txtIn.next();
					
					try {
							dataVendita = txtIn.next();
							sdf.parse(dataVendita);
					}catch(ParseException pe){
							System.out.println("Formato Data Vendita non valida");
							dataVendita = "00/00/0000";
					}
					
					quantita = txtIn.nextInt();
					
					prezzoTotale = txtIn.nextBigDecimal();
					
					prezzoFarmaco = prezzoTotale.divide(new BigDecimal(quantita));
					
					
					Impiegato impiegatoInst = new Impiegato(cognomeImpiegato,nomeImpiegato);
					Farmaco farmacoInst = new Farmaco(nomeFarmaco,prezzoFarmaco);
					Vendita venditaInst = new Vendita(impiegatoInst,farmacoInst,dataVendita,quantita,prezzoTotale);
					vendite.add(indexVendita,venditaInst);
					
					indexVendita++;
					
				}
			
				System.out.println("Vendite importate correttamente");
				txtIn.close();
				
		}catch(FileNotFoundException fnfe) { System.out.println("File non trovato"); }
		
		
	}
	
	
	/**
	 * Questo metodo permette di importare gli impiegati da file
	 * @param arraylist contenente gli impiegati
	 * @throws FileNotFoundException se ci sono problemi relativi sulla lettura da file
	 */
	public static void importaImpiegatiGui(ArrayList <Impiegato> impiegati) {
		
		int indexImpiegato=0;
		String cognome; 
		String nome;
		int maxVenditeAnnuali=0;
			
		try {
				Scanner txtIn = new Scanner(new File("impiegati.txt"));  //non è stato inserito nessun path perchè potrebbe variare la root di sistema
				
				while(txtIn.hasNextLine()) { ///finchè è presente una riga
					
					cognome = txtIn.next(); //per le stringhe si usa next()
					
					nome = txtIn.next();
					
					maxVenditeAnnuali= txtIn.nextInt(); //nextInt per acquisire un intero
				
					Impiegato impiegatoInst = new Impiegato(cognome,nome,maxVenditeAnnuali); ///viene instanziato un nuovo oggetto di tipo Impiegato contenente i valori presi dal file
					impiegati.add(indexImpiegato,impiegatoInst); ///viene aggiunto l'impiegato letto da file nell'arraylist
				
					indexImpiegato++;
				}
			
				System.out.println("Impiegati importati correttamente");
				txtIn.close(); ///viene chiuso lo scanner 
			
			
		}catch(FileNotFoundException fnfe) { System.out.println("File non trovato"); }
	}
	
	
	
	/**
	 * Questo metodo permette di importare i farmaci da file
	 * @param farmaci contenente i farmaci
	 * @throws java.text.ParseException eccezione sollevata in presenza di errori con le date 
	 * @throws FileNotFoundException quando ci sono problemi relativi alla lettura del file
	 */
	public static void importaFarmaciGui(ArrayList <Farmaco> farmaci) {
		
		int indexFarmaco = 0; 
		String nomeFarmaco = " ";
		String dataConfezionamento = " ";
		String dataScadenza = " ";
		String tipoFarmaco = " ";
		BigDecimal prezzo = new BigDecimal("0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		
		try {
				Scanner txtIn = new Scanner(new File("farmaci.txt"));  //non è stato inserito nessun path perchè potrebbe variare la root di sistema
				
				while(txtIn.hasNextLine()) { ///finchè è presente una riga
					
					nomeFarmaco = txtIn.next(); //per le stringhe si usa next()
					
					try {
							dataConfezionamento = txtIn.next();
							sdf.parse(dataConfezionamento);
						
					}catch(ParseException pe) {
							System.out.println("Formato Data Confezionamento non valido del farmaco " + indexFarmaco);
							dataConfezionamento = "00/00/0000";
						
					}
					
					try {
							dataScadenza = txtIn.next();
							sdf.parse(dataScadenza);
					
					}catch(ParseException pe) {
							System.out.println("Formato Data Scadenza non valido del farmaco " + indexFarmaco);
							dataConfezionamento = "00/00/0000";
					
					}
					
					tipoFarmaco = txtIn.next();
					
					prezzo = txtIn.nextBigDecimal();
									
					Farmaco farmacoInst = new Farmaco(nomeFarmaco, dataConfezionamento, dataScadenza, tipoFarmaco, prezzo); ///viene instanziato un nuovo oggetto di tipo Farmaco contenente i valori presi dal file
					farmaci.add(indexFarmaco,farmacoInst); ///viene aggiunto il farmaco letto da file nell'arraylist
				
					indexFarmaco++;
				}
			
				System.out.println("Farmaci importati correttamente");
				txtIn.close(); ///viene chiuso lo scanner 
			
			
		}catch(FileNotFoundException fnfe) { System.out.println("File non trovato"); }
	}
	
	/**
	 * Questo metodo permette di importare i tipi dei farmaci da file
	 * @param tipo_farmaci contenente i tipi dei farmaci
	 * @throws FileNotFoundException quando ci sono problemi relativi alla lettura del file
	 */
	public static void importaTipoFarmaciGui(ArrayList<String> tipo_farmaci) {
		int indexTipoFarmaco = 0;
		String tipoFarmaco = "";
		
		try {
			
			Scanner txtIn = new Scanner(new File("tipo_farmaci.txt"));
			
			while(txtIn.hasNextLine()) {
				
				tipoFarmaco = txtIn.next();
				
				String tipoFarmacoInst = new String(tipoFarmaco);
				tipo_farmaci.add(indexTipoFarmaco,tipoFarmacoInst);
				
				indexTipoFarmaco++;
				
			}
			
			System.out.println("Tipi dei Farmaci importati correttamente");
			txtIn.close();
			
		}catch(FileNotFoundException fnfe) {
			System.out.println("File non trovato");
		}
	}
	
}
