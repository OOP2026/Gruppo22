package model;

public class TESI {
    private String titolo;
    private String fileContact;
    private StatoWorkflow stato;
    private String tipologia;

    public TESI() {}

    // --- Getter e Setter ---

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getFileContact() {
        return fileContact;
    }

    public void setFileContact(String fileContact) {
        this.fileContact = fileContact;
    }

    public StatoWorkflow getStato() {
        return stato;
    }

    public void setStato(StatoWorkflow stato) {
        this.stato = stato;
    }

    public void setIdTesi(int id) {
    }

    public void setArgomento(String argomento) {
    }

    public String setDescrizione(String descrizione) {
        return "Descrizione";
    }

    public String setCodiceDocente(String codiceDocente) {
        return "CodiceDocente";
    }
    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}