package implementazionepostgresdao;

import dao.UtenteDAO;
import database.DBConnection;
import model.UTENTE;
import model.STUDENTE;
import model.COORDINATORE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UTENTEDAOPostgresImpl implements UtenteDAO {

    @Override
    public UTENTE verificaLogin(String username, String password) {

        UTENTE utenteLoggato = null;
        String query = "SELECT * FROM Utenti WHERE username = ? AND password = ?";

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
                        utenteLoggato = new STUDENTE(username, password, nome, cognome, matricola, corsoStudi);

                    } else if (ruolo.equalsIgnoreCase("Docente")) {
                        String codiceDocente = resultSet.getString("codice_docente");
                        utenteLoggato = new UTENTE(username, password, nome, cognome, codiceDocente);

                    } else if (ruolo.equalsIgnoreCase("Coordinatore")) {
                        String idCoordinatore = resultSet.getString("id_coordinatore");
                        utenteLoggato = new COORDINATORE(username, password, nome, cognome, idCoordinatore);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Errore di connessione o esecuzione query: " + e.getMessage());
        }

        return utenteLoggato;
    }
}