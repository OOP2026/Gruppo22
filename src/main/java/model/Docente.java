package model;

// Sfruttiamo l'ereditarietà: Docente è un Utente
public class Docente extends Utente {

    // Manteniamo SOLO il campo specifico di questa classe.
    // nome, cognome, username e password sono ereditati in automatico.
    private String idDocente;

    // Costruttore vuoto
    public Docente() {
        super();
    }

    // Costruttore a 5 parametri richiesto dal Controller
    public Docente(String username, String password, String nome, String cognome, String idDocente) {
        // Passiamo i dati anagrafici e le credenziali alla superclasse
        super(username, password, nome, cognome);
        this.idDocente = idDocente;
    }

    // Getter e Setter per l'unico campo specifico
    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }
}