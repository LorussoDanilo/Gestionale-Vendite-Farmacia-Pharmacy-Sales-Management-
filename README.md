
# CS_IT_2021_gruppo24
## GESTIONALE FARMACIA V1.0
Realizzato da: 

* Barbieri Giuseppe
* Cardinale Christian
* Lorusso Danilo

### DESCRIZIONE
Questo software consente la gestione di una azienda farmaceutica a 360Ã‚Â° in due modi: 
Il primo attraverso l'utilizzo della console ![Cattura](https://user-images.githubusercontent.com/76106261/123839950-0dd89780-d90e-11eb-99c0-2295905f6b23.PNG)


Il secondo,piÃƒÂ¹ pratico, attraverso una interfaccia grafica ![Cattura](https://user-images.githubusercontent.com/76106261/123836814-660d9a80-d90a-11eb-8a06-ae7e6fffc4ea.PNG)


Entrambe le tipologie di software offrono all'utente le seguenti funzionalitÃƒÂ :
* Gestione Impiegato
  - E' possibile inserire,modificare o eliminare un impiegato.
  - E' possibile specificare un limite di vendite annuale massimo per ciascun impiegato (da 1000 a 14000 vendite)
  Questo range Ã¨ stato deciso in base a una stima di vendite possibili per impiegato. Questa stima Ã¨ stata ricavata da alcune considerazioni:
  -Un impiegato puÃ² lavorare circa 350 giorni all'anno su 365(PoichÃ¨ la farmacia Ã¨ un servizio di prima necessitÃ )
  -Dato che viene imposto un limite di vendite giornaliere(40) il limite massimo di vendite annuali Ã¨ data da 40*350 = 14000
  -E' stato scelto 1000 come limite minimo poichÃ¨ si Ã¨ ipotizzato che un impiegato possa fare poche vendite giornaliere durante l'anno  
    
* Gestione Farmaci
  - E' possibile inserire un nuovo farmaco inserendo il nome,
    la data di confezionamento e di scadenza (che devono essere diverse e la prima non deve essere antecedente alla seconda),
    il tipo del farmaco(Al momeno i farmaci sono tutti Antinfiammatori ma il software consente l'inserimento di tutti i tipi di farmaci)
    ed il prezzo di vendita.
   - E' possibile modificare un farmaco giÃƒÂ  esistente
   
    **Versione Console** VerrÃƒÂ  richiesta l'indice del farmaco da modificare ![Cattura](https://user-images.githubusercontent.com/76106261/123837168-d0263f80-d90a-11eb-89c3-6ebd8e47b323.PNG)

    
    **Versione GUI** VerrÃƒÂ  mostrata la lista dei farmaci e bisognerÃƒÂ  selezionare quello che si desidera modificare
    Una volta selezionato il farmaco, verrÃƒÂ  richiesto l'inserimento dei singoli campi.
    
   - E' possibile rimuovere un farmaco.

*Gestione Vendite
  - E' possibile inserire una nuova vendita 
    *Versione Console* BisognerÃƒÂ  inserire l'indice dell'impiegato e del farmaco da vendere, qual'ora l'inserimento dovesse essere corretto,
    verrÃƒÂ  richiesto l'inserimento della data di vendita e la quantitÃƒÂ (non potrÃƒÂ  superare le 40 unitÃƒÂ  giornaliere per impiegato).
    nella venditÃƒÂ  sarÃƒÂ  presente una variabile "prezzo totale" il quale verrÃƒÂ  calcolato automaticamente dal software.
    
    *Versione GUI*BisognerÃ  cliccare il pulsante inserisci e ci saranno vari popup che chiederanno all'utente di selezionare l'impiegato che effettua la vendita,
    l'inserimento della data di vendita, di selezionare il farmaco venduto(tra quelli presenti nella lista dei farmaci) ed infine la quantitÃ  
    nella venditÃƒÂ  sarÃƒÂ  presente una variabile "prezzo totale" il quale verrÃƒÂ  calcolato automaticamente dal software.
    
    
   -E' possibile modificare una vendita giÃƒÂ  esistente
    *Versione Console* VerrÃƒÂ  richiesto l'indice della vendita da modificare,qual'ora quest'ultimo dovesse  essere corretto,
    verranno richiesti i singoli campi che formano la vendita (fatta eccezione per il prezzo totale).
    *Versione GUI*
    BisognerÃ  selezionare la vendita all'interno della tabella e cliccare il bottone modific, dopodichÃ¨ verrÃ  chiesto all'utente l'inserimento dei nuovi campi
    
   -E' possibile rimuovere la singola vendita oppure un gruppo di vendite
      *Versione Console* 
        Nel primo caso, viene chiesto l'indice della vendita da eliminare
        Nel secondo caso, ci saranno varie scelte: ![Cattura](https://user-images.githubusercontent.com/76106261/123837970-ba654a00-d90b-11eb-9445-debeb68ce25c.PNG)
       *Versione GUI*
       Saranno presenti due appositi pulsanti:
        -Rimuovi, che come dice il termine rimuoverÃ  la vendita selezionata nella tabella
        -Rimuovi per, che consente di eliminare un gruppo di vendite dove ques'ultima ha dei dati che combaciano con la scelta dell'utente![Cattura](https://user-images.githubusercontent.com/76106261/123838364-3790bf00-d90c-11eb-90e5-1001996e0b24.PNG)

        
 
### NOTE DI IMPLEMENTAZIONE
* Il formaato della data ÃƒÂ¨ gg/mm/aaaa
* i tipi farmaci sono trattati separatamente dai farmaci, ÃƒÂ¨ possibile inserire e rimuovere i tipi farmaci ed infine sono anch'essi resi persisenti
* I dati sono resi persistenti mediante l'utilizzo di file di testo.
* Non sono accettati whitespace nell'inserimento. Es: Antonio Di Francesco. Per poter inserire uno spazio tra due parole si usa questa sintassi: Antonio Di_Francesco
* Si Assume 
* Sono stati implementati i seguenti controlli (presenti nel package eccezioni- classe Controlli):
 - CheckAlphaInput,per controllare il corretto inserimento di una Stringa inserita dall'utente verificando che quest'ultima sia composta da soli caratteri alfabetici 
     questo controllo viene applicato per il Nome del farmaco,Nome e cognome impiegato e tipo del farmaco. 
 - CheckDecimalInput, verifica che il prezzo del farmaco e il prezzo totale della vendita corrisponda ad   
    un numero decimale. E' stato utilizzato il tipo BigDecimal in quanto il tipo double causava errori all'interno del programma.
 - CheckIntegerInput, verifica se l'input dell'utente corrisponde ad un intero questo controllo viene applicato per la scelta all'interno del menÃ¹ e 
    per l'inserimento della quantitÃ 
 - CheckData, verifica che la data inserita dall'utente corrisponda al formato adottato 
 - CheckConfrontaData, confronta la prima data con la seconda e verifica che la prima sia antecedente alla seconda 
 - CheckId (Farmaco , Impiegato, Vendita, Tipo Farmaco), verificano la correttezza dell'input (intero) e se l'id Ã¨ presente  nell'elenco 
 - CheckNumeroVendite (Annuali e Giornaliere), verificano se l'input relativo al valore del limite delle  vendite corrisponda alle costanti minime e massime dei limiti.
 - LimiteVendite (Annuali e Giornaliere), controllano successivamente  all'inserimento della quantitÃ  in un inserimento  di una vendita, se Ã¨ stato superato il limite annuale        e/o giornaliero.
* Sono stati implementati i seguenti controlli (presenti nel package eccezioniGui- classe ControlliGui): 
 -CheckConfrontaData, per confrontare due date e verificare che la prima sia antecedente alla seconda. Questo controllo viene applicato nei vari controlli sulle date ad esempio     data di confezionamento < data di scadenza
 - CheckNumeroVendite (Annuali e Giornaliere), verificano se l'input relativo al valore del limite delle  vendite corrisponda alle costanti minime e massime dei limiti.
 - LimiteVendite (Annuali e Giornaliere), controllano successivamente  all'inserimento della quantitÃ  in un inserimento  di una vendita, se Ã¨ stato superato il limite annuale        e/o giornaliero. A differenza della versione console, il metodo Ã¨ stato scisso in due tipi per la modifica e per l'inserimento, ma ciÃ² nonostante l'obiettivo Ã¨ il medesimo
 - CheckData(Giorno,Mese,Anno), verifica che la data inserita dall'utente corrisponda al formato adottato 
 -  - CheckIntegerInput, verifica se l'input dell'utente corrisponde ad un intero questo controllo viene applicato per la scelta all'interno del menÃ¹ e 
    per l'inserimento della quantitÃ 

