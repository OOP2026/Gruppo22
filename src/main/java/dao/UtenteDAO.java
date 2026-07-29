package dao;

import model.UTENTE;

public interface UtenteDAO {
    UTENTE verificaLogin(String username, String password);
}
