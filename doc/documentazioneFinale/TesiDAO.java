package dao;

import model.TESI;
import java.util.List;

public interface TesiDAO {
    List<TESI> findAll();
    boolean save(TESI tesi);
    void update(TESI tesi);

    // Nell'implementazione:
    boolean inserisciTesi(TESI tesi);
}