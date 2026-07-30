package dao;

import model.SedutaDiLaurea;
import java.util.List;

/**
 * Interfaccia DAO per la gestione della persistenza delle Sedute di Laurea.
 */
public interface SedutaDAO {

    /**
     * Recupera tutte le sedute di laurea.
     * @return Lista di sedute.
     */
    List<SedutaDiLaurea> findAll();

    void save(SedutaDiLaurea seduta);

    /**
     * Inserisce una nuova seduta nel database.
     * @param seduta L'entità da persistere.
     * @return true se l'inserimento ha successo.
     */
    boolean inserisciSeduta(SedutaDiLaurea seduta);
}