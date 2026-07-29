package implementazionepostgresdao;

import dao.SedutaDAO;
import database.DBConnection;
import model.SEDUTA_DI_LAURA;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SedutaDAOPostgresImpl implements SedutaDAO {

    @Override
    public List<SEDUTA_DI_LAURA> findAll() {
        List<SEDUTA_DI_LAURA> lista = new ArrayList<>();
        String query = "SELECT * FROM seduta_di_laurea";

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                SEDUTA_DI_LAURA s = new SEDUTA_DI_LAURA();
                s.setData(rs.getDate("data").toLocalDate());
                s.setOra_inizio(rs.getTime("ora_inizio").toLocalTime());
                s.setOra_fine(rs.getTime("ora_fine").toLocalTime());
                s.setLuogo(rs.getString("luogo"));
                lista.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void save(SEDUTA_DI_LAURA seduta) {
        String query = "INSERT INTO seduta_di_laurea (data, ora_inizio, ora_fine, luogo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(seduta.getData()));
            pstmt.setTime(2, Time.valueOf(seduta.getOra_inizio()));
            pstmt.setTime(3, Time.valueOf(seduta.getOra_fine()));
            pstmt.setString(4, seduta.getLuogo());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciSeduta(SEDUTA_DI_LAURA seduta) {
        return true;
    }
}