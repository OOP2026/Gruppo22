package implementazionedao;

import dao.RichiestaTirocinioDAO;
import database.DBConnection;
import model.RichiestaTirocinio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementazione PostgreSQL per l'accesso ai dati delle richieste di tirocinio.
 */
public class RichiestaTirocinioDAOPostgresImpl implements RichiestaTirocinioDAO {

    private static final Logger LOGGER = Logger.getLogger(RichiestaTirocinioDAOPostgresImpl.class.getName());

    @Override
    public void save(RichiestaTirocinio r) {
        String query = "INSERT INTO richiesta_tirocinio (argomento_scelto, data_richiesta, stato) VALUES (?, ?, ?)";

        DBConnection dbInstance = DBConnection.getInstance();
        if (dbInstance == null) {
            LOGGER.log(Level.SEVERE, "Impossibile ottenere l'istanza del database.");
            return;
        }

        try (Connection conn = dbInstance.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, r.getArgomentoScelto());
            pstmt.setDate(2, Date.valueOf(r.getDataRichiesta()));
            pstmt.setString(3, r.getStato().toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Errore durante l'esecuzione della query di inserimento", e);
        }
    }
}
