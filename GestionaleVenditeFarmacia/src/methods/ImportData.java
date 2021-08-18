package methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import farmaco.Farmaco;
import impiegato.Impiegato;
import interfacciaUtente.InterfacciaUtente;
import vendita.Vendita;

/**
 * Questa classe permette di Importare i dati dai file: impiegati.txt, farmaci.txt,vendite.txt
 * così da poterli usare all'interno del programma
 * 
 * @author Barbieri Giuseppe
 *
 */
public class ImportData {
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
	private ArrayList<String> tipo_farmaci = new ArrayList <String>(DIM_TIPO_FARMACI); //arraylist del tipo dei farmaci
	
	/**
	 * Costruttore con 4 parametri
	 * 
	 * @param vendite rappresenta la vendita da importare su file
	 * @param impiegati rappresenta l' impiegato da importare su file
	 * @param farmaci rappresenta il farmaco da importare su file
	 * @param tipo_farmaci rappresenta il tipo del farmaco da importare su file
	 */
	public ImportData(ArrayList <Vendita> vendite, ArrayList <Impiegato> impiegati, ArrayList <Farmaco> farmaci, ArrayList <String> tipo_farmaci) {
		this.vendite = vendite;
		this.impiegati = impiegati;
		this.farmaci = farmaci;
		this.tipo_farmaci = tipo_farmaci;
	}
	
	public void importaVendite(InterfacciaUtente Eseguibile) {
		int indexVendita = 0;
		String cognomeImpiegato = "";
		String nomeImpiegato = "";
		String nomeFarmaco = "";
		String dataVendita = "";
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
							dataVendita = txtIn.next(); //acquisisce la data di vendita
							sdf.parse(dataVendita); //effettua il parsing della data dalla stringa acquisita
					
					}catch(ParseException pe) { //qual'ora il format letto non dovesse essere corretto
						System.out.println("Formato Data Vendita del farmaco " + nomeFarmaco + "Non Valido");
						dataVendita = "00/00/0000"; //imposta la data con questo valore di default
					}
			
					quantita = txtIn.nextInt();
			
					prezzoTotale = txtIn.nextBigDecimal();
			
					prezzoFarmaco = prezzoTotale.divide(new BigDecimal(quantita));  //formula inversa per ricavare il prezzo del singolo farmaco
			
					/*Instanziato un nuovo oggetto 
					di tipo Impiegato con due soli parametri in quanto nella vendita ci interessa soltanto il cognome e il nome dell'impiegto che effettua la vendita*/
					Impiegato impiegatoInst = new Impiegato(cognomeImpiegato,nomeImpiegato);
			
					//Instanziato un nuovo oggetto di timpo Farmaco con due soli parametri in quanto nella vendita ci interessa soltanto il nome del farmaco venduto e il suo prezzo
					Farmaco farmacoInst = new Farmaco(nomeFarmaco,prezzoFarmaco);
			
					Vendita venditaInst= new Vendita(impiegatoInst,farmacoInst,dataVendita,quantita,prezzoTotale);
					vendite.add(indexVendita,venditaInst);
			
				}
			
				System.out.println("Vendite importate correttamente");
				txtIn.close();
				
		}catch(FileNotFoundException fnfe) {
			System.out.println("File non trovato");
		}
	}
	
	/**
	 * Questo metodo permette di importare i farmaci dal file farmaci.txt
	 * 
	 * @param Eseguibile viene utilizzato perchè contiene tutti gli arraylist per poterli usare in base all'occorrenza
	 * @throws FileNotFoundException se il file è mancante o inesistente
	 * @throws ParseException se il formato della data inserita non è valida
	 * 
	 */
	public void importaFarmaci(InterfacciaUtente Eseguibile) {
		int indexFarmaco = 0;
		String nomeFarmaco = " ";
		String dataConfezionamento = " ";
		String dataScadenza = " ";
		String tipoFarmaco = " ";
	    BigDecimal prezzo= new BigDecimal("0.00");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //format della data
		sdf.setLenient(false); //con questa istruzione la data deve contenere esattamente il layout inposto (dd/MM/yyyy)
		
		try {
			
				Scanner txtIn = new Scanner(new File("farmaci.txt"));
			
				while(txtIn.hasNextLine()) {
				
					nomeFarmaco = txtIn.next();
				
					try {
							dataConfezionamento = txtIn.next(); //acquisisce la data di confezionameno
							sdf.parse(dataConfezionamento); //effettua il parsing della data dalla stringa
					
					}catch(ParseException pe) { //qual'ora il format letto non dovesse essere corretto
							System.out.println("Formato Data Confezionamento del farmaco " + nomeFarmaco + "Non Valido");
							dataConfezionamento = "00/00/0000"; //imposta la data con questo valore di default
					}
				
					try {
							dataScadenza = txtIn.next();
							sdf.parse(dataScadenza);
					
					}catch(ParseException pe) {
							System.out.println("Formato Data Scadenza del farmaco " + nomeFarmaco);
							dataScadenza = "00/00/0000";
					}
				
					tipoFarmaco = txtIn.next();
				
					prezzo = txtIn.nextBigDecimal();
				
					Farmaco farmacoInst = new Farmaco(nomeFarmaco, dataConfezionamento, dataScadenza, tipoFarmaco, prezzo);
					farmaci.add(indexFarmaco,farmacoInst);
				
					indexFarmaco++;
				}
				
				System.out.println("Farmaci importati correttamente");
				txtIn.close(); //chiudo lo scanner
			
		}catch(FileNotFoundException fnfe) {
				System.out.println("File non trovato");
		}
		
		
		
	}
	
	/**
	 * Questo metodo permette di importare gli impiegati dal file impiegati.txt
	 * 
	 * @param Eseguibile, usato perchè contiene tutti gli arraylist per poterli usare in base all'occorrenza
	 * @throws FileNotFoundException se il file è mancante
	 */
	public void importaImpiegati(InterfacciaUtente Eseguibile) {
		String cognome; 
		String nome;
		int maxVenditeAnnuali=0;
		int indexImpiegato=0;
		
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
			
			
		}catch(FileNotFoundException fnfe) {
				System.out.println("File non trovato");
		}
	}
	
	/**
	 * Questo metodo permette di importare i tipi dei farmaci dal file tipo_farmaci.txt
	 * 
	 * @param Eseguibile, usato perchè contiene tutti gli arraylist per poterli usare in base all'occorrenza
	 * @throws FileNotFoundException se il file è mancante
	 */
	public void importaTipoFarmaci(InterfacciaUtente Eseguibile) {
		int indexTipoFarmaco = 0;
		String tipoFarmaco = " ";
		
		try {
			
				Scanner txtIn = new Scanner(new File("tipo_farmaci.txt"));
			
				while(txtIn.hasNextLine()) {
					
					tipoFarmaco = txtIn.next();
				
					String tipoFarmacoInst = new String(tipoFarmaco);///viene instanziato un nuovo oggetto di tipo String contenente i valori presi in input dal file
					tipo_farmaci.add(indexTipoFarmaco, tipoFarmacoInst); ///l'oggetto viene aggiunto all'arraylist tipo_farmaci(di tipo String)
				
					indexTipoFarmaco++;
				}
				
				System.out.println("Tipi dei farmaci importati correttamente");
				txtIn.close();
			
		}catch(FileNotFoundException fnfe) {
				System.out.println("File non trovato");
		}
	}
	
	
	
	
	
}
