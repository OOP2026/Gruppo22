package model;

// Implementazione Esterno
public class Tipo_Esterno extends Tirocinio {

    // --- LA MODIFICA È QUI ---
    // Le costanti (static final) in Java devono essere scritte tutte in MAIUSCOLO
    public static final String titoloargomento = "";

    private String referenteAziendale;

    public Tipo_Esterno() {
        super();
    }

    public Tipo_Esterno(String titoloArgomento, String referenteAziendale) {
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