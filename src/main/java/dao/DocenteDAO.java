package dao;

import model.Docente;

public interface DocenteDAO {
    Docente findByCodice(String codiceDocente);

    Docente findById(int id);

    boolean inserisciDocente(Docente docente);
}
