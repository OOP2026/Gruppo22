package model;

// Implementazione Esterno[cite: 5]
public class Tipo_Esterno extends Tirocinio {
    public static final String titoloArgomento = "";
    private String referenteAziendale;
    public Tipo_Esterno() { super(); }
    public Tipo_Esterno(String titoloArgomento, String referenteAziendale) {
        super();
        this.referenteAziendale = referenteAziendale;
    }
    public String getReferenteAziendale() { return referenteAziendale; }
    public void setReferenteAziendale(String referenteAziendale) { this.referenteAziendale = referenteAziendale; }
}