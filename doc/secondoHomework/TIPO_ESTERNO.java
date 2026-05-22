package model;


public class TIPO_ESTERNO extends TIROCINIO {


    private String referenteAziendale;


    public TIPO_ESTERNO() {
        super();
    }


    public TIPO_ESTERNO(String titoloArgomento, String referenteAziendale) {
        super(titoloArgomento);
        this.referenteAziendale = referenteAziendale;
    }

    public String getReferenteAziendale() {
        return referenteAziendale;
    }

    public void setReferenteAziendale(String referenteAziendale) {
        this.referenteAziendale = referenteAziendale;
    }
}