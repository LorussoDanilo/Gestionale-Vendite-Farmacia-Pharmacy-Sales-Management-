
# CS_IT_2021_gruppo24
## GESTIONALE FARMACIA V1.0
Realizzato da: 

* Barbieri Giuseppe
* Cardinale Christian
* Lorusso Danilo

### DESCRIZIONE
Questo software consente la gestione di una azienda farmaceutica a 360Â° in due modi: 
Il primo attraverso l'utilizzo della console ![Cattura](https://user-images.githubusercontent.com/76106261/123839950-0dd89780-d90e-11eb-99c0-2295905f6b23.PNG)


Il secondo,piÃ¹ pratico, attraverso una interfaccia grafica ![Cattura](https://user-images.githubusercontent.com/76106261/123836814-660d9a80-d90a-11eb-8a06-ae7e6fffc4ea.PNG)


Entrambe le tipologie di software offrono all'utente le seguenti funzionalitÃ :
* Gestione Impiegato
  - E' possibile inserire,modificare o eliminare un impiegato.
  - E' possibile specificare un limite di vendite annuale massimo per ciascun impiegato (da 1000 a 14000 vendite)
  Questo range è stato deciso in base a una stima di vendite possibili per impiegato. Questa stima è stata ricavata da alcune considerazioni:
  -Un impiegato può lavorare circa 350 giorni all'anno su 365(Poichè la farmacia è un servizio di prima necessità)
  -Dato che viene imposto un limite di vendite giornaliere(40) il limite massimo di vendite annuali è data da 40*350 = 14000
  -E' stato scelto 1000 come limite minimo poichè si è ipotizzato che un impiegato possa fare poche vendite giornaliere durante l'anno  
    
* Gestione Farmaci
  - E' possibile inserire un nuovo farmaco inserendo il nome,
    la data di confezionamento e di scadenza (che devono essere diverse e la prima non deve essere antecedente alla seconda),
    il tipo del farmaco(Al momeno i farmaci sono tutti Antinfiammatori ma il software consente l'inserimento di tutti i tipi di farmaci)
    ed il prezzo di vendita.
   - E' possibile modificare un farmaco giÃ  esistente
   
    **Versione Console** VerrÃ  richiesta l'indice del farmaco da modificare ![Cattura](https://user-images.githubusercontent.com/76106261/123837168-d0263f80-d90a-11eb-89c3-6ebd8e47b323.PNG)

    
    **Versione GUI** VerrÃ  mostrata la lista dei farmaci e bisognerÃ  selezionare quello che si desidera modificare
    Una volta selezionato il farmaco, verrÃ  richiesto l'inserimento dei singoli campi.
    
   - E' possibile rimuovere un farmaco.

*Gestione Vendite
  - E' possibile inserire una nuova vendita 
    *Versione Console* BisognerÃ  inserire l'indice dell'impiegato e del farmaco da vendere, qual'ora l'inserimento dovesse essere corretto,
    verrÃ  richiesto l'inserimento della data di vendita e la quantitÃ (non potrÃ  superare le 40 unitÃ  giornaliere per impiegato).
    nella venditÃ  sarÃ  presente una variabile "prezzo totale" il quale verrÃ  calcolato automaticamente dal software.
    
    *Versione GUI*Bisognerà cliccare il pulsante inserisci e ci saranno vari popup che chiederanno all'utente di selezionare l'impiegato che effettua la vendita,
    l'inserimento della data di vendita, di selezionare il farmaco venduto(tra quelli presenti nella lista dei farmaci) ed infine la quantità 
    nella venditÃ  sarÃ  presente una variabile "prezzo totale" il quale verrÃ  calcolato automaticamente dal software.
    
    
   -E' possibile modificare una vendita giÃ  esistente
    *Versione Console* VerrÃ  richiesto l'indice della vendita da modificare,qual'ora quest'ultimo dovesse  essere corretto,
    verranno richiesti i singoli campi che formano la vendita (fatta eccezione per il prezzo totale).
    *Versione GUI*
    Bisognerà selezionare la vendita all'interno della tabella e cliccare il bottone modific, dopodichè verrà chiesto all'utente l'inserimento dei nuovi campi
    
   -E' possibile rimuovere la singola vendita oppure un gruppo di vendite
      *Versione Console* 
        Nel primo caso, viene chiesto l'indice della vendita da eliminare
        Nel secondo caso, ci saranno varie scelte: ![Cattura](https://user-images.githubusercontent.com/76106261/123837970-ba654a00-d90b-11eb-9445-debeb68ce25c.PNG)
       *Versione GUI*
       Saranno presenti due appositi pulsanti:
        -Rimuovi, che come dice il termine rimuoverà la vendita selezionata nella tabella
        -Rimuovi per, che consente di eliminare un gruppo di vendite dove ques'ultima ha dei dati che combaciano con la scelta dell'utente![Cattura](https://user-images.githubusercontent.com/76106261/123838364-3790bf00-d90c-11eb-90e5-1001996e0b24.PNG)

        
 
### NOTE DI IMPLEMENTAZIONE
* Il formaato della data Ã¨ gg/mm/aaaa
* i tipi farmaci sono trattati separatamente dai farmaci, Ã¨ possibile inserire e rimuovere i tipi farmaci ed infine sono anch'essi resi persisenti
* I dati sono resi persistenti mediante l'utilizzo di file di testo.
* Non sono accettati whitespace nell'inserimento. Es: Antonio Di Francesco. Per poter inserire uno spazio tra due parole si usa questa sintassi: Antonio Di_Francesco
* Si Assume 
* Sono stati implementati i seguenti controlli (presenti nel package eccezioni- classe Controlli):
 - CheckAlphaInput,per controllare il corretto inserimento di una Stringa inserita dall'utente verificando che quest'ultima sia composta da soli caratteri alfabetici 
     questo controllo viene applicato per il Nome del farmaco,Nome e cognome impiegato e tipo del farmaco. 
 - CheckDecimalInput, verifica che il prezzo del farmaco e il prezzo totale della vendita corrisponda ad   
    un numero decimale. E' stato utilizzato il tipo BigDecimal in quanto il tipo double causava errori all'interno del programma.
 - CheckIntegerInput, verifica se l'input dell'utente corrisponde ad un intero questo controllo viene applicato per la scelta all'interno del menù e 
    per l'inserimento della quantità
 - CheckData, verifica che la data inserita dall'utente corrisponda al formato adottato 
 - CheckConfrontaData, confronta la prima data con la seconda e verifica che la prima sia antecedente alla seconda 
 - CheckId (Farmaco , Impiegato, Vendita, Tipo Farmaco), verificano la correttezza dell'input (intero) e se l'id è presente  nell'elenco 
 - CheckNumeroVendite (Annuali e Giornaliere), verificano se l'input relativo al valore del limite delle  vendite corrisponda alle costanti minime e massime dei limiti.
 - LimiteVendite (Annuali e Giornaliere), controllano successivamente  all'inserimento della quantità in un inserimento  di una vendita, se è stato superato il limite annuale        e/o giornaliero.
* Sono stati implementati i seguenti controlli (presenti nel package eccezioniGui- classe ControlliGui): 
 -CheckConfrontaData, per confrontare due date e verificare che la prima sia antecedente alla seconda. Questo controllo viene applicato nei vari controlli sulle date ad esempio     data di confezionamento < data di scadenza
 - CheckNumeroVendite (Annuali e Giornaliere), verificano se l'input relativo al valore del limite delle  vendite corrisponda alle costanti minime e massime dei limiti.
 - LimiteVendite (Annuali e Giornaliere), controllano successivamente  all'inserimento della quantità in un inserimento  di una vendita, se è stato superato il limite annuale        e/o giornaliero. A differenza della versione console, il metodo è stato scisso in due tipi per la modifica e per l'inserimento, ma ciò nonostante l'obiettivo è il medesimo
 - CheckData(Giorno,Mese,Anno), verifica che la data inserita dall'utente corrisponda al formato adottato 
 -  - CheckIntegerInput, verifica se l'input dell'utente corrisponde ad un intero questo controllo viene applicato per la scelta all'interno del menù e 
    per l'inserimento della quantità
