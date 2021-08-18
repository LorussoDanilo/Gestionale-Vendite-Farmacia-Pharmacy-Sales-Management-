package methodsGui;

import java.math.BigDecimal;
import java.util.ArrayList;

import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Questa classe contiene i metodi che permettono la modifica dei dati all'interno delle tabelle delle varie finestre
 * @author LorussoDanilo
 *
 */
public class ChangeDataGui {
	
	/**
	 * Questo metodo consente di modificare i dati contenuti nella tabella all'interno del frame GestioneVendite della vendita selezionata
	 * @param vendite rappresenta la lista delle vendite
	 * @param indice rappresenta l'indice della vendita selezionata
	 * @param impiegatoSelezionato rappresenta l'impiegato selezionato dalla relativa lista
	 * @param nomeFarmacoSelezionato rappresenta il farmaco selezionato dalla relativa lista
	 * @param dataVenditaInserita rappresenta la nuova data della vendita selezionata inserita dall'utente
	 * @param quantitaInserita rappresenta la nuova quantita di scatole di farmaci della vendita selezionata inserita dall'utente
	 * @param prezzoTotale rappresenta il nuovo prezzo totale ottenuto dal prezzo del nuovo farmaco selezionato e dalla nuova quantita inserita
	 */
	public void modificaVenditaGui(ArrayList <Vendita> vendite,int indice, Impiegato impiegatoSelezionato,Farmaco nomeFarmacoSelezionato, String dataVenditaInserita, int quantitaInserita,BigDecimal prezzoTotale){
		
		vendite.get(indice).getImpiegato().setCognomeImpiegato(impiegatoSelezionato.getCognomeImpiegato());
		
		vendite.get(indice).getImpiegato().setNomeImpiegato(impiegatoSelezionato.getNomeImpiegato());
		
		vendite.get(indice).getFarmacoVenduto().setNomeFarmaco(nomeFarmacoSelezionato.getNomeFarmaco());
		
		vendite.get(indice).setDataVendita(dataVenditaInserita);
		
		vendite.get(indice).setDataVendita(dataVenditaInserita);
		
		vendite.get(indice).setQuantitaVenduta(quantitaInserita);
		
		prezzoTotale = vendite.get(indice).setPrezzoTotale();
		
		SaveDataGui.salvaVendite(vendite);

	}
	
	/**
	 * Questo metodo consente di modificare i dati all'interno della tabella contenuta del frame GestioneImpiegati dell'impiegato selezionato
	 * @param impiegati rappresenta la lista degli impiegati
	 * @param indice rappresenta l'indice dell' impiegato selezionato
	 * @param cognomeInserito rappresenta il nuovo cognome inserito dall'utente
	 * @param nomeInserito rappresenta il nuovo nome inserito dall'utente
	 * @param maxVenditeAnnualiInserito rappresenta il nuovo limite di vendite annuali inserito dall'utente
	 */
	public void modificaImpiegatoGui(ArrayList <Impiegato> impiegati, int indice, String cognomeInserito, String nomeInserito, int maxVenditeAnnualiInserito) {
		
		impiegati.get(indice).setCognomeImpiegato(cognomeInserito);
		impiegati.get(indice).setNomeImpiegato(nomeInserito);
		impiegati.get(indice).setMaxVenditeAnnuali(maxVenditeAnnualiInserito);
		
		SaveDataGui.salvaImpiegati(impiegati);
	}
	/**
	 * Questo metodo consente di modificare i dati all'interno della tabella contenuta nel frame GetioneFarmaci del farmaco selezionato
	 * @param farmaci rappresenta la lista dei farmaci
	 * @param indice rappresenta l'indice del farmaco selezionato
	 * @param nomeFarmacoInserito rappresenta il nuovo nome del farmaco inserito dall'utente
	 * @param dataConfezionamentoInserita rappresenta la nuova data di confezionamento inserita dall'utente
	 * @param dataScadenzaInserita rappresenta la nuova data di scadenza inserita dall'utente
	 * @param tipoSelezionato rappresenta il nuovo tipo di farmaco selezionato dall'utente
	 * @param prezzoInserito rappresenta il nuovo prezzo del farmaco inserito dall'utente
	 */
	public void modificaFarmacoGui(ArrayList <Farmaco> farmaci, int indice, String nomeFarmacoInserito, String dataConfezionamentoInserita, String dataScadenzaInserita, String tipoSelezionato,BigDecimal prezzoInserito) {
		
		farmaci.get(indice).setNomeFarmaco(nomeFarmacoInserito);
		farmaci.get(indice).setDataConfezionamento(dataConfezionamentoInserita);
		farmaci.get(indice).setDataScadenza(dataScadenzaInserita);
		farmaci.get(indice).setTipo(tipoSelezionato);
		farmaci.get(indice).setPrezzo(prezzoInserito);
		
		SaveDataGui.salvaFarmaci(farmaci);
		
	}
	
}
