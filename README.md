# Progetto finale corso BackEnd: Epicode Energy Services

Il progetto consiste nel realizzare un sistema CRM per un'azienda fornitrice di energia, denominata "EPIC ENERGY SERVICES", basato su REST Spring Boot e PostgreSQL.
Il sistema può gestire i clienti dell'azienda e le relative fatture, fornendo tutte le funzioni necessarie a gestire i suddetti elementi.
È previsto inoltre un sistema di autenticazione ed autorizzazione basato su token JWT che permetta a diversi utenti di registrarsi al sistema e di accedere al backend.

In aggiunta al sistema è stata realizzata una collection Postman per tutte le chiamate alle API del servizio ed implementati una serie di test tramite JUnit.

Insieme all'applicativo backend, è stato realizzato un piccolo frontend sfruttando Thymeleaf.
Il frontend permette l'accesso alle funzioni CRUD e di ricerca.



===========================================================================

La porta utilizzata è quella di default, ovvero la 8080.
Per adattare il progetto in base alla macchina e far sì che possa comunicare con il database presente in locale, modificare il file application.properties secondo necessità.

Il progetto gestisce il database secondo la modalità CREATE, nel caso si volesse utilizzare la modalità UPDATE basta avere l'accortezza di commentare i metodi presenti nella classe Popolator, nel package it.epicode.beservice, onde evitare il nuovo inserimento ad ogni restart del progetto di dati già presenti e problemi di duplicazioni.

Per evitare problemi di dipendenze ed id non facenti capo a nulla, all'inizio il miglior percorso da seguire secondo me è:
- salvare liste comuni e province (importati automaticamente dai file .csv)
- salvare gli indirizzi (senza le classi precedenti non sarebbe stato possibile)
- salvare gli stati fattura e i ruoli utente
- salvare i clienti a piacere (senza le classi precedenti non sarebbe stato possibile) 
- salvare le fatture a piacere (senza le classi precedenti non sarebbe stato possibile) 
- salvare un/gli user a piacere (senza non si avrebbe accesso all'interfaccia web non potendo loggarsi)

Tutte queste operazioni sono effettuate in automatico dai metodi presenti nella classe Popolator.java, in modo che l'applicazione sia pronta "out-of-the-box" per il lancio su server.

L'utente inserito in automatico ha come Username:"admin" e come Password:"password".
Gli utenti hanno email e password criptate per ragioni di privacy.

Nella cartella JSON per Postman sono presenti i file .json da importare in Postman per eseguire le chiamate HTTP al server, un file contiene la Collection di chiamate mentre l'altro contiene l'Environment dove sono le environment variables utilizzate nelle chiamate.
