/**
 * Questo package contiene tutti i metodi utilizzati all'interno del programma
 */
package methods;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import eccezioni.Controlli;
import farmaco.Farmaco;
import impiegato.Impiegato;
import interfacciaUtente.InterfacciaUtente;
import vendita.Vendita;

/**
 * Questa classe contiene i metodi che consento le modifiche ai dati che sono già presenti sui file: farmaci.txt,impiegati.txt,vendite.txt
 * Dunque, al fine di garantire il corretto funzionamento dei metodi è necessario che all'interno di questi file vi sia almeno un record.
 * @author Barbieri Giuseppe
 *
 *@version 1.0
 */
public class ChangeData {
	
	public static Controlli controllo = new Controlli(); //definizione dell'oggetto controllo per utilizzare i controlli sugli input
	/*
	 * Definizione delle costanti utilizzate per definire
	 * la dimensione degli arraylist
	 */
	final int DIM_VENDITE = 20;
	final int DIM_IMPIEGATI = 10;
	final int DIM_FARMACI = 20;

	
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE); //arraylist delle vendite
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI); //arraylist degli impiegati
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI); //arraylist dei farmaci

	
	/**
	 * Costruttore con 3 parametri
	 * 
	 * @param vendite rappresenta la vendita da importare su file
	 * @param impiegati rappresenta l' impiegato da importare su file
	 * @param farmaci rappresenta il farmaco da importare su file
	 */
	public ChangeData(ArrayList <Vendita> vendite, ArrayList <Impiegato> impiegati, ArrayList <Farmaco> farmaci) {
		this.vendite = vendite;
		this.impiegati = impiegati;
		this.farmaci = farmaci;
	}
	
	/**
	 * Questo metodo consente la modifica dei campi un impiegato scelto in input dall'utente
	 * all'utente viene chiesto l'indice dell'impiegato da voler modificare e se dovesse essere presente gli verrà
	 * chiesto di scegliere il campo da modificare
	 * @param Eseguibile viene utilizzato perchè contiene tutti gli arraylist per poterli usare in base all'occorrenza
	 */
	public void modificaImpiegato(InterfacciaUtente Eseguibile) {
		
		String cognomeImpiegato = "";
		String nomeImpiegato = "";
		int maxVenditeAnnuali = 0;
		int indexImpiegato = 0;
		int campo = 0; //indica il campo scelto dall'utente che intende modificare
		boolean flagModifica = true;
		
		System.out.println("Quale impiegato vuoi modificare?");
		Eseguibile.printData.printImpiegati();
		indexImpiegato = controllo.checkIdImpiegato(impiegati);
		
		System.out.println("Quale campo vuoi modificare?");
		System.out.println("1. Cognome Impiegato");
		System.out.println("2. Nome Impiegato");
		System.out.println("3. Max Vendite Annuali");
		
		do {
			campo = controllo.checkIntegerInput("Scelta"); //viene utilizzato lo stesso parametro di controllo come se fosse un menù di scelta
			
			switch(campo) {
					case 1: //caso in cui voglia modificare il cognome dell'impiegato
						cognomeImpiegato = controllo.checkAlphaInput("CognomeImpiegato");
						impiegati.get(indexImpiegato).setCognomeImpiegato(cognomeImpiegato);
					break;
					
					case 2: // caso in cui voglia modificare il nome dell'impiegato
						nomeImpiegato = controllo.checkAlphaInput("NomeImpiegato");
						impiegati.get(indexImpiegato).setNomeImpiegato(nomeImpiegato);
					break;
					
					case 3: //caso in cui voglia modificare il numero massimo di vendite annuali
						maxVenditeAnnuali = controllo.checkNumeroVenditeAnnuali();
						impiegati.get(indexImpiegato).setMaxVenditeAnnuali(maxVenditeAnnuali);
					break;
					
					default:
						System.out.println("Scelta non corretta! riprova");
						flagModifica = false;
						break;
			}
			
			
		}while(flagModifica == false);
		
		Eseguibile.saveData.salvaImpiegati(); //salvataggio su file dei nuovi dati
		System.out.println("Impiegato modificato correttamente");

	}
	
	/**
	 * Questo metodo consente la modifica dei singoli campi di un farmaco.
	 * All'utente viene chiesto l'inserimento dell'id del farmaco e se dovesse esistere
	 * gli viene chiesto il campo da modificare
	 * In seguito alla modifica, quest'ultime vengono salvate su file.
	 * @param Eseguibile  viene utilizzato perchè contiene tutti gli arraylist per poterli usare in base all'occorrenza
	 * @throws ParseException eccezione che viene lanciata nel caso in cui vengano riscontrati errori nelle date
	 */
	public void modificaFarmaco(InterfacciaUtente Eseguibile) throws ParseException {
		String	nomeFarmaco = " ";
		String dataConfezionamento = " ";
		String dataScadenza = " ";
		String tipo = " ";
		int indexFarmaco = 0;
		int campo = 0;
		boolean flagModifica = true;
		BigDecimal prezzo = new BigDecimal("0.00");
		boolean flagData = false;
		
		Eseguibile.printData.printFarmaci();

		System.out.println("Quale farmaco vuoi modificare?");
		indexFarmaco = controllo.checkIdFarmaco(farmaci); //acquisizione con controllo sull'id del farmaco
		
		System.out.println("Quale campo vuoi modificare?");
		System.out.println("1. Nome Farmaco");
		System.out.println("2. Data Confezionamento");
		System.out.println("3. Data Scadenza");
		System.out.println("4. Tipo");
		System.out.println("5. Prezzo");
		
		do {
			campo = controllo.checkIntegerInput("Scelta"); //viene utilizzato lo stesso parametro di controllo come se fosse un menù di scelta
			
			switch(campo) {
				
			case 1: //l'utente sceglie il nome del farmaco
					nomeFarmaco = controllo.checkAlphaInput("NomeFarmaco"); 
					farmaci.get(indexFarmaco).setNomeFarmaco(nomeFarmaco);
			break;
			
			case 2://l'utente sceglie la data di confezionamento
				do {
					dataConfezionamento = controllo.checkData("Confezionamento");
					dataScadenza = farmaci.get(indexFarmaco).getDataScadenza(); //acquisisco la data di scadenza per fare il controllo
					flagData = controllo.checkConfrontaData(dataConfezionamento, dataScadenza);
					
				}while(flagData == false);
				
				farmaci.get(indexFarmaco).setDataConfezionamento(dataConfezionamento);
				
			break;
			
			case 3://l'utente sceglie la data di scadenza
				do {
					dataScadenza = controllo.checkData("Scadenza");
					dataConfezionamento = farmaci.get(indexFarmaco).getDataConfezionamento(); //acquisisco la data di confezionamento per fare il controllo
					flagData = controllo.checkConfrontaData(dataConfezionamento, dataScadenza);
				}while(flagData==false);
				
					farmaci.get(indexFarmaco).setDataScadenza(dataScadenza);
				
				break;
				
				case 4://l'utente sceglie il tipo del farmaco
					tipo = controllo.checkAlphaInput("TipoFarmaco");
					farmaci.get(indexFarmaco).setTipo(tipo);
				break;
			
				case 5: //l'utente sceglie il prezzo del farmaco
					prezzo = controllo.checkDecimalInput("Prezzo");
					farmaci.get(indexFarmaco).setPrezzo(prezzo);
				break;
			
				default:
					System.out.println("Scelta non corretta,riprova");
				break;
			}
	
		}while(flagModifica == false);
		
		Eseguibile.saveData.salvaFarmaci(); //salvo i farmaci su file
		System.out.println("Farmaco modificato correttamente");
	}
	
	/**
	 * Quesot metodo consente la modifica dei campi di una vendita scelta dall'utente.
	 * dopo aver richiesto l'id della vendita, se questa dovesse esistere, gli verranno richiesti i campi da modificare
	 * E' stato scelto di inserire l'indice dell'impiegato del farmaco per semplificar il codice in quanto la richiesta del nome e cognome dell'impiegato
	 * e nome del farmaco con la realizzazione di appositi controlli sarebbe risultata più complessa ed anche perchè sono già presenti dei metodi di controllo
	 * per i relativi id.
	 * 
	 * @param Eseguibile viene utilizzato perchè contiene tutti gli arraylist per poterli usare in base all'occorrenza
	 * @throws ParseException eccezione che viene lanciata nel caso in cui si presentino errori nel contollo della data di scadenza
	 */
	public void modificaVendita(InterfacciaUtente Eseguibile) throws ParseException {
		int indexImpiegato = 0;
		int indexFarmaco = 0;
		String dataVendita = "";
		int  quantita = 0;
		int indexVendita = 0;
		int campo = 0;
		boolean flagModifica = true;
		boolean flagData = false;
		
		Eseguibile.printData.printAllVendite();
		System.out.println("Quale vendita vuoi modificare?");
		indexVendita = controllo.checkIdVendita(vendite);
		
		System.out.println("Quale campo vuoi modificare?");
		System.out.println("1. Impiegato");
		System.out.println("2. Farmaco");
		System.out.println("3. Data Vendita");
		System.out.println("4. Quantita' venduta");
		
		do {
			campo = controllo.checkIntegerInput("Scelta");
			
			switch(campo) {
			
			case 1: //l'utente sceglie di modificare l'impiegato
				Eseguibile.printData.printImpiegati();
				indexImpiegato = controllo.checkIdImpiegato(impiegati);
				vendite.get(indexVendita).setImpiegato(impiegati.get(indexImpiegato)); //imposta il nuovo impiegato selezionato
				
			break;
			
			case 2: //l'utente sceglie di modificare il nome del farmaco
				Eseguibile.printData.printFarmaci();
				indexFarmaco = controllo.checkIdFarmaco(farmaci);
				vendite.get(indexVendita).setFarmacoVenduto(farmaci.get(indexFarmaco)); //preso l'indice della vendita, imposta il nuovo farmaco venduto (solo il nome)
			break;
			
			case 3: //l'utente sceglie di modificare la data di vendita
				do {
					dataVendita = controllo.checkData("Vendita");
					vendite.get(indexVendita).setDataVendita(dataVendita);
					flagData = controllo.checkConfrontaData(dataVendita, farmaci.get(indexFarmaco).getDataScadenza()); //serve a confrontare la data di vendita con la data di scadenza del farmaco
					if(flagData == false) { //se il farmaco risulta scaduto
						System.out.println("Farmaco scaduto! \nLa data di vendita deve essere antecedente alla data di scadenza del farmaco!");
					}
				}while(flagData == false);
			break;
			
			case 4: //l'utente sceglie di modificare la quantita' venduta (il prezzo totale della vendita sarà aggiornato automaticamente)
				quantita = controllo.checkIntegerInput("Quantita");
				vendite.get(indexVendita).setQuantitaVenduta(quantita);
			break;
			
			default:
				System.out.println("Scelta non corretta,riprova");
				flagModifica = false;
			break;
			}
			
		}while(flagModifica == false);
			System.out.println("Modifiche apportate con successo!");
			Eseguibile.saveData.salvaVendite();
	}
	
}
	