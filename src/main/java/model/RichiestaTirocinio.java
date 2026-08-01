package model;

import java.time.LocalDate;
import java.time.ZoneId; // 1. Aggiungi questo import

public class RichiestaTirocinio {
    private String argomentoScelto;
    private LocalDate dataRichiesta;
    private StatoWorkflow stato;

    public RichiestaTirocinio() {
        this.stato = StatoWorkflow.IN_ATTESA;

        // 2. LA MODIFICA È QUI: Dichiariamo esplicitamente il fuso orario
        // In alternativa, puoi usare ZoneId.systemDefault() per forzare la lettura dal sistema locale
        this.dataRichiesta = LocalDate.now(ZoneId.of("Europe/Rome"));
    }

    public RichiestaTirocinio(String argomentoScelto) {
        this();
        this.argomentoScelto = argomentoScelto;
    }

    public void accetta() {
        this.stato = StatoWorkflow.APPROVATA;
    }

    public void rifiuta() {
        this.stato = StatoWorkflow.RIFIUTATA;
    }

    public String getArgomentoScelto() {
        return argomentoScelto;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public StatoWorkflow getStato() {
        return stato;
    }
}