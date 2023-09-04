package Utils;

import DBConnection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

    DBConnection db = new DBConnection();

    public ResultSet getAllDataFromDb(int user_id){
        String query = "SELECT codigoproducto, nombreproducto, cantidadproducto, preciounitario, CONVERT_TZ(fecha,'+00:00','-06:00') fecha FROM `producto`";
        String queryFromTheSameUser = "SELECT producto.codigoproducto, producto.nombreproducto, producto.cantidadproducto, producto.preciounitario, CONVERT_TZ(fecha,'+00:00','-06:00') fecha, users.user_id, users.user_name, users.last_name, users.email, users.isSuperAdmin, CONVERT_TZ(users.created_at,'+00:00','-06:00') created_at FROM users INNER JOIN producto ON producto.codigo_user_id = users.user_id WHERE users.user_id = ?";
        ResultSet inventario = null;

        try {
            inventario = db.readFromDb(queryFromTheSameUser, user_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inventario;
    }

    public String getSingleProductDb(String product_code, int user_id ) throws SQLException {
        String query = "SELECT * FROM producto WHERE `codigoproducto` = ?";
        ResultSet product = null;
        String codigo_producto = "";
//        product = db.singleProduct(query, product_code);
        try {
            product = db.singleProduct(query, product_code, user_id);
        }catch (SQLException e){
            System.out.println("unable to get the product");
            e.printStackTrace();
        }
        if(product != null){
            while (product.next()){
                codigo_producto = product.getString(1);
            }
        }
        return codigo_producto;
    }

    public ResultSet getSingleProductFromDb(String product_code, int user_id ) throws SQLException {
        String query = "SELECT * FROM producto WHERE `codigoproducto` = ?";
        String queryWithUserId = "SELECT * FROM `producto` WHERE producto.codigoproducto = ? AND producto.codigo_user_id = ?";
        ResultSet product = null;
//        product = db.singleProduct(queryWithUserId, product_code, user_id);
        try {
            product = db.singleProduct(queryWithUserId, product_code, user_id);
        }catch (SQLException e){
            System.out.println("unable to get the product");
            e.printStackTrace();
        }
        return product;
    }

    public int insertNewProductDb(String product_name, int product_qty, float product_price, int user_id) throws SQLException {
        System.out.println(user_id+"    user id desdq queries");
        String queryAddProduct = "INSERT INTO `producto` (`codigoproducto`, `nombreproducto`, `cantidadproducto`, `preciounitario`) VALUES (?, ?, ?, ?)";
        String queryUpdateWithUser = "INSERT INTO `producto` (`nombreproducto`, `cantidadproducto`, `preciounitario`, `codigo_user_id`) VALUES (?, ?, ?, ?)";
        return db.insertRecord(queryUpdateWithUser, product_name, product_qty, product_price, user_id);
    }

    public int updateRecordOnDb(int amount_to_update, int user_id , String product_code) throws SQLException {
        System.out.println("extrar e ingresa?"+ amount_to_update + user_id+ product_code );
        String queryUpdate = "UPDATE `producto` SET `cantidadproducto` = ? WHERE `producto`.`codigoproducto` = ?";
        String queryUpdateWithUser = "UPDATE `producto` SET `cantidadproducto` = ?, `codigo_user_id` = ? WHERE `producto`.`codigoproducto` = ?";
        return db.updateRecord(queryUpdateWithUser, amount_to_update, user_id, product_code);
    }

    public int deleteRecordFromDb(String product_code, int user_id) throws SQLException {
        String deleteQuery = "DELETE FROM `producto` WHERE `producto`.`codigoproducto` = ?";
        String deleteRecordWithUser = "DELETE FROM `producto` WHERE `producto`.`codigoproducto` = ? AND producto.codigo_user_id = ?";
        return db.deleteProduct(deleteRecordWithUser, product_code, user_id);
    }

    //? Login y registrar
    public ResultSet findSingleAccount(String email) throws SQLException {
        ResultSet userFound = null;
        String query = "SELECT * FROM `users` WHERE `email` = ?";
        try {
            userFound = db.findSingleRecord(query, email);
        }catch (SQLException e){
            System.out.println("unable to get the product");
            e.printStackTrace();
        }
        return userFound;
    }

    public int insertNewUser(String userName, String lastName, String email, String password) throws SQLException {
        String queryAddUser = "INSERT INTO `users` (`user_name`, `last_name`, `email`, `password`) VALUES (?, ?, ?, ?)";
        return db.insertNewUserDb(queryAddUser, userName, lastName, email, password);
    }

    public ResultSet userInformation(int user_id){
        ResultSet user = null;
        String query = "SELECT user_id, user_name, last_name, email, isSuperAdmin, CONVERT_TZ(created_at,'+00:00','-06:00') created_at FROM `users` WHERE user_id = ?";
        try {
            user = db.getUserFromDb(query, user_id);
        }catch (SQLException e){
            System.out.println(e+"No se pudo obtener la informacion del user");
        }
        return user;
    }
}

