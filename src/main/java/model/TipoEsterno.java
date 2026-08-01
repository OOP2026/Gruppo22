package model;

// Implementazione Esterno
public class TipoEsterno extends Tirocinio {

    // --- LA MODIFICA È QUI ---
    // Le costanti (static final) in Java devono essere scritte rigorosamente tutte in MAIUSCOLO
    public static final String TITOLO_ARGOMENTO = "";

    private String referenteAziendale;

    public TipoEsterno() {
        super();
    }

    public TipoEsterno(String TITOLO_ARGOMENTO, String referenteAziendale) {
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