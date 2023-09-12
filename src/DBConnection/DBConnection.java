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
            info.put("password", "password");
            dbConnection = DriverManager.getConnection(url, info);

        }catch (SQLException e){
            System.out.println("No nos conectamos F");
            e.printStackTrace();
            return null;
        }
        return dbConnection;
    }

    //* Crear conexion statement y query
    public ResultSet readFromDb(String query, int user_id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, user_id);
        return statement.executeQuery();
    }

    public int insertRecord(String query, String nombre_producto, int cantidad_p, float precio_unitario, int user_id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, nombre_producto);
        preparedStatement.setInt(2, cantidad_p);
        preparedStatement.setFloat(3, precio_unitario);
        preparedStatement.setInt(4, user_id);
// !       preparedStatement.setString(5, date); // Investigar sobre como manejar fechas
        return preparedStatement.executeUpdate();
    }

    public ResultSet singleProduct(String query, String code, int user_id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, code);
        preparedStatement.setInt(2, user_id);
        return preparedStatement.executeQuery();
    }

    public int updateRecord(String query, int amount_to_update, int user_id, String id_to_update ) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, amount_to_update);
        preparedStatement.setInt(2, user_id);
        preparedStatement.setString(3, id_to_update);
        return preparedStatement.executeUpdate();
    }

    public int deleteProduct(String query, String product_to_delete, int user_id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, product_to_delete);
        preparedStatement.setInt(2, user_id);
        return preparedStatement.executeUpdate();
    }

    //? Get single account
    public ResultSet findSingleRecord(String query, String eamil) throws SQLException {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, eamil);
        return statement.executeQuery();
    }

    //? insert new user
    public int insertNewUserDb(String query, String userName, String lastName, String email, String password) throws SQLException {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, userName);
        statement.setString(2, lastName);
        statement.setString(3, email);
        statement.setString(4, password);
        return statement.executeUpdate();
    }

    public ResultSet getUserFromDb(String query, int user_id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1,user_id);
        return statement.executeQuery();
    }

    public void closeConnection() throws SQLException {
        Connection con = getConnection();
        con.close();
    }
}
