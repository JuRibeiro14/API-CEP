import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EnderecoBd {

    public void AdicionarEnd(Endereco endereco) throws SQLException {
        String query = "INSERT INTO cep(cep, rua, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DB.connect()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, endereco.getCep());
                stmt.setString(2, endereco.getRua());
                stmt.setString(3, endereco.getBairro());
                stmt.setString(4, endereco.getCidade());
                stmt.setString(5, endereco.getEstado());
                stmt.executeUpdate();
            }
        }
    }

    public static List<Endereco> ListarEnd() throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String query = "SELECT * FROM cep";
        try (Connection connection = DB.connect()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Endereco endereco = new Endereco(rs.getString("cep"),
                            rs.getString("rua"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("estado"));
                    enderecos.add(endereco);
                }
            }
        }
        return enderecos;
    }
}
