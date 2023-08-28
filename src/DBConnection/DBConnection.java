package DBConnection;

import java.sql.*;
import java.util.Properties;

public class DBConnection {
    public Connection getConnection() {
        Connection dbConnection = null;
        try {
            String url = "url";
            Properties info = new Properties();
            info.put("user", "user");
            info.put("password", "pass");
            dbConnection = DriverManager.getConnection(url, info);

        }catch (SQLException e){
            System.out.println("No nos conectamos F");
            e.printStackTrace();
            return null;
        }
        return dbConnection;
    }

    //* Crear conexion statement y query
    public ResultSet readFromDb(String query) throws SQLException {
        Connection con = getConnection();
        ResultSet data = null;
        Statement statement = con.createStatement();
        data = statement.executeQuery(query);
        return data;
    }

    public int insertRecord(String query, String codigo_producto, String nombre_producto, int cantidad_p, float precio_unitario) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, codigo_producto);
        preparedStatement.setString(2, nombre_producto);
        preparedStatement.setInt(3, cantidad_p);
        preparedStatement.setFloat(4, precio_unitario);
// !       preparedStatement.setString(5, date); // Investigar sobre como manejar fechas
        return preparedStatement.executeUpdate();
    }

    public ResultSet singleProduct(String query, String code) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, code);
        return preparedStatement.executeQuery();
    }

    public int updateRecord(String query,  String id_to_update, int amount_to_update) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, amount_to_update);
        preparedStatement.setString(2, id_to_update);
        return preparedStatement.executeUpdate();
    }

    public int deleteProduct(String query, String product_to_delete) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, product_to_delete);
        return preparedStatement.executeUpdate();
    }

    public void closeConnection() throws SQLException {
        Connection con = getConnection();
        con.close();
    }
}
