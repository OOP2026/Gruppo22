package model;

public class TESI {
    private String titolo;
    private String fileContact;
    private StatoWorkflow stato;

    public TESI() {}

    // ==========================================
    // OPERATIONS (Dal Class Diagram)
    // ==========================================

    public void approvata() {
        this.stato = StatoWorkflow.APPROVATA;
    }

    public void respingi(String motivazione) {
        this.stato = StatoWorkflow.RIFIUTATA;
        // La motivazione potrebbe essere salvata in un attributo a parte se necessario
        System.out.println("Tesi respinta. Motivazione: " + motivazione);
    }

    public String visualizzaDettagli() {
        return "Titolo: " + titolo + " | File: " + fileContact + " | Stato: " + stato;
    }

    // ==========================================
    // GETTER E SETTER
    // ==========================================

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getFileContact() { return fileContact; }
    public void setFileContact(String fileContact) { this.fileContact = fileContact; }

    public StatoWorkflow getStato() { return stato; }
    public void setStato(StatoWorkflow stato) { this.stato = stato; }

 }