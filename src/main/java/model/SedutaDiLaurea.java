package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class SedutaDiLaurea {

    private LocalDate data;

    // Rinominate le variabili rimuovendo l'underscore (camelCase)
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private String ora;
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

    // Rinominato in getOraInizio
    public LocalTime getOraInizio() {
        return oraInizio;
    }

    // Rinominato in getOraFine
    public LocalTime getOraFine() {
        return oraFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // Rinominato in setOraInizio
    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    // Rinominato in setOraFine
    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setOra(String ora) {
    }

    public LocalTime getOra() {
        return oraInizio;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }
}