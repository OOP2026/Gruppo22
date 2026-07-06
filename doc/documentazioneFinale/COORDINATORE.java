package model;

public class COORDINATORE extends UTENTE {
    private String idCoordinatore;
    private String username;
    private String password;
    private String nome;
    private String cognome;

    /**
     * Costruttore base dell'entità Utente.
     *
     * @param username
     * @param password
     * @param nome
     * @param cognome
     * @param idCoordinatore
     */
    public COORDINATORE(String username, String password, String nome, String cognome, String idCoordinatore) {
        super();
    }

    /**
     * Costruttore dell'entità Coordinatore.
     */
    public void Coordinatore(String username, String password, String nome, String cognome, String idCoordinatore) {
        // Invocazione del costruttore della superclasse;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.idCoordinatore = idCoordinatore;
    }

    // --- METODI GETTER ---
    public String getIdCoordinatore() {
        return idCoordinatore;
    }
}