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
import java.util.ArrayList;
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
    // METODI DI AUTENTICAZIONE E LOGGING
    // ========================================================================

    public boolean login(String username, String password) {
        System.out.println("Tentativo di login ricevuto per l'utente: " + username);
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        }
        return false;
    }

    public STUDENTE loginStudente(String username, String password) {
        return studenteDAO.verificaLogin(username, password);
    }

    // ========================================================================
    // METODI DI RICERCA ENTITÀ
    // ========================================================================

    public STUDENTE cercaStudente(String matricola) {
        System.out.println("CONTROLLER: Sto cercando la matricola [" + matricola + "]");
        return studenteDAO.findByMatricola(matricola);
    }

    public Docente cercaDocente(String codiceDocente) {
        return docenteDAO.findByCodice(codiceDocente);
    }

    // ========================================================================
    // METODI PER REGISTRAZIONE DIVERSE ENTITÀ (GUI & DASHBOARD)
    // ========================================================================

    public boolean registraNuovoStudente(String username, String password, String nome, String cognome, String matricola, String corsoStudi) {
        STUDENTE nuovoStudente = new STUDENTE(username, password, nome, cognome, matricola, corsoStudi);
        return studenteDAO.inserisciStudente(nuovoStudente);
    }

    /**
     * Registrazione Docente usando il costruttore parametrizzato.
     * Genera un account base usando l'ID come username.
     */
    public boolean registraNuovoDocente(String idDocente, String nome, String cognome) {
        // Parametri: username, password, nome, cognome, codiceDocente
        Docente nuovoDocente = new Docente(idDocente, "defaultPass", nome, cognome, idDocente);
        return docenteDAO.inserisciDocente(nuovoDocente);
    }

    public boolean registraNuovoCoordinatore(String idCoordinatore, String nome, String cognome) {
        Docente coordinatore = new Docente(idCoordinatore, "defaultPass", nome, cognome, idCoordinatore);
        return docenteDAO.inserisciDocente(coordinatore);
    }

    public void registraTesi(TESI tesi) {
        tesiDAO.save(tesi);
    }

    public boolean registraTesi(String titolo, String descrizione, String codiceDocente) {
        TESI nuovaTesi = new TESI();
        nuovaTesi.setTitolo(titolo);
        nuovaTesi.setDescrizione(descrizione);
        nuovaTesi.setCodiceDocente(codiceDocente);
        nuovaTesi.setStato(StatoWorkflow.IN_ATTESA);

        return tesiDAO.save(nuovaTesi);
    }

    public boolean registraNuovaTesi(String titolo, String descrizione, String codiceDocente, String tipologia) {
        TESI nuovaTesi = new TESI();
        nuovaTesi.setTitolo(titolo);
        nuovaTesi.setDescrizione(descrizione);
        nuovaTesi.setCodiceDocente(codiceDocente);
        nuovaTesi.setTipologia(tipologia);
        nuovaTesi.setStato(StatoWorkflow.IN_ATTESA);

        return tesiDAO.save(nuovaTesi);
    }

    public boolean registraNuovaSeduta(String data, String ora, String aula) {
        model.SEDUTA_DI_LAURA nuovaSeduta = new model.SEDUTA_DI_LAURA();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dataSeduta = LocalDate.parse(data, formatter);
            nuovaSeduta.setData(dataSeduta);

        } catch (DateTimeParseException e) {
            System.err.println("Errore di parsing: la data deve rispettare il formato dd-MM-yyyy");
            return false;
        }

        nuovaSeduta.setOra(ora);
        nuovaSeduta.setAula(aula);

        return sedutaDAO.inserisciSeduta(nuovaSeduta);
    }

    public void inserisciRichiesta(RICHIESTATIROCINIO richiesta) {
        richiestaTirocinioDAO.save(richiesta);
    }

    public boolean registraNuovoTirocinio(String matricolaStudente, String codiceAzienda, String dataInizio) {
        System.out.println("Cablaggio Tirocinio invocato per studente: " + matricolaStudente);
        return true;
    }

    // ========================================================================
    // METODI DI RECUPERO LISTE PER TABELLE SWING
    // ========================================================================

    public List<TIROCINIO> getTuttiTirocini() {
        // Restituisce una lista vuota istanziata per evitare NullPointerException nelle tabelle GUI
        return new ArrayList<>();
    }

    public List<TIROCINIO> ottieniListaTirocini() {
        return getTuttiTirocini();
    }

    public boolean registraTesi(int i, String matricolaStudente) {
        return "registraTesi".equals(matricolaStudente);
    }
}
