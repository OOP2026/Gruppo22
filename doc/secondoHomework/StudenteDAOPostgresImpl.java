package implementazionepostgresdao;

import dao.StudenteDAO;
import database.DBConnection;
import model.STUDENTE;
import java.sql.*;

public class StudenteDAOPostgresImpl implements StudenteDAO {
    public STUDENTE verificaLogin(String matricola, String password) {

        String query = "SELECT * FROM studente WHERE matricola = ? AND password = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, matricola);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                STUDENTE s = new STUDENTE();
                s.setMatricola(rs.getString("matricola"));
                s.setNome(rs.getString("nome"));
                s.setCognome(rs.getString("cognome"));
                return s;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean inserisciStudente(STUDENTE studente) {
        // 1. Query SQL allineata allo schema fisico del database (solo 3 attributi)
        String query = "INSERT INTO studente (matricola, nome, cognome) VALUES (?, ?, ?)";

        // 2. Costrutto try-with-resources per la gestione delle risorse JDBC
        try (java.sql.Connection conn = database.DBConnection.getInstance().getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {

            // 3. Binding esclusivo dei 3 parametri catturati dalla GUI
            pstmt.setString(1, studente.getMatricola());
            pstmt.setString(2, studente.getNome());
            pstmt.setString(3, studente.getCognome());

            // 4. Esecuzione dell'operazione DML
            int righeInserite = pstmt.executeUpdate();

            return righeInserite > 0;

        } catch (java.sql.SQLException e) {
            if (e.getSQLState().equals("23505")) { // 23505 è il codice SQL per unique_violation
                System.err.println("Tentativo di inserire una matricola duplicata.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public STUDENTE findByMatricola(String matricola) {
        return null;
    }
}