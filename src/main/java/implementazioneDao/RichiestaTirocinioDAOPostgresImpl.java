package implementazionepostgresdao;

import dao.RichiestaTirocinioDAO;
import database.DBConnection;
import model.RICHIESTATIROCINIO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Implementazione
public class RichiestaTirocinioDAOPostgresImpl implements RichiestaTirocinioDAO {
    public void save(RICHIESTATIROCINIO r) {
        String query = "INSERT INTO richiesta_tirocinio (argomento_scelto, data_richiesta, stato) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, r.getArgomentoScelto());
            pstmt.setDate(2, Date.valueOf(r.getDataRichiesta()));
            pstmt.setString(3, r.getStato().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
