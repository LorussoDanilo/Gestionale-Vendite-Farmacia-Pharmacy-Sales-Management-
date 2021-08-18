/**
 /**
 * Questo package contiene la classe che gestisce gli input errati dell'utente
 */
package eccezioniGui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Questa classe contiene i metodi per gestire input errati dell'utente
 * @author CardinaleChristian
 *
 */
public class ControlliGui {
	
	/*
	 * Definizione delle costanti che stabiliscono
	 * i limiti di vendite giornaliere e annuali
	 * che un impiegato può eseguire
	 */
	final static int MAX_VENDITE_GIORNALIERE = 40;
	final static int MIN_VENDITE_ANNUALI = 1000;
	final static int MAX_VENDITE_ANNUALI = 14000;
	
	
	/**
	 * Questo metodo confronta la prima data con la seconda e verifica che la prima data sia antecedente alla seconda
	 * 
	 * @param dataString1 rappresenta la stringa che deve essere antecedente ad un'altra data
	 * @param dataString2 rappresenta la stringa che deve essere successiva ad un'altra data
	 * @return un valore booleano: se la condizione(prima data antecedente alla seconda) è verificata,
	 *   restituisce true, altrimenti restituisce false
	 * @throws ParseException se la data inserita non è del formato corretto
	 */
	public static boolean checkConfrontaData(String dataString1,String dataString2) throws ParseException {
		
		boolean flagData = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		
		try {
				Date data1 = sdf.parse(dataString1);
				Date data2 = sdf.parse(dataString2);
		
				if(data1.compareTo(data2) <= 0) {
					
					flagData = true;
					
				}else {
						flagData = false;
				}
				
		}catch(Exception e1) {
			String errore = e1.getMessage() + "Errore";
			System.out.println(errore);
		}
		
		return flagData;	
	}
	
	
	/**
	 * Questo metodo consente di verificare che il limite di vendite annuali inserito
	 *  per un impiegato rispetti il limite imposto
	 * 
	 * @return la variabile che rappresenta il numero di vendite annuali inserito dall'utente
	 * @throws InputMismatException se la cifra inserita è fuori dal range di valori ammessi,
	 * oppure se la stringa inserita contiene caratteri alfabetici
	 * 
	 */
	public static boolean checkNumeroVenditeAnnuali(int maxVenditeAnnuali) {
		
		boolean flagRangeErrato = true;
		
		if(maxVenditeAnnuali < MIN_VENDITE_ANNUALI || maxVenditeAnnuali > MAX_VENDITE_ANNUALI) {
				flagRangeErrato = true;
		}else {	flagRangeErrato = false;} //riporto il flag alla sua inizializzazione
			 
		return flagRangeErrato;
	}
	
	
	
	
	/**
	 * Questo metodo consente di stabilire il numero massimo di vendite annuali per ciascun impiegato durante la modifica dei dati.
	 * Se il limite viene superato non sarà possibile per l'impiegato inserire un'ulteriore vendita
	 * 
	 * @param vendite rappresenta le vendite contenute nell'arrayList di tipo Vendita
	 * @param impiegati rappresenta gli impigati  nell'arrayList di tipo Impiegato
	 * @param indexImpiegato rappresenta l'indice dell'impiegato a cui si riferisce
	 * @param dataVendita è la variabile che rappresenta la data della vendita inserita dall'utente
	 * @param quantitaCorrente è la variabile che contiene la quantità venduta di un farmaco appena inserito dall'utente
	 * @param quantitaPrecedente indica la quantita già presente in tabella,altrimenti si sommerebbe alla quantità totale causando errori aritmetici
	 * @return il valore di ritorno è un booleano: se dovesse essere true, allora il limite di vendite annuali, da parte di un impiegato, non è stato superato;
	 * 	altrimenti se è false il limite di vendite annuali è stato superato
	 */
	public static boolean limiteVenditeAnnualiModifica(ArrayList<Vendita> vendite, ArrayList<Impiegato> impiegati, int indexImpiegato,String dataVendita,int quantitaCorrente,int quantitaPrecedente) {
		
		int quantitaTotaleAnnua = 0; ///Conterrà la quantità venduta 
		boolean flagQuantita = false;
		String cognomeImpiegato = impiegati.get(indexImpiegato).getCognomeImpiegato();
		String nomeImpiegato = impiegati.get(indexImpiegato).getNomeImpiegato();
		
		if(vendite.isEmpty() == false) {
			
			for(Vendita v : vendite) {
					if(v.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato) && v.getImpiegato().getNomeImpiegato().equals(nomeImpiegato)
							&& v.getDataVendita().subSequence(6, 9).equals(dataVendita.subSequence(6, 9))) { ///quest'ultima istruzione consente di controllare soltanto la parte dell'anno delle due date (/yyyy)
						
							quantitaTotaleAnnua += v.getQuantitaVenduta(); //se l'anno corrisponde incrementa il valore in quantitaTotaleAnnua
						
					}
				
			}
		}
		
		quantitaTotaleAnnua = quantitaTotaleAnnua - quantitaPrecedente;
		quantitaTotaleAnnua += quantitaCorrente;
				
		if(quantitaTotaleAnnua <= impiegati.get(indexImpiegato).getMaxVenditeAnnuali()) { //se il limite annuale è minore o uguale al limite massimo
					
			flagQuantita = true;
			
		}else { //altrimenti non fare nulla
				flagQuantita = false;
				JOptionPane.showMessageDialog(null,  "Raggiunto il limite massimo di vendite ANNUALI per l'impiegato" + impiegati.get(indexImpiegato) + " " + impiegati.get(indexImpiegato).getNomeImpiegato() + "\nVENDITA NON AGGIUNTA!");
			  }
		
		return flagQuantita;
		
		
	}
	
	
	
	/**
	 * Questo metodo consente di stabilire il numero massimo di vendite giornaliere per tutti gli impiegati durante la modifica dei dati.
	 * se il limite viene superato, non sarà possibile per l'impiegato inserire un'ulteriore vendita.
	 * 
	 * @param vendite rappresenta le vendite contenute nell'arrayList di tipo Vendita
	 * @param impiegati rappresenta gli impigati  nell'arrayList di tipo Impiegato
	 * @param indexImpiegato rappresenta l'indice dell'impiegato a cui si riferisce
	 * @param dataVendita è la variabile che rappresenta la data della vendita inserita dall'utente
	 * @param quantitaCorrente è la variabile che contiene la quantità venduta di un farmaco appena inserito dall'utente
	 * @param quantitaPrecedente indica la quantita già presente in tabella,altrimenti si sommerebbe alla quantità totale causando errori aritmetici
	 * @return il valore di ritorno è un booleano: se dovesse essere true, allora il limite di vendite giornaliere, da parte di un impiegato, non è stato superato;
	 * 	altrimenti se è false il limite di vendite giornaliere è stato superato
	 */
	public static boolean limiteVenditeGiornaliereModifica(ArrayList<Vendita> vendite, ArrayList<Impiegato> impiegati, int indexImpiegato,String dataVendita,int quantitaCorrente,int quantitaPrecedente) {
		
		int quantitaTotaleGiornaliera=0;
		boolean flagQuantita = false;
		String cognomeImpiegato = impiegati.get(indexImpiegato).getCognomeImpiegato();
		String nomeImpiegato = impiegati.get(indexImpiegato).getNomeImpiegato();
		
		if(vendite.isEmpty() == false) {
			
			for(Vendita v: vendite) {
				
				if(v.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato) && v.getImpiegato().getNomeImpiegato().equals(nomeImpiegato)
						&& v.getDataVendita().equals(dataVendita)) {
							
							quantitaTotaleGiornaliera += v.getQuantitaVenduta(); // se il controllo è vero, incrementa la quantità totale giornaliera
							
				}
			} 
		}		
				//Effettuo la sottrazione della quantità già presente in tabella,altrimenti si sommerebbe alla quantità totale causando errori aritmetici
				quantitaTotaleGiornaliera = quantitaTotaleGiornaliera - quantitaPrecedente;
				quantitaTotaleGiornaliera += quantitaCorrente; 
				if(quantitaTotaleGiornaliera <= MAX_VENDITE_GIORNALIERE) {
					flagQuantita = true;
				}else {
							flagQuantita = false;
							JOptionPane.showMessageDialog(null,  "Raggiunto il limite massimo di vendite GIORNALIERE per l'impiegato  " + impiegati.get(indexImpiegato).getCognomeImpiegato() + "\nVENDITA NON AGGIUNTA!");
					
				}
				return flagQuantita;
	}
	
	
	/**
	 * Questo metodo consente di stabilire il numero massimo di vendite annuali per ciascun impiegato durante l'inserimento di nuovi dati.
	 * Se il limite viene superato non sarà possibile per l'impiegato inserire un'ulteriore vendita
	 * 
	 * @param vendite rappresenta le vendite contenute nell'arrayList di tipo Vendita
	 * @param impiegati rappresenta gli impigati  nell'arrayList di tipo Impiegato
	 * @param indexImpiegato rappresenta l'indice dell'impiegato a cui si riferisce
	 * @param dataVendita è la variabile che rappresenta la data della vendita inserita dall'utente
	 * @param quantitaCorrente è la variabile che contiene la quantità venduta di un farmaco appena inserito dall'utente
	 * @param quantitaPrecedente indica la quantita già presente in tabella,altrimenti non si sommerebbe alla quantità totale causando errori aritmetici
	 * @return il valore di ritorno è un booleano: se dovesse essere true, allora il limite di vendite annuali, da parte di un impiegato, non è stato superato;
	 * 	altrimenti se è false il limite di vendite annuali è stato superato
	 */
	public static boolean limiteVenditeAnnualiInserisci(ArrayList<Vendita> vendite, ArrayList<Impiegato> impiegati, int indexImpiegato,String dataVendita,int quantitaCorrente,int quantitaPrecedente) {
		
		int quantitaTotaleAnnua = 0; ///Conterrà la quantità venduta 
		boolean flagQuantita = false;
		String cognomeImpiegato = impiegati.get(indexImpiegato).getCognomeImpiegato();
		String nomeImpiegato = impiegati.get(indexImpiegato).getNomeImpiegato();
		
		if(vendite.isEmpty() == false) {
			
			for(Vendita v : vendite) {
					if(v.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato) && v.getImpiegato().getNomeImpiegato().equals(nomeImpiegato)
							&& v.getDataVendita().subSequence(6, 9).equals(dataVendita.subSequence(6, 9))) { ///quest'ultima istruzione consente di controllare soltanto la parte dell'anno delle due date (/yyyy)
							quantitaPrecedente += v.getQuantitaVenduta();
							quantitaTotaleAnnua = quantitaPrecedente; //se l'anno corrisponde incrementa il valore in quantitaTotaleAnnua
						
					}
				
			}
		}
		
		quantitaTotaleAnnua += quantitaCorrente;
				
		if(quantitaTotaleAnnua <= impiegati.get(indexImpiegato).getMaxVenditeAnnuali()) { //se il limite annuale è minore o uguale al limite massimo
					
			flagQuantita = true;
			
		}else { //altrimenti non fare nulla
				flagQuantita = false;
				JOptionPane.showMessageDialog(null,  "Raggiunto il limite massimo di vendite ANNUALI per l'impiegato" + impiegati.get(indexImpiegato) + "\nVENDITA NON AGGIUNTA!");
			  }
		
		return flagQuantita;
		
		
	}
	
	
	
	/**
	 * Questo metodo consente di stabilire il numero massimo di vendite giornaliere per tutti gli impiegati durante l'inserimento di nuovi dati.
	 * se il limite viene superato, non sarà possibile per l'impiegato inserire un'ulteriore vendita.
	 * 
	 * @param vendite rappresenta le vendite contenute nell'arrayList di tipo Vendita
	 * @param impiegati rappresenta gli impigati  nell'arrayList di tipo Impiegato
	 * @param indexImpiegato rappresenta l'indice dell'impiegato a cui si riferisce
	 * @param dataVendita è la variabile che rappresenta la data della vendita inserita dall'utente
	 * @param quantitaCorrente è la variabile che contiene la quantità venduta di un farmaco appena inserito dall'utente
	 * @param quantitaPrecedente indica la quantita già presente in tabella,altrimenti non si sommerebbe alla quantità totale causando errori aritmetici
	 * @return il valore di ritorno è un booleano: se dovesse essere true, allora il limite di vendite giornaliere, da parte di un impiegato, non è stato superato;
	 * 	altrimenti se è false il limite di vendite giornaliere è stato superato
	 */
	public static boolean limiteVenditeGiornaliereInserisci(ArrayList <Vendita> vendite, ArrayList<Impiegato> impiegati, int indexImpiegato,String dataVendita,int quantitaCorrente,int quantitaPrecedente) {
		int quantitaTotaleGiornaliera = 0;
		boolean flagQuantita = false;
		String cognomeImpiegato = impiegati.get(indexImpiegato).getCognomeImpiegato();
		String nomeImpiegato = impiegati.get(indexImpiegato).getNomeImpiegato();
		
		if(!vendite.isEmpty()) {
			 for(Vendita v: vendite) {

				 if(v.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato) && v.getImpiegato().getNomeImpiegato().equals(nomeImpiegato) && v.getDataVendita().equals(dataVendita)) {
					 	
					 quantitaPrecedente += v.getQuantitaVenduta();
					 quantitaTotaleGiornaliera = quantitaPrecedente; //se l'anno corrisponde incrementa il valore in quantitaTotaleGiornaliera			 
				 }
			 
			 }
		}
			
			quantitaTotaleGiornaliera += quantitaCorrente;
		
			if(quantitaTotaleGiornaliera <= MAX_VENDITE_GIORNALIERE ) {
				
				flagQuantita = true;
			}else {
						flagQuantita = false;
						JOptionPane.showMessageDialog(null,  "Raggiunto il limite massimo di vendite GIORNALIERE per l'impiegato  " + impiegati.get(indexImpiegato) + "\nVENDITA NON AGGIUNTA!");
			}
			
		return flagQuantita;
	}
	
	
	
	/**
	 * Questo metodo verifica che la data inserita dall'utente sia del formato indicato
	 * 
	 * @param data è la data da controllare
	 * @return la variabile , inserita dall'utente , che rappresenta la stringa corrispondente alla data con il formato indicato corretto
	 * @throws ParseException se la data inserita non è del formato corretto
	 */
	public static boolean checkData(String data) {
		
		boolean flagData = false;
		SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
		sdfD.setLenient(false);
			
		try {
				sdfD.parse(data);
				if(data.length() != 10) {
					throw new ParseException(data,0);
				}
				
				flagData = true;
				
		}catch(ParseException pe) {
			JOptionPane.showMessageDialog(null, "Formato data non valido.");
			flagData = false;
		}
		
	
		return flagData;
	}
	
	
	/**
	 * Questo metodo verifica che la data inserita dall'utente sia del formato indicato
	 * 
	 * @param data è la data da controllare
	 * @return la variabile , inserita dall'utente , che rappresenta la stringa corrispondente alla data con il formato indicato corretto
	 * @throws ParseException se la data inserita non è del formato corretto
	 */
	public static boolean checkDataMese(String data) {
		
		boolean flagData = false;
		SimpleDateFormat sdfD = new SimpleDateFormat("MM/yyyy");
		sdfD.setLenient(false);
			
		try {
				sdfD.parse(data);
				if(data.length() != 7) {
					throw new ParseException(data,0);
				}
				
				flagData = true;
				
		}catch(ParseException pe) {
			JOptionPane.showMessageDialog(null, "Formato data non valido.");
			flagData = false;
		}
		
	
		return flagData;
	}
	
	
	/**
	 * Questo metodo verifica che la data inserita dall'utente sia del formato indicato
	 * 
	 * @param data è la data da controllare
	 * @return la variabile , inserita dall'utente , che rappresenta la stringa corrispondente alla data con il formato indicato corretto
	 * @throws ParseException se la data inserita non è del formato corretto
	 */
	public static boolean checkDataAnno(String data) {
		
		boolean flagData = false;
		SimpleDateFormat sdfD = new SimpleDateFormat("yyyy");
		sdfD.setLenient(false);
			
		try {
				sdfD.parse(data);
				if(data.length() != 4) {
					throw new ParseException(data,0);
				}
				
				flagData = true;
				
		}catch(ParseException pe) {
			JOptionPane.showMessageDialog(null, "Formato data non valido.");
			flagData = false;
		}
		
	
		return flagData;
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
	public static int checkIntegerInput(String tipoInteger) {
		
		int integer = 0;
		boolean flagInteger = false;
		
			do {
				if(tipoInteger.equals("Scelta")) {
					System.out.println("Effettua una scelta");
				}else if(tipoInteger.equals("Quantita")) {
					System.out.println("Inserisci una quantita");
				}	
				
			
					try {
							flagInteger = true;
				
				
					}catch(InputMismatchException ime) {
						System.out.println("Il numero inserito è errato! Riprovare!");
						flagInteger = false;
						
					}
			
			}while(flagInteger == false);
	
		return integer;
	}
}
