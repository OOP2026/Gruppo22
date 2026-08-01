package implementazionedao;

import dao.SedutaDAO;
import database.DBConnection;
import model.SedutaDiLaurea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// Importazioni necessarie per il logging
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementazione Postgres del DAO per SedutaDiLaurea.
 */
public class SedutaDAOPostgresImpl implements SedutaDAO {

    // 1. Inizializzazione del Logger
    private static final Logger LOGGER = Logger.getLogger(SedutaDAOPostgresImpl.class.getName());

    @Override
    public List<SedutaDiLaurea> findAll() {
        List<SedutaDiLaurea> lista = new ArrayList<>();
        String query = "SELECT * FROM seduta_di_laurea";

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                SedutaDiLaurea s = new SedutaDiLaurea();

                Date sqlDate = rs.getDate("data");
                if (sqlDate != null) {
                    s.setData(sqlDate.toLocalDate());
                }

                Time sqlOraInizio = rs.getTime("ora_inizio");
                if (sqlOraInizio != null) {
                    s.setOra_inizio(sqlOraInizio.toLocalTime());
                }

                Time sqlOraFine = rs.getTime("ora_fine");
                if (sqlOraFine != null) {
                    s.setOra_fine(sqlOraFine.toLocalTime());
                }

                s.setLuogo(rs.getString("luogo"));
                lista.add(s);
            }
        } catch (SQLException e) {
            // 2. Sostituzione di printStackTrace con il Logger
            LOGGER.log(Level.SEVERE, "Errore durante il recupero delle sedute di laurea", e);
        }
        return lista;
    }

    /**
     * Salva la seduta; delega all'implementazione che restituisce boolean.
     * Se l'inserimento fallisce viene lanciata RuntimeException per segnalare l'errore al chiamante.
     */
    @Override
    public void save(SedutaDiLaurea seduta) {
        boolean ok = inserisciSeduta(seduta);
        if (!ok) {
            throw new RuntimeException("Errore durante l'inserimento della seduta di laurea");
        }
    }

    /**
     * Inserisce la seduta nel DB. Restituisce true se l'inserimento ha modificato almeno una riga.
     */
    @Override
    public boolean inserisciSeduta(SedutaDiLaurea seduta) {
        String query = "INSERT INTO seduta_di_laurea (data, ora_inizio, ora_fine, luogo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // data (java.time.LocalDate) -> java.sql.Date
            if (seduta.getData() != null) {
                pstmt.setDate(1, Date.valueOf(seduta.getData()));
            } else {
                pstmt.setNull(1, Types.DATE);
            }

            // ora_inizio (java.time.LocalTime) -> java.sql.Time
            if (seduta.getOra_inizio() != null) {
                pstmt.setTime(2, Time.valueOf(seduta.getOra_inizio()));
            } else {
                pstmt.setNull(2, Types.TIME);
            }

            // ora_fine (java.time.LocalTime) -> java.sql.Time
            if (seduta.getOra_fine() != null) {
                pstmt.setTime(3, Time.valueOf(seduta.getOra_fine()));
            } else {
                pstmt.setNull(3, Types.TIME);
            }

            // luogo
            if (seduta.getLuogo() != null) {
                pstmt.setString(4, seduta.getLuogo());
            } else {
                pstmt.setNull(4, Types.VARCHAR);
            }

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            // 2. Sostituzione di printStackTrace con il Logger
            LOGGER.log(Level.SEVERE, "Errore durante l'inserimento della seduta di laurea", e);
            return false;
        }
    }
}