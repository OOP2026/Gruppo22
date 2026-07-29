package dao;

import model.SEDUTA_DI_LAURA;
import java.util.List;

public interface SedutaDAO {
    List<SEDUTA_DI_LAURA> findAll();
    void save(SEDUTA_DI_LAURA seduta);

    boolean inserisciSeduta(model.SEDUTA_DI_LAURA seduta);
}