/**
 * Questo package contiene la classe che descrive l'oggetto farmaco
 */
package farmaco;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Questa classe contiene gli attributi e i metodi per definire la struttura dell'oggetto farmaco e i suoi utilizzi
 * 
 * @author Barbieri Giuseppe
 * @version 1.0
 */
public class Farmaco {
	
	
	private String	nomeFarmaco;
	private String dataConfezionamento;
	private String dataScadenza;
	private String tipo;
	private BigDecimal prezzo = new BigDecimal("0.00");
	//necessario per poter utilizzare il formato italiano per rappresentare i numeri decimali [10,0]
	DecimalFormat Euro_df = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALY);
	
	/**
	 * Costruttore con 5 parametri 
	 * 
	 * @param nomeFarmaco rappresenta il nome del farmaco
	 * @param dataConfezionamento rappresenta la data di confezionamento del farmaco
	 * @param dataScadenza rappresenta la data di scadenza del farmaco
	 * @param tipo rappresenta il tipo del farmaco (Antinfiammatorio,antidepressivo ecc..)
	 * @param prezzo rappresenta il prezzo di un farmaco
	 */
	public Farmaco(String nomeFarmaco,String dataConfezionamento,String dataScadenza,String tipo,BigDecimal prezzo) {
		
		this.nomeFarmaco = nomeFarmaco;
		this.dataConfezionamento= dataConfezionamento;
		this.dataScadenza = dataScadenza;
		this.tipo = tipo;
		this.prezzo = prezzo;
		
	}
	
	/**
	 * Costruttore con 2 parametri utilizzato in importaVendita
	 * @param nomeFarmaco rappresenta ilnome del farmaco
	 * @param prezzo rappresenta il prezzo di un farmaco
	 */
	public Farmaco(String nomeFarmaco, BigDecimal prezzo) {
		this.nomeFarmaco = nomeFarmaco;
		this.prezzo = prezzo;
	}

	/**
	 * Metodi get e set per gli attributi di un farmaco
	 * Sono utili per restituire i vari attributi e per settarli
	 * 
	 */
	public String getNomeFarmaco() {
		return nomeFarmaco;
	}

	public void setNomeFarmaco(String nomeFarmaco) {
		this.nomeFarmaco = nomeFarmaco;
	}

	public String getDataConfezionamento() {
		return dataConfezionamento;
	}

	public void setDataConfezionamento(String dataConfezionamento) {
		this.dataConfezionamento = dataConfezionamento;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	
	/**
	 * redifinizione del metodo toString per stampare le informazioni principali dell'oggetto farmaco
	 */
	public String toString(int indice) {
		return "id = "+ indice +" nome Farmaco = " + nomeFarmaco 
				+ ", data Confezionamento = " + dataConfezionamento
				+ ", data Scadenza = " + dataScadenza 
				+ ", tipo = " + tipo 
				+ ", prezzo = " + Euro_df.format(prezzo) + "€";
	}
	
	/**
	 * toString utilizzato all'interno della gui per visualizzare i dati del farmaco durante l'inserimento di una nuova vendita
	 * o nella modifica di una già esistente
	 */
	@Override
	public String toString() {
		return nomeFarmaco + " " + dataConfezionamento + " " + dataScadenza + " " + tipo +  " " + Euro_df.format(prezzo) + "€";
	}
	
	
}
