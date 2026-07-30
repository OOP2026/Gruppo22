package dao;

import database.DBConnection;
import model.RichiestaTirocinio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface RichiestaTirocinioDAO {
    void save(RichiestaTirocinio r);
}

