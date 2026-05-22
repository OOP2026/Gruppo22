package model;


public class STUDENTE extends UTENTE {


    private String matricola;


    private TESI tesi;
    private RICHIESTATIROCINIO richiestaTirocinio;


    public STUDENTE() {
        super();
    }


    public void effettuaRichiesta(TIROCINIO argomento, DOCENTE docente) {
        this.richiestaTirocinio = new RICHIESTATIROCINIO();
        this.richiestaTirocinio.setArgomentoScelto(argomento.getTitoloArgomento());
        this.richiestaTirocinio.setStato(StatoWorkflow.IN_ATTESA);

    }


    public void caricaTesi(String titolo, String seduta_di_laurea) {
        this.tesi = new TESI();
        this.tesi.setTitolo(titolo);
        this.tesi.setStato(StatoWorkflow.IN_ATTESA);
    }


    public String visualizzaEsito() {
        StringBuilder esito = new StringBuilder();

        if (this.tesi != null) {
            esito.append("Workflow Tesi: ").append(this.tesi.getStato());
        } else {
            esito.append("Nessun elaborato di tesi depositato");
        }

        esito.append(" | ");

        if (this.richiestaTirocinio != null) {
            esito.append("Stato Richiesta Tirocinio: ").append(this.richiestaTirocinio.getStato());
        } else {
            esito.append("Nessuna istanza di tirocinio attiva");
        }

        return esito.toString();
    }


    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public TESI getTesi() {
        return tesi;
    }

    public void setTesi(TESI tesi) {
        this.tesi = tesi;
    }

    public RICHIESTATIROCINIO getRichiestaTirocinio() {
        return richiestaTirocinio;
    }

    public void setRichiestaTirocinio(RICHIESTATIROCINIO richiestaTirocinio) {
        this.richiestaTirocinio = richiestaTirocinio;
    }
}