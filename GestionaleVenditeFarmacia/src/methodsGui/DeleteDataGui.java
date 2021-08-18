package methodsGui;

import java.util.ArrayList;
import java.util.function.Predicate;

import javax.swing.JOptionPane;

import farmaco.Farmaco;
import impiegato.Impiegato;
import vendita.Vendita;

/**
 * Questa classe contiene i metodi che consentono l'eliminazione dei dati dalle tabelle presenti nelle varie finestre
 * @author LorussoDanilo
 *
 */
public class DeleteDataGui {

	
	/**
	 * Questo metodo permette di rimuovere un impiegato dall'arrayList di tipo Impiegato
	 * @param impiegati rappresenta la lista degli impiegati
	 * @param indice rappresenta l'indice dell'impiegato da eliminare
	 */
	public void removeImpiegato(ArrayList <Impiegato> impiegati, int indice) {
		
		impiegati.remove(indice);
		//Salvo le modifiche su file
		SaveDataGui.salvaImpiegati(impiegati);
	}
	
	/**
	 * Questo metodo permette di rimuovere un farmaco dall'arrayList di tipo Farmaco
	 * @param farmaci rappresenta la lista dei farmaci
	 * @param indice rappresenta l'indice del farmaco da eliminare
	 */
	public void removeFarmaco(ArrayList <Farmaco> farmaci, int indice) {
		
		farmaci.remove(indice);
		//Salvo le modifiche su file
		SaveDataGui.salvaFarmaci(farmaci);
	}
	
	
	/**
	 * Questo metodo permette di rimuovere un farmaco dall'arrayList di tipo Farmaco
	 * @param tipo_farmaci rappresenta la lista dei tipo farmaci
	 * @param indice rappresenta l'indice del tipo di farmaco da eliminare
	 */
	public void removeTipoFarmaco(ArrayList <String> tipo_farmaci, int indice) {
		
		tipo_farmaci.remove(indice);
		//Salvo le modifiche su file
		SaveDataGui.salvaTipoFarmaci(tipo_farmaci);
	}
	
	/**
	 * Questo metodo permette di rimuovere una vendita dall'arrayList di tipo Vendita
	 * @param farmaci rappresenta la lista delle vendite
	 * @param indice rappresenta l'indice della vendita da eliminare
	 */
	public void removeVendita(ArrayList <Vendita> vendite, int indice) {
		
		vendite.remove(indice);
		//Salvo le modifiche su file
		SaveDataGui.salvaVendite(vendite);
	}
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite di un determinato impiegato.
	 * Inoltre è necessario separare il nome e il cognome perchè sono scelti attraverso un menù a tendina che li rende due oggetti differenti.
	 * @param vendite rappresenta la lista delle vendite
	 * @param impiegatoSelezionato rappresenta le vendite da eliminare effettuate dall'impiegato selezionato
	 */
	public void removeVenditeByImpiegato(ArrayList <Vendita> vendite, Impiegato impiegatoSelezionato) {
		boolean flagImpiegato = false;
		
		/*
		 * Queste sono le condizioni che permettono di eliminare le vendite effettuate dall'impiegato 
		 * che ha il cognome e nome scelto dall'utente.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizioneNome = venditeLambda -> venditeLambda.getImpiegato().getCognomeImpiegato().equals(impiegatoSelezionato.getCognomeImpiegato());
												   
		Predicate<Vendita> condizioneCognome = venditeLambda -> venditeLambda.getImpiegato().getNomeImpiegato().equals(impiegatoSelezionato.getNomeImpiegato()); 
		  
		if( vendite.removeIf(condizioneNome.and(condizioneCognome))) {
			JOptionPane.showMessageDialog(null,  "Vendite con l'impiegato" + impiegatoSelezionato.getCognomeImpiegato() + " " + impiegatoSelezionato.getNomeImpiegato() + " " + "Eliminate");
			flagImpiegato = true;
			SaveDataGui.salvaVendite(vendite);
		}
		
		
		if(flagImpiegato == false) {
			
			JOptionPane.showMessageDialog(null,  "Nessuna Vendita trovata con l'impiegato " + impiegatoSelezionato.getCognomeImpiegato() + " " + impiegatoSelezionato.getNomeImpiegato() );
		}
		
				
		}
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite di un determinato farmaco.
	 * @param vendite rappresenta la lista delle vendite
	 * @param farmacoSelezionato rappresenta le vendite da eliminare di un determinato farmaco
	 */
	public void removeVenditeByFarmaco(ArrayList <Vendita> vendite, Farmaco farmacoSelezionato) {
		boolean flagFarmaco = false;
		
		/*
		 * Queste sono le condizioni che permettono di eliminare le vendite di un farmaco
		 * selezionato dall'utente.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizioneFarmaco = venditeLambda -> venditeLambda.getFarmacoVenduto().getNomeFarmaco().equals(farmacoSelezionato.getNomeFarmaco());
												   		  
		if( vendite.removeIf(condizioneFarmaco)) {
			JOptionPane.showMessageDialog(null,  "Vendite con il farmaco " + farmacoSelezionato.getNomeFarmaco() + " "+ "Eliminate");
			flagFarmaco = true;
			SaveDataGui.salvaVendite(vendite);
		}
		
		
		if(flagFarmaco == false) {
			
			JOptionPane.showMessageDialog(null,  "Nessuna Vendita trovata con il farmaco " + farmacoSelezionato.getNomeFarmaco() );
		}
		
				
		}
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite di un determinato giorno dell'anno.
	 * @param vendite rappresenta la lista delle vendite
	 * @param data rappresenta la data delle vendite da eliminare.
	 */
	public void removeVenditeByGiorno(ArrayList <Vendita> vendite, String data) {
		boolean flagGiorno = false;
		
		/*
		 * Queste sono le condizioni che permettono di eliminare le vendite effettuate in un determinato mese dell'anno
		 * selezionato dall'utente.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizioneData = venditeLambda -> venditeLambda.getDataVendita().equals(data);
												   		  
		if( vendite.removeIf(condizioneData)) {
			JOptionPane.showMessageDialog(null,  "Vendite in data " + data + " "+ "Eliminate");
			flagGiorno = true;
			SaveDataGui.salvaVendite(vendite);
		}
		
		
		if(flagGiorno == false) {
			
			JOptionPane.showMessageDialog(null,  "Nessuna Vendita trovata in data " + data );
		}
		
				
		}
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite di un determinato mese dell'anno.
	 * @param vendite rappresenta la lista delle vendite
	 * @param data rappresenta la data delle vendite da eliminare.
	 */
	public void removeVenditeByMese(ArrayList <Vendita> vendite, String data) {
		boolean flagMese = false;
		
		/*
		 * Queste sono le condizioni che permettono di eliminare le vendite effettuate in un determinato mese dell'anno
		 * selezionato dall'utente.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizioneData = venditeLambda -> venditeLambda.getDataVendita().subSequence(3, 10).equals(data);
												   		  
		if( vendite.removeIf(condizioneData)) {
			JOptionPane.showMessageDialog(null,  "Vendite in data " + data + " "+ "Eliminate");
			flagMese = true;
			SaveDataGui.salvaVendite(vendite);
		}
		
		
		if(flagMese == false) {
			
			JOptionPane.showMessageDialog(null,  "Nessuna Vendita trovata in data " + data );
		}
		
				
		}
	
	/**
	 * Questo metodo consente di eliminare tutte le vendite di un determinato anno.
	 * @param vendite rappresenta la lista delle vendite
	 * @param data rappresenta la data delle vendite da eliminare.
	 */
	public void removeVenditeByAnno(ArrayList <Vendita> vendite, String data) {
		boolean flagAnno = false;
		
		/*
		 * Queste sono le condizioni che permettono di eliminare le vendite effettuate in un determinato mese dell'anno
		 * selezionato dall'utente.
		 * La funzione Lambda consente l'iterazione all'interno della vendita e la successiva rimozione degli elementi che rispettano la condizione imposta.
		 */
		Predicate<Vendita> condizioneData = venditeLambda -> venditeLambda.getDataVendita().subSequence(6, 10).equals(data);
												   		  
		if( vendite.removeIf(condizioneData)) {
			JOptionPane.showMessageDialog(null,  "Vendite in data " + data + " "+ "Eliminate");
			flagAnno = true;
			SaveDataGui.salvaVendite(vendite);
		}
		
		
		if(flagAnno == false) {
			
			JOptionPane.showMessageDialog(null,  "Nessuna Vendita trovata in data " + data );
		}
		
				
		}
}
