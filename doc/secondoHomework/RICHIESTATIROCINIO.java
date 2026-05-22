package model;

import java.time.LocalDate;

public class RICHIESTATIROCINIO {
    private String argomentoScelto;
    private LocalDate dataRichiesta;
    private StatoWorkflow stato;


    public RICHIESTATIROCINIO() {
        this.stato = StatoWorkflow.IN_ATTESA;
        this.dataRichiesta = LocalDate.now();
    }


    public RICHIESTATIROCINIO(String argomentoScelto) {
        this();
        this.argomentoScelto = argomentoScelto;
    }

    public void accetta() {
        this.stato = StatoWorkflow.APPROVATA;
    }


    public void rifiuta() {
        this.stato = StatoWorkflow.RIFIUTATA;
    }


    public String getDettagli() {
        return "Tirocinio: " + this.argomentoScelto +
                " | Data Sottomissione: " + this.dataRichiesta +
                " | Stato attuale: " + this.stato;
    }

    public String getArgomentoScelto() {
        return argomentoScelto;
    }

    public void setArgomentoScelto(String argomentoScelto) {
        this.argomentoScelto = argomentoScelto;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public StatoWorkflow getStato() {
        return stato;
    }

    public void setStato(StatoWorkflow stato) {
        this.stato = stato;
    }
}

