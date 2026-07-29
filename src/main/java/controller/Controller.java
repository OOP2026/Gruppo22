package Controller;

import model.*;

// Importazioni delle Interfacce DAO
import dao.RichiestaTirocinioDAO;
import dao.TesiDAO;
import dao.StudenteDAO;
import dao.DocenteDAO;
import dao.SedutaDAO;

// Importazioni delle Implementazioni Postgres
import implementazionepostgresdao.RichiestaTirocinioDAOPostgresImpl;
import implementazionepostgresdao.TesiDAOPostgresImpl;
import implementazionepostgresdao.StudenteDAOPostgresImpl;
import implementazionepostgresdao.DocenteDAOPostgresImpl;
import implementazionepostgresdao.SedutaDAOPostgresImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Controller {

    // Dichiarazione delle dipendenze (I DAO)
    private RichiestaTirocinioDAO richiestaTirocinioDAO;
    private TesiDAO tesiDAO;
    private StudenteDAO studenteDAO;
    private DocenteDAO docenteDAO;
    private SedutaDAO sedutaDAO;

    /**
     * Costruttore del Controller.
     * Inizializza le implementazioni concrete per l'accesso al database PostgreSQL.
     */
    public Controller() {
        this.richiestaTirocinioDAO = new RichiestaTirocinioDAOPostgresImpl();
        this.tesiDAO = new TesiDAOPostgresImpl();
        this.studenteDAO = new StudenteDAOPostgresImpl();
        this.docenteDAO = new DocenteDAOPostgresImpl();
        this.sedutaDAO = new SedutaDAOPostgresImpl();
    }

    // ========================================================================
    // METODI DI CONTROLLO (Invocati dalla Boundary / GUI)
    // ========================================================================

    /**
     * Metodo invocato dalla LoginGUI per autenticare un utente.
     */
    public boolean login(String username, String password) {
        System.out.println("Tentativo di login ricevuto per l'utente: " + username);

        // Mock per testare il login senza DB
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        }

        return false;
    }

    /**
     * Inserisce una nuova richiesta di tirocinio nel sistema.
     */
    public void inserisciRichiesta(RICHIESTATIROCINIO richiesta) {
        richiestaTirocinioDAO.save(richiesta);
    }

    /**
     * Recupera i dati dello studente tramite la matricola.
     */
    public STUDENTE cercaStudente(String matricola) {
        System.out.println("CONTROLLER: Sto cercando la matricola [" + matricola + "]");
        return studenteDAO.findByMatricola(matricola);
    }

    /**
     * Registra una nuova tesi.
     */
    public void registraTesi(TESI tesi) {
        tesiDAO.save(tesi);
    }

    /**
     * Recupera i dati di un docente tramite il suo codice identificativo.
     */
    public Docente cercaDocente(String codiceDocente) {
        return docenteDAO.findByCodice(codiceDocente);
    }

    public STUDENTE loginStudente(String username, String password) {
        return studenteDAO.verificaLogin(username, password);
    }

    /**
     * Metodo invocato dalla GUI che riceve i dati singoli,
     * costruisce l'entità TESI e la passa al DAO.
     */
    public boolean registraTesi(String titolo, String descrizione, String codiceDocente) {
        TESI nuovaTesi = new TESI();
        nuovaTesi.setTitolo(titolo);
        nuovaTesi.setDescrizione(descrizione);
        nuovaTesi.setCodiceDocente(codiceDocente);
        nuovaTesi.setStato(StatoWorkflow.IN_ATTESA);

        return tesiDAO.save(nuovaTesi);
    }

    public List<TIROCINIO> ottieniListaTirocini() {
        return List.of();
    }

    public boolean registraTesi(int i, String matricolaStudente) {
        return "registraTesi".equals(matricolaStudente);
    }

    public boolean registraNuovoStudente(String username, String password, String nome, String cognome, String matricola, String corsoStudi) {
        // 1. Crei l'oggetto model
        STUDENTE nuovoStudente = new STUDENTE(username, password, nome, cognome, matricola, corsoStudi);

        // 2. Lo passi al DAO per il salvataggio
        return studenteDAO.inserisciStudente(nuovoStudente);
    }

    // ========================================================================
    // NUOVI METODI PER L'INSERIMENTO DALLA DASHBOARD
    // ========================================================================

    /**
     * Metodo per la registrazione di un nuovo Docente.
     */
    public boolean registraNuovoDocente(String idDocente, String nome, String cognome) {
        Docente nuovoDocente = new Docente();
        nuovoDocente.setIdDocente(idDocente); // Aggiunto il setting dell'ID fondamentale per il DB
        nuovoDocente.setNome(nome);
        nuovoDocente.setCognome(cognome);

        // Passaggio al DAO
        return docenteDAO.inserisciDocente(nuovoDocente);
    }

    /**
     * Metodo aggiornato per la registrazione di una nuova Tesi, inclusivo di Tipologia.
     */
    public boolean registraNuovaTesi(String titolo, String descrizione, String codiceDocente, String tipologia) {
        // Creazione istanza del model e setting degli attributi
        TESI nuovaTesi = new TESI();
        nuovaTesi.setTitolo(titolo);
        nuovaTesi.setDescrizione(descrizione);
        nuovaTesi.setCodiceDocente(codiceDocente);
        nuovaTesi.setTipologia(tipologia);

        // Impostazione dello stato di default del workflow
        nuovaTesi.setStato(StatoWorkflow.IN_ATTESA);

        // Esecuzione dell'inserimento tramite DAO
        return tesiDAO.save(nuovaTesi);
    }

    /**
     * Metodo per la registrazione di una Seduta di Laurea con parsing sicuro della data.
     */
    public boolean registraNuovaSeduta(String data, String ora, String aula) {
        model.SEDUTA_DI_LAURA nuovaSeduta = new model.SEDUTA_DI_LAURA();

        try {
            // Creiamo il formattatore per il pattern europeo Giorno-Mese-Anno
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Eseguiamo il parsing della stringa in ingresso
            LocalDate dataSeduta = LocalDate.parse(data, formatter);

            // Settiamo la data parsata nel model (assicurati di avere il metodo setData nella classe)
            nuovaSeduta.setData(dataSeduta);

        } catch (DateTimeParseException e) {
            // Se l'utente digita una data scorretta (es. 2026/07/12), il try fallisce e blocca l'inserimento
            System.err.println("Errore di parsing: la data deve rispettare il formato dd-MM-yyyy");
            return false;
        }

        // Impostiamo i restanti parametri
        nuovaSeduta.setOra(ora);
        nuovaSeduta.setAula(aula);

        // Passaggio al DAO per il salvataggio in PostgreSQL
        return sedutaDAO.inserisciSeduta(nuovaSeduta);
    }

    public boolean registraNuovoCoordinatore(String idCoordinatore, String nome, String cognome) {
        // Ipotizzando che tu abbia un Model chiamato Coordinatore (o DOCENTE se ha quel ruolo)
        // Se la tabella/interfaccia richiede l'oggetto Docente, puoi usare:
        Docente coordinatore = new Docente();
        coordinatore.setIdDocente(idCoordinatore);
        coordinatore.setNome(nome);
        coordinatore.setCognome(cognome);

        // Sostituisci con il DAO opportuno se hai un CoordinatoreDAO specifico,
        // altrimenti se usi la tabella docente:
        return docenteDAO.inserisciDocente(coordinatore);
    }

    public boolean registraNuovoTirocinio(String matricolaStudente, String codiceAzienda, String dataInizio) {
        // Esempio di cablaggio per la persistenza di un tirocinio
        // RICHIESTATIROCINIO nuovaRichiesta = new RICHIESTATIROCINIO();
        // ... settaggio attributi ...
        // return richiestaTirocinioDAO.save(nuovaRichiesta);

        System.out.println("Cablaggio Tirocinio invocato per studente: " + matricolaStudente);
        return true; // Modificare con la chiamata reale al DAO quando implementata la tabella specifica
    }
}