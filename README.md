Per questo progetto ho scelto 4 casi d'uso:

Caso d'uso UC1: consulta offerta Attore primario: utente non registrato Scenario principale di successo: -L'utente consulta l'elenco delle tipologie di intervento -L'utente sceglie una tipologia di intervento e ne richiede i dettagli -Il sistema mostra descrizione,nome, costo della tipologia di intervento L'utente ripete i passi precedenti un numero indefinito di volte

Caso d'uso UC2: crea intervento  Attore primario: amministrazione Scenario principale di successo: -L'ammistrazione crea un intervento -L'amministrazione imposta una tipologia di intervento all'intervento creato -L'amministrazione associa un cliente all'intervento creato -Il sistema registra l'intervento (impostando automaticamente la data di prenotazione) Precondizioni: l'amministratore è identificato e autenticato

Caso d'uso UC3: consulta risultati proprio intervento Attore primario: cliente Scenario principale: -Il cliente consulta l'elenco dei propri interventi -Il sistema mostra al clienti l'elenco dei suoi intervento -Il cliente chiede il dettaglio di un intervento -Il sistema mostra il dettaglio dell'intervento -Il cliente ripete i passi precedenti finché necessario Precondizioni: il clienet è identificato e autenticato

Caso d'uso UC4: inserimento tipologia di intervento Attore primario: amministrazione Scenario principale: -L'amministratore inserisce una nuova tipologia di intervento specificandone i dettagli -Il sistema registra la tipologia di intervento -I punti precedenti vengono ripetuti fino a che necessario Precondizioni: l'amministratore è identificato e autenticato

Caso d'uso UC5: inserimento meccanico. Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’intervento. Scenario principale di successo: L'amministrazione seleziona la voce "inserisci meccanico" Il sistema mostra la form L'amministrazione inserisce nome, cognome e foto del meccanico Il sistema registra il meccanico e mostra la lista di tutti i meccanico
