package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class coordinatore extends DOCENTE {

    public coordinatore() {
        super();
    }

    // --- Operations definite nel Class Diagram ---

    public void creaSeduta(LocalDate data, LocalTime oraInizio, String luogo) {
        // Istanzia un oggetto SEDUTA_DI_LAURA e lo registra nel sistema
    }

    public void formaCommissione(SEDUTA_DI_LAURA seduta) {
        // Esegue l'algoritmo di assegnazione dei docenti alla seduta d'esame specifica
    }
}