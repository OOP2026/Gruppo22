package model;

public class Utente {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String codiceDocente;

    /**
     * Costruttore base dell'entità Utente.
     */
    public Utente() {
        this.username = "";
        this.password = "";
        this.nome = "";
        this.cognome = "";
        this.codiceDocente = "";
    }

    public Utente(String username, String password, String nome, String cognome, String codiceDocente) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceDocente = codiceDocente;
    }

    // --- METODI GETTER ---
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodiceDocente() {
        return codiceDocente;
    }

    // --- METODI SETTER ---
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCodiceDocente(String codiceDocente) {
        this.codiceDocente = codiceDocente;
    }
}
