Diagramma delle classi UML
[UML.pdf](https://github.com/user-attachments/files/17347382/UML.pdf)


Struttura generale del progetto 
(con utilizzo di MVC e ObserverObservable)

Model
nel package model metto le classi che rappresentano le entità dell’applicazione 
e la logica che riguarda la manipolazione dei dati e dello stato di queste entità
inoltre definisco i metodi che vanno ad aggiornare gli osservatori (view) 

- ModelManager: mi serve per gestire il model, estende Observable perchè ad ogni suo cambiamento voglio notificare la view, che è osservatrice del model, ed è singleton perchè mi serve una sola istanza della classe che gestisce il model
ha accesso allo stato della partita, dell’utente e dei giocatori

- Partita: mi serve per gestire lo stato di una partita, è singleton perchè gioco una partita alla volta, quindi non ho bisogno di più istanze contemporaneamente.
al suo interno quando è necessario vengono inizializzati il mazzo di carte usato durante la partita
e la lista dei giocatori che partecipano, tiene traccia del turno e della fase in cui è la partita
(pre scommessa/post scommessa) 
e offre metodi per capire quando è finita, per aggiornare lo stato dei giocatori e per “resettarsi” ed iniziare una nuova partita
mi serve soprattutto per gestire il flusso della partita

- package carte: le classi di questo package hanno a che fare con le carte,
 esso contiene le seguenti classi:
  - Carta: gestisce i dati relativi a una carta da gioco e offre metodi per accedere al suo valore, simbolo, seme e immagine, ha come enum interne Seme e Simbolo

  - Mazzo: rappresenta un mazzo di carte, offre metodi per mixare e distribuire le carte.

  - Mano: gestisce lo stato di una mano, quindi le carte, il conteggio e le condizioni di vittoria o sconfitta
  inoltre permette di determinare se la mano può essere divisa in due mani separate,
  ho introdotto questa classe perché mi era utile per gestire la situazione di split e quindi la gestione di più mani in un singolo turno,
  durante lo sviluppo si è rivelata utile anche per suddividere meglio le responsabilità
  infatti tutto quello che è dentro questa classe all’inizio era dentro alla classe del giocatore che lavorava direttamente con una lista di carte

- package giocatori: le classi di questo package gestiscono vari tipi di giocatore,
 esso contiene le seguenti classi:
  - Giocatore: gestisce lo stato di un giocatore e ne definisce un comportamento di base.
  Ho creato questa classe perché ogni giocatore al suo interno ha le informazioni necessarie per la propria partita (mani, punteggio, ecc…), è una classe astratta,  perché rappresenta un'entità molto generica e    serve come base per rappresentare qualsiasi tipo di giocatore nel gioco, che sia un utente o un bot.
  Ogni sottoclasse implementa in modo diverso la giocata e l’aggiornamento dello stato delle mani a fine partita,
  mentre la logica comune (hit, stay e passaggio alla mano successiva) è definita qui.

  - GiocatoreUtente: rappresenta l’utente selezionato,
   è singleton perché c’è un solo utente giocante alla volta
  (se in futuro l’applicazione dovesse diventare “multigiocatore” il modo in cui è stata sviluppata la classe Giocatore mi aiuterebbe a passare a una classe non singleton senza particolari modifiche)

  L'utente gioca tramite l’interazione con l'interfaccia grafica, a differenza dei bot, che agiscono automaticamente, il metodo gioca() quindi non esegue azioni in attesa di input.
  quando la partita finisce confronta le mani con quella del dealer e oltre ad aggiornare lo stato
  modifica i dati dell'utente (chips, mani giocate, vinte, perse e pareggiate).

  - GiocatoreBot: rappresenta un giocatore non controllato da un umano che gioca in modo automatico. Il bot gioca continuando a chiedere carte fino a che non raggiunge o supera il conteggio di 17 e poi termina     la mano,
  quando la partita finisce confronta la mano con quella del dealer e ne aggiorna lo stato
  
  - GiocatoreDealer: rappresenta il dealer della partita, è singleton perché c’è un solo dealer,
  estende GiocatoreBot perché non è comandato da un umano, ma gioca in modo automatico.
  Forse ne avrei potuto fare a meno, ma effettivamente dichiarare il dealer come un'entità unica, lo rende facilmente accessibile, e  definisce meglio la struttura dell’applicazione
  

- classe FileUtils: questa è una classe di utilità contenente i metodi statici utili alla manipolazione dei file


note:
il model è riutilizzabile al 100%, quindi non c’è nessun import da altri package del progetto





Controller
nel package controller gestisco la comunicazione tra model e view, metto la logica di avvio del menu 
e quella che gestisce il flusso di gioco (gameloop) dell’applicazione.
Nel controller invoco i metodi del model che, modificando i dati o lo stato, aggiornano gli osservatori del model  (la view)

- JBlackJack: contiene il main,
la sua responsabilità è quella di avviare l’applicazione,
all’inizio istanzia il controller e chiede a esso di inizializzare il menu

- Controller: rappresenta il controller, è responsabile dell’inizializzazione del menu e della logica di gioco, è singleton perché mi serve una sola istanza.
nel costruttore istanzia il model e la view,
poi aggiunge la view agli osservatori del model,
il suo metodo initMenu() inizializza il menu
con una logica che permette di capire se è la prima volta che l’applicazione viene avviata
(vedi il paragrafo  “Gestione utente” sezione Avvio)
inoltre contiene il metodo gameloop, responsabile della gestione del flusso di gioco di una partita

- package actionListeners: le classi di questo package definiscono quello che accade in risposta a determinati eventi, vengono richiamate nella view quando le “aggancio” ai componenti che voglio, esso contiene le seguenti classi:

  - package menu: contiene tutte le classi relative alle azioni eseguibili sul menu
    - SetGiocatoriActionListener: cambia il numero di giocatori che partecipano alla partita 
    - PlayActionListener: attiva il pannello della partita e chiede al model di inizializzarla
    - package utente: contiene tutte le classi relative alle azioni eseguibili sul menu relative all’utente
      - CreaUtenteActionListener: apre una finestrella dove inserire l’username e dopo determinati controlli, se possibile, chiede al model di creare l’utente e imposta l’utente appena creato come attivo
      - EliminaUtenteActionListener: controlla che ci sia più di un utente e nel caso chiede al model di eliminare l’utente attivo, dopodichè attiva il primo utente creato in ordine cronologico
      - SetUtenteActionListener: attiva l’utente selezionato

  - package partita: contiene tutte le classi relative alle azioni eseguibili in partita
    - BackActionListener: attiva il pannello del menu e resetta la partita
    - package azioni: contiene tutte le classi relative alle azioni di gioco eseguibili in partita 
      - HitActionListener: chiede al giocatore di cui è il turno di fare “hit” (richiedere una carta) e chiama il gameloop
      - StayActionListener: chiede al giocatore di cui è il turno di fare “stay” (passare alla mano successiva) e chiama il gameloop
      - SplitActionListener: verifica che ci siano abbastanza chips e se ci sono chiede al giocatore corrente di fare “split” (dividere la mano in due mani) e chiama il gameloop
      - ScommettiActionListener: verifica che la scommessa sia valida 
      (numero non negativo, minore delle chips in possesso del giocatore)
      e chiede al giocatore corrente di scommettere, poi distribuisce le carte
      importante: senza richiamare il gameloop!!
      in questo modo si resta in attesa dell’input del giocatore





View
nel package view gestisco la logica di presentazione, l’interazione con l’utente 
e imposto quale ActionListener del controller viene usato al click dei miei componenti,
inoltre definisco il metodo “update” per ridisegnare, su richiesta del model,  la view e aggiornare i dati mostrati 

- View
implementa Observer in modo da poter essere aggiunta agli osservatori del model, e quindi per ridisegnarsi su richiesta di esso, è singleton perchè la view è un JFrame 
e in un'applicazione del genere, almeno per il momento, non è necessaria più di una finestra.
Sovrascrive il metodo update per aggiornarsi, quando questo viene chiamato chiede ai pannelli di aggiornarsi

- package menuPanel: package dedicato al panel del menu, contiene le seguenti classi:
  - MenuPanel: è composto da una topbar e da una bottombar, la prima contiene i pannelli relativi alla gestione dell’utente e alla scelta del numero dei giocatori che partecipano alla partita,
  la seconda contiene un pannello che mostra i dati dell’utente e un altro pannello contenente i bottoni gioca e esci
  quando si aggiorna chiede ai suoi componenti di aggiornarsi
  - package topbar
    - TopBarMenuPanel
    - GestioneUtentePanel
    - NumeroGiocatoriPanel
  - package bottombar
  	- BottomBarMenuPanel
    - GiocaEsciPanel

- package gamePanel: package dedicato al panel della partita, contiene le seguenti classi:
  - GamePanel: è composto da una topbar, da un bodyPanel e una bottombar
  nella topbar c’è il pannello che mostra i dati dell’utente e il pulsante per tornare al menu,
  nel body c’è il pannello del dealer e nella bottombar c’è il pannello dedicato alle azioni di gioco e il pannello che contiene i pannelli dei giocatori
  
  l’aggiornamento gestisce la comparsa/scomparsa di determinati bottoni: 
  - se sono in fase di distribuzione delle carte oppure non è il turno dell’utente i bottoni scompaiono del tutto
  - se sono in fase di pre scommessa appare il pannello della scommessa e scompare quello che contiene le azioni di gioco hit stay e split, altrimenti il contrario
  - se l’utente può splittare compare il pulsante di split
  	inoltre aggiorna le carte del dealer e dei giocatori
  
  - package topbar
    - TopBarGamePanel
  - package bodyPanel
    - BodyPanel
  - package bottombar
    - BottomBarGamePanel - GiocatoriPanel
  	- package actionsPanel
  		- ActionsPanel - PreBetPanel - PostBetPanel
  	- package giocatorePanel
      - GiocatorePanel - ManiPanel - ManoPanel - CartePanel
    
- classe DatiUtentePanel: pannello utilizzato per mostrare i dati dell’utente attivo

- classe MyJLabel: una classe per costruire label personalizzate più facilmente, utilizza il builder pattern proprio per essere più flessibile nella costruzione

- classe AudioManager: una classe per gestire l’audio dell’applicazione, è singleton

inoltre utilizzo una cartella “resources” con tre sottocartelle per immagini, audio e dati

note:
sono consapevole della possibilità di generalizzare di più determinati componenti, ma ho dato maggiore priorità al corretto sviluppo del codice più riutilizzabile (model, controller)
in un futuro refactoring potrei eliminare molto codice dalla view o addirittura svilupparne un altra






Utilizzo di Java Swing

struttura
principalmente ho una classe View che estende JFrame 
con due JPanel come campi (uno per il menu e l’altro per la partita)
che vengono mostrati o nascosti quando serve sfruttando il CardLayout,
inoltre utilizzo vari layout di swing per definire il layout dei pannelli e poi dei vari componenti

componenti personalizzati
per avere dei componenti personalizzati ho utilizzato principalmente delle classi che estendono JPanel

bottoni
utilizzo i JButton,
alla costruzione del componente che li contiene, gli viene agganciato l’actionListener appropriato in base all’evento che deve accadere al click, poi vengono posizionati nel Layout 

campi di testo
utilizzo i JtextField,
oppure utilizzo il metodo showInputDialog della classe JOptionPane per far comparire un popUp e successivamente catturare l’input dell’utente

select
utilizzo le JComboBox,
principalmente nel menu per selezionare l’utente

gestione errori
utilizzo il metodo showMessageDialog della classe JOptionPane
per far apparire un popUp di errore che informa l’utente

label personalizzate
per utilizzare delle label personalizzate ho creato questa classe implementando il builder pattern per avere una costruzione di label complesse (diversi font, font-size, colori, ecc...) più semplice

timer
ho usato il  timer di swing per ritardare l’esecuzione del programma nella distribuzione di carte e  nel gameloop
Struttura Menu

- scelta utente
posso selezionare l’utente tramite una select che mostra tutti gli utenti nel file “utenti.txt” quando seleziono un utente aggiorno la view

- creazione utente
posso creare un nuovo utente tramite un bottone accanto alla select

- dati utente
mostro i dati dell'utente selezionato 

- modifica dati utente
 do la possibilità di modificare username. immagine di profile (avatar)

- eliminazione utente
posso eliminare l’utente dopo averlo selezionato dalla select

- scelta giocatori
nel menu scelgo quanti giocatori ci sono in partita
minimo 1 (me stesso) massimo 3 (me stesso + 2 bot)

- avvio partita
bottone per iniziare la partita, fa le inizializzazioni necessarie, cambia pannello e avvia la partita

- esci
bottone per uscire, ferma l’esecuzione dell’applicazione





Gestione utente

In generale
quando aggiungo un utente creo un file legato a lui
in base all’utente selezionato vengono caricati/scritti i dati dal/nel file legato a quell’utente


Azioni utente
- creazione utente
quando clicco sul pulsante “Crea nuovo utente” nel menu, 
appare una finestrella con un campo di testo dove inserire l’username,
se l’input è valido (non vuoto, minore di 20 caratteri, nome non inserito precedentemente)
viene creato un nuovo utente attraverso il metodo creaUtente del GiocatoreUtente 
(chiamato attraverso il model)
il metodo creaUtente prima salva l’username nel file “utenti.txt”
poi crea un nuovo file  “username_dati.txt”
riempiendolo con i dati del nuovo utente
dopodichè come attivo l’utente appena creato

- selezione utente
carico gli utenti in una “comboBox” (per ogni riga nel file “utenti.txt” ho un option della comboBox),
quando seleziono un utente, cliccando su una delle options 
imposto l’utente selezionato tramite il metodo setUtente del GiocatoreUtente 
(chiamato attraverso il model)
il metodo setDati utilizza il file dell’utente selezionato
per cambiare i campi del GiocatoreUtente  
inoltre scrive il nome dell'utente selezionato nel file “ultimo_utente.txt”
in modo da ricordare l’ultimo utente selezionato, per impostarlo nel successivo avvio dell’applicazione

- eliminazione utente
quando clicco sul pulsante “Elimina utente” nel menu, elimino l’utente attivo
attraverso il metodo eliminaUtente del GiocatoreUtente
(chiamato attraverso il model)
e rendo attivo l’utente che è stato creato per primo nella lista di utenti,
sempre tramite il metodo setUtente 
il metodo eliminaUtente elimina il nome dell’utente selezionato dal file “utenti.txt”
e il file “utenteSelezionato_dati.txt”,


Dati utente
quando viene selezionato un utente, oppure quando cambiano delle statistiche dell’utente in gioco 
(mani giocate, vinte, perse, ecc..) la view riceve l’update dal model ridisegnando il panel del menu e della partita


Avvio
all’avvio dell’applicazione, se il file “utenti.txt” è vuoto, chiedo di impostare un username, 
inserisco così il primo utente nel file “utenti.txt” e lo setto nel model
altrimenti setto l’ultimo utente selezionato dal file “ultimo_utente.txt” 





Gestione partita
In generale
posso avere da 1 a 3 giocatori, il primo di questi è sempre l’utente
e l’ultimo il dealer (quindi i giocatori totali possono essere 4)
ogni partita è composta da tanti turni quanti sono i giocatori,
ogni turno finisce quando il giocatore fa blackjack, sballa o chiede di stare per ogni sua mano
quando l'ultimo turno è finito il dealer gioca e scopre se i giocatori hanno vinto o perso,
poi comincia una nuova partita, quindi vengono ridistribuite le carte


Primo Avvio 
prima di avviare la partita è possibile selezionare il numero di giocatori tramite una comboBox.
Al click del pulsante “gioca”,  graficamente faccio visualizzare il pannello della partita al posto di quello del menu e tramite il metodo del model initPartita() chiedo alla partita di fare le inizializzazioni di stato usando i metodi initMazzo() e initGiocatori() quest’ultimo riempie la lista di giocatori della partita in base al numero di giocatori scelti
adesso l’utente ha la possibilità di stabilire la scommessa
dopo la scommessa inizia la distribuzione delle carte iniziali


Svolgimento partita
il flusso di una partita (avvii successivi al primo, scorrimento delle mani, dei turni e conclusione)
è gestito dal metodo gameloop() del Controller

il gameloop() prima chiama il metodo gioca() del giocatore corrente
poi deve decidere se passare alla mano o al turno successivo (se la mano è l'ultima passa al turno successivo) 
ma lo deve fare in modo diverso a seconda del tipo di giocatore:

- bot:
se il giocatore corrente è un bot, controlla che la mano sia terminata, nel caso passa a quella successiva 
poi controlla che la partita sia finita e nel caso termina il gameloop e chiama la logica di fine partita, altrimenti richiama il gameloop
(la partita finisce solo quando il dealer finisce di giocare)

- utente:
se il giocatore corrente è l'utente e la mano è terminata, passa alla mano successiva e richiama il gameloop

questa differenza è data dal fatto che la mano dell'utente raggiunge lo stato di terminata attraverso l'input dell'utente
mentre la mano del bot deve raggiungere lo stato di terminata in modo automatico e se il bot è il dealer, 
deve terminare il gameloop quando la partita finisce


Utilizzo del metodo gioca dei diversi giocatori
il metodo .gioca() sovrascritto dall'utente non fa niente, in questo modo attendo il suo input 
quando l'utente clicca hit stay o split viene richiamato il gameloop
in questo modo attendo l'input finchè la mano dell'utente non è terminata
a quel punto si entra nella condizione specifica che passa alla mano o al turno successivo 
e viene richiamato il gameloop per far giocare i bot 

quando il bot gioca, controlla che il conteggio della mano sia minore di 17 se lo è chiama hit() altrimenti chiama stay()
il dealer estende il bot ed eredita questo comportamento di gioco


Fine partita
quando il dealer finisce di giocare il suo turno il model chiede alla partita di eseguire il metodo fine()
cioè di aggiornare lo stato dei giocatori (operazione fatta all’interno del giocatore)
e di resettare lo stato della partita (rimettere i flag a false e impostare il turno a 0)





Utilizzo di Stream
ho utilizzato gli stream nella classe GiocatoreUtente, 

nel metodo setUtente:
per mappare ogni riga del file dei dati dell’utente in una coppia chiave valore
facendo lo split sul carattere “:” (due punti), 
(le righe del file sono formattate in questo modo chiave:valore)
dopodichè per ogni elemento nello stream risultante
viene utilizzato uno switch per confrontare la chiave e determinare quale campo dell'utente
deve essere aggiornato

e nel metodo creaFileUtente:
In questo metodo uso Stream.of() per generare direttamente la lista di stringhe, da dare al metodo scriviFile() di FileUtils per creare il file.





Effetti speciali
Per rendere l’esperienza di gioco più fluida e realistica ho utilizzato il Timer di swing per rallentare la distribuzione delle carte e il passaggio da un turno all’altro





Gestione audio
utilizzo la classe AudioManager, definita nel package della view

viene utilizzata quando creo la view per far suonare la clip di sottofondo in loop
e per attivare degli effetti sonori quando l’utente interagisce con l’applicazione 
(es: click del pulsante hit)
oppure per attivare degli effetti legati allo svolgimento della partita 
(es: distribuzione delle carte, raggiungimento di un blackjack)

ho personalizzato la classe per regolare il volume delle clip 
e per decidere se mandarle in loop o no

queste due scelte mi sono servite per la musica di sottofondo, dato che volevo che suonasse in loop e che a prescindere il volume venisse abbassato per far sentire meglio gli altri effetti sonori

(mi rendo conto che la regolazione del volume di una clip audio in un contesto reale probabilmente viene fatta al di fuori del codice, magari attraverso software dedicati, ma non essendone esperto ho utilizzato poche righe di codice per sistemare il problema del volume della clip di sottofondo troppo alto)
