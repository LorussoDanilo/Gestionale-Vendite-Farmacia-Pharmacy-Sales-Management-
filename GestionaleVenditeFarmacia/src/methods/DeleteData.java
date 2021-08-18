package methods;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import eccezioni.Controlli;
import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;
import interfacciaUtente.InterfacciaUtente;

/**
 * Questa classe consente di cancellare i dati contenuti nei file:
 * farmaci.txt,impiegati.txt e vendite.txt
 * 
 * @author CardinaleChristian
 *  
*/
public class DeleteData {

	Scanner sc= new Scanner(System.in); //scanner per l'acquisizione in input
	public static Controlli controllo = new Controlli(); //definizione dell'oggetto controllo di tipo Controlli
	
	
	/**
	 * Definizione delle costanti che stabiliscono
	 * la dimensione degli arraylist
	 */
	final int DIM_VENDITE = 20;
	final int DIM_IMPIEGATI = 10;
	final int DIM_FARMACI = 20;
	final int DIM_TIPO_FARMACI = 20;
	
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE);			//arraylist delle vendite
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI);	//arraylist degli impiegati
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI);			//arraylist dei farmaci
	private ArrayList<String> tipo_farmaci = new ArrayList <String>(DIM_TIPO_FARMACI);  //arraylist dei tipi di farmaci
	
	/**
	 * Costruttore 
	 * 
	 * @param vendite rappresenta l'arraylist che contiene la/le vendita/e da eliminare
	 * @param impiegati rappresenta l'arraylist che contiene l'/gl' impiegato/i da eliminare
	 * @param farmaci rappresenta l'arraylist che contiene il/i farmaco/i da eliminare
	 * 
	 */
	public DeleteData(ArrayList<Vendita> vendite, ArrayList<Impiegato> impiegati, ArrayList<Farmaco> farmaci, ArrayList <String> tipo_farmaci) {
		
		this.vendite = vendite;
		this.impiegati = impiegati;
		this.farmaci = farmaci;
		this.tipo_farmaci = tipo_farmaci;
		
	}
	
	
	/**
	 * Questo metodo permette di rimuovere una vendita
	 * dall'arraylist di tipo vendita
	 * 
	 * @param indice rappresenta l'indice dell'arraylist Vendita , e il relativo contenuto , che verrà rimosso
	 */
	public void removeVendita(int indice) {
		
		vendite.remove(indice);
		System.out.println("Vendita " + indice + " eliminata");
		
	}
	
	
	/**
	 * Questo metodo permette di rimuovere una vendita
	 * attraverso l'inserimento dell'indice a cui si trova la vendita che si vuole eliminare.
	 * Cosi , la vendita inserita dall'utente verrà eliminata
	 * sia dall'arraylist di tipo Vendita(Attraverso i public void removeVendita(int indice) )
	 * e sia dal file che contiene i dati delle vendite(vendite.txt)
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente , contiene tutti gli arraylist cosi da poterli usare in base all'occorenza
	 */
	public void richiestaIdEliminaVendita(InterfacciaUtente Eseguibile) {
		
		int eliminaIndex = 0;
		Eseguibile.printData.printAllVendite();
		System.out.println("Quale vendita vuoi eliminare?");
		eliminaIndex = controllo.checkIdVendita(vendite);
		
		removeVendita(eliminaIndex);
		
		Eseguibile.saveData.salvaVendite();
	}
	
	
	/**
	 * Questo metodo consente all'utente di eliminare un gruppo di vendite attraverso un filtro.
	 * Ovvero tutte le vendite con un determinato impiegato,farmaco,giorno dell'anno, mese dell'anno o di un anno verranno eliminate
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente , contiene tutti gli arraylist cosi da poterli usare in base all'occorenza
	 *
	 * 
	 * 
	 */
	public void filtroEliminaVendite(InterfacciaUtente Eseguibile) {
		
		int scelta = 0;
		String cognomeImpiegato = " ";
		String nomeImpiegato = " ";
		String nomeFarmaco = " ";
		String data = " ";
		boolean flagFiltro= true;
		
		System.out.println("Filtro di elimina gruppo vendite");
		System.out.println("1. Elimina per Impiegato");
		System.out.println("2. Elimina per Nome del Farmaco");
		System.out.println("3. Elimina per Giorno dell'anno");
		System.out.println("4. Elimina per Mese dell'anno");
		System.out.println("5. Elimina per Anno intero");
		
		do {
				scelta = controllo.checkIntegerInput("Scelta");
				
				switch (scelta) {
				
				case 1:
						cognomeImpiegato = controllo.checkAlphaInput("CognomeImpiegato");
						
						nomeImpiegato = controllo.checkAlphaInput("NomeImpiegato");
						
						removeVenditeByImpiegato(cognomeImpiegato,nomeImpiegato);
						
						break;
				
				case 2: 
						nomeFarmaco = controllo.checkAlphaInput("NomeFarmaco");
						
						removeVenditeByFarmaco(nomeFarmaco);
						
						break;
						
				case 3:
						data = controllo.checkData("Giorno");
						
						removeVenditeByGiorno(data);
						
						break;
				
				case 4: 
						data = controllo.checkData("Mese");
						
						removeVenditeByMese(data);
						
						break;
				
				case 5: 
						data = controllo.checkData("Anno");
				
						removeVenditeByAnno(data);
						
						break;
						
				default: System.out.println("Scelta non corretta, riprova");
						 flagFiltro = false;
						 
								
				
				}
			
		}while(flagFiltro == false);
		
		Eseguibile.saveData.salvaVendite();
		
		
	}
	
	
	
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite contenente un determinato impiegato
	 * 
	 * @param cognomeImpiegato rappresenta il cognome dell'impiegato inserito dall'utente 
	 * che verrà confrontato con quelli presenti nell'istanze del'arraylist di tipo Vendita
	 * 
	 * @param nomeImpiegato rappresenta il nome dell'impiegato inserito dall'utente 
	 * che verrà confrontato con quelli presenti nell'istanze del'arraylist di tipo Vendita
	 * 
	 */
	public void removeVenditeByImpiegato(String cognomeImpiegato , String nomeImpiegato) {
		
		boolean flagImpiegato = false;
		
		/*
		 * Questa è la condizione che permette di eliminare le vendite effettuate dall'impiegato 
		 * che ha il cognome e nome inserito dall'utente.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizione = vendite -> vendite.getImpiegato().getCognomeImpiegato().equals(cognomeImpiegato)
												   && vendite.getImpiegato().getNomeImpiegato().equals(nomeImpiegato); 
		  
      if( vendite.removeIf(condizione)) {
    	  System.out.println("Le vendite con l'impiegato " + cognomeImpiegato + " " + nomeImpiegato + " sono state cancellate");
    	  flagImpiegato = true;
      }
		
		
      if(flagImpiegato == false) {
			
    	  System.out.println("Nessuna vendita trovata con l'impiegato " + cognomeImpiegato + " " + nomeImpiegato);
      }
		
				
	}
	
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite contenente un determinato farmaco
	 * 
	 * @param nomeFarmaco rappresenta il nome del farmaco inserito dall'utente 
	 * che verrà confrontato con quelli presenti nell'istanze del'arraylist di tipo Vendita
	 * 
	 */
	public void removeVenditeByFarmaco(String nomeFarmaco) {
		
		boolean flagFarmaco = false;
		
		/*
		 * Questa è la condizione che permette di eliminare le vendite effettuate dall'impiegato 
		 * che ha venduto un determinato farmaco inserito dall'utente.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizione = vendite -> vendite.getFarmacoVenduto().getNomeFarmaco().equals(nomeFarmaco); 
		  
      if( vendite.removeIf(condizione)) {
    	  System.out.println("Le vendite con il farmaco " + nomeFarmaco + " sono state cancellate");
    	  flagFarmaco = true;
      }
		
		
      if(flagFarmaco == false) {
			
    	  System.out.println("Nessuna vendita trovata con il farmaco " + nomeFarmaco);
      }
		
				
	}
		
	
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite contenente un determinato giorno dell'anno
	 * 
	 * @param data rappresenta la data di un determinato giorno dell'anno inserito dall'utente 
	 * che verrà confrontato con quelle presenti nelle istanze dell'arraylist di tipo Vendita
	 * 
	 */
	public void removeVenditeByGiorno(String data) {
		
		boolean flagGiorno = false;
		
		/*
		 * Questa è la condizione che permette di eliminare le vendite effettuate dall'impiegato 
		 * in un determinato giorno dell'anno.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizione = vendite -> vendite.getDataVendita().equals(data); 
		  
      if( vendite.removeIf(condizione)) {
    	  System.out.println("Le vendite in data " + data + " sono state cancellate");
    	  flagGiorno = true;
      }
		
		
		if(flagGiorno == false) {
			
			System.out.println("Nessuna vendita trovata con il giorno " + data);
		}
		
				
	}
	
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite contenente un determinato mese dell'anno
	 * 
	 * @param data rappresenta la data di un determinato mese dell'anno inserito dall'utente 
	 * che verrà confrontato con quelli presenti nelle istanze dell'arraylist di tipo Vendita
	 * 
	 */
	public void removeVenditeByMese(String data) {
		
		boolean flagMese = false;
		
		/*
		 * Questa è la condizione che permette di eliminare le vendite effettuate dall'impiegato 
		 * in un determinato mese dell'anno.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizione = vendite -> vendite.getDataVendita().subSequence(3, 10).equals(data); 
		  
      if( vendite.removeIf(condizione)) {
    	  System.out.println("Le vendite in data " + data + " sono state cancellate");
    	  flagMese = true;
      }
		
		
		if(flagMese == false) {
			
			System.out.println("Nessuna vendita trovata con il mese " + data);
		}
		
				
	}
	
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite contenente un determinato anno
	 * 
	 * @param data rappresenta la data di un determinato anno inserito dall'utente 
	 * che verrà confrontato con quelli presenti nelle istanze dell'arraylist di tipo Vendita
	 * 
	 */
	public void removeVenditeByAnno(String data) {
		
		boolean flagAnno = false;
		
		/*
		 * Questa è la condizione che permette di eliminare le vendite effettuate dall'impiegato 
		 * in un determinato anno.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizione = vendite -> vendite.getDataVendita().subSequence(6, 10).equals(data); 
		  
      if( vendite.removeIf(condizione)) {
    	  System.out.println("Le vendite in data " + data + " sono state cancellate");
    	  flagAnno = true;
      }
		
		
		if(flagAnno == false) {
			
			System.out.println("Nessuna vendita trovata con l' anno " + data);
		}
		
				
	}
	
	
	/**
	 * Questo metodo permette di rimuovere un farmaco dall'arraylist di tipo Farmaco
	 * 
	 * @param indice rappresenta l'indice dell'arraylist farmaci , e il relativo contenuto , che verrà rimosso 
	 * 
	 */
	public void removeFarmaco(int indice) {
		
		farmaci.remove(indice);
		System.out.println("Farmaco " + indice + " eliminato");
		
	}
	
	
	/**
	 * Questo metodo permette di rimuovere un farmaco
	 * attraverso l'inserimento dell'indice a cui si trova il farmaco che si vuole eliminare.
	 * Cosi , il farmaco inserito dall'utente verrà eliminato
	 * sia dall'arraylist di tipo Farmaco (Attraverso il metodo public void removeFarmaco(int indice) )
	 * e sia dal file che contiene i dati dei farmaci(farmaci.txt)
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente , contiene tutti gli arraylist cosi da poterli usare in base all'occorenza
	 * 
	 */
	public void richiestaIdEliminaFarmaco(InterfacciaUtente Eseguibile) {
		
		int eliminaIndex = 0;
		Eseguibile.printData.printFarmaci();
		System.out.println("Quale farmaco vuoi eliminare?");
		eliminaIndex = controllo.checkIdFarmaco(farmaci);
		
		removeFarmaco(eliminaIndex);
		
		Eseguibile.saveData.salvaFarmaci();
		
	}
	
	/**
	 * Questo metodo permette di rimuovere un tipo di farmaco dall'arraylist di tipo String
	 * 
	 * @param indice rappresenta l'indice dell'arraylist tipo_farmaci , e il relativo contenuto , che verrà rimosso 
	 * 
	 */
	public void removeTipoFarmaco(int indice) {
		
		tipo_farmaci.remove(indice);
		System.out.println("Tipo Farmaco con indice" + indice + " eliminato");
		
	}
	/**
	 * Questo metodo permette di rimuovere un tipo di farmaco
	 * attraverso l'inserimento dell'indice a cui si trova il tipo di farmaco che si vuole eliminare.
	 * Cosi , il tipo di farmaco inserito dall'utente verrà eliminato
	 * sia dall'arraylist di tipo String (Attraverso il metodo public void removeTipoFarmaco(int indice) )
	 * e sia dal file che contiene i dati dei tipi di farmaci(tipo_farmaci.txt)
	 * 
	 * @param Eseguibile dato che è di tipo InterfacciaUtente , contiene tutti gli arraylist cosi da poterli usare in base all'occorenza
	 * 
	 */
	public void richiestaIdEliminaTipoFarmaco(InterfacciaUtente Eseguibile) {
		
		int eliminaIndex = 0;
		Eseguibile.printData.printTipoFarmaci();
		System.out.println("Quale tipo farmaco vuoi eliminare?");
		eliminaIndex = controllo.checkIdTipoFarmaco(tipo_farmaci);
		
		removeTipoFarmaco(eliminaIndex);
		
		Eseguibile.saveData.salvaTipoFarmaci();
		
	}
	
	/**
	 * Questo metodo permette di rimuovere un impiegato dall'arraylist di tipo Impiegato
	 * 
	 * @param indice rappresenta l'indice dell'arraylist impiegati , e il relativo contenuto , che verrà rimosso 
	 * 
	 */
	public void removeImpiegato(int indice) {
		
		impiegati.remove(indice);
		System.out.println("Impiegato " + indice + " eliminato");
		
	}
	
	
	/**
	 * Questo metodo permette di rimuovere un impiegato
	 * attraverso l'inserimento dell'indice a cui si trova l'impiegato che si vuole eliminare.
	 * Cosi , impiegato inserito dall'utente verrà eliminato
	 * sia dall'arraylist di tipo Impiegato(Attraverso il metodo public void removeImpiegato(int indice) )
	 * e sia dal file che contiene i dati degli impiegati(impiegati.txt)
	 * @param Eseguibile dato che è di tipo InterfacciaUtente , contiene tutti gli arraylist cosi da poterli usare in base all'occorenza
	 * 
	 */
	public void richiestaIdEliminaImpiegato(InterfacciaUtente Eseguibile) {
		
		int eliminaIndex = 0;
		Eseguibile.printData.printImpiegati();
		System.out.println("Quale impiegato vuoi eliminare?");
		eliminaIndex = controllo.checkIdImpiegato(impiegati);
		
		removeImpiegato(eliminaIndex);
		
		Eseguibile.saveData.salvaImpiegati();
		
	}
	
	
}
