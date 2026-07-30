package dao;

import model.Studente;

public interface StudenteDAO {
    Studente findByMatricola(String matricola);

    Studente verificaLogin(String username, String password);

    boolean inserisciStudente(Studente studente);
}
