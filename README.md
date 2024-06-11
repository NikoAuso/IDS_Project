<a href="https://nikoauso.github.io/IDS_Project/">
    <figure align="center">
        <img src="https://nikoauso.github.io/IDS_Project/docs/Logo.jpg" alt="logo del progetto" height="200" style="border-radius: 30%; border: solid 3px #54b8c4">
    </figure>
</a>

<hr/>

# TurisTown

Progetto sviluppato per l'esame di Ingegneria del Software (Prof. Morichetta - Prof.
Polini) [ST0496](https://unicam.coursecatalogue.cineca.it/insegnamenti/2022/8096/2016/10000/10028?annoOrdinamento=2016&coorte=2020)

[Descrizione del progetto](https://nikoauso.github.io/IDS_Project/docs/Descrizione%20progetto.pdf)


## Componenti

- Aurora Provinciali [109418]
- Martina Chiucconi [109405]
- Nicolò Ausili [115328]

## Indice
- [Descrizione](#descrizione)
- [Processo di sviluppo](#processo-di-sviluppo)
  - [Iterazione 1](#iterazione-1)
  - [Iterazione 2](#iterazione-2)
  - [Iterazione 3](#iterazione-3)
- [Tecnologie e strumenti utilizzati](#tecnologie-e-strumenti-utilizzati)
- [Come eseguire il progetto](#come-eseguire-il-progetto)


## Descrizione

TurisTown è una piattaforma innovativa progettata per la valorizzazione e digitalizzazione di un territorio comunale. La
piattaforma permetterà il caricamento e la gestione di informazioni culturali, turistiche, sportive e di altre nature,
connesse a punti geolocalizzati all'interno del comune.

Inoltre, permette ai cittadini di contribuire in modo collaborativo, caricando contenuti testuali e multimediali, che
saranno pubblicati previa verifica della conformità da parte dei curatori.

Tali informazioni saranno utili sia per i residenti che per i visitatori del territorio.

## Processo di sviluppo

Il progetto è stato sviluppato seguendo il modello di sviluppo incrementale, con iterazioni time-boxed della durata di
2 settimane ciascuna. Il modello di sviluppo incrementale prevede la realizzazione di un prodotto software in modo
incrementale, suddividendo il lavoro in parti più piccole e più facili da gestire.

In totale sono state svolte 3 iterazioni, ognuna delle quali ha portato a un incremento del prodotto software.

Come strumento di versioning è stato utilizzato Git e GitHub attraverso i quali è stato distinto un'unico branch per lo
sviluppo.

Le varie iterazioni hanno dato origine ai seguenti artefatti:

- **Diagramma dei casi d'uso**: raccolta e specifica dei requisiti e funzionalità del sistema;
- **Diagramma classi di analisi**: identificati i concetti che il sistema rappresenta;
- **Diagramma classi di progetto**: realizzato per derivare le classi di progetto;
- **Diagrammi di sequenza**: descrivono come le classi di analisi interagiscono tra di loro per realizzare il
  comportamento definito nei casi d'uso;
- **Code Base**: il codice sorgente del progetto.

### Iterazione 1
Nel corso della prima iterazione abbiamo effettuato l'analisi dei requisiti del progetto, che hanno poi gettato le basi
del sistema.
A seguire abbiamo definito il diagramma dei casi d'uso, dettagliandone le varie descrizioni e funzionalità, per poi
terminare l'iterazione con la prima stesura del diagramma delle classi di analisi, dove abbiamo rappresentato le
caratteristiche e le relazioni tra le varie entità richiamate dal sistema.

### Iterazione 2

Durante la seconda iterazione abbiamo proseguito dettagliando più nello specifico il diagramma dei casi d'uso, inserendo
le pre e post condizioni necessarie, e definendo le sequenze degli eventi principali. Inoltre abbiamo adattato il
diagramma delle classi di analisi alle modifiche apportate al progetto, dovute dalla rielaborazione di alcune idee.
Siamo andati a definire il diagramma delle classi di progetto, di pari passo con l'implementazione delle classi su
IntelliJ e abbiamo terminato l'iterazione creando il diagramma di sequenza per alcuni casi d'uso.

### Iterazione 3

Nella terza iterazione abbiamo terminato la creazione dei diagrammi di sequenza e siamo andati a completare in maniera
definitiva il diagramma delle classi di progetto con le relative implementazioni del codice.
Abbiamo scelto di utilizzare Gradle come strumento di build automation per la gestione delle dipendenze del progetto e
H2 come database per la persistenza dei dati. Abbiamo poi migrato il backend del progetto su Spring Boot.
Inoltre, abbiamo implementato le API REST, documentate tramite Swagger, e abbiamo iniziato a testare le API tramite
l'applicativo Postman per eseguire i test.

## Tecnologie e strumenti utilizzati

- **Visual Paradigm**: per la modellazione dei diagrammi UML
- **Intellij IDEA**: per lo sviluppo del codice
- **Gradle**: per la gestione delle dipendenze
- **H2**: per la persistenza dei dati
- **Spring Boot**: per la realizzazione del backend
- **Swagger**: per la documentazione delle API
- **Postman**: per il testing delle API
- **GitHub**: per il versioning del codice

### Autenticazione e autorizzazione

Per la sicurezza delle API è stata utilizzata la libreria Spring Security, che permette di gestire l'autenticazione e
l'autorizzazione degli utenti. In particolare, l'autenticazione è basata su username e password dell'utente, mentre
l'autorizzazione è basata su ruoli assegnati all'utente.

Nella piattaforma sono già registrati diversi utenti con ruoli differenti, tutti elencati all'interno della
documentazione presente su Postman.

### Design Pattern applicati

- **Factory method**: utilizzato per la creazione di oggetti di tipo `POI`.
- **Builder**: utilizzato per la creazione di oggetti di tipo `Contenuto`.
- **Observer**: utilizzato per la notifica di nuovi contenuti inseriti dagli utenti.

## Come eseguire il progetto

**Prerequisiti**

- aver clonato la repository https://github.com/NikoAuso/IDS_Project.git
- aver scaricato Gradle Build Tool

**Installazione**

1. aprire il terminale nella cartella del progetto clonato
2. eseguire il comando `gradle build`
3. eseguire il comando `gradle bootRun`
4. per testare l'applicazione sono disponibili delle API Request
   su [Postman](https://lively-trinity-134640.postman.co/workspace/IDS_Project-2024~f6af244c-b5c7-4183-9a54-adafcf9e233d/collection/16242130-fea3a4e8-0f18-4819-9345-57d114cde7b7?action=share&creator=16242130) (necessario account su Postman) o
   tramite [API Docs](http://localhost:8081/swagger-ui/index.html)
