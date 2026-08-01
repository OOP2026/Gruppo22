package model;

public class Studente extends Utente {

    private String matricola;
    private String corsoStudi;

    // Costruttore principale
    public Studente(String username, String password, String nome, String cognome, String matricola, String corsoStudi) {
        // --- MODIFICA ARCHITETTURALE ---
        // Passiamo i parametri ricevuti direttamente al costruttore della classe padre (Utente)
        super(username, password, nome, cognome);
        this.matricola = matricola;
        this.corsoStudi = corsoStudi;
    }

    // Costruttore vuoto (spesso richiesto dai DAO)
    public Studente() {
        super();
    }

    // --- GETTER e SETTER ---

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getCorsoStudi() {
        return corsoStudi;
    }

    public void setCorsoStudi(String corsoStudi) {
        this.corsoStudi = corsoStudi;
    }

    // Il metodo vuoto setNome() è stato rimosso per sfruttare correttamente l'ereditarietà.
    // I metodi per nome, cognome, username e password vengono ereditati in automatico da Utente.
}