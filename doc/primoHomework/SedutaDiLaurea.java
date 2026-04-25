import java.util.*;
import java.time.LocalDate;

// --- 1. ENUMERATIONS ---
enum StatoWorkflow {
    IN_ATTESA, APPROVATA, RIFIUTATA
}

// --- 2. GERARCHIA UTENTI ---
abstract class Utente {
    protected String nome, cognome, email, login, password;

    public Utente(String nome, String cognome, String email, String login, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}

class Studente extends Utente {
    private String matricola;
    private Tesi tesi;

    public Studente(String nome, String cognome, String email, String login, String password, String matricola) {
        super(nome, cognome, email, login, password);
        this.matricola = matricola;
    }

    public void caricaTesi(String titolo, String contenuto) {
        this.tesi = new Tesi(titolo, contenuto);
        System.out.println("Studente " + matricola + " ha caricato la tesi: " + titolo);
    }

    public Tesi getTesi() { return tesi; }
}

class Docente extends Utente {
    private List<Tirocinio> elencoArgomentiTirocinio;

    public Docente(String nome, String cognome, String email, String login, String password) {
        super(nome, cognome, email, login, password);
        this.elencoArgomentiTirocinio = new ArrayList<>();
    }

    public void aggiungiArgomento(Tirocinio t) {
        elencoArgomentiTirocinio.add(t);
    }

    public void valutaTesi(Tesi t, StatoWorkflow esito) {
        t.setStato(esito);
        System.out.println("Docente " + cognome + " ha valutato la tesi come: " + esito);
    }
}

class Coordinatore extends Docente {
    public Coordinatore(String nome, String cognome, String email, String login, String password) {
        super(nome, cognome, email, login, password);
    }

    public void creaSeduta(LocalDate data, String luogo) {
        System.out.println("Seduta creata per il giorno " + data + " presso " + luogo);
    }

    // Metodo chiave della traccia
    public void formaCommissione(SedutaDiLaurea seduta, List<Studente> studentiPrenotati, List<Docente> docentiDisponibili) {
        for (Studente s : studentiPrenotati) {
            if (s.getTesi() != null && s.getTesi().getStato() == StatoWorkflow.APPROVATA) {
                // Logica: aggiungi relatore dello studente alla commissione
                System.out.println("Tesi approvata per studente " + s.nome + ". Aggiunta alla commissione.");
            }
        }
    }
}

// --- 3. CLASSI DI DOMINIO (TIROCINIO, TESI, SEDUTA) ---
abstract class Tirocinio {
    protected String titoloArgomento;
    public Tirocinio(String titolo) { this.titoloArgomento = titolo; }
}

class TipoEsterno extends Tirocinio {
    private String referenteAziendale;
    public TipoEsterno(String titolo, String referente) {
        super(titolo);
        this.referenteAziendale = referente;
    }
}

class TipoInterno extends Tirocinio {
    public TipoInterno(String titolo) { super(titolo); }
}

class Tesi {
    private String titolo;
    private String fileContent;
    private StatoWorkflow stato;

    public Tesi(String titolo, String fileContent) {
        this.titolo = titolo;
        this.fileContent = fileContent;
        this.stato = StatoWorkflow.IN_ATTESA;
    }

    public void setStato(StatoWorkflow stato) { this.stato = stato; }
    public StatoWorkflow getStato() { return stato; }
}

class SedutaDiLaurea {
    private LocalDate data;
    private String luogo;
    private List<Docente> commissione;

    public SedutaDiLaurea(LocalDate data, String luogo) {
        this.data = data;
        this.luogo = luogo;
        this.commissione = new ArrayList<>();
    }
}

// --- 4. MAIN DI TEST ---
public class Main {
    public static void main(String[] args) {
        // Creazione attori
        Coordinatore coord = new Coordinatore("Mario", "Rossi", "m.rossi@unina.it", "mrossi", "12345");
        Studente stud = new Studente("Luca", "Bianchi", "l.bianchi@studenti.it", "lbianchi", "pass", "N8600123");

        // Simulazione Workflow
        stud.caricaTesi("Sviluppo AI", "Contenuto del file...");

        // Il docente approva la tesi
        coord.valutaTesi(stud.getTesi(), StatoWorkflow.APPROVATA);

        // Il coordinatore crea la seduta e forma la commissione
        coord.creaSeduta(LocalDate.of(2026, 7, 15), "Aula Magna");
    }
}