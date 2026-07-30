package dao;

import model.Tesi;

import java.util.List;

/**
 * Interfaccia DAO per la gestione della persistenza delle Tesi.
 * * L'interfaccia {@code TesiDAO} definisce il contratto per le operazioni di persistenza
 *   (CRUD) relative all'entità Tesi sul database PostgreSQL.
 *  Questa interfaccia garantisce il disaccoppiamento tra la logica di business
 *   e l'accesso fisico ai dati.
 *
 *   @author Agostino Landolfo
 *   @author Raffaele Dipinto
 *  @version 1.0
 */
public interface TesiDAO {

    /**
     * Recupera tutte le tesi.
     * @return Lista di tesi.
     */
    List<Tesi> findAll();

    /**
     * Inserisce una nuova tesi nel database.
     * @param tesi L'entità da persistere.
     * @return true se l'inserimento ha successo.
     */
    boolean inserisciTesi(Tesi tesi);

    /**
     * Aggiorna i dati di una tesi esistente.
     * @param tesi L'entità da aggiornare.
     */
    void update(Tesi tesi);

    boolean save(Tesi nuovaTesi);
}