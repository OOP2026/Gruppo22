package implementazionedao;

import dao.StudenteDAO;
import database.DBConnection;
import model.Studente;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudenteDAOPostgresImpl implements StudenteDAO {

    private static final Logger LOGGER = Logger.getLogger(StudenteDAOPostgresImpl.class.getName());

    public Studente verificaLogin(String matricola, String password) {

        String query = "SELECT matricola, nome, cognome FROM studente WHERE matricola = ? AND password = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, matricola);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Studente s = new Studente();
                    s.setMatricola(rs.getString("matricola"));
                    s.setNome(rs.getString("nome"));
                    s.setCognome(rs.getString("cognome"));
                    return s;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Errore durante la verifica del login", e);
        }
        return null;
    }

    @Override
    public boolean inserisciStudente(Studente studente) {
        String query = "INSERT INTO studente (matricola, nome, cognome) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, studente.getMatricola());
            pstmt.setString(2, studente.getNome());
            pstmt.setString(3, studente.getCognome());

            int righeInserite = pstmt.executeUpdate();

            return righeInserite > 0;

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                LOGGER.log(Level.WARNING, "Tentativo di inserire una matricola duplicata: {0}", studente.getMatricola());
            } else {
                LOGGER.log(Level.SEVERE, "Errore durante l'inserimento dello studente", e);
            }
            return false;
        }
    }

    @Override
    public Studente findByMatricola(String matricola) {
        return null;
    }
}