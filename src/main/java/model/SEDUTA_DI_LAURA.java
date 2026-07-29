package model;
import java.time.LocalDate;
import java.time.LocalTime;

public class SEDUTA_DI_LAURA {
    private LocalDate data;
    private LocalTime ora_inizio;
    private LocalTime ora_fine;
    private String luogo;
    private String ora;
    private String aula;

    public SEDUTA_DI_LAURA() {}

    public SEDUTA_DI_LAURA(LocalDate data, LocalTime ora_inizio, LocalTime ora_fine, String luogo) {
        this.data = data;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;
        this.luogo = luogo;
    }

    public LocalDate getData() {
        return data;
    }
    public LocalTime getOra_inizio() {
        return ora_inizio;
    }

    public LocalTime getOra_fine() {
        return ora_fine;
    }

    public String getLuogo() {
        return luogo;
    }

    // Assicurati di avere anche i rispettivi setter se ti servono:
    public void setData(LocalDate data) { this.data = data; }
    public void setOra_inizio(LocalTime ora_inizio) { this.ora_inizio = ora_inizio; }
    public void setOra_fine(LocalTime ora_fine) { this.ora_fine = ora_fine; }
    public void setLuogo(String luogo) { this.luogo = luogo; }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public LocalTime getOra() {
        return ora_inizio;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }
    // Getter e Setter[cite: 8]
}