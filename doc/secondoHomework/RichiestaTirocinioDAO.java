package dao;

import database.DBConnection;
import model.RICHIESTATIROCINIO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface RichiestaTirocinioDAO {
    void save(RICHIESTATIROCINIO r);
}

