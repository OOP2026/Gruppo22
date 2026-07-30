package implementazionepostgresdao;

import dao.UtenteDAO;
import database.DBConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAOPostgresImpl implements UtenteDAO {

    @Override
    public Utente verificaLogin(String username, String password) {

        Utente utenteLoggato = null;
        String query = "SELECT * FROM Utenti WHERE username = ? AND password = ?";

        // La connessione viene recuperata dal Singleton
        Connection connection = DBConnection.getInstance().getConnection();

        // TRY-WITH-RESOURCES: garantisce la chiusura automatica dello Statement
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            // TRY-WITH-RESOURCES annidato: garantisce la chiusura del ResultSet
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String ruolo = resultSet.getString("ruolo");

                    if (ruolo.equalsIgnoreCase("Studente")) {
                        String matricola = resultSet.getString("matricola");
                        String corsoStudi = resultSet.getString("corso_studi");
                        utenteLoggato = new STUDENTE(username, password, nome, cognome, matricola, corsoStudi);

                    } else if (ruolo.equalsIgnoreCase("Docente")) {
                        String codiceDocente = resultSet.getString("codice_docente");
                        // Ora istanziamo la classe passando tutti i parametri
                        utenteLoggato = new Utente(username, password, nome, cognome, codiceDocente);

                    } else if (ruolo.equalsIgnoreCase("Coordinatore")) {
                        String idCoordinatore = resultSet.getString("id_coordinatore");
                        utenteLoggato = new COORDINATORE(username, password, nome, cognome, idCoordinatore);
                    }
                }
            } // Qui il ResultSet viene chiuso automaticamente

        } catch (SQLException e) {
            System.err.println("Errore di connessione o esecuzione query: " + e.getMessage());
        } // Qui il PreparedStatement viene chiuso automaticamente

        return utenteLoggato;
    }
}