package model;

public class TIPO_INTERNO extends TIROCINIO {

    private String laboratorio;

    public TIPO_INTERNO() {
        // Chiama il costruttore vuoto di TIROCINIO
        super();
    }

    // 2. Costruttore con parametri
    // Chiede tutti i dati e passa al padre (TIROCINIO) quelli che gli competono
    public TIPO_INTERNO(int idTirocinio, String titoloArgomento, String tipologia, String aziendaEnte, String laboratorio) {
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