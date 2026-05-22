package model;

import java.time.LocalDate;
import java.time.LocalTime;


public class SEDUTA_DI_LAURA {
    private LocalDate data;
    private LocalTime ora_inizio;
    private LocalTime ora_fine;
    private String luogo;


    public SEDUTA_DI_LAURA() {
    }


    public SEDUTA_DI_LAURA(LocalDate data, LocalTime ora_inizio, LocalTime ora_fine, String luogo) {
        this.data = data;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;
        this.luogo = luogo;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(LocalTime ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public LocalTime getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(LocalTime ora_fine) {
        this.ora_fine = ora_fine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }
}