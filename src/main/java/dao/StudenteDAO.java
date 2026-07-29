package dao;

import model.STUDENTE;

public interface StudenteDAO {
    STUDENTE findByMatricola(String matricola);

    STUDENTE verificaLogin(String username, String password);

    boolean inserisciStudente(STUDENTE studente);
}
