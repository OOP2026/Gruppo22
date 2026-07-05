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
    public UTENTE verificaLogin(String username, String password) {
        UTENTE utenteLoggato = null;

        String query = "SELECT * FROM Utenti WHERE username = ? AND password = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

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

                    // --- LA MODIFICA È QUI ---
                    // Ora istanziamo la classe concreta DOCENTE passandole tutti i parametri,
                    // esattamente come hai fatto per lo Studente e il Coordinatore.
                    utenteLoggato = new UTENTE(username, password, nome, cognome, codiceDocente);

                } else if (ruolo.equalsIgnoreCase("Coordinatore")) {
                    String idCoordinatore = resultSet.getString("id_coordinatore");
                    utenteLoggato = new COORDINATORE(username, password, nome, cognome, idCoordinatore);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        return utenteLoggato;
    }
}