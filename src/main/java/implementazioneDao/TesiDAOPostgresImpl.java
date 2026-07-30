package implementazionepostgresdao;

import dao.TesiDAO;
import database.DBConnection;
import model.TESI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TesiDAOPostgresImpl implements TesiDAO {

    @Override
    public List<TESI> findAll() {
        List<TESI> lista = new ArrayList<>();
        String query = "SELECT * FROM tesi";

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                TESI t = new TESI();
                t.setTitolo(rs.getString("titolo"));
                // In futuro: mappatura degli altri campi qui...
                lista.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero delle tesi: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean save(TESI tesi) {
        String query = "INSERT INTO tesi (titolo, stato) VALUES (?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, tesi.getTitolo());
            pstmt.setString(2, tesi.getStato().toString()); // Conversione dell'enum

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Ritorna true solo se l'inserimento ha avuto successo

        } catch (SQLException e) {
            System.err.println("Errore durante il salvataggio della tesi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void update(TESI tesi) {
        /* Logica di aggiornamento da implementare in futuro */
    }

    // N.B.: Il metodo ricorsivo errato 'boolean inserisciTesi()' è stato rimosso per design.

    @Override
    public boolean inserisciTesi(TESI tesi) {
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
            System.err.println("Errore durante l'inserimento della tesi: " + e.getMessage());
            return false;
        }
    }
}