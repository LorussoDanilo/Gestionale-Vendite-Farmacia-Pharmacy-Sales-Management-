/**
 * Questo package contiene i metodi per la gestione dei dati all'interno della GUI
 */
package methodsGui;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Questa classe permette la visualizzazione dei dati all'interno della GUI
 * @author LorussoDanilo,Barbieri Giuseppe
 *
 */
public class PrintDataGui {

	
	/**
	 * Questo metodo permette la visualizzazione dei dati degli impiegati importando gli impiegati da file
	 * @param impiegati rappresenta l'arrayList che contiene gli impiegati
	 * @param tabellaImpiegati rappresenta la tabella a cui andrà applicato il modello definito all'interno di questo metodo
	 * @return il modello della tabella che andrà assegnato alla tabella selezionata
	 */
	public DefaultTableModel visualizzaDatiImpiegati(ArrayList <Impiegato> impiegati,DefaultTableModel tabellaImpiegati) {
		
		int indexImpiegato = 0;
		
		String colImpiegati[] = {"ID", "Cognome","Nome", "Limite Vendite Annuali"};
		DefaultTableModel tabellaImpiegatiModel = new DefaultTableModel(colImpiegati, 0);
		ImportDataGui.importaImpiegatiGui(impiegati);
		
		for(Impiegato i : impiegati) {
			String cognomeImpiegato = i.getCognomeImpiegato();
			String nomeImpiegato = i.getNomeImpiegato();
			int limiteVenditeAnnuali = i.getMaxVenditeAnnuali();
			
			
			Object[] datiImpiegato = {indexImpiegato,cognomeImpiegato,nomeImpiegato,limiteVenditeAnnuali};
			
			indexImpiegato++;
			tabellaImpiegatiModel.addRow(datiImpiegato);
			
		}
		
		return tabellaImpiegatiModel;
	}
	
	/**
	 * Questo metodo consente la visualizzazione dei dati relativi ai farmaci attraverso la finestra Gestione Farmaci della gui
	 * @param farmaci rappresenta la lista di farmaci che verrà visualizzata in formato tabellare
	 * @param farmaciTable rappresenta la tabella che contiene i dati
	 * @return La tabella contenente i valori dell'arrayList di tipo Farmaco
	 */
	public DefaultTableModel visualizzaDatiFarmaci(ArrayList <Farmaco> farmaci,DefaultTableModel farmaciTable) {
		int indexFarmaco = 0;
		ImportDataGui.importaFarmaciGui(farmaci);
		String colFarmaci[] = {"ID","Nome","Data Confezionamento","Data Scadenza","Tipo","Prezzo €"}; //definisco i nomi delle colonne
		DefaultTableModel tabellaFarmaci = new DefaultTableModel(colFarmaci,0);
		
		for(Farmaco f: farmaci) {
			
			String nomeFarmaco = f.getNomeFarmaco();
			String dataConfezionamento = f.getDataConfezionamento();
			String dataScadenza = f.getDataScadenza();
			String tipo = f.getTipo();
			BigDecimal prezzo = f.getPrezzo();
			
			Object[] datiFarmaci = {indexFarmaco,nomeFarmaco,dataConfezionamento,dataScadenza,tipo,prezzo}; //array che conterrà i valori da assegnare alla tabella
			//si utilizza un array in quanto DefaultTableModel opera con gli array e non con gli arraylist
			indexFarmaco++;
			tabellaFarmaci.addRow(datiFarmaci);
		}
		return tabellaFarmaci;
	}
	
	/**
	 * Questo metodo consente la visualizzazione dei dati relativi ai tipi dei farmaci attraverso la finestra Gestione Tipo Farmaci
	 * @param tipo_farmaco rappresenta la lista di tipo tipo_farmaci che verrà visualizzata in formato tabellare
	 * @param tipoFarmaciTable rappresenta la tabella che contiene i dati
	 * @return La tabella contenente i valori dell'arrayList di tipo tipo_farmaci
	 * 
	 */
	public DefaultTableModel visualizzaDatiTipoFarmaci(ArrayList <String> tipo_farmaci,DefaultTableModel tipoFarmaciTable) {
		int indexTipoFarmaco = 0;
		ImportDataGui.importaTipoFarmaciGui(tipo_farmaci);
		String colTipoFarmaci[] = {"ID","Tipo"}; //definisco le colonne della tabella tipo farmaci
		DefaultTableModel tabellaTipoFarmaci = new DefaultTableModel(colTipoFarmaci,0);
		for(String s: tipo_farmaci) {
			String tipoFarmaco = s;
			
			Object[] datiTipoFarmaco = {indexTipoFarmaco,tipoFarmaco};
			indexTipoFarmaco++;
			tabellaTipoFarmaci.addRow(datiTipoFarmaco);
			
		}
		
		return tabellaTipoFarmaci;
	}
	
	/**
	 * Questo metodo consente la visualizzazione dei dati relativi alle vendite attraverso la finestra Gestione Vendite della gui
	 * @param vendite rappresenta la lista di tipo Vendita che verrà visualizzata in formato tabellare
	 * @param venditeTable rappresenta la tabella che contiene i dati
	 * @return La tabella contenente i valori dell'arrayList di tipo Vendita
	 */
	public DefaultTableModel visualizzaDatiVendite(ArrayList <Vendita> vendite, DefaultTableModel venditeTable) {
		
		int indexVendita = 0;
		ImportDataGui.importaVenditeGui(vendite);
		String colVendite[] = {"Codice","Cognome","Nome","Farmaco","Data","Quantità","Totale (€)"};
		DefaultTableModel tabellaVendita = new DefaultTableModel(colVendite,0);
		for(Vendita v : vendite) {
			
			String cognomeImpiegato = v.getImpiegato().getCognomeImpiegato();
			String nomeImpiegato = v.getImpiegato().getNomeImpiegato();
			String nomeFarmaco = v.getFarmacoVenduto().getNomeFarmaco();
			String dataVendita = v.getDataVendita();
			int quantitaVenduta = v.getQuantitaVenduta();
			BigDecimal prezzoTotale = v.getPrezzoTotale();
			
			Object[] datiVendita = {indexVendita,cognomeImpiegato,nomeImpiegato,nomeFarmaco,dataVendita,quantitaVenduta,prezzoTotale}; //array che conterrà i valori da assegnare alla tabella
			//si utilizza un array in quanto DefaultTableModel opera con gli array e non con gli arraylist
			
			indexVendita++;
			tabellaVendita.addRow(datiVendita);
			
			
		}
		return tabellaVendita;
	}
	
	
	
}
