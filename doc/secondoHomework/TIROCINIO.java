package model;

public abstract class TIROCINIO {

    private String titoloArgomento;

    public TIROCINIO() {}

    public TIROCINIO(String titoloArgomento) {
        this.titoloArgomento = titoloArgomento;
    }

    public String getTitoloArgomento() { return titoloArgomento; }
    public void setTitoloArgomento(String titoloArgomento) { this.titoloArgomento = titoloArgomento; }
}