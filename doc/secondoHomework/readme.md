# Relazione Tecnica: Specifica del Class Diagram
**Sistema Gestione Tesi -- Milestone 2** *Componenti del Gruppo 22: Agostino Landolfo*

---

## 1. Introduzione
Il presente documento espone l'analisi strutturale e le specifiche di dettaglio delle classi che compongono il sistema. L'ingegnerizzazione dell'applicativo segue il pattern architetturale Model-View-Controller (MVC), garantendo alta coesione modulare e un netto disaccoppiamento tra la persistenza dei dati (Model), la logica di business (Controller) e l'interfaccia grafica (View).
## 2. Architettura e Pattern MVC

L'applicazione è partizionata in macro-pacchetti logici:

Model: Contiene le entità di dominio del sistema (utenti, tesi, tirocini, aziende).

Controller: Coordina l'applicazione, intercetta le richieste della View, esegue la business logic e aggiorna lo stato delle entità.

GUI (View): Gestisce la presentazione visiva e la cattura dell'input tramite la libreria Java Swing.

## 3. Class Diagram di Dettaglio
Di seguito viene riportato il diagramma delle classi modellato mediante lo strumento CASE *Visual Paradigm*.

![Class Diagram del Sistema Gestione Tesi](Homework2_Gruppo22.png)

## 4. Dettaglio delle Classi e Modificatori

### 4.1 Pacchetto Model
Il modello implementa i principi della OOP (incapsulamento, ereditarietà).

UTENTE: Superclasse astratta che centralizza i dati comuni (username, password, nome, cognome).

STUDENTE, DOCENTE, COORDINATORE: Specializzazioni di UTENTE con privilegi operativi distinti.

TIROCINIO e AZIENDA: Nuove entità introdotte per gestire il workflow dei tirocini (curriculari, extracurriculari ed esteri).

### 4.2 Pacchetto Persistence Layer (DAO Pattern)
Per garantire la persistenza dei dati, è stato introdotto il Data Access Object (DAO) Pattern, che isola l'accesso al database PostgreSQL dalla logica di business.

Interfacce DAO: Definiscono il contratto per le operazioni CRUD (es. StudenteDAO, TirocinioDAO).

Implementazione Postgres: Le classi di implementazione (es. StudenteDAOPostgresImpl) traducono le chiamate di sistema in query SQL parametrizzate tramite PreparedStatement. Questa scelta previene vulnerabilità di tipo SQL Injection.

Gestione Connessioni: Il sistema utilizza il pattern Singleton nella classe DBConnection per ottimizzare l'apertura e la chiusura delle connessioni al database, garantendo l'efficienza delle risorse.

### 4.3 Pacchetto Controller
Mantiene i riferimenti logici e coordina le operazioni tra View e Model/DAO. Gestisce l'autenticazione tramite scansione polimorfica e funge da mediatore per le operazioni di persistenza, garantendo che la View non contenga logica di accesso ai dati.
### 4.3 Pacchetto GUI
Le interfacce grafiche sono state progettate per delegare ogni operazione di salvataggio o recupero dati al Controller. Ogni modulo (Studenti, Coordinatori, Tirocini) è stato implementato garantendo la corretta validazione dei campi di input prima dell'invocazione dei metodi di persistenza.

## 5. Analisi delle Relazioni UML
Generalizzazione: Connette le classi specializzate (Studente, Docente, Coordinatore) alla superclasse UTENTE.

Associazione e Dipendenza: La View detiene un riferimento al Controller. Il Controller dipende dalle interfacce DAO per la gestione dei dati, garantendo il disaccoppiamento (Dependency Injection semplificata).

# 6. Conclusioni
L'architettura attuale soddisfa i requisiti di scalabilità definiti. L'integrazione del layer di persistenza DAO con il pattern MVC permette di gestire l'evoluzione del sistema, consentendo l'aggiunta di nuove funzionalità (come la gestione dei tirocini) senza compromettere l'integrità del core system. Le soluzioni tecniche adottate, inclusa la gestione delle eccezioni SQL e dei vincoli di unicità (Primary Key), assicurano la robustezza operativa del software.
