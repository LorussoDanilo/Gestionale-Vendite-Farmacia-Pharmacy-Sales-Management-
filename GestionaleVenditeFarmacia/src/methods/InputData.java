/**
 * Questo package contiene tutti i metodi utilizzati all'interno del programma
 */
package methods;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import eccezioni.Controlli;
import farmaco.Farmaco;
import impiegato.Impiegato;
import interfacciaUtente.InterfacciaUtente;
import vendita.Vendita;

/**
 * Questa classe contiene tutti i metodi di inserimento di dati da parte
 * dell'utente nelle arrayList e nei relativi file
 * Si assume che i valori inseriti in input non contengono spazi
 * esempio errato: De rossi
 * esempio corretto: DeRossi
 * 
 * @author gruppo_24
 * @version 1.0
 *
 */
public class InputData {
	
	public static Scanner sc = new Scanner(System.in); //scanner per l'acquisizione dell'input
	public Controlli controllo = new Controlli();
	
	
	/*
	 * Definiione delle costanti utilizzate per definire
	 * la dimensione degli arraylist
	 */
	final int DIM_VENDITE = 20;
	final int DIM_IMPIEGATI = 10;
	final int DIM_FARMACI = 20;
	final int DIM_TIPO_FARMACI = 20;
	
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE); //arraylist delle vendite
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI); //arraylist degli impiegati
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI); //arraylist dei farmaci
	private ArrayList<String> tipo_farmaci = new ArrayList <String>(DIM_TIPO_FARMACI);
	/**
	 * Costruttore con 4 parametri
	 * 
	 * @param vendite rappresenta la vendita da salvare su file
	 * @param impiegati rappresenta l' impiegato da salvare su file
	 * @param farmaci rappresenta il farmaco da salvare su file
	 * @param tipo_farmaci rappresenta il tipo del farmaco da salvare su file
	 */
	public InputData(ArrayList <Vendita> vendite, ArrayList <Impiegato> impiegati, ArrayList <Farmaco> farmaci, ArrayList <String> tipo_farmaci) {
		this.vendite = vendite;
		this.impiegati = impiegati;
		this.farmaci = farmaci;
		this.tipo_farmaci = tipo_farmaci;
	}
	
	/**
	 * Questo metodo consente all'utente di inserire una nuova vendita nel file vendite.txt.
	 * Permette di inserire la data odierna o una data qualsiasi. Inoltre permette l'emissione di uno scontrino.
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente, contiene tutti gli ArrayList. Cosi da poterli usare in base all'occorrenza.
	 * @throws ParseException se il formato della data non è valido
	 */
	public void inserisciVendita(InterfacciaUtente Eseguibile) throws ParseException {
		
		int indexImpiegato = 0;
		int indexFarmaco = 0;
		int sceltaTipoData = 0;
		String dataVendita = "";
		String dataScadenza = "";
		int quantita = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		boolean flagScelta = false;
		int scontrino = 0;
		boolean flagScontrino = false;
		boolean flagConfrontaData = false;
		
		Eseguibile.printData.printImpiegati();
		indexImpiegato = controllo.checkIdImpiegato(impiegati);
		
		//Ciclo per l'inserimento della data
		do {
			System.out.println("Quale data vuoi inserire?");
			System.out.println("1. Data Odierna");
			System.out.println("2. Altra Data");
			//In base a ciò che c'è scritto tra parentesi vengono mostrate a schermo delle stampe personalizzate
			sceltaTipoData = controllo.checkIntegerInput("Scelta");
			
			switch(sceltaTipoData) {
				
				case 1:
						flagScelta = true;
					
						Date data = new Date();
					
						dataVendita = sdf.format(data);
					
						break;
				case 2:
						flagScelta = true;
					
						dataVendita = controllo.checkData("Vendita");
					
						break;
					
				default:	
						System.out.println("Scelta non corretta, riprova");
						flagScelta = false;
			
			}


		}while(flagScelta == false);
		
		//Ciclo per l'emissione dello scontrino
		do {
			
			//ciclo per confrontare la data della vendita con la data di scadenza del farmaco
			do {
				Eseguibile.printData.printFarmaci();
				indexFarmaco = controllo.checkIdFarmaco(farmaci);
				
				dataScadenza = farmaci.get(indexFarmaco).getDataScadenza();
				
				//La prima data deve essere antecedente alla seconda
				flagConfrontaData = controllo.checkConfrontaData(dataVendita,dataScadenza);
				
				if(flagConfrontaData == false) {System.out.println("Farmaco Scaduto! \n Inserisci un altro farmaco");}
				
			}while(flagConfrontaData == false);
			
			quantita = controllo.checkIntegerInput("Quantita");
			
			
				if(controllo.limiteVenditeGiornaliere(vendite, impiegati, indexImpiegato, dataVendita, quantita) == true && controllo.limiteVenditeAnnuali(vendite, impiegati, indexImpiegato, dataVendita, quantita) == true) {
						addVendita(impiegati.get(indexImpiegato), farmaci.get(indexFarmaco),dataVendita,quantita);
				}
			
			System.out.println("Vuoi aggiungere un altro farmaco?");
			System.out.println("1. Si");
			System.out.println("2. Emettere Scontrino");
			scontrino = controllo.checkIntegerInput("Scelta");
			
			if(scontrino == 2) { flagScontrino = true; }
			
		}while(flagScontrino == false);
		
		System.out.println("Vendite aggiunte correttamente");
		
		Eseguibile.saveData.salvaVendite();
	}
	
	
	/**
	 * Questo metodo permette di aggiungere una nuova vendita all'arrayList di tipo Vendita
	 * 
	 * @param impiegato rappresenta il nome e il cognome dell'impiegato, inserito dall'utente, che verrà inserito nel nuovo oggetto Vendita aggiunto all'arraylist
	 * @param farmaco rappresenta il nome farmaco, inserito dall'utente, che verrà inserito nel nuovo oggetto Vendita aggiunto all'arraylist
	 * @param dataVendita rappresenta la data della vendita, inserita dall'utente, che verrà inserita nel nuovo oggetto Vendita aggiunto all'arraylist
	 * @param quantitaVenduta rappresenta la quantità di farmaci, inserita dall'utente, che verrà inserita nel nuovo oggetto Vendita aggiunto all'arraylist
	 */
	public void addVendita(Impiegato impiegato,Farmaco farmaco,String dataVendita,int quantitaVenduta) {
		vendite.add(new Vendita(impiegato,farmaco,dataVendita,quantitaVenduta));
	}
	
	
	
	/**
	 * Questo metodo consente all'utente di inserire un nuovo farmaco nel file farmaci.txt
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente, contiene tutti gli ArrayList. Cosi da poterli usare in base all'occorrenza.
	 * @thows ParseException se il formato della data non è valido
	 */
	public void inserisciFarmaco(InterfacciaUtente Eseguibile) throws ParseException {
		
		
		String nomeFarmaco = " ";
		String dataOdierna = "";
		String dataConfezionamento = " ";
		String dataScadenza = " ";
		String tipo = " ";
		BigDecimal prezzo = new BigDecimal("0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		Date dataO = new Date();
		dataOdierna = sdf.format(dataO);
		int sceltaTipoFarmaco = 0;
		boolean flagConfrontaData = false;
		int inserimento = 0;
		boolean flagInserimento = false;
		
		do {
				
				nomeFarmaco = controllo.checkAlphaInput("NomeFarmaco");
				
				
				do {
						dataConfezionamento = controllo.checkData("Confezionamento");
						
						dataScadenza = controllo.checkData("Scadenza");
						
						if(controllo.checkConfrontaData(dataConfezionamento, dataScadenza) == true && controllo.checkConfrontaData(dataConfezionamento, dataOdierna) == true) {
							flagConfrontaData = true;
						}else {
							if(controllo.checkConfrontaData(dataConfezionamento, dataOdierna) == false) {
								System.out.println("La data di confezionamento deve essere antecedente alla data odierna");
							}
							if(controllo.checkConfrontaData(dataConfezionamento, dataScadenza) == false) {
								System.out.println("La data di confezionamento deve essere antecedente alla data di scadenza");
							}
							
							flagConfrontaData = false;
						}
				}while(flagConfrontaData == false);
				
				System.out.println("\n");
				Eseguibile.printData.printTipoFarmaci();
				sceltaTipoFarmaco = controllo.checkIdTipoFarmaco(tipo_farmaci);
				tipo = tipo_farmaci.get(sceltaTipoFarmaco);
				
				prezzo = controllo.checkDecimalInput("Prezzo");
				
			
				addFarmaco(nomeFarmaco,dataConfezionamento,dataScadenza,tipo,prezzo);
				
				System.out.println("Vuoi aggiungere un altro farmaco?");
				System.out.println("1. Si");
				System.out.println("2. Termina inserimento");
				inserimento = controllo.checkIntegerInput("Scelta");
				
				if(inserimento == 2) { flagInserimento = true; }
			
		}while(flagInserimento == false);
			
		System.out.println("Farmaco aggiunto correttamente");
			
		Eseguibile.saveData.salvaFarmaci();
	}
	
	/**
	 * Questo metodo aggiunge un farmaco all'interno 
	 * dell'arrayList farmaci
	 * 
	 * @param nomeFarmaco  rappresenta il nome del farmaco, inserito dall'utente, che verrà inserito nel nuovo oggetto Farmaco aggiunto all'arraylist
	 * @param dataConfezionamento rappresenta la data di confezionamento del farmaco, inserito dall'utente, che verrà inserita nel nuovo oggetto Farmaco aggiunto all'arraylist
	 * @param dataScadenza rappresenta la data di scadenza del farmaco, inserito dall'utente, che verrà inserita nel nuovo oggetto Farmaco aggiunto all'arraylist
	 * @param tipo rappresenta il tipo del farmaco, inserito dall'utente, che verrà inserito nel nuovo oggetto Farmaco aggiunto all'arraylist
	 * @param prezzo rappresenta il prezzo del farmaco, inserito dall'utente, che verrà inserito nel nuovo oggetto Farmaco aggiunto all'arraylist
	 */
	public void addFarmaco(String nomeFarmaco,String dataConfezionamento,String dataScadenza,String tipo,BigDecimal prezzo) {
		farmaci.add(new Farmaco(nomeFarmaco,dataConfezionamento,dataScadenza,tipo,prezzo));
	}
	
	/**
	 * Questo metodo permette l'aggiunta di un tipo farmaco all'arrayList tipo_farmaci
	 * 
	 * @param tipoFarmaco rappresenta il tipo di farmaco inserito dall'utente
	 */
	public void addTipoFarmaco(String tipoFarmaco) {
		tipo_farmaci.add(new String(tipoFarmaco));
	}
	
	/**
	 * Questo metodo aggiunge un tipo farmaco all'interno dell'arrayList tipo_farmaci
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente, contiene tutti gli ArrayList. Cosi da poterli usare in base all'occorrenza.
	 */
	public void inserisciTipoFarmaco(InterfacciaUtente Eseguibile) {
		
		String tipoFarmaco = "";
		Eseguibile.printData.printTipoFarmaci();
		tipoFarmaco = controllo.checkAlphaInput("TipoFarmaco");
		
		addTipoFarmaco(tipoFarmaco);
		
		System.out.println("Tipo farmaco aggiunto correttamente");
		System.out.println("Tipo farmaci registrati");
		
		Eseguibile.printData.printTipoFarmaci();
		Eseguibile.saveData.salvaTipoFarmaci();

	}
	
	
	/**
	 * Questo metodo aggiunge un impiegato all'interno dell'arrayList impiegati
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente, contiene tutti gli ArrayList. Cosi da poterli usare in base all'occorrenza.
	 */
	public void inserisciImpiegato(InterfacciaUtente Eseguibile) {
		
		String cognomeImpiegato;
		String nomeImpiegato;
		int maxVenditeAnnuali = 0;
		boolean flagInserimento = false;
		int inserimento = 0;
		
		do {
			
			cognomeImpiegato = controllo.checkAlphaInput("CognomeImpiegato");
			
			nomeImpiegato = controllo.checkAlphaInput("NomeImpiegato");
						
			maxVenditeAnnuali = controllo.checkNumeroVenditeAnnuali();
			
			addImpiegato(cognomeImpiegato,nomeImpiegato,maxVenditeAnnuali);
			
			System.out.println("Vuoi aggiungere un altro impiegato?");
			System.out.println("1. Si");
			System.out.println("2. Termina inserimento");
			inserimento = controllo.checkIntegerInput("Scelta");
			
			if(inserimento == 2) { flagInserimento = true; }
			
		}while(flagInserimento == false);
		
		System.out.println("Impiegati aggiunti correttamente");
		
		Eseguibile.saveData.salvaImpiegati();
		
		
	}
	
	
	/**
	 * Questo metodo permette di aggiungere un nuovo impiegato
	 * all'arraylist di tipo Impiegato
	 * 
	 * @param cognomeImpiegato rappresenta il cognome dell'impiegato , inserito dall'utente , che verrà inserito nel nuovo oggetto Impiegato aggiunto all'arraylist
	 * @param nomeImpiegato rappresenta il nome dell'impiegato , inserito dall'utente , che verrà inserito nel nuovo oggetto Impiegato aggiunto all'arraylist
	 * @param maxVenditeAnnuali rappresenta il limite di vendite annuali , inserito dall'utente , che un impiegato può fare
	 * che verrà inserito nel nuovo oggetto Impiegato aggiunto all'arraylist
	 */
	public void addImpiegato(String cognomeImpiegato, String nomeImpiegato , int maxVenditeAnnuali) {
		impiegati.add(new Impiegato(cognomeImpiegato,nomeImpiegato,maxVenditeAnnuali));
		
		
	}
	
	
	
	
}
