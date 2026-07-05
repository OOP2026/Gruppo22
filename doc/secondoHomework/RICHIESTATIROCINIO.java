package model;
import java.time.LocalDate;

public class RICHIESTATIROCINIO {
    private String argomentoScelto;
    private LocalDate dataRichiesta;
    private StatoWorkflow stato;

    public RICHIESTATIROCINIO() {
        this.stato =StatoWorkflow.IN_ATTESA;
        this.dataRichiesta = LocalDate.now();
    }

    public RICHIESTATIROCINIO(String argomentoScelto) {
        this();
        this.argomentoScelto = argomentoScelto;
    }

    // Operations[cite: 9]
    public void accetta() { this.stato =StatoWorkflow.APPROVATA; }
    public void rifiuta() { this.stato =StatoWorkflow.RIFIUTATA; }

    // Getter/Setter omessi per brevità[cite: 9]

    // Aggiungi questi metodi in RICHIESTATIROCINIO.java

    public String getArgomentoScelto() {
        return argomentoScelto;
    }

    public java.time.LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public StatoWorkflow getStato() {
        return stato;
    }
}