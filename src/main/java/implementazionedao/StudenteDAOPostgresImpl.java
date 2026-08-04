package implementazionedao;

import dao.StudenteDAO;
import database.DBConnection;
import model.Studente;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudenteDAOPostgresImpl implements StudenteDAO {

    // Inizializzazione del Logger di classe
    private static final Logger LOGGER = Logger.getLogger(StudenteDAOPostgresImpl.class.getName());

    public Studente verificaLogin(String matricola, String password) {

        // --- LA MODIFICA È QUI ---
        // Sostituito SELECT * con la proiezione esplicita delle sole colonne necessarie
        String query = "SELECT matricola, nome, cognome FROM studente WHERE matricola = ? AND password = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, matricola);
            pstmt.setString(2, password);

            // Chiusura sicura del ResultSet
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
            // Sostituzione di e.printStackTrace() con il Logger
            LOGGER.log(Level.SEVERE, "Errore durante la verifica del login", e);
        }
        return null;
    }

    @Override
    public boolean inserisciStudente(Studente studente) {
        // 1. Query SQL allineata allo schema fisico del database (solo 3 attributi)
        String query = "INSERT INTO studente (matricola, nome, cognome) VALUES (?, ?, ?)";

        // 2. Costrutto try-with-resources per la gestione delle risorse JDBC
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // 3. Binding esclusivo dei 3 parametri catturati dalla GUI
            pstmt.setString(1, studente.getMatricola());
            pstmt.setString(2, studente.getNome());
            pstmt.setString(3, studente.getCognome());

            // 4. Esecuzione dell'operazione DML
            int righeInserite = pstmt.executeUpdate();

            return righeInserite > 0;

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) { // 23505 è il codice SQL per unique_violation
                // Sostituzione di System.err.println con il Logger livello WARNING
                LOGGER.log(Level.WARNING, "Tentativo di inserire una matricola duplicata: {0}", studente.getMatricola());
            } else {
                // Sostituzione di e.printStackTrace() con il Logger livello SEVERE
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