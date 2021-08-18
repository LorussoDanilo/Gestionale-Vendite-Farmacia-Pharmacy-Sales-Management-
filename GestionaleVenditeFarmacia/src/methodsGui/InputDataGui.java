package methodsGui;

import java.math.BigDecimal;
import java.util.ArrayList;

import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Classe contenente i metodi necessari per l'inserimento dei farmaci,tipo farmaci,impiegati e vendite
 * Verranno utilizzati i metodi delle classi Import e Save per importare i nuovi dati e salvarli su file
 * @author Barbieri Giuseppe
 * @version 1.0
 */
public class InputDataGui {

	//definizione delle costanti dei vari arrayList
	final static int DIM_IMPIEGATI = 10;
	final static int DIM_FARMACI = 20;
	final static int DIM_TIPO_FARMACI = 10;
	final static int DIM_VENDITE = 20;
	
	//Definizione degli arrayList
	private ArrayList<Vendita> vendite = new ArrayList<Vendita>(DIM_VENDITE); //arraylist delle vendite
	private ArrayList<Impiegato> impiegati = new ArrayList<Impiegato>(DIM_IMPIEGATI); //arraylist degli impiegati
	private ArrayList<Farmaco> farmaci = new ArrayList<Farmaco>(DIM_FARMACI); //arraylist dei farmaci
	private ArrayList<String> tipo_farmaci = new ArrayList <String>(DIM_TIPO_FARMACI);
	 
	/**
	 * Metodo per aggiungere un nuovo impiegato
	 * @param cognomeImpiegato rappresenta il cognome del nuovo impiegato
	 * @param nomeImpigato rappresenta il nome del nuovo impiegato
	 * @param maxVenditeAnnuali rappresenta il limite di vendite annuali per quell'impiegato
	 */
	public void addImpiegato(String cognomeImpiegato,String nomeImpiegato,int maxVenditeAnnuali) {
		impiegati.add(new Impiegato(cognomeImpiegato,nomeImpiegato,maxVenditeAnnuali));
		ImportDataGui.importaImpiegatiGui(impiegati);
		SaveDataGui.salvaImpiegati(impiegati);
		
		System.out.println(impiegati); //stampa per verificare il corretto funzionamento
	}
	
	/**
	 * Metodo per Aggiungere un nuovo farmaco
	 * @param nomeFarmaco rappresenta il nuovo nome del farmaco da aggiungere
	 * @param dataConfezionamento rappresenta la nuova data di confezionamento del farmaco da aggiungere
	 * @param dataScadenza farmaco rappresenta la nuova data di scadenza da aggiungere
	 * @param tipo farmaco rappresenta il nuovo tipo farmaco da aggiungere
	 * @param prezzo rappresenta il prezzo del nuovo farmaco da aggiungere estratto dall'arrayList di tipo tipo_farmaci
	 */
	public void addFarmaco(String nomeFarmaco,String dataConfezionamento,String dataScadenza,String tipo,BigDecimal prezzo) {
		farmaci.add(new Farmaco(nomeFarmaco,dataConfezionamento,dataScadenza,tipo,prezzo));
		ImportDataGui.importaFarmaciGui(farmaci);
		SaveDataGui.salvaFarmaci(farmaci);
		System.out.println(farmaci);
		
		
	}
	
	/**
	 * Metodo per aggiungere un nuovo tipo farmaco
	 * @param tipo rappresenta il tipo di farmaco che sarà aggiunto
	 */
	public void addTipoFarmaco(String tipo) {
		tipo_farmaci.add(new String(tipo));
		ImportDataGui.importaTipoFarmaciGui(tipo_farmaci);
		SaveDataGui.salvaTipoFarmaci(tipo_farmaci);
		
		System.out.println(tipo_farmaci);
	}
	
	/**
	 * Metodo per aggiungere una nuova vendita
	 * @param impiegato rappresenta l'impiegato che effettua la vendita estratto dall'arrayList di tipo Impiegato
	 * @param farmacoVenduto rappresenta il farmaco che è venduto da un impiegato estratto dall'arrayList di tipo Farmaco
	 * @param dataVendita rappresenta la data della nuova vendita
	 * @param quantita rappresenta la quantita della nuova vendita
	 * @param prezzoTotale rappresenta il prezzzo totale della nuova vendita
	 */
	public void addVendita(Impiegato impiegato,Farmaco farmacoVenduto,String dataVendita,int quantita,BigDecimal prezzoTotale) {
		vendite.add(new Vendita(impiegato,farmacoVenduto,dataVendita,quantita,prezzoTotale));
		
		ImportDataGui.importaVenditeGui(vendite);
		SaveDataGui.salvaVendite(vendite);
		System.out.println(vendite); //stampa per verificare il corretto funzionamento
	}
	
}
