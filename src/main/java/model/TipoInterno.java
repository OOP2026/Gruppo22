package model;

// --- LA MODIFICA È QUI ---
// Rimozione dell'underscore per rispettare il PascalCase standard di Java
public class TipoInterno extends Tirocinio {

    private String laboratorio;

    // Anche il costruttore deve avere lo stesso identico nome della classe
    public TipoInterno() {
        // Chiama il costruttore vuoto di TIROCINIO
        super();
    }

    // Costruttore con parametri
    public TipoInterno(int idTirocinio, String titoloArgomento, String tipologia, String aziendaEnte, String laboratorio) {
        // Chiama il costruttore del padre passandogli le 4 variabili base
        super(idTirocinio, titoloArgomento, tipologia, aziendaEnte);

        // Assegna la variabile specifica del figlio
        this.laboratorio = laboratorio;
    }

    // Getter e Setter per l'attributo specifico
    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }
}