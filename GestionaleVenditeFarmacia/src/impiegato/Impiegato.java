/**
 * questo package contiene la classe che descrive l'oggetto impiegato
 */
package impiegato;

/**
 * Questa classe contiene gli attributi e i metodi per definire la struttura dell'oggetto impiegato e i suoi utilizzi
 * 
 * @author Cardinale Christian
 *
 */
public class Impiegato {

	private String cognomeImpiegato;
	private String nomeImpiegato;
	private int maxVenditeAnnuali;
	
	/**
	 * Costruttore con 3 parametri
	 * 
	 * @param cognomeImpiegato rappresenta il cognome dell'impiegato
	 * @param nomeImpiegato rappresenta il nome dell'impiegato
	 * @param maxVenditeAnnuali rappresenta il limite di vendite annuali di un determinato impiegato
	 */
	public Impiegato(String cognomeImpiegato, String nomeImpiegato , int maxVenditeAnnuali) {
		
		this.cognomeImpiegato = cognomeImpiegato;
		this.nomeImpiegato = nomeImpiegato;
		this.maxVenditeAnnuali = maxVenditeAnnuali;
		
	}

	/**
	 * Costruttore con 2 parametri utilizzando in importaVendite
	 * 
	 * @param cognomeImpiegato rappresenta il cognome dell'impiegato
	 * @param nomeImpiegato rappresenta il nome dell'impiegato
	 */
	public Impiegato(String cognomeImpiegato, String nomeImpiegato) {
		this.cognomeImpiegato = cognomeImpiegato;
		this.nomeImpiegato = nomeImpiegato;
	}


	/**
	 * Metodi get e set per gli attributi di un impiegato
	 * Sono utili per restituire i vari attributi e per settarli
	 * 
	 */
	public String getCognomeImpiegato() {
		return cognomeImpiegato;
	}

	public void setCognomeImpiegato(String cognomeImpiegato) {
		this.cognomeImpiegato = cognomeImpiegato;
	}

	public String getNomeImpiegato() {
		return nomeImpiegato;
	}

	public void setNomeImpiegato(String nomeImpiegato) {
		this.nomeImpiegato = nomeImpiegato;
	}

	public int getMaxVenditeAnnuali() {
		return maxVenditeAnnuali;
	}

	public void setMaxVenditeAnnuali(int maxVenditeAnnuali) {
		this.maxVenditeAnnuali = maxVenditeAnnuali;
	}
	
	/**
	 * redifinizione del metodo toString per stampare le informazioni principali dell'oggetto impiegato
	 */
	public String toString(int indice) {
		
		return "id = "+ indice + " cognome impiegato = " + cognomeImpiegato 
				+ ", nome impiegato = " + nomeImpiegato 
				+ ", limite vendite annuali = " + maxVenditeAnnuali;
	}
	
	/**
	 * ToString utilizzato in InserisciVendita gui per visualizzare soltanto il nome cognome e vendite massime dell'impiegato
	 */
	@Override
	public String toString() {
		return cognomeImpiegato + " " + nomeImpiegato + " " + maxVenditeAnnuali;
	}
}
