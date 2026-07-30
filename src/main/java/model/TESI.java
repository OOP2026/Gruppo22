package model;

public class TESI {

    private int idTesi;
    private String titolo;
    private String argomento;
    private String fileContact;
    private StatoWorkflow stato;
    private String tipologia;
    private String descrizione;
    private String codiceDocente;

    // Costruttore vuoto
    public TESI() {}

    // --- Getter e Setter ---

    public int getIdTesi() {
        return idTesi;
    }

    public void setIdTesi(int idTesi) {
        this.idTesi = idTesi;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
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

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodiceDocente() {
        return codiceDocente;
    }

    public void setCodiceDocente(String codiceDocente) {
        this.codiceDocente = codiceDocente;
    }
}