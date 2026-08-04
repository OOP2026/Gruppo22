package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class SedutaDiLaurea {

    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    // Rimosso l'attributo inutilizzato 'private String ora;'

    private String luogo;
    private String aula;

    public SedutaDiLaurea() {}

    public SedutaDiLaurea(LocalDate data, LocalTime oraInizio, LocalTime oraFine, String luogo) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.luogo = luogo;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public LocalTime getOraFine() {
        return oraFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    // Rimossi i metodi inutili e incoerenti setOra() e getOra()

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }
}