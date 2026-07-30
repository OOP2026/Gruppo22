package model;

public class Docente {
    private String idDocente;
    private String nome;
    private String cognome;
    private String username;
    private String password;

    // Costruttore vuoto
    public Docente() {}

    // Costruttore a 5 parametri richiesto dal Controller
    public Docente(String username, String password, String nome, String cognome, String idDocente) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.idDocente = idDocente;
    }

    // Getter e Setter
    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}