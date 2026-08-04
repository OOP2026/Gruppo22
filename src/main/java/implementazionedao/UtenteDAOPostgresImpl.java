package implementazionedao;

import dao.UtenteDAO;
import database.DBConnection;
import model.Utente;
import model.Studente;
import model.Coordinatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtenteDAOPostgresImpl implements UtenteDAO {

    // Inizializzazione del Logger di classe
    private static final Logger LOGGER = Logger.getLogger(UtenteDAOPostgresImpl.class.getName());

    @Override
    public Utente verificaLogin(String username, String password) {

        Utente utenteLoggato = null;

        // --- LA MODIFICA È QUI ---
        // Sostituito SELECT * con le colonne esplicitamente necessarie per ottimizzare la query
        String query = "SELECT nome, cognome, ruolo, matricola, corso_studi, id_coordinatore FROM Utenti WHERE username = ? AND password = ?";

        Connection connection = DBConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String ruolo = resultSet.getString("ruolo");

                    if (ruolo.equalsIgnoreCase("Studente")) {
                        String matricola = resultSet.getString("matricola");
                        String corsoStudi = resultSet.getString("corso_studi");
                        utenteLoggato = new Studente(username, password, nome, cognome, matricola, corsoStudi);

                    } else if (ruolo.equalsIgnoreCase("Docente")) {
                        // Verrà istanziato un Utente base senza bisogno del codice_docente.
                        utenteLoggato = new Utente(username, password, nome, cognome);

                    } else if (ruolo.equalsIgnoreCase("Coordinatore")) {
                        String idCoordinatore = resultSet.getString("id_coordinatore");
                        utenteLoggato = new Coordinatore(username, password, nome, cognome, idCoordinatore);
                    }
                }
            }

        } catch (SQLException e) {
            // Utilizzo del Logger standard
            LOGGER.log(Level.SEVERE, "Errore di connessione o esecuzione query per il login", e);
        }

        return utenteLoggato;
    }
}