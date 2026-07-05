package model;

public class UTENTE {
    private String username;
    private String password;
    private String nome;
    private String cognome;

    /**
     * Costruttore base dell'entità Utente.
     */
    public UTENTE() {
        this.username = this.username;
        this.password = this.password;
        this.nome = this.nome;
        this.cognome = this.cognome;
    }

    public UTENTE(String username, String password, String nome, String cognome, String codiceDocente) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
