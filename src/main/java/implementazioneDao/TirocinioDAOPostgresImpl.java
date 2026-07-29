package implementazionepostgresdao;

import dao.TirocinioDAO;
import database.DBConnection;
import model.TIPO_ESTERNO;
import model.TIPO_INTERNO;
import model.TIROCINIO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TirocinioDAOPostgresImpl implements TirocinioDAO {

    @Override
    public List<TIROCINIO> findAll() {
        List<TIROCINIO> tirocini = new ArrayList<>();

        // La query UNION che fonde le due tabelle!
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
                TIROCINIO t = new TIROCINIO();
                // Usa i setter che hai definito nella classe TIROCINIO
                t.setIdTirocinio(rs.getInt("id"));
                t.setTipologia(rs.getString("tipologia"));
                t.setAziendaEnte(rs.getString("ente"));
                // Se nella classe TIROCINIO non hai l'indirizzo, puoi omettere questa riga
                // t.setIndirizzo(rs.getString("indirizzo"));

                tirocini.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tirocini;
    }

    @Override
    public void saveInterno(TIPO_INTERNO t) {

    }

    @Override
    public void saveEsterno(TIPO_ESTERNO t) {

    }
}
