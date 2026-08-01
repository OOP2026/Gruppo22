package dao;

import model.Utente; // Import fondamentale della classe reale

/**
 * Interfaccia DAO (Data Access Object) per la gestione dell'entità Utente.
 * Definisce i servizi di accesso ai dati garantendo il totale disaccoppiamento
 * tra la logica applicativa (Controller) e l'infrastruttura di persistenza.
 */
public interface UtenteDAO {

    /**
     * Verifica le credenziali di accesso di un utente interrogando la base di dati.
     *
     * @param username Lo username inserito dall'utente.
     * @param password La password inserita dall'utente.
     * @return L'istanza polimorfica dell'Utente (Studente, Docente o Coordinatore)
     *         se l'autenticazione ha successo; null in caso di fallimento.
     */
    // --- LA MODIFICA È QUI ---
    // Rimossa la dichiarazione generica <Utente>
    Utente verificaLogin(String username, String password);

}