package implementazionedao;

import dao.TesiDAO;
import database.DBConnection;
import model.Tesi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesiDAOPostgresImpl implements TesiDAO {

    // Inizializzazione del Logger di classe per gestire le eccezioni
    private static final Logger LOGGER = Logger.getLogger(TesiDAOPostgresImpl.class.getName());

    @Override
    public List<Tesi> findAll() {
        List<Tesi> lista = new ArrayList<>();

        // --- LA MODIFICA È QUI ---
        // Sostituito SELECT * con la singola colonna necessaria per ottimizzare l'estrazione
        String query = "SELECT titolo FROM tesi";

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Tesi t = new Tesi();
                t.setTitolo(rs.getString("titolo"));
                // In futuro: mappatura degli altri campi qui (ricordati di aggiungerli anche nella query!)
                lista.add(t);
            }
        } catch (SQLException e) {
            // Sostituito System.err con il log strutturato
            LOGGER.log(Level.SEVERE, "Errore durante il recupero delle tesi", e);
        }
        return lista;
    }

    @Override
    public boolean save(Tesi tesi) {
        String query = "INSERT INTO tesi (titolo, stato) VALUES (?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, tesi.getTitolo());
            pstmt.setString(2, tesi.getStato().toString()); // Conversione dell'enum

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Ritorna true solo se l'inserimento ha avuto successo

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il salvataggio della tesi", e);
            return false;
        }
    }

    @Override
    public void update(Tesi tesi) {
        /* Logica di aggiornamento da implementare in futuro */
    }

    // N.B.: Il metodo ricorsivo errato 'boolean inserisciTesi()' è stato rimosso per design.

    @Override
    public boolean inserisciTesi(Tesi tesi) {
        String query = "INSERT INTO tesi (titolo, descrizione, codice_docente, tipologia) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, tesi.getTitolo());
            pstmt.setString(2, tesi.getDescrizione());
            pstmt.setString(3, tesi.getCodiceDocente());
            pstmt.setString(4, tesi.getTipologia());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Ritorna true se l'inserimento è andato a buon fine

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Errore durante l'inserimento della tesi", e);
            return false;
        }
    }
}