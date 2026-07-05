package implementazionepostgresdao;

import dao.DocenteDAO;
import database.DBConnection;
import model.Docente;
import java.sql.*;

public class DocenteDAOPostgresImpl implements DocenteDAO {

    // 1. Rimosse le variabili d'istanza (username, password, nome, cognome).
    // Un Data Access Object (DAO) deve essere "stateless" (senza stato).
    // Il suo unico compito è eseguire le operazioni CRUD, non memorizzare i dati dell'entità.

    @Override
    public Docente findByCodice(String idDocente) {
        // La query SQL parametrizzata previene attacchi di SQL Injection.
        // N.B: Allineato il nome della colonna a "id_docente" come verificato su PostgreSQL.
        String query = "SELECT * FROM docente WHERE id_docente = ?";

        // Costrutto try-with-resources per il rilascio automatico delle risorse JDBC
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, idDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Docente d = new Docente();

                    // Popolamento dell'oggetto utilizzando i setter corretti definiti nel Model
                    d.setIdDocente(rs.getString("id_docente"));
                    d.setNome(rs.getString("nome"));
                    d.setCognome(rs.getString("cognome"));

                    return d;
                }
            }
        } catch (SQLException e) {
            System.err.println("Eccezione durante la ricerca del docente: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // Ritorna null se non c'è corrispondenza nel database
    }

    @Override
    public Docente findById(int id) {
        // Poiché l'identificativo del docente è una Stringa (es. "D-4091") e non un intero,
        // questo metodo ereditato dall'interfaccia può rimanere non implementato (oppure
        // potresti rimuoverlo dall'interfaccia DocenteDAO se non necessario in altre classi).
        return null;
    }

    @Override
    public boolean inserisciDocente(Docente docente) {
        // 2. Corretta la sintassi SQL: rimossa la virgola in eccesso e allineati
        // i parametri (?) al numero effettivo delle colonne (3).
        String query = "INSERT INTO docente (id_docente, nome, cognome) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Estraiamo i dati dall'oggetto e facciamo il binding sulla query
            pstmt.setString(1, docente.getIdDocente()); // Corretto il getter in getIdDocente()
            pstmt.setString(2, docente.getNome());
            pstmt.setString(3, docente.getCognome());

            // executeUpdate() restituisce il numero di tuple (righe) modificate
            int righeInserite = pstmt.executeUpdate();

            return righeInserite > 0; // Restituisce true se l'operazione di persistenza ha avuto successo

        } catch (SQLException e) {
            System.err.println("Eccezione durante l'inserimento del docente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}