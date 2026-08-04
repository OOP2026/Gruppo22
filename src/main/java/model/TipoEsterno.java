package model;

// Implementazione Esterno
public class TipoEsterno extends Tirocinio {

    // La costante di classe resta correttamente in SCREAMING_SNAKE_CASE
    public static final String TITOLO_ARGOMENTO = "";

    private String referenteAziendale;

    public TipoEsterno() {
        super();
    }

    // --- LA MODIFICA È QUI ---
    // Rimosso il parametro "titoloArgomento" inutilizzato (Dead Code)
    public TipoEsterno(String referenteAziendale) {
        super();
        this.referenteAziendale = referenteAziendale;
    }

    public String getReferenteAziendale() {
        return referenteAziendale;
    }

    public void setReferenteAziendale(String referenteAziendale) {
        this.referenteAziendale = referenteAziendale;
    }
}