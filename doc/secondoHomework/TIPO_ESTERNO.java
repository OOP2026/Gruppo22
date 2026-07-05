package model;

// Implementazione Esterno[cite: 5]
public class TIPO_ESTERNO extends TIROCINIO {
    public static final String titoloArgomento = "";
    private String referenteAziendale;
    public TIPO_ESTERNO() { super(); }
    public TIPO_ESTERNO(String titoloArgomento, String referenteAziendale) {
        super();
        this.referenteAziendale = referenteAziendale;
    }
    public String getReferenteAziendale() { return referenteAziendale; }
    public void setReferenteAziendale(String referenteAziendale) { this.referenteAziendale = referenteAziendale; }
}
