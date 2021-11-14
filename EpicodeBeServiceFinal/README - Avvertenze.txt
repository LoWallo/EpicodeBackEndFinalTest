Il progetto gestisce il database secondo la modalità CREATE, nel caso si volesse utilizzare la modalità UPDATE basta avere l'accortezza di commentare i metodi presenti nella classe Popolator, nel package it.epicode.beservice, onde evitare il nuovo inserimento ad ogni restart del progetto di dati già presenti e problemi di duplicazioni.

Per evitare problemi di dipendenze ed id non facenti capo a nulla, all'inizio il miglior percorso da seguire secondo me è:
- salvare liste comuni e province (importati automaticamente dai file .csv)
- salvare gli stati fattura e i ruoli utente (salvati in database ad ogni avvio dell'applicazione tramite metodi in Popolator.java)
- salvare gli indirizzi (nella collezione postman ci sono appunto 4 metodi save precompilati ma possono essere modificati a piacere)
- salvare i clienti a piacere (senza le classi precedenti non sarebbe stato possibile) 
- salvare le fatture a piacere (senza le classi precedenti non sarebbe stato possibile) 

- salvare gli user a piacere (può essere fatto in qualsiasi momento)