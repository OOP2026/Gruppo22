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
                // Mappatura degli altri campi...
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean save(TESI tesi) {
        String query = "INSERT INTO tesi (titolo, stato) VALUES (?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, tesi.getTitolo());
            pstmt.setString(2, tesi.getStato().toString()); // Convertiamo l'enum[cite: 6]
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(TESI tesi) { /* Logica di aggiornamento */ }


    // Nell'implementazione:
    boolean inserisciTesi() {
        return inserisciTesi();
    }

    // Nell'implementazione:
    @Override
    public boolean inserisciTesi(TESI tesi) {
        String query = "INSERT INTO tesi (titolo, descrizione, codice_docente, tipologia) VALUES (?, ?, ?, ?)";
        // ... qui metti il tuo try-catch con PreparedStatement e executeUpdate() ...
        return true; // Ritorna true se l'inserimento ha successo
    }
}