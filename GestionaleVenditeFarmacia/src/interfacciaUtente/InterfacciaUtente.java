/**
 * Questo package contiene la classe che eredita tutti i metodi da tutte le altre classi
 * e permette l'esecuzione del programma
 */

package interfacciaUtente;

import vendita.Vendita;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import eccezioni.Controlli;
import farmaco.Farmaco;
import impiegato.Impiegato;
import methods.*;

/**
 * Questa classe contiene il metodo main, ovvero quel metodo che
 * si serve delle altre classi ed i relativi metodi per poter mandare in esecuzione il programma
 * @author Barbieri Giuseppe
 * @author 
 *
 *@version 1.0
 */
public class InterfacciaUtente {
	public ImportData importData;
	public PrintData printData;
	public DeleteData deleteData;
	public ChangeData changeData;
	public SaveData saveData;
	public InputData inputData;
	
	/*
	 * Definiione delle costanti utilizzate per definire
	 * la dimensione degli arraylist
	 */
	final int DIM_VENDITE = 20;
	final int DIM_IMPIEGATI = 10;
	final int DIM_FARMACI = 20;
	final int DIM_TIPO_FARMACI = 20;
	
	public static Controlli controllo = new Controlli();
	public static Scanner sc = new Scanner(System.in);
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE); //arraylist delle vendite
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI); //arraylist degli impiegati
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI); //arraylist dei farmaci
	private ArrayList<String> tipo_farmaci = new ArrayList <String>(DIM_TIPO_FARMACI);
	
	/**
	 * Costruttore
	 * @param args
	 */
	public InterfacciaUtente() {
		this.inputData = new InputData(vendite, impiegati, farmaci, tipo_farmaci);
		this.changeData = new ChangeData(vendite, impiegati, farmaci);
		this.deleteData = new DeleteData(vendite,impiegati,farmaci,tipo_farmaci);
		this.saveData = new SaveData(vendite, impiegati, farmaci, tipo_farmaci);
		this.printData = new PrintData(vendite, impiegati, farmaci, tipo_farmaci);
		this.importData = new ImportData(vendite, impiegati, farmaci, tipo_farmaci);
	}
	
	/**
	 * il metodo main conterrà uno switch contenente le varie scelte (da 1-15) dell'utente
	 * to-do creare metodo di stampa apposito per le System.out.println
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		InterfacciaUtente Eseguibile = new InterfacciaUtente();
		/*
		 * Metodi per importare i file: impiegati.txt,farmaci.txt,vendite.txt,tipo_farmaci.txt
		 * i metodi di import vengono effettuati prima del menù di scelta affinchè gli arraylist contengano i valori già in partenza
		 * Altrimenti ci sarebbe una cattiva comunicazione tra file e arraylist (es. dati mancanti all'interno degli arraylist)
		 * 
		 */
        Eseguibile.importData.importaImpiegati(Eseguibile);
        Eseguibile.importData.importaFarmaci(Eseguibile);
        Eseguibile.importData.importaVendite(Eseguibile);
        Eseguibile.importData.importaTipoFarmaci(Eseguibile);

		int scelta = 0;
	
		do {
			
			Eseguibile.printData.MenuScelta(); //stampa del menù di scelta
			scelta= controllo.checkIntegerInput("Scelta"); //acquisizione della scelta
			
		
			switch(scelta) {
			
					case 1:
							Eseguibile.printData.printFiltroVendite();
							break;
					case 2:
							Eseguibile.inputData.inserisciVendita(Eseguibile);
							break;
					case 3:
							Eseguibile.changeData.modificaVendita(Eseguibile);		
							break;
					case 4:
							Eseguibile.deleteData.richiestaIdEliminaVendita(Eseguibile);
							break;
					case 5:
							Eseguibile.deleteData.filtroEliminaVendite(Eseguibile);
							break;
					case 6:
							Eseguibile.printData.printFarmaci();
							break;
					case 7:
							Eseguibile.inputData.inserisciFarmaco(Eseguibile);
							break;
					case 8:
							Eseguibile.changeData.modificaFarmaco(Eseguibile);
							break;
					case 9:
							Eseguibile.deleteData.richiestaIdEliminaFarmaco(Eseguibile);
							break;
					case 10:
							Eseguibile.inputData.inserisciTipoFarmaco(Eseguibile);
							break;
					case 11:
							Eseguibile.deleteData.richiestaIdEliminaTipoFarmaco(Eseguibile);
							break;
					case 12:
							Eseguibile.printData.printImpiegati();
							break;
					case 13:
							Eseguibile.inputData.inserisciImpiegato(Eseguibile);
							break;
					case 14:
							Eseguibile.changeData.modificaImpiegato(Eseguibile);
							break;
					case 15:
							Eseguibile.deleteData.richiestaIdEliminaImpiegato(Eseguibile);
							break;
					case 16:
							System.out.println("Sessione Terminata");
							break;
	
					default: System.out.println("Scelta non corretta,riprova!");
			}		
		}while(scelta!=16); //finchè la scelta è diversa da 16
		
			
	}

}
