/**
 * Questo package contiene la classe che descrive l'oggetto vendita
 */
package vendita;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

import farmaco.Farmaco;
import impiegato.Impiegato;

/**
 * Questa classe contiene gli attributi e i metodi per definire la struttura dell'oggetto vendita e i suoi utilizzi
 * 
 * @author Lorusso Danilo
 *
 */
public class Vendita {
		private Impiegato impiegato;
		private Farmaco farmacoVenduto;
		private String dataVendita;
		private int  quantitaVenduta;
		private BigDecimal prezzoTotale = new BigDecimal("0.00");
		//necessario per poter utilizzare il formato italiano per rappresentare i numeri decimali [10,0]
		DecimalFormat Euro_df = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALY);

	/**
	 * Costruttore con 4 parametri
	 * 
	 * @param impiegato rappresenta l'impiegato
	 * @param farmacoVenduto rappresenta il farmaco venduto da un impiegato
	 * @param dataVendita rappresenta la data in cui avviene la vendita
	 * @param quantitaVenduta rappresenta la quantità venduta da un impiegato
	 */
	public Vendita(Impiegato impiegato, Farmaco farmacoVenduto, String dataVendita, int quantitaVenduta) {
		
			this.impiegato = impiegato;
			this.farmacoVenduto = farmacoVenduto;
			this.dataVendita = dataVendita;
			this.quantitaVenduta = quantitaVenduta;
			this.prezzoTotale = setPrezzoTotale(quantitaVenduta);
	}
	
	/**
	 * Costruttore vuoto
	 */
	public Vendita() {}

	/**
	 * Costruttore con 5 parametri utilizzato in importaVendite
	 * @param impiegato rappresenta l'impiegato
	 * @param farmacoVenduto rappresenta il farmaco venduto da un impiegato
	 * @param dataVendita rappresenta la data di vendita 
	 * @param quantita rappresenta la quantità di un farmaco venduto dall'impiegato
	 * @param prezzoTotale rappresenta il prezzo totale (prezzo unitario * quantita) della vendita
	 */
	public Vendita(Impiegato impiegato, Farmaco farmacoVenduto, String dataVendita, int quantitaVenduta,BigDecimal prezzoTotale) {
		this.impiegato = impiegato;
		this.farmacoVenduto = farmacoVenduto;
		this.dataVendita = dataVendita;
		this.quantitaVenduta = quantitaVenduta;
		this.prezzoTotale = prezzoTotale;
	}

	/**
	 * Metodi get e set per gli attributi di una vendita
	 * Sono utili per restituire i vari attributi e per settarli
	 * 
	 */
	public Impiegato getImpiegato() {
		return impiegato;
	}


	public void setImpiegato(Impiegato impiegato) {
		this.impiegato = impiegato;
	}


	public Farmaco getFarmacoVenduto() {
		return farmacoVenduto;
	}


	public void setFarmacoVenduto(Farmaco farmacoVenduto) {
		this.farmacoVenduto = farmacoVenduto;
	}


	public String getDataVendita() {
		return dataVendita;
	}


	public void setDataVendita(String dataVendita) {
		this.dataVendita = dataVendita;
	}


	public int getQuantitaVenduta() {
		return quantitaVenduta;
	}


	public void setQuantitaVenduta(int quantitaVenduta) {
		this.quantitaVenduta = quantitaVenduta;
		setPrezzoTotale(quantitaVenduta);
	}


	public BigDecimal getPrezzoTotale() {
		return prezzoTotale;
	}

	/**
	 * imposta il calcolo del prezzo totale della vendita
	 * 
	 * @return il prezzo totale della vendita
	 */
	public BigDecimal setPrezzoTotale() {
		this.prezzoTotale = farmacoVenduto.getPrezzo().multiply(new BigDecimal(this.quantitaVenduta));
		return prezzoTotale;
	}
	
	/**
	 * imposta il calcolo del prezzo totale della vendita
	 * 
	 * @param quantitaInt indica una determinata quantità da moltiplicare con il prezzo
	 * @return il prezzo totale della vendita
	 */
	public BigDecimal setPrezzoTotale(int quantitaInt) {
		this.prezzoTotale = farmacoVenduto.getPrezzo().multiply(new BigDecimal(quantitaInt));
		return prezzoTotale;
	}
	
	
	/**
	 * redefinizione del metodo toString per stampare le informazioni principali dell'oggetto vendita
	 */
	public String toString(int indice) {
		return "id = "+ indice +" impiegato = " + impiegato.getCognomeImpiegato() 
				+ " " + impiegato.getNomeImpiegato() 
				+ ", farmaco venduto = " + farmacoVenduto.getNomeFarmaco()
				+ ", data vendita = " + dataVendita
				+ ", quantita venduta = " + quantitaVenduta
				+ ", prezzo totale = " + Euro_df.format(prezzoTotale) + "";
	}


}