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

public class UtenteDAOPostgresImpl implements UtenteDAO {

    @Override
    public Utente verificaLogin(String username, String password) {

        Utente utenteLoggato = null;
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
                        utenteLoggato = new Studente(username, password, nome, cognome, matricola, corsoStudi);

                    } else if (ruolo.equalsIgnoreCase("Docente")) {
                        String codiceDocente = resultSet.getString("codice_docente");
                        // se hai classe Docente la usi, altrimenti usa Utente con un campo in più
                        utenteLoggato = new Utente(username, password, nome, cognome);

                    } else if (ruolo.equalsIgnoreCase("Coordinatore")) {
                        String idCoordinatore = resultSet.getString("id_coordinatore");
                        utenteLoggato = new Coordinatore(username, password, nome, cognome, idCoordinatore);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Errore di connessione o esecuzione query: " + e.getMessage());
        }

        return utenteLoggato;
    }
}