package implementazionedao;

import dao.TirocinioDAO;
import database.DBConnection;
import model.Tipo_Esterno;
import model.Tipo_Interno;
import model.Tirocinio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TirocinioDAOPostgresImpl implements TirocinioDAO {

    // Inizializzazione del Logger di classe
    private static final Logger LOGGER = Logger.getLogger(TirocinioDAOPostgresImpl.class.getName());

    @Override
    public List<Tirocinio> findAll() {
        List<Tirocinio> tirocini = new ArrayList<>();

        // La query UNION che fonde le due tabelle
        String query =
                "SELECT id_intero AS id, 'INTERNO' AS tipologia, laboratorio AS ente, 'Sede Universitaria' AS indirizzo " +
                        "FROM tipo_interno " +
                        "UNION " +
                        "SELECT id_intero AS id, 'ESTERNO' AS tipologia, azienda AS ente, indirizzo " +
                        "FROM tipo_esterno";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Tirocinio t = new Tirocinio();

                // Binding dei risultati sull'oggetto Tirocinio
                t.setIdTirocinio(rs.getInt("id"));
                t.setTipologia(rs.getString("tipologia"));
                t.setAziendaEnte(rs.getString("ente"));

                tirocini.add(t);
            }
        } catch (SQLException e) {
            // Sostituzione di e.printStackTrace() con il Logger ufficiale
            LOGGER.log(Level.SEVERE, "Errore durante l'estrazione della lista dei tirocini", e);
        }
        return tirocini;
    }

    @Override
    // Metodo aggiornato con la nuova classe Tipo_Interno
    public void saveInterno(Tipo_Interno t) {
        // Da implementare
    }

    @Override
    // Metodo aggiornato con la nuova classe Tipo_Esterno
    public void saveEsterno(Tipo_Esterno t) {
        // Da implementare
    }
}