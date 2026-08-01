package model;

public class Coordinatore extends Utente {

    // Manteniamo SOLO l'attributo specifico del Coordinatore.
    // username, password, nome e cognome sono ereditati automaticamente da Utente.
    private String idCoordinatore;

    /**
     * Costruttore dell'entità Coordinatore.
     *
     * @param username       Lo username per il login
     * @param password       La password per il login
     * @param nome           Il nome del coordinatore
     * @param cognome        Il cognome del coordinatore
     * @param idCoordinatore L'identificativo specifico del ruolo
     */
    public Coordinatore(String username, String password, String nome, String cognome, String idCoordinatore) {
        // 1. Invocazione del costruttore della superclasse (Utente) per i dati anagrafici
        super(username, password, nome, cognome);

        // 2. Assegnazione del dato specifico della classe figlia
        this.idCoordinatore = idCoordinatore;
    }

    // --- METODI GETTER e SETTER ---

    public String getIdCoordinatore() {
        return idCoordinatore;
    }

    public void setIdCoordinatore(String idCoordinatore) {
        this.idCoordinatore = idCoordinatore;
    }
}