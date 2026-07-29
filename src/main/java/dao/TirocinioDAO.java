package dao;

import model.TIPO_ESTERNO;
import model.TIPO_INTERNO;
import model.TIROCINIO;

import java.util.List;

public interface TirocinioDAO {
     List<TIROCINIO> findAll();

    void saveInterno(TIPO_INTERNO t);
    void saveEsterno(TIPO_ESTERNO t);
}