package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce la connessione fisica al database PostgreSQL.
 * Implementa il pattern Singleton per centralizzare e ottimizzare l'accesso ai dati,
 * garantendo l'esistenza di un'unica istanza di connessione a livello applicativo.
 */
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    // TODO: Modificare questi parametri con le credenziali del proprio database locale
    private static final String URL = "jdbc:postgresql://localhost:5432/gestione_tesi";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    /**
     * Costruttore privato del Singleton.
     * Inizializza il driver JDBC e stabilisce la connessione con il database.
     */
    private DBConnection() {
        try {
            // Registrazione del Driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione al database stabilita con successo.");
        } catch (ClassNotFoundException e) {
            System.err.println("Errore: Driver JDBC PostgreSQL non trovato nell'infrastruttura.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Punto di accesso globale all'istanza Singleton.
     *
     * @return L'istanza univoca di DBConnection.
     */
    public static DBConnection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DBConnection();
            }
        } catch (SQLException e) {
            System.err.println("Errore durante la verifica dello stato della connessione.");
        }
        return instance;
    }

    /**
     * Fornisce l'oggetto Connection per eseguire i PreparedStatement.
     *
     * @return L'oggetto Connection attivo.
     */
    public Connection getConnection() {
        return connection;
    }
}