/**
 * Questo package contiene i metodi per gestire le eccezioni
 */
package eccezioni;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Questa classe contiene tutti i controlli necessari al corretto funzionamento del programma.
 * I metodi di questa classe non permettono all'utente di effettuare errori dovuti all'inserimento
 * scorretto di valori in input.
 * 
 * @author LorussoDanilo
 * @version 1.0
 */
public class Controlli {

	private static final int MIN_VENDITE_ANNUALI = 1000;
	private static final int MAX_VENDITE_ANNUALI = 14000;
	private static final int MAX_VENDITE_GIORNALIERE = 40;
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Costruttore vuoto
	 */
	public Controlli() {}
	
	/**
	 * Questo metodo verifica che la stringa inserita dall'utente corrisponda ad una stringa
	 * composta da soli caratteri alfabetici, e possibili spazi vuoti.
	 * All'interno del programma viene applicato a:
	 * -Nome del farmaco;
	 * -Cognome Impiegato;
	 * -Nome Impiegato;
	 * -Tipo del farmaco.
	 * 
	 * @param tipoStringa è una stringa passata come parametro per effettuare una stampa personalizzata
	 * della stringa che si preferisce
	 * @return la stringa inserita dall'utente che rappresenta una stringa alfabetica
	 * @throws InputMismatchException se la stringa non è composta da soli caratteri alfabetici e possibili spazi vuoti
	 */
	public String checkAlphaInput(String tipoStringa) {
		String stringaInput = "";
		boolean flagStringa = false;
		
			do {
				if(tipoStringa.equals("NomeFarmaco")) { //se la scelta corrisponde al nome del farmaco
					System.out.println("Inserisci nome farmaco");
				}else if(tipoStringa.equals("CognomeImpiegato")) { //se la scelta corrisponde al cognome dell'impiegato
					System.out.println("Inserisci Cognome Impiegato");
				}else if(tipoStringa.equals("NomeImpiegato")) {//se la scelta corrisponde al nome dell'impiegato
					System.out.println("Inserisci Nome Impiegato");
				}else if(tipoStringa.equals("TipoFarmaco")) {//se la scelta corrisponde al tipo del farmaco
					System.out.println("Inserisci tipo farmaco");
				}
			
					try {
				
						stringaInput = sc.next();
						stringaInput += sc.nextLine();
				
						if(!stringaInput.matches("[a-zA-Z\\s]+")) { //se la stringa in input contiene numeri
							throw new InputMismatchException();
						}
						flagStringa = true;
				
					}catch(InputMismatchException ime) {
						System.out.println("La stringa inserita non è composta da caratteri alfabetici! Riprovare!");
						flagStringa = false;
					}
			
			}while(flagStringa == false);
	
	return stringaInput;
	
	
	}
	
	
	
	/**
	 * Questo metodo verifica che la stringa inserita dall'utente corrisponda ad un numero decimale.
	 * All'interno del programma viene applicato a:
	 * -prezzo del farmaco;
	 * -prezzo totale della vendita.
	 * 
	 * @param tipoStringa è una stringa passata come parametro per effettuare una stampa personalizzata
	 * del decimale che si preferisce
	 * @return la stringa inserita dall'utente che rappresenta un numero decimale
	 * @throws InputMismatchException se la stringa non corrisponde ad un numero decimale
	 */
	
	public BigDecimal checkDecimalInput(String tipoDecimal) {
		
		BigDecimal decimal = new BigDecimal("0.00");
		boolean flagDecimal = false;
		
		do {
			
				if(tipoDecimal.equalsIgnoreCase("Prezzo")) {
					System.out.println("Inserisci il prezzo");
				}
			
				try {
						decimal = sc.nextBigDecimal();
						flagDecimal = true;
				
				}catch(InputMismatchException ime) {
					System.out.println("La stringa inserita non corrisponde ad un numero decimale! Riprovare!");
					flagDecimal = false;
					sc.next();
				}
			
			
			
		}while(flagDecimal == false);
		
		return decimal;
		
	}
	
	
	
	
	
	
	/**
	 * Questo metodo verifica che la stringa inserita dall'utente sia un numero intero positivo.
	 * All'interno del programma viene applicato a:
	 * -Richiesta di una scelta;
	 * -Richiesta di una quantità venduta.
	 * 
	 * @param tipoInteger è una stringa passata come parametro per effettuare una stampa personalizzata
	 * dell'intero che si preferisce
	 * @return la stringa inserita dall'utente che rappresenta un intero positivo
	 * @throws InputMismatchException se la stringa non corrisponde ad un intero positivo
	 */
	public int checkIntegerInput(String tipoInteger) {
		int integer = 0;
		boolean flagInteger = false;
		
			do {
				if(tipoInteger.equals("Scelta")) {
					System.out.println("Effettua una scelta");
				}else if(tipoInteger.equals("Quantita")) {
					System.out.println("Inserisci una quantita");
				}	
				
			
					try {
				
						integer = sc.nextInt();
						flagInteger = true;
				
				
					}catch(InputMismatchException ime) {
						System.out.println("Il numero inserito è errato! Riprovare!");
						flagInteger = false;
						sc.next(); //Viene richiesto l'inserimento se il numero errato
					}
			
			}while(flagInteger == false);
	
	return integer;
	}
	
	/**
	 * Questo metodo verifica che la data inserita dall'utente sia del formato indicato
	 * 
	 * @param tipoData è una stringa passata come parametro per effettuare una stampa personalizzata
	 *  del formato della data che si preferisce
	 * @return la stringa che rappresenta il formato della data 
	 * @throws ParseException se la data inserita non è del formato corretto
	 */
	public String checkData(String tipoData) {
		String data = "";
		boolean flagData = false;
		SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfM = new SimpleDateFormat("MM/yyyy");
		SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
		sdfD.setLenient(false);
		sdfM.setLenient(false);
		sdfY.setLenient(false);
		int tipoFormatoData = 0; //Serve per fare lo switch e settare quindi il tipo di formato della data da usare
		
		
		
		do {
			if(tipoData.equals("Vendita")) {
				System.out.println("Inserisci data di vendita [gg/mm/aaaa]");
				tipoFormatoData = 1;
			}else if(tipoData.equals("Confezionamento")) {
				System.out.println("Inserisci data confezionamento [gg/mm/aaaa]");
				tipoFormatoData = 1;
			}else if(tipoData.equals("Scadenza")) {
				System.out.println("Inserisci data di scadenza [gg/mm/aaaa]");
				tipoFormatoData = 1;
			}else if(tipoData.equals("Giorno")) {
				System.out.println("Inserisci giorno [gg/mm/aaaa]");
				tipoFormatoData = 1;
			}else if(tipoData.equals("Mese")) {
				System.out.println("Inserisci mese [mm/aaaa]");
				tipoFormatoData = 2;
			}else if(tipoData.equals("Anno")) {
				System.out.println("Inserisci anno [aaaa]");
				tipoFormatoData = 3;
			}
		
		
			switch(tipoFormatoData) {
				case 1:
						try {
								data = sc.next();
								sdfD.parse(data);
								if(data.length() != 10) { throw new ParseException(data,0); }
								flagData = true;
							
						}catch(ParseException pe) {
							System.out.println("Formato data non valido");
							flagData = false;
						}
					
					
						break;
			
					
	
				case 2:
						try {
								data = sc.next();
								sdfM.parse(data);
								if(data.length()!=7) {throw new ParseException(data,0); }
								flagData = true;
								
						}catch(ParseException pe) {
								System.out.println("Formato mese non valido");
								flagData = false;
						}
							
						break;
							
			  case 3:
				  			try {
				  				data = sc.next();
				  				sdfY.parse(data);
				  				if(data.length() != 4) {throw new ParseException(data, 0);}
				  				flagData = true;
				  				
				  			}catch(ParseException pe) {
				  				System.out.println("Formato anno non valido");
				  				flagData = false;
				  			}
				  			
				  			break;
	
		
			}
		
			}while(flagData == false);
	
		return data;
	}
	
	/**
	 * Questo metodo confronta la prima data con la seconda e verifica che la prima data sia antecedente alla seconda
	 * 
	 * @param dataString1 rappresenta la stringa che deve essere antecedente ad un'altra data
	 * @param dataString2 rappresenta la stringa che deve essere successiva ad un'altra data
	 * @return un valore booleano: se la condizione(prima data antecedente alla seconda) è verificata,
	 *   restituisce true, altrimenti restituisce false
	 * @throws ParseException se la data inserita non è del formato corretto
	 */
	public boolean checkConfrontaData(String dataString1,String dataString2) throws ParseException {
		
		boolean flagData = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		
		Date data1 = sdf.parse(dataString1);
		Date data2 = sdf.parse(dataString2);
		
		if(data1.compareTo(data2) <= 0) {
			flagData = true;
		}else {
				flagData = false;
		}
		
		return flagData;	
	}
	
	
	/**
	 * Questo metodo consente di verificare che l'indice della vendita inserita dell'utente 
	 * sia presente nell'arrayList di tipo Vendita
	 * 
	 * @param vendite rappresenta l'arraylist delle vendite utile per effettuare controllo su esso
	 * @return la variabile che rappresenta l'indice della vendita inserita dall'utente
	 * @throws InputMismatchException se l'indice inserito dall'utente non è un intero positivo
	 * @throws IndexOutOfBoundsException se l'indice inserito dall'utente non è presente nell'araylist di tipo Vendita
	 */
	public int checkIdVendita (ArrayList <Vendita> vendite) {
		
		int indexVendita = 0;
		boolean flagIndex = false;
		
		do {
			
			try {
						System.out.println("Inserisci id vendita(0 = prima vendita)");
						indexVendita = sc.nextInt();
						vendite.get(indexVendita);	//serve a verificare se l'indice è presente nell'arraylist
						flagIndex = true;
			}catch(InputMismatchException ime) {
				
						System.out.println("Non è un id");
						sc.next();
									
			}catch(IndexOutOfBoundsException iobe) {
				
						System.out.println("Id vendita non trovato");
						flagIndex = false;
			}
			
		}while(flagIndex == false);
		
		return indexVendita;
	}
	
	
	
	
	/**
	 * Questo metodo consente di verificare che l'indice dell'impiegato inserito dall'utente sia
	 * presente nell'ArrayList di tipo Impiegato
	 * 
	 * @param impiegati rappresenta l'arraylist degl'impiegati utile per effettuare un controllo su esso
	 * @return la variabile che rappresenta l'indice dell'impiegato inserito dall'utente
	 * @throws InputMismatchException se l'indice inserito dall'utente non è un numero intero positivo
	 * @throws IndexOutOfBoundsException se l'indice inserito dall'utente non è presente nell'arrayList di tipo Impiegato
	 */
	public int checkIdImpiegato(ArrayList<Impiegato> impiegati ) {
		int indexImpiegato = 0;
		boolean flagIndex = false;
		
		do {
			try {
				System.out.println("Inserisci id impiegato (0 = primo impiegato)");
				indexImpiegato = sc.nextInt();
				impiegati.get(indexImpiegato);//Istruzione per verificare che l'indice sia presente
				flagIndex = true;
			}catch(InputMismatchException ime) {
				System.out.println("Non è un id");
				flagIndex = false;
				sc.next();
			}catch(IndexOutOfBoundsException iobe) {
				System.out.println("Id impiegato non trovato");
				flagIndex = false;
			}
			
		}while(flagIndex == false);
		
		return indexImpiegato;

	}
	
	/**
	 * Questo metodo consente di verificare che l'indice del farmaco inserito dall'utente sia
	 * presente nell'ArrayList di tipo Impiegato
	 * 
	 * @param farmaci rappresenta l'arraylist dei farmaci utile per effettuare un controllo su esso
	 * @return la variabile che rappresenta l'indice del farmaco inserito dall'utente
	 * @throws InputMismatchException se l'indice inserito dall'utente non è un numero intero positivo
	 * @throws IndexOutOfBoundsException se l'indice inserito dall'utente non è presente nell'arrayList di Farmaco
	 */
	public int checkIdFarmaco(ArrayList<Farmaco> farmaci ) {
		int indexFarmaco = 0;
		boolean flagIndex = false;
		
		do {
			try {
				System.out.println("Inserisci id farmaco (0 = primo farmaco)");
				indexFarmaco = sc.nextInt();
				farmaci.get(indexFarmaco);//Istruzione per verificare che l'indice sia presente
				flagIndex = true;
			}catch(InputMismatchException ime) {
				System.out.println("Non è un id");
				flagIndex = false;
				sc.next();
			}catch(IndexOutOfBoundsException iobe) {
				System.out.println("Id farmaco non trovato");
				flagIndex = false;
			}
			
		}while(flagIndex == false);
		
		return indexFarmaco;

	}
	
	
	
	/**
	 * Questo metodo consente di verificare che l'indice del tipo del farmaco inserito dall'utente 
	 * sia presente nell'arrayList di tipo Farmaco
	 * 
	 * @param tipo_farmaci rappresenta l'arraylist del tipo dei farmaci utile per effettuare un controllo su esso
	 * @return la variabile che rappresenta l'indice del tipo del farmaco inserito dall'utente
	 * @throws InputMismatchException se l'indice inserito dall'utente non è un numero intero positivo
	 * @throws IndexOutOfBoundsException se l'indice inserito dall'utente non è presente nell'arrayList di Tipo Farmaco
	 * 
	 */
	
	public int checkIdTipoFarmaco(ArrayList<String> tipo_farmaci) {
		
		int indexTipoFarmaco = 0;
		boolean flagIndex = false;
		
		do {
			
				try {
						System.out.println("Inserisci id del tipo farmaco");
						indexTipoFarmaco = sc.nextInt();
						tipo_farmaci.get(indexTipoFarmaco); //istruzione per verificare se l'indice è presente
						flagIndex = true;
						
				
				}catch(InputMismatchException ime) {
						
						System.out.println("Non è un id");
						flagIndex = false;
						sc.next();
					
				}catch(IndexOutOfBoundsException iobe) {
					
						System.out.println("Id tipo farmaco non trovato");
						flagIndex = false;
				}
		
		}while(flagIndex == false);
		
		
		return indexTipoFarmaco;
				
	}
	
	
	
	
	
	
	/**
	 * Questo metodo consente di verificare che il limite di vendite annuali inserito per un impiegato rispetti il 
	 * limite imposto
	 * 
	 * @return la variabile che rappresenta il numero di vendite annuali inserito dall'utente
	 * @throws InputMismatException se la cifra inserita è fuori dal range di valori ammessi,
	 * oppure se la stringa inserita contiene caratteri alfabetici
	 * 
	 */
	public int checkNumeroVenditeAnnuali() {
		
		int maxVenditeAnnuali = 0;
		boolean flagRangeErrato = false;
		boolean flagNumeroVendite = false;
		
		do {
			try {
				System.out.println("Inserisci il limite di vendite annuali [1000-14000] ");
				maxVenditeAnnuali = sc.nextInt();
			 
				if(maxVenditeAnnuali < MIN_VENDITE_ANNUALI || maxVenditeAnnuali > MAX_VENDITE_ANNUALI) {
					flagRangeErrato = true;
					throw new InputMismatchException();
				}
			 
				flagNumeroVendite = true;
			 
			 
				}catch(InputMismatchException ime) {
					System.out.println("Fuori dal range");
			flagNumeroVendite = false;
				if(flagRangeErrato == false) {
					sc.next();
				}
			}
		
		flagRangeErrato = false;//Riporto il flag alla sua inizializzazione per evitare il loop
		
		}while(flagNumeroVendite == false);
		
		return maxVenditeAnnuali;
	}
	
	/**
	 * Questo metodo consente di stabilire il numero massimo di vendite annuali per ciascun impiegato.
	 * Se il limite viene superato non sarà possibile per l'impiegato inserire un'ulteriore vendita
	 * 
	 * @param vendite rappresenta le vendite contenute nell'arrayList di tipo Vendita
	 * @param impiegati rappresenta gli impigati  nell'arrayList di tipo Impiegato
	 * @param indexImpiegato rappresenta l'indice dell'impiegato a cui si riferisce
	 * @param dataVendita è la variabile che rappresenta la data della vendita inserita dall'utente
	 * @param quantitaCorrente è la variabile che contiene la quantità venduta di un farmaco appena inserito dall'utente
	 * @return il valore di ritorno è un booleano: se dovesse essere true, allora il limite di vendite annuali, da parte di un impiegato, non è stato superato;
	 * 	altrimenti se è false il limite di vendite annuali è stato superato
	 */
	public boolean limiteVenditeAnnuali(ArrayList<Vendita> vendite, ArrayList<Impiegato> impiegati, int indexImpiegato,String dataVendita,int quantitaCorrente) {
		
		int quantitaTotaleAnnua = 0; ///Conterrà la quantità venduta 
		boolean flagQuantita = false;
		String cognomeImpiegato = impiegati.get(indexImpiegato).getCognomeImpiegato();
		String nomeImpiegato = impiegati.get(indexImpiegato).getNomeImpiegato();
		
		
		if(vendite.isEmpty() == false) {
			
			for(Vendita v : vendite) {
				
					if(v.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato) && v.getImpiegato().getNomeImpiegato().equals(nomeImpiegato)
							&& v.getDataVendita().subSequence(6, 9).equals(dataVendita.subSequence(6, 9))) { ///quest'ultima istruzione consente di controllare soltanto la parte dell'anno delle due date (/yyyy)
						
							quantitaTotaleAnnua += v.getQuantitaVenduta();///se l'anno corrisponde incrementa il valore in quantitaTotaleAnnua
						
					}
				
				}
		}
		
				quantitaTotaleAnnua += quantitaCorrente;
				
				if(quantitaTotaleAnnua <= impiegati.get(indexImpiegato).getMaxVenditeAnnuali()) { //se il limite annuale è minore o uguale al limite massimo
					 flagQuantita = true;
				}else { //altrimenti non fare nulla
					 flagQuantita = false;
					 System.out.println("Raggiunto il limite massimo di vendite annuali per l'impiegato  " + impiegati.get(indexImpiegato).getCognomeImpiegato()
							 + " " + impiegati.get(indexImpiegato).getNomeImpiegato());
				}
		
			return flagQuantita;
		}
	
	
	
	/**
	 * Questo metodo consente di stabilire il numero massimo di vendite giornaliere per tutti gli impiegati.
	 * se il limite viene superato, non sarà possibile per l'impiegato inserire un'ulteriore vendita.
	 * 
	 * @param vendite rappresenta le vendite contenute nell'arrayList di tipo Vendita
	 * @param impiegati rappresenta gli impigati  nell'arrayList di tipo Impiegato
	 * @param indexImpiegato rappresenta l'indice dell'impiegato a cui si riferisce
	 * @param dataVendita è la variabile che rappresenta la data della vendita inserita dall'utente
	 * @param quantitaCorrente è la variabile che contiene la quantità venduta di un farmaco appena inserito dall'utente
	 * @return il valore di ritorno è un booleano: se dovesse essere true, allora il limite di vendite giornaliere, da parte di un impiegato, non è stato superato;
	 * 	altrimenti se è false il limite di vendite giornaliere è stato superato
	 */
	public boolean limiteVenditeGiornaliere(ArrayList<Vendita> vendite, ArrayList<Impiegato> impiegati, int indexImpiegato,String dataVendita,int quantitaCorrente) {
		
		int quantitaTotaleGiornaliera=0;
		boolean flagQuantita = false;
		String cognomeImpiegato = impiegati.get(indexImpiegato).getCognomeImpiegato();
		String nomeImpiegato = impiegati.get(indexImpiegato).getNomeImpiegato();
		
		if(vendite.isEmpty() == false) {
			
			for(Vendita v: vendite) {
				
				if(v.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato) && v.getImpiegato().getNomeImpiegato().equals(nomeImpiegato)
						&& v.getDataVendita().equals(dataVendita)) {/// in questo caso confronta l'intera data
							
							quantitaTotaleGiornaliera += v.getQuantitaVenduta(); // se il controllo è vero, incrementa la quantità totale giornaliera
				}
			}
		
		}	
				quantitaTotaleGiornaliera += quantitaCorrente;
				
				if(quantitaTotaleGiornaliera <= MAX_VENDITE_GIORNALIERE) {
					 	flagQuantita = true; ///se le vendite non superano il limite giornaliero massimo
				}else {
					flagQuantita = false;
					System.out.println("Raggiunto il limite massimo di vendite giornaliere per l'impiegato  " 
										+ impiegati.get(indexImpiegato).getCognomeImpiegato()
										+ " " + impiegati.get(indexImpiegato).getNomeImpiegato());
				}
			return flagQuantita;
	}
		
	
	
	
}
