package model;

import java.util.ArrayList;
import java.util.List;

public class DOCENTE extends UTENTE {

    // Relazione 1 a molti (*) con i tirocini gestiti dal docente
    private List<TIROCINIO> tirociniProposti;

    public DOCENTE() {
        super();
        this.tirociniProposti = new ArrayList<>();
    }

    public static DOCENTE CreatDOCENTE() {
        return new DOCENTE();
    }

    // --- Operations definite nel Class Diagram ---

    public void elencaArgomentiTesi() {
        // Logica per visualizzare o restituire i temi di tesi disponibili
    }

    public void aggiungiTesi(TESI tesi) {
        // Consente al docente di associare un nuovo argomento di tesi al proprio profilo
    }

    public void valutaRichiesta(RICHIESTATIROCINIO richiesta) {
        // Logica per esaminare una richiesta inoltrata da uno studente
    }

    public void approva() {
        // Approvazione generica nell'ambito dei flussi accademici
    }

    // --- Getter e Setter per l'incapsulamento ---
    public List<TIROCINIO> getTirociniProposti() {
        return tirociniProposti;
    }

    public void setTirociniProposti(List<TIROCINIO> tirociniProposti) {
        this.tirociniProposti = tirociniProposti;
    }
}