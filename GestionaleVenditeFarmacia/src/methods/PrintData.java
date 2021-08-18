
package methods;

import java.util.ArrayList;
import java.util.Scanner;

import eccezioni.Controlli;
import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;
/**
 * Questa classe contiene i metodi di visualizzazione su console 
 * dei dati necessari
 * @author BarbieriGiuseppe
 *
 */
public class PrintData {
	
	Scanner sc = new Scanner(System.in);
	public static Controlli controllo = new Controlli();
	
	
	/*
	 * Definiione delle costanti utilizzate per definire
	 * la dimensione degli arraylist
	 */
	final int DIM_VENDITE = 20;
	final int DIM_IMPIEGATI = 10;
	final int DIM_FARMACI = 20;
	final int DIM_TIPO_FARMACI = 20;
	
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE); 		   	//arraylist delle vendite
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI); 	//arraylist degli impiegati
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI); 			//arraylist dei farmaci
	private ArrayList<String> tipo_farmaci = new ArrayList <String>(DIM_TIPO_FARMACI);  //arraylist del tipo dei farmaci
	/**
	 * Costruttore
	 * @param vendite rappresenta la vendita da salvare su file
	 * @param impiegati rappresenta l' impiegato da salvare su file
	 * @param farmaci rappresenta il farmaco da salvare su file
	 * @param tipo_farmaci rappresenta il tipo del farmaco da salvare su file
	 */
	public PrintData(ArrayList<Vendita> vendite, ArrayList<Impiegato> impiegati, ArrayList<Farmaco> farmaci,ArrayList<String> tipo_farmaci) {
		this.vendite = vendite;
		this.impiegati = impiegati;
		this.farmaci = farmaci;
		this.tipo_farmaci = tipo_farmaci;
	}
	
	/**
	 * Questo metodo consente di visualizzare su console il menù di scelta
	 */
	public void MenuScelta() {
		System.out.println("\n|GESTIONE FARMACIA| \n");
		
		System.out.println("\n |GESTIONE VENDITE| \t\t |GESTIONE FARMACI| \t\t |GESTIONEIMPIEGATI|");
		System.out.println("1. Stampa Vendite \t\t 6. Stampa Farmaci \t\t 12. Stampa Impiegati");
		System.out.println("2. Inserisci Vendita \t\t 7. Inserisci Farmaco \t\t 13. Inserisci Impiegato");
		System.out.println("3. Modifica Vendita \t\t 8. Modifica Farmaco \t\t 14. Modifica Impiegato");
		System.out.println("4. Elimina Vendita \t\t 9. Elimina Farmaco \t\t 15. Elimina Impiegato");
		System.out.println("5. Elimina Gruppo vendite \t 10.Inserisci tipo farmaco\n \t\t\t\t 11.Elimina tipo farmaco");
		System.out.println("\n16. Esci");
	}
	
	
	/**
	 * Questo metodo consente di visualizare su console le vendite attraverso un filtro
	 * che le visualizza interamente , o per un determinato impiegato , farmaco , data di vendita o quantita venduta
	 *
	 */
	public void printFiltroVendite(){
		
		int scelta = 0;
		String nomeFarmaco = " ";
		String cognomeImpiegato = " ";
		String nomeImpiegato = " ";
		String dataVendita = " ";
		int quantitaVenduta = 0;
		boolean flagFiltro = false;
		
		System.out.println("Filtro di ricerca vendite:");
		System.out.println("1. Tutte le vendite");
		System.out.println("2. Per Impiegato");
		System.out.println("3. Per Farmaco");
		System.out.println("4. Per Data di Vendita");
		System.out.println("5. Per Quantita venduta");
		
		do {
			
				scelta = controllo.checkIntegerInput("Scelta");
				
				switch(scelta) {
				
				
					case 1:
							printAllVendite();
							
							
							break;
						
					case 2:
							cognomeImpiegato = controllo.checkAlphaInput("CognomeImpiegato");
							nomeImpiegato = controllo.checkAlphaInput("NomeImpiegato");
							
							printAllVenditeByImpiegato(cognomeImpiegato,nomeImpiegato);
														
							break;
						
					case 3:
							nomeFarmaco = controllo.checkAlphaInput("NomeFarmaco");
							
							printAllVenditeByFarmaco(nomeFarmaco);
						
							break;
						
					case 4:
							dataVendita = controllo.checkData("Vendita");
							
							printAllVenditeByData(dataVendita);
							
							break;
						
					case 5:
							quantitaVenduta = controllo.checkIntegerInput("Quantita");
							
							printAllVenditeByQuantita(quantitaVenduta);
							
							break;
						
					default: System.out.println("Scelta non corretta , riprova");
							 flagFiltro = true;
					
					
				}
				
			
			
			
			
			
		}while(flagFiltro == true);
		
		
		
	}
	
	
	/**
	 * Questo metodo consente la visualizzazione su console di tutte le vendite presenti nell'arraylist
	 * il for-each cicla tutte le vendite e fa stampare il tostring di ciascuno
	 */
	public void printAllVendite() {
		for(Vendita v: vendite) {
			System.out.println(v.toString(vendite.indexOf(v)));
		}
		
		if(vendite.isEmpty()) {
			System.out.println("Non ci sono vendite memorizzate");
		}
	}
	
	
	/**
	 * Questo medotodo consente di visualizzare su console tutte le vendite di un determinato impiegato
	 * 
	 * @param cognomeImpiegato rappresenta le vendite che contengono il cognome dell'impiegato inserito
	 * @param nomeImpiegato rappresenta le vendite che contengono il nome dell'impiegato inserito
	 */
	public void printAllVenditeByImpiegato(String cognomeImpiegato, String nomeImpiegato) {
		
		boolean flagImpiegato = false;
		
		for(Vendita v: vendite) {
			
			if(v.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato)
			   && v.getImpiegato().getNomeImpiegato().equals(nomeImpiegato)) {
				
				System.out.println(v.toString(vendite.indexOf(v)));
				flagImpiegato = true;
			}
			
		}
		
		if(flagImpiegato == false) {
			System.out.println("Nessuna vendita trovata con l'impiegato " + cognomeImpiegato + " " + nomeImpiegato); 
		}
	}
	
	
	
	/**
	 * Questo metodo consente di visualizzare su console tutte le vendite di un determinato farmaco
	 * 
	 * @param nomeFarmaco rappresenta le vendite che contengono il nome del farmaco inserito
	 */
	public void printAllVenditeByFarmaco(String nomeFarmaco) {
		
		boolean flagFarmaco = false;
		
		for(Vendita v: vendite) {
			
			if(v.getFarmacoVenduto().getNomeFarmaco().equals(nomeFarmaco)) {
				
				System.out.println(v.toString(vendite.indexOf(v)));
				flagFarmaco = true;
			}
			
		}
		
		if(flagFarmaco == false) {
			System.out.println("Nessuna vendita trovata con il farmaco" + nomeFarmaco); 
		}
	}
	
	
	/**
	 * Questo metodo consente di visualizzare su console tutte le vendite di una determina data
	 * 
	 * @param dataVendita rappresenta le vendite che contengono la data di vendita inserita
	 */
	public void printAllVenditeByData(String dataVendita) {
		
		boolean flagData = false;
		
		for(Vendita v: vendite) {
			
			if(v.getDataVendita().equals(dataVendita)) {
				
				System.out.println(v.toString(vendite.indexOf(v)));
				flagData = true;
			}
			
		}
		
		if(flagData == false) {
			System.out.println("Nessuna vendita trovata con la data" + dataVendita); 
		}
	}
	
	
	/**
	 * Questo metodo consente di visualizzare su console tutte le vendite con una determina quantita
	 * 
	 * @param quantitaVenduta rappresenta le vendite che contengono la quantità di farmaci inserita
	 */
	public void printAllVenditeByQuantita(int quantitaVenduta) {
		
		boolean flagQuantita = false;
		
		for(Vendita v: vendite) {
			
			if(v.getQuantitaVenduta() == quantitaVenduta) {
				
				System.out.println(v.toString(vendite.indexOf(v)));
				flagQuantita = true;
			}
			
		}
		
		if(flagQuantita == false) {
			System.out.println("Nessuna vendita trovata con la quantita" + quantitaVenduta); 
		}
	}
	
	
	/**
	 * Questo metodo consente la visualizzazione su console di tutti i farmaci presenti nell'arraylist
	 * il for-each cicla tutti i farmaci e fa stampare il tostring di ciascuno
	 */
	public void printFarmaci() {
		for(Farmaco f : farmaci) {
			System.out.println(f.toString(farmaci.indexOf(f)));
		}
		
		if(farmaci.isEmpty()) { System.out.println("Non ci sono famarci memorizzati");}
	}
	
	/**
	 * Questo metodo consente la visualizzazione su console di tutti gli impiegati presenti nell'arraylist
	 * il for-each cicla tutti gli impiegati e fa stampare il tostring di ciascuno
	 */
	public void printImpiegati() {
		for(Impiegato i : impiegati) {
			System.out.println(i.toString(impiegati.indexOf(i)));
		}
		
		if(impiegati.isEmpty()) { System.out.println("Non ci sono impiegati memorizzati");}
	}
	
	/**
	 * Questo metodo consente di visualizzare su console i tipi dei farmaci presenti nell'arraylist tipo farmaco
	 * è presente una variabile indexTipoFarmaco che viene incrementata all'interno del for each
	 * in modo tale da essere utilizzata in una eventuale selezione da parte dell'utente
	 */
	public void printTipoFarmaci() {
		int indexTipoFarmaco = 0;
		for(String s : tipo_farmaci) {
			System.out.println(indexTipoFarmaco + ". " + s);
			
			indexTipoFarmaco++;
		}
		if(tipo_farmaci.isEmpty()) { System.out.println("Non ci sono tipi di farmaci memorizzati");}
	}
	
}
