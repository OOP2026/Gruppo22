package model;

/**
 * Classe Model che rappresenta l'entità generale Tirocinio.
 * Mantiene le proprietà comuni tra tirocini interni ed esterni
 * per facilitare la visualizzazione nella Dashboard.
 */
public class TIROCINIO {

    // Attributi base identificati nel tuo progetto
    private int idTirocinio;
    private String titoloArgomento;

    // Attributi aggiunti per facilitare la gestione unificata (UNION)
    private String tipologia;   // "INTERNO" o "ESTERNO"
    private String aziendaEnte; // Contiene il laboratorio (interno) o l'azienda (esterno)

    // Costruttore vuoto (fondamentale per il DAO quando istanzia l'oggetto riga per riga)
    public TIROCINIO() {
    }

    // Costruttore completo
    public TIROCINIO(int idTirocinio, String titoloArgomento, String tipologia, String aziendaEnte) {
        this.idTirocinio = idTirocinio;
        this.titoloArgomento = titoloArgomento;
        this.tipologia = tipologia;
        this.aziendaEnte = aziendaEnte;
    }

    // --- GETTER ---

    public int getIdTirocinio() {
        return idTirocinio;
    }

    public String getTitoloArgomento() {
        return titoloArgomento;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getAziendaEnte() {
        return aziendaEnte;
    }

    // --- SETTER --- (Questi risolveranno gli errori rossi nel tuo DAO)

    public void setIdTirocinio(int idTirocinio) {
        this.idTirocinio = idTirocinio;
    }

    public void setTitoloArgomento(String titoloArgomento) {
        this.titoloArgomento = titoloArgomento;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setAziendaEnte(String aziendaEnte) {
        this.aziendaEnte = aziendaEnte;
    }
}