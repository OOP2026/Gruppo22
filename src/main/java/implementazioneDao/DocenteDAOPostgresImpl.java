package implementazionedao;

import dao.DocenteDAO;
import database.DBConnection;
import model.Docente;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocenteDAOPostgresImpl implements DocenteDAO {

    // 1. Inizializzazione del Logger standard di Java (Statico e Finale per la classe)
    private static final Logger LOGGER = Logger.getLogger(DocenteDAOPostgresImpl.class.getName());

    @Override
    public Docente findByCodice(String idDocente) {
        // La query SQL parametrizzata previene attacchi di SQL Injection.
        String query = "SELECT * FROM docente WHERE id_docente = ?";

        // Costrutto try-with-resources per il rilascio automatico delle risorse JDBC
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, idDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Estraiamo i dati dal database
                    String id = rs.getString("id_docente");
                    String nome = rs.getString("nome");
                    String cognome = rs.getString("cognome");

                    // Usiamo il costruttore a 5 parametri
                    return new Docente(id, "defaultPass", nome, cognome, id);
                }
            }
        } catch (SQLException e) {
            // 2. SOSTITUZIONE: Utilizzo del Logger al posto di e.printStackTrace()
            LOGGER.log(Level.SEVERE, "Eccezione durante la ricerca del docente con ID: " + idDocente, e);
        }

        return null; // Ritorna null se non c'è corrispondenza nel database
    }

    @Override
    public Docente findById(int id) {
        // Poiché l'identificativo del docente è una Stringa (es. "D-4091") e non un intero,
        // questo metodo ereditato dall'interfaccia può rimanere non implementato.
        return null;
    }

    @Override
    public boolean inserisciDocente(Docente docente) {
        // Corretta la sintassi SQL
        String query = "INSERT INTO docente (id_docente, nome, cognome) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Estraiamo i dati dall'oggetto e facciamo il binding sulla query
            pstmt.setString(1, docente.getIdDocente());
            pstmt.setString(2, docente.getNome());
            pstmt.setString(3, docente.getCognome());

            // executeUpdate() restituisce il numero di tuple (righe) modificate
            int righeInserite = pstmt.executeUpdate();

            return righeInserite > 0; // Restituisce true se l'operazione ha avuto successo

        } catch (SQLException e) {
            // 2. SOSTITUZIONE: Utilizzo del Logger al posto di e.printStackTrace()
            LOGGER.log(Level.SEVERE, "Eccezione durante l'inserimento del docente", e);
            return false;
        }
    }
}