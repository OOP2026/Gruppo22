package model;

public class STUDENTE extends UTENTE { // UTENTE dovrebbe contenere username, password, nome, cognome[cite: 2]
    private String matricola;
    private String corsoStudi;

    // Costruttore principale
    public STUDENTE(String username, String password, String nome, String cognome, String matricola, String corsoStudi) {
        super(); // Passa i dati alla classe UTENTE
        this.matricola = matricola;
        this.corsoStudi = corsoStudi;
    }

    // Costruttore vuoto (spesso richiesto dai DAO)
    public STUDENTE() {
        super();
    }

    // --- GETTER e SETTER ---
    public String getMatricola() { return matricola; }
    public void setMatricola(String matricola) { this.matricola = matricola; }

    public String getCorsoStudi() {
        return corsoStudi; }
    public void setCorsoStudi(String corsoStudi) {
        this.corsoStudi = corsoStudi; }

    // NOTA: Non serve ridefinire getNome() o setNome() se li hai in UTENTE[cite: 2]
}